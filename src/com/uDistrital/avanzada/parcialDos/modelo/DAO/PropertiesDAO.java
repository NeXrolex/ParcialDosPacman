/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.DAO;

import com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionProperties;
import com.uDistrital.avanzada.parcialDos.modelo.interfaces.IRead;
import java.io.File;
import java.util.Properties;

/**
 * Lee los valores del archivo de propiedades
 *
 * @author Alex
 */
public class PropertiesDAO implements IRead<String> {
    
    private ConexionProperties conexionProps;
    private File archivoActual;

    public PropertiesDAO() {
        this.conexionProps = new ConexionProperties();
    }
    /**
     * Establece desde el jfilechooser
     * 
     * @param archivo 
     */
    public void setArchivoProperties(File archivo) {
        if (archivo == null) {
            throw new IllegalArgumentException("Archivo .properties nulo.");
        }
        if (!archivo.exists() || !archivo.isFile() || !archivo.canRead()) {
            throw new IllegalStateException();
        }
        this.archivoActual = archivo;
    }
   /**
     * Consulta el valor asociado a una clave específica dentro del archivo 
     * .properties.
     * 
     * @param clave Clave a buscar (por ejemplo: "URLBD", "USERBD", "PASSBD").
     * @return Valor encontrado o null si no existe.
     */
    /**
     * Consulta el valor asociado a una clave específica dentro del .properties.
     * Cada vez que se invoca: vuelve a pasar el File a ConexionProperties y 
     * carga el archivo.
     *
     * @param clave Clave a buscar (ej.: "URLBD", "USERBD", "PASSBD").
     * @return Valor encontrado (trim) o null si no existe.
     */
    @Override
    public String consultar(String clave) {
        if (archivoActual == null) {
            throw new IllegalStateException();
        }

        // Abrimos SIEMPRE con el archivo actual antes de consultar
        conexionProps.setArchivo(archivoActual);
        Properties props = conexionProps.conexion();//carga desde el File actual
        if (props == null) {
            throw new IllegalStateException();
        }

        return props.containsKey(clave) ? props.getProperty(clave).trim(): null;
    }

    
}
