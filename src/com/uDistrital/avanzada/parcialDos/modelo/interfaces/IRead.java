/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.interfaces;

/**
 * Contrato de servicios para extraer datos
 * Hace referencia a la r del crud
 * @author Alex
 */
public interface IRead<T> {
    
    //Usamos t porque hacemos referencia a un generico
    /**
     * consulta un elemento
     * @param elemento Elemento
     * @return generico consultado
     */
    public T consultar(T elemento);
    
}
