/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.control;

import java.io.File;

/**
 * Controlador principal que coordina todo el sistema Actúa como intermediario
 * entre todos los controladores
 *
 * @author Steven,Jard,Alex
 */
public class ControlGeneral {

    private ControlVista cVista;
    private ControlProperties cProperties;
    private ControlSocket cSocket;

    public ControlGeneral() {
        this.cProperties = new ControlProperties();
        this.cSocket = new ControlSocket(this);

        // ControlVista recibe esta instancia
        this.cVista = new ControlVista(this);
    }

    /**
     * Procesa el archivo properties seleccionado por el usuario
     *
     * @param archivo Archivo seleccionado
     * @return true si se cargó correctamente, false si hubo error
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
     * Envia un movimiento al controlSocket para que sea 
     * enviado al server 
     * 
     * @param comando Movimeinto a enviar 
     */
    public void enviarMovimientos(String comando){
        cSocket.enviarMovimiento(comando);
    }

}
