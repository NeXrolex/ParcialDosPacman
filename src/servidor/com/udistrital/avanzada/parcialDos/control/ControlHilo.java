/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 * Maneja la comunicación con UN cliente específico Se ejecuta en un hilo
 * separado No es una clase interna
 *
 * @author Steven
 */
public class ControlHilo implements Runnable {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private ControlGeneralServidor cGeneralServidor;
    private String usuarioAutenticado = null;

    /**
     * Constructor
     */
    public ControlHilo(Socket socket, ControlGeneralServidor cGeneralServidor) {
        this.socket = socket;
        this.cGeneralServidor = cGeneralServidor;
    }

    /**
     * Método principal del hilo
     */
    @Override
    public void run() {
        try {
            // Inicializar streams
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            // PASO 1: Protocolo PING/PONG
            String ping = input.readUTF();
            if ("PING".equalsIgnoreCase(ping)) {
                output.writeUTF("PONG");
                output.flush();
            } else {
                return;
            }

            // PASO 2: Bucle de procesamiento de mensajes
            boolean clienteConectado = true;
            while (clienteConectado) {
                try {
                    String mensaje = input.readUTF();
                    if (mensaje == null || mensaje.isEmpty()) {
                        break;
                    }

                    if (mensaje.startsWith("LOGIN")) {
                        procesarLogin(mensaje);
                    } else if (mensaje.startsWith("SALIR")
                            || mensaje.startsWith("DISCONNECT")) {
                        clienteConectado = false;
                    } else {
                        if (usuarioAutenticado != null) {
                            procesarComando(mensaje);
                        } else {
                            output.writeUTF("ERROR;No autenticado");
                            output.flush();
                        }
                    }
                } catch (EOFException e) {
                    clienteConectado = false;
                }
            }

        } catch (IOException e) {
        } finally {
            cerrarConexion();
        }
    }

    /**
     * Procesa el comando LOGIN Formato: LOGIN;usuario;contraseña
     */
    public void procesarLogin(String mensaje) {
        try {
            String[] partes = mensaje.split(";");
            if (partes.length != 3) {
                output.writeUTF("ERROR;Formato inválido");
                output.flush();
                return;
            }

            String usuario = partes[1].trim();
            String contrasena = partes[2];

            boolean autenticado = cGeneralServidor
                    .autenticarUsuario(usuario, contrasena);
            if (autenticado) {
                usuarioAutenticado = usuario;
                cGeneralServidor.setUsuarioConectado(usuario);
                output.writeUTF("OK;Login exitoso");
            } else {
                output.writeUTF("ERROR;Credenciales inválidas");
            }
            output.flush();
        } catch (IOException e) {
            cerrarConexion();
        }
    }

    /**
     * Procesa otros comandos
     */
    private void procesarComando(String comando) {
        try {
            String mov = comando == null ? "" : comando.trim().toUpperCase();

            if ("ARRIBA".equals(mov) || "ABAJO".equals(mov)
                    || "IZQUIERDA".equals(mov) || "DERECHA".equals(mov)) {

                cGeneralServidor.aplicarMovimiento(mov);

                output.flush();
                return;
            }

            output.flush();
        } catch (IOException e) {
            // logging simple y continuo

        }
    }

    /**
     * Cierra la conexión
     */
    private void cerrarConexion() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();

            }
        } catch (IOException e) {

        }
    }
}
