/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.modelo;

import usuario.com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionBaseDatos;

/**
 * Representa a un usario en el sistema
 *
 * @author Alex
 */
public class UsuarioVO extends Persona {
    
    private String contrasena;
    private int puntaje;

    /**
     * Constructor simple
     * 
     * @param nombre Nombre del usuario
     */
    public UsuarioVO(String nombre) {
        super(nombre);
        
    }
    
    
    
    /**
     * Constructor que asigna valores de un usuario
     * 
     * @param nombre Nombre del usuario
     * @param contrasena Contrasena del usuario
     */
    public UsuarioVO(String nombre, String contrasena, int puntaje) {
        super(nombre);
        this.contrasena = contrasena;
        this.puntaje = puntaje;
    }
    
    
    
    /**
     * Obtiene la contrasena
     * 
     * @return Contrasena
     */
    public String getContrasena() {
        return contrasena;
    }
    
    /**
     * Asigna una contrasena
     * 
     * @param contrasena Contrasena del usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    /**
     * Obtiene el puntaje del jugador
     * 
     * @return Puntaje 
     */
    public int getPuntaje() {
        return puntaje;
    }
    
    /**
     * Asigna un puntaje
     * 
     * @param puntaje Puntaje a asignar
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
    
    
}
