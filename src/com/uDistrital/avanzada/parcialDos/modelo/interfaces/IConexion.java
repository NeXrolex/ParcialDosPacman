/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.interfaces;

/**
 * Contrato de servicios destinado a la conexion
 * ya se de archivos, bases de datos
 *  Con ella cumplimos la "I" del solid
 * @author ALex
 */
public interface IConexion<T> {
    
    //Parametro t, es un generico
    public T conexion();
    
}
