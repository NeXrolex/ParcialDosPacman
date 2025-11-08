/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.control;
import java.io.File;


/**
 * @author Steven, Jard, Alex
 */
public class ControlGeneralUsuario {

    private ControlVistaUsuario cVistaUsuario;
    private ControlProperties cProperties;

    private ControlSocket cSocket;

    public ControlGeneralUsuario() {
        this.cProperties = new ControlProperties();
        this.cSocket = new ControlSocket(this);

        
        this.cVistaUsuario = new ControlVistaUsuario(this);
    }


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
     * Envía un movimiento al servidor
     * 
     * @param movimiento Dirección del movimiento (ARRIBA, ABAJO, IZQUIERDA, DERECHA)
     * @return true si se envió correctamente
     */
    public boolean enviarMovimiento(String movimiento) {
        try {
            String respuesta = cSocket.enviarComando("MOVIMIENTO", movimiento);
            return respuesta != null && respuesta.toUpperCase().startsWith("OK");
        } catch (Exception e) {
            return false;
        }
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
     * Cierra la conexión con el servidor
     */
    public void cerrarConexion() {
        cSocket.cerrar();
    }
}
