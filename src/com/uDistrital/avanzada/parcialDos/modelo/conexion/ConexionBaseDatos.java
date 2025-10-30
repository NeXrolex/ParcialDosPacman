/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.uDistrital.avanzada.parcialDos.modelo.interfaces.IConexion;

/**
 * Se encarga de realizar la conexion a la base de datos Implementa nuestro
 * contrato para la conexion
 *
 * @author Alex
 */
public class ConexionBaseDatos implements IConexion {

    private String usuario;
    private String contrasena;
    private String URLBD;
    private Connection cn = null;
    
    /**
     * Conecta con la base de datos y cumple el contrato de servicios de 
     * IConexion
     * 
     * @return Conexion a la base de datos
     */
    @Override
    public Connection conexion() {
        try {
            //Establecemos la conexion con la base de datos
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
            //Usamos los parametros asignados anteriormente
        } catch (SQLException ex) {
        }
        return cn;
    }
    
    /**
     * Obtiene el nombre del usuario de la base de datos
     * 
     * @return Usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Asigna el usuario a la base de datos
     * 
     * @param usuario Usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Obtiene la contrasenia
     * 
     * @return Contrasena
     */
    public String getContrasena() {
        return contrasena;
    }
    
    /**
     * Asigana la contrasena
     * 
     * @param contrasena Contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    /**
     * Obtiene la direccion de la base de datos
     * 
     * @return URLBaseDatos
     */
    public String getURLBD() {
        return URLBD;
    }
    
    /**
     * Asigna la URL de la base de datos
     * 
     * @param URLBD URLBaseDatos
     */
    public void setURLBD(String URLBD) {
        this.URLBD = URLBD;
    }

}
