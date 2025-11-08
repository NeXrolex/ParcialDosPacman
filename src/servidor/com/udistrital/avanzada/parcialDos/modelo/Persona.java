/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.modelo;

/**
 * Representa a una persona en el sistema
 *
 * @author Alex
 */
public class Persona {
    
    private String nombre;
    
    /**
     * constructor que asigna el nombre de 
     * una persona
     * 
     * @param nombre Nombre de la persona 
     */
    public Persona(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el nombre de una persona
     * 
     * @return Nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Asigna el nombre de una persona
     * 
     * @param nombre Nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
