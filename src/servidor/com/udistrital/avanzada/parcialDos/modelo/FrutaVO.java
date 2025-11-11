/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.modelo;

/**
 * Representa una fruta o item del juego
 *
 * @author jard,Steven 
 */
public class FrutaVO {

    private String nombre;
    private int puntos;
    private String rutaImagen;
    private int x;
    private int y;
    private boolean comida;
    private Object label;
    
    /**
     * Constructor  
     * 
     * @param nombre Nombre de la fruta u objeto
     * @param puntos Puntos
     * @param rutaImagen Ruta de la imagen
     */
    public FrutaVO(String nombre, int puntos, String rutaImagen) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.rutaImagen = rutaImagen;
        this.comida = false;
    }

    /**
     * Obtiene el nombre de la fruta
     * 
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene los puntos
     * 
     * @return puntos
     */
    public int getPuntos() {
        return puntos;
    }
    
    /**
     * Obtiene la ruta de la imagen
     * 
     * @return  ruta Ruta de la imagen 
     */
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    /**
     * Cordenada en x de la fruta
     * 
     * @return Posicion en x
     */
    public int getX() {
        return x;
    }
    
    /**
     * Asigna la posicion x
     * 
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Obtiene el valor en y de la fruta
     * 
     * @return Valor de Y
     */
    public int getY() {
        return y;
    }
    
    /**
     * Aisgna el valor en y de la fruta
     * 
     * @param y Valor en y
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Compruba si la fruta ya fue comida
     * 
     * @return True si fue comida o false si sigue disponible
     */
    public boolean isComida() {
        return comida;
    }
    
    /**
     * Marca la fruta en sus dos estados
     * 
     * @param comida Comida 
     */
    public void setComida(boolean comida) {
        this.comida = comida;
    }
    
    /**
     *  obtiene la Referencia grafica de la
     * fruta
     * 
     * @return Referencia grafica
     */
    public Object getLabel() {  
        return label;
    }
    
    /**
     * Asigna la referencia grafica de la fruta
     * 
     * @param label Componente asociado
     */
    public void setLabel(Object label) {  
        this.label = label;
    }
}
