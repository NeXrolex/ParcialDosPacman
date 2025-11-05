/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.control;

import com.uDistrital.avanzada.parcialDos.modelo.DAO.PropertiesDAO;
import java.io.File;

/**
 * Maneja la logica y flujo de informacion 
 * del archivo de propiedades
 *
 * @author Alex
 */
public class ControlProperties {

    private final PropertiesDAO propertiesDAO;

    public ControlProperties() {
        this.propertiesDAO = new PropertiesDAO();
    }

    /**
     * Recibe el archivo properties y se lo pasa al dao 
     * para mantener la referencia
     * 
     * @param archivo 
     */
    public void setArchivoProperties(File archivo) {
        propertiesDAO.setArchivoProperties(archivo);
    }

    /**
     * Obtiene la referencia de la url de la 
     * base de datos
     * 
     * @return url base de datos
     */
    public String getDbUrl() {
        return propertiesDAO.consultar("URLBD");
    }
    
    /**
     * Obtiene el usuario de la base de datos
     * 
     * @return Usuario base de datos
     */
    public String getDbUser() {
        return propertiesDAO.consultar("USERBD");
    }
    
    /**
     * Obtiene la contrasena de la base de datos
     * 
     * @return Contrasena base de datos
     */
    public String getDbPassword() {
        return propertiesDAO.consultar("PASSBD");
    }
}
