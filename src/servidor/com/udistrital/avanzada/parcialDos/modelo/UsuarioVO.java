/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.modelo;

import servidor.com.udistrital.avanzada.parcialDos.modelo.Persona;
import servidor.com.udistrital.avanzada.parcialDos.modelo.conexion.ConexionBaseDatos;

/**
 * Representa a un usario en el sistema
 *
 * @author Alex
 */
public class UsuarioVO extends Persona {

    private String contrasena;
    private int puntaje;
    private double tiempo;

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
     * @param tiempo Tiempo del usuario
     */
    public UsuarioVO(String nombre, String contrasena, int puntaje,
            double tiempo) {
        super(nombre);
        this.contrasena = contrasena;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
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
    
    /**
     * Obtiene el tiempo del usuario
     * 
     * @return Tiempo 
     */
    public double getTiempo() {
        return tiempo;
    }
    
    /**
     * Asigan el tiempo del usuario
     * 
     * @param tiempo Tiempo del usuario
     */
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    
    

}
