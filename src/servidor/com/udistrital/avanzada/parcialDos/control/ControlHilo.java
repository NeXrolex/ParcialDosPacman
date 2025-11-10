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
                    } else if (mensaje.startsWith("SALIR") || mensaje.startsWith("DISCONNECT")) {
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
    private void procesarLogin(String mensaje) {
        try {
            String[] partes = mensaje.split(";");
            if (partes.length != 3) {
                output.writeUTF("ERROR;Formato inválido");
                output.flush();
                return;
            }

            String usuario = partes[1].trim();
            String contrasena = partes[2];

            System.out.println("  → Autenticando: " + usuario);

            // Verificar credenciales
            boolean autenticado = cGeneralServidor.autenticarUsuario(usuario, contrasena);

            if (autenticado) {
                usuarioAutenticado = usuario;
                output.writeUTF("OK;Autenticación exitosa");
                output.flush();
            } else {
                output.writeUTF("ERROR;Usuario o contraseña incorrectos");
                output.flush();
            }

        } catch (IOException e) {
            System.err.println("  ✗ Error en LOGIN: " + e.getMessage());
        }
    }

    /**
     * Procesa otros comandos
     */
    private void procesarComando(String comando) {
        try {
            System.out.println("  → Comando: " + comando);
            output.writeUTF("OK;Comando recibido");
            output.flush();
        } catch (IOException e) {
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
                System.out.println("  → Conexión cerrada");
            }
        } catch (IOException e) {
            
        }
    }
}
