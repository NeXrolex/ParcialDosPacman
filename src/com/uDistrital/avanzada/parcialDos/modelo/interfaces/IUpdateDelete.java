/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.interfaces;

/**
 * Interface que cumple el contrato de servicio de
 * Actualizar y eliminar 
 * Hace re fe referencia a la "U" y a la "D" del crud
 *
 * @author Alex
 */
public interface IUpdateDelete<T> {
    
    /**
     * Modifica un elemento
     * 
     * @param elemento Elemento a modificar
     */
    void modificar(T elemento);
    
    /**
     * Elimina un elemento
     * 
     * @param elemento Elemento
     */
    void eliminar(T elemento);
}
