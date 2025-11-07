/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.modelo.conexion;

import usuario.com.uDistrital.avanzada.parcialDos.modelo.interfaces.IConexion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Realiza una conexion a un archivo de acceso aleatorio 
 *
 * @author Alex
 */
public class ConexionRandomAccesFile implements IConexion{

   private File archivo;
    private String modo = "rw"; // lectura/escritura por defecto

    public ConexionRandomAccesFile() {}
    
    /**
     * Constructor que asigna 
     * 
     * @param archivo
     * @param modo 
     */
    public ConexionRandomAccesFile(File archivo, String modo) {
        this.archivo = archivo;
        this.modo = modo;
    }

    /**
     * Cumple el contrato de servicio de IConexion
     * 
     * @return Conexion al raf
     */
    @Override
    public RandomAccessFile conexion() {
        if (archivo == null) {
            throw new IllegalStateException();
        }
        try {
            return new RandomAccessFile(archivo, modo);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException();
        }
    }
    
}
