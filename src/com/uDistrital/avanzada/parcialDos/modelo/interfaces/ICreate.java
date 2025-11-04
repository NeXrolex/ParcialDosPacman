/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.interfaces;

/**
 * Interface con contrato de servecio para insertar elementos 
 * Ademas cumple con la "C" del crud 
 *
 * @author Alex
 */
public interface ICreate<T> {
    
    //T porque hace referencia a un generico
    /**
     * Inserta un elemento generico
     * 
     * @param elemento Elemento a insertar
     */
    public void insertar(T elemento);
    
}
