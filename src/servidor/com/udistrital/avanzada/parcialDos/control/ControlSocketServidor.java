/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Steven
 */
public class ControlSocketServidor implements Runnable{

    private ControlGeneralServidor cGeneralServidor;
    private DataInputStream dInput;
    private DataOutputStream dOutput;
    private ServerSocket servidor;
    private Socket sc;
    private String ipServidor = "localhost";
    private int puertoServidor = 5555;
    private boolean servidorActivo = true;

    /**
     * Constructor que recibe la inyeccion del control general
     *
     * @param cGeneral Control General
     */
    public ControlSocketServidor(ControlGeneralServidor cGeneralServidor) {
        this.cGeneralServidor = cGeneralServidor;
    }

    public void run() {
        try {
            servidor = new ServerSocket(puertoServidor);
            
            while (servidorActivo) {
                sc = servidor.accept();
                ControlHilo manejador = new ControlHilo(sc, cGeneralServidor);
                Thread hiloCliente = new Thread(manejador);
                hiloCliente.start();
            }
        } catch (IOException ex) {

        } finally {
            cerrarConexion();
        }
    }

    /**
     * Cierra las conexiones activas (streams, socket del cliente y servidor).
     *
     * Este método libera los recursos asociados a la comunicación del servidor,
     * evitando fugas de memoria o bloqueos de puertos.
     */
    private void cerrarConexion() {
        try {
            if (dInput != null) {
                dInput.close();
            }
            if (dOutput != null) {
                dOutput.close();
            }
            if (servidor != null && !servidor.isClosed()) {
                servidor.close();
            }
        } catch (IOException e) {
        }
    }

    /**
     * Inicializa los input y output, si no existen los crea
     *
     * @param socket Socket
     */
    public void inicializarStreams() {
        try {
            if (sc == null) {
                throw new IllegalStateException("Socket nulo al"
                        + " inicializar streams.");
            }
            if (dInput == null) {
                dInput = new DataInputStream(sc.getInputStream());
            }
            if (dOutput == null) {
                dOutput = new DataOutputStream(sc.getOutputStream());
            }
        } catch (IOException es) {
            throw new RuntimeException("No fue posible inicializar los"
                    + " flujos del socket.", es);
        }
    }

    /**
     * Lee una cadena utf desde el server y bloquea hasta que llega un mensaje
     * valido
     *
     * @return
     */
    public String leerUTF() {
        try {
            if (dInput == null) {
                throw new IllegalStateException("InputStream no inicializado.");
            }
            return dInput.readUTF();//lee el mensaje
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo datos desde"
                    + " el socket.", e);
        }
    }

    /**
     * Escribe cadenas en formato writeUTF y contiene el fluch para enviar
     *
     * @param msg Mensaje
     */
    public void enviarUTF(String msg) {
        try {
            if (dOutput == null) {
                throw new IllegalStateException("OutputStream no"
                        + " inicializado.");
            }
            dOutput.writeUTF(msg != null ? msg : "");
            dOutput.flush();//Todo lo escrito se envia 
        } catch (IOException e) {
            throw new RuntimeException("Error enviando datos por socket.", e);
        }
    }

//Para llamar luego desde el properties
    /**
     * Configura la dirección IP y el puerto del servidor.
     *
     * Este método será invocado desde la lectura del archivo properties para
     * establecer los valores de la ip y el puerto
     *
     * @param ip Dirección IP en la que el servidor debe escuchar.
     * @param puerto Puerto en el que el servidor atenderá las conexiones.
     */
    public void configurarServidor(String ip, int puerto) {
        this.ipServidor = ip;
        this.puertoServidor = puerto;
    }

    public void detenerServidor() {
        servidorActivo = false;
        try {
            if (servidor != null && !servidor.isClosed()) {
                servidor.close();
            }
        } catch (IOException e) {
            System.err.println("Error al detener servidor: " + e.getMessage());
        }
    }
}
