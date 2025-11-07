/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.modelo.conexion;

import usuario.com.uDistrital.avanzada.parcialDos.modelo.interfaces.IConexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Conecta con el archivo de propiedades y cumple el contrato de servios
 * de IConexion
 *
 * @author Alex
 */
public class ConexionProperties implements IConexion {

    private File archivo;

     // Constructor vacio
    public ConexionProperties() {
    }

    /**
     * Constructor que asigna el archivo
     *
     * @param archivo Archivo
     */
    public ConexionProperties(File archivo) {
        this.archivo = archivo;
    }

    /**
     * Metodo que conecta con el archivo de propiedades
     *
     * @return Properties
     */
    @Override
    public Properties conexion() {
        try {

            if (archivo == null) {
                throw new IllegalStateException("No se ha asociado un"
                        + " archivo .properties.");
            }
            //Si se escoge un archivo incorrecto
            if (!archivo.exists() || !archivo.isFile()) {

                throw new IllegalStateException("El archivo .properties no "
                        + "existe o no es válido: " + archivo);
            }

            Properties props = new Properties();
            //Usamos try para despues de su uso se cierre el archivo, importante para las buenas parcticas
            try (FileInputStream aux = new FileInputStream(archivo)) {
                props.load(aux);
                return props;
            }

        } catch (IOException ioe) {

        }

        return null;

    }
    /**
     * Obtiene un archivo
     *
     * @return Archivo
     */
    public File getArchivo() {
        return archivo;
    }

    /**
     * Asigna un archivo
     *
     * @param archivo Archivo
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

}
