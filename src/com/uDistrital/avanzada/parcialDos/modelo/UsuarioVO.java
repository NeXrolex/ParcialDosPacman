/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo;

/**
 * Representa a un usario en el sistema
 *
 * @author Alex
 */
public class UsuarioVO extends Persona {
    
    private String contrasena;
    
    /**
     * Constructor que asigna valores de un usuario
     * 
     * @param nombre Nombre del usuario
     * @param contrasena Contrasena del usuario
     */
    public UsuarioVO(String nombre, String contrasena) {
        super(nombre);
        this.contrasena = contrasena;
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
    
    
}
