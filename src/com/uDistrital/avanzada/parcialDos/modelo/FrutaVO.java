/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo;

/**
 * Representa una fruta o item del juego
 *
 * @author Tu nombre
 */
public class FrutaVO {

    private String nombre;
    private int puntos;
    private String rutaImagen;
    private int x; // posición en el panel
    private int y;
    private boolean comida; // si ya fue comida

    public FrutaVO(String nombre, int puntos, String rutaImagen) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.rutaImagen = rutaImagen;
        this.comida = false;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isComida() {
        return comida;
    }

    public void setComida(boolean comida) {
        this.comida = comida;
    }
}
