/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import usuario.com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionSocket;

/**
 * Maneja la logica de los sockets
 *
 * @author Alex
 */
public class ControlSocket {

    private ControlGeneralUsuario cGeneral;
    private Socket socket;
    private DataInputStream dInput;
    private DataOutputStream dOutPut;

    /**
     * Constructor que recibe la inyeccion del control general
     *
     * @param cGeneral Control General
     */
    public ControlSocket(ControlGeneralUsuario cGeneral) {
        this.cGeneral = cGeneral;
    }

    public boolean conectar() {
        try {
            // Solicita la conexion al servidor 
           socket = new ConexionSocket().conexion();
            //Metodo interno que inicializa losOutput e input
            inicializarStreams(socket);
            //Protocolo para saber si esta conectado al server
            enviarUTF("PING");
            //Espera una respuesta
            String resp = leerUTF();
            return resp != null && (resp.equalsIgnoreCase("PONG")
                    || resp.toUpperCase().startsWith("OK"));
        } catch (RuntimeException ex) {
            cerrar(); //Si ocurre algun error termina la conexion
            return false;
        }
    }

    /**
     * Envía LOGIN;nombre;contrasena. Si falla, cierra el socket .
     *
     * @return true si el servidor responde OK
     */
    public boolean iniciarSesion(String nombre, String contrasena) {
        if (nombre == null || nombre.isBlank() || contrasena == null) {
            cerrar(); //Validaciones basicas para no enviar vacios al socket
            return false;
        }
        try {
            String cmd = armar("LOGIN", nombre.trim(), contrasena);
            enviarUTF(cmd);
            String resp = leerUTF();
            boolean ok = resp != null && resp.toUpperCase().startsWith("OK");
            //Validacion por si no existe en la base de datos
            if (!ok) {
                cerrar();//cierra el socket
            }
            return ok;
        } catch (RuntimeException ex) {
            cerrar(); //Si paso algun error, cierra el socket
            return false;
        }
    }

    /**
     * Metodo para enviar comandos genericos
     *
     */
    public String enviarComando(String... partesCmd) {
        String cmd = armar(partesCmd);
        enviarUTF(cmd);
        return leerUTF();
    }

    /**
     * Envia un mensaje de movimeientos al servidor para que lo traduzca y lo
     * representa en en ltablero del pacman
     *
     * @param comando Movimientos a enviar
     */
    public void enviarMovimiento(String comando) {
        if (comando == null || comando.trim().isEmpty()) {
            return;
        }
        enviarUTF(comando.trim());
    }

    /**
     * Busca si esta conectado en el socket
     *
     * @return True si esta conectado o false
     *
     */
    public boolean estaConectado() {
        return ConexionSocket.estaConectado();//Metodo de ConexionSocket
    }

    // Cierra socket y limpia streams
    public void cerrar() {
        try {
            dInput = null;
            dOutPut = null;
            ConexionSocket.cerrarConexion();//Llama a cerrar conexion de 
            //ConexionSocket
        } catch (RuntimeException ignore) {
            // cierre silencioso
        }
    }

    /**
     * Inicializa los input y output, si no existen los crea
     *
     * @param socket Socket
     */
    public void inicializarStreams(Socket socket) {
        try {
            if (socket == null) {
                throw new IllegalStateException("Socket nulo al"
                        + " inicializar streams.");
            }
            if (dInput == null) {
                dInput = new DataInputStream(socket.getInputStream());
            }
            if (dOutPut == null) {
                dOutPut = new DataOutputStream(socket.getOutputStream());
            }
        } catch (IOException es) {
            throw new RuntimeException("No fue posible inicializar los"
                    + " flujos del socket.", es);
        }
    }

    public Socket getSocket() {
        return this.socket; // asegúrate de guardar la instancia en la clase
    }

    /**
     * Escribe cadenas en formato writeUTF y contiene el fluch para enviar
     *
     * @param msg Mensaje
     */
    public void enviarUTF(String msg) {
        try {
            if (dOutPut == null) {
                throw new IllegalStateException("OutputStream no"
                        + " inicializado.");
            }
            dOutPut.writeUTF(msg != null ? msg : "");
            dOutPut.flush();//Todo lo escrito se envia 
        } catch (IOException e) {
            throw new RuntimeException("Error enviando datos por socket.", e);
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
     * Ensambla los comandos que van a ser enviados al servidor
     *
     * se emplea en iniciar sesion
     *
     * @param partes
     * @return
     */
    public String armar(String... partes) {
        if (partes == null || partes.length == 0) {//verifica que hayan datos
            return "";
        }
        StringBuilder sb = new StringBuilder();
        /*El string bueilder es mas 
        eficiente para concatenar string*/
        for (int i = 0; i < partes.length; i++) {
            sb.append(partes[i] == null ? "" : partes[i].trim());
            if (i < partes.length - 1) {
                sb.append(';');//separa los elementos con ;
            }
        }
        return sb.toString();
    }

}
