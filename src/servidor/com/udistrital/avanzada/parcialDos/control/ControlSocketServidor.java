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
public class ControlSocketServidor {

    private ControlGeneralServidor cGeneralServidor;
    private DataInputStream dInput;
    private DataOutputStream dOutput;
    private ServerSocket servidor;
    private Socket sc;
    private String ipServidor = "localhost";
    private int puertoServidor = 5555;

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

            while (true) {

                sc = servidor.accept();

                dInput = new DataInputStream(sc.getInputStream());
                dOutput = new DataOutputStream(sc.getOutputStream());

                String comando = dInput.readUTF();
                if (comando.equalsIgnoreCase("PING")) {
                    dOutput.writeUTF("PONG");
                    dOutput.flush();
                } else {
                    // Aqui se podrian procesar los movimientos
                    dOutput.writeUTF("OK;Comando recibido");
                    dOutput.flush();
                }
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
}
