/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.conexion;

import com.uDistrital.avanzada.parcialDos.modelo.interfaces.IConexion;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Realiza la conexion al serverSocket tiene valores estaticos para poder
 * establecerlos desde el propertiesDAO
 *
 * Implementa IConexion porque cumple un fin comun igua a las otras conexiones
 *
 * @author Alex
 */
public class ConexionSocket implements IConexion<Socket> {

    private static Socket socket = null; //Nuestra conexion vive de forma global
    //Atributos configurables
    private static String ipServidor;
    private static int puertoServidor;

    /**
     * Asigna los valores necesarios para establecer la conexion con el servidor
     *
     * @param ip Ip del servidor
     * @param puerto Puerto del servidor
     */
    public static void configurarSocket(String ip, int puerto) {

        ConexionSocket.ipServidor = ip;
        ConexionSocket.puertoServidor = puerto;

    }

    /**
     * Cumple el contrato de servicios y se conecta al servidor
     *
     * @return
     */
    @Override
    public Socket conexion() {
        //Si existe uno abierto nos devolvera el socket actual
        if (socket != null && socket.isConnected() && !socket.isClosed()) {
            return socket;
        }
        //Si los valores no estan establecidos no iniciara la conexion
        if (ipServidor == null || ipServidor.isEmpty() || puertoServidor <= 0) {

        }

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ipServidor, puertoServidor),
                    5000);//Valor maximo en tiempo para la conexion
            socket.setTcpNoDelay(true);
            return socket;
        } catch (IOException ex) {

        }
        return null;
    }
    
    /**
     * Cierra la conexion con el socket
     * si esta activa
     * 
     */
    public static void cerrarConexion() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException("Error al cerrar el socket.", e);
            }
        }
    }
    
    /**
     * Compruba si el socket esta conectado
     * 
     * @return True en el caso donde donde este conetado o false
     * en el caso que no
     */
    public static boolean estaConectado() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }
    
    /**
     * Obtiene la ip del servidor
     * 
     * @return Ip del servidoe
     */
    public static String getIpServidor() {
        return ipServidor;
    }
    
    /**
     * Obtiene el puerto del servidor
     * 
     * @return Puerto del servidor
     */
    public static int getPuertoServidor() {
        return puertoServidor;
    }
}
