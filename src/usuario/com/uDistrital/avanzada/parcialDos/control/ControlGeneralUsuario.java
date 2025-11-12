/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.control;

import java.io.File;

/**
 * Maneja toda la informacion y delega a los controles hacer sus respectivas
 * acciones
 *
 * @author Steven, Jard, Alex
 */
public class ControlGeneralUsuario {

    private ControlVistaUsuario cVistaUsuario;
    private ControlVentanaStreaming cVistaStream;
    private ControlProperties cProperties;
    private ControlSocket cSocket;

    public ControlGeneralUsuario() {
        this.cProperties = new ControlProperties();
        this.cSocket = new ControlSocket(this);
        this.cVistaUsuario = new ControlVistaUsuario(this);
        this.cVistaStream = new ControlVentanaStreaming(this);
    }

    /**
     * Por el JFilechooser obtiene el archivo de propiedades y establece los
     * valores para conectar al socket
     *
     * @param archivo ARchivo
     * @return True si establece or false si no lo hace
     */
    public boolean procesarArchivoProperties(File archivo) {
        try {
            cProperties.setArchivoProperties(archivo);
            cProperties.establecerValoresSocket();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Intenta conectar al servidor
     *
     * @return true si la conexión fue exitosa
     */
    public boolean conectarAlServidor() {
        return cSocket.conectar();
    }

    /**
     * Inicia sesión con usuario y contraseña
     *
     * @param usuario Nombre de usuario
     * @param contrasena Contraseña
     * @return true si el login fue exitoso
     */
    public boolean iniciarSesion(String usuario, String contrasena) {
        return cSocket.iniciarSesion(usuario, contrasena);
    }

    /**
     * Delega al socket enviar el movimeinto que llaga desde la vista
     *
     * @param comando
     */
    public void enviarMovimiento(String comando) {

        cSocket.enviarMovimiento(comando);
    }

    /**
     * Verifica si está conectado al servidor
     *
     * @return true si está conectado
     */
    public boolean estaConectado() {
        return cSocket.estaConectado();
    }

    /**
     * Delega al control socket que cierre la conexion con el servidor
     */
    public void cerrarConexion() {
        cSocket.cerrar();
    }
    private ReceptorPanel receptor;

    public void iniciarRecepcionStream() {
        if (cSocket.estaConectado()) {
            receptor = new ReceptorPanel(cSocket.getSocket(), cVistaStream.getVistaStream());
            // Ejecutar recepción en hilo separado para no bloquear la GUI
            new Thread(() -> receptor.recibirCaptura()).start();
        } else {
            System.out.println("No hay conexión al servidor.");
        }
    }

}
