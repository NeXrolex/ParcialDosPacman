/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.modelo.conexion;

import com.uDistrital.avanzada.parcialDos.modelo.interfaces.IConexion;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Maneja el serverSocket del servidor
 *
 * @author Alex
 */
public class ConexionServerSocket implements IConexion<ServerSocket> {

    private static ServerSocket serverSocket;
    private static int puerto;

    /**
     * Metodo que se encarga de configurar el puerto por el cual el servidor va
     * a atender a los usuario
     *
     * @param entrada Puerto de entrada
     */
    public static void configurar(int entrada) {
        puerto = entrada;
    }

    /**
     * cumple el contrato de servicios de ICOnexion y establece el serverSocket
     *
     * @return ServerSocket
     */
    @Override
    public ServerSocket conexion() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            return serverSocket;
        }
        if (puerto <= 0) {
            throw new IllegalStateException("PUERTO_SERVIDOR no configurado.");
        }
        try {
            //Crea el serverSocket con el puerto previamente configurado
            serverSocket = new ServerSocket(puerto);
            return serverSocket;
        } catch (IOException e) {
            throw new RuntimeException("No fue posible iniciar "
                    + "el ServerSocket.", e);
        }
    }

    /**
     * Encargado de cerrar el serverSocket
     *
     */
    public static void cerrar() {

        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {
            }
        }

    }
    
    /**
     * Comprueba si el server socket se encuentra activo
     * o en su defecto cerrado
     * @return True or false
     */
    public static boolean activo() {
        return serverSocket != null && !serverSocket.isClosed();
    }
    
    /**
     * Obtiene el puerto por el que se conectan los 
     * usuarios
     * 
     * @return 
     */
    public static int getPuerto() {
        return puerto;
    }

}
