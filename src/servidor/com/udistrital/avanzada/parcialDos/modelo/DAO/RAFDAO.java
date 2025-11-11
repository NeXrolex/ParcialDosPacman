/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.modelo.DAO;

import com.uDistrital.avanzada.parcialDos.modelo.interfaces.ICreate;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import servidor.com.udistrital.avanzada.parcialDos.modelo.conexion.ConexionRandomAccesFile;

/**
 * DAO que se encarga de los procesos de guardar los datos de la partida
 *
 * @author Jard
 */
public class RAFDAO implements ICreate {

    private ConexionRandomAccesFile conexion;
    private File archivo;
    /**
     * Constructor del DAO 
     * @param archivo archivo donde se guardan las conexiones
     */
    public RAFDAO(File archivo) {
        this.archivo = archivo;
        this.conexion = new ConexionRandomAccesFile(archivo, "rw");
        crearArchivo();
    }
    /**
     * Crea el archivo si no existe
     */
    private void crearArchivo() {
        try {
            // Crear directorios padre si no existen
            File archivoSeleccionado = archivo.getParentFile();
            if (archivoSeleccionado != null && !archivoSeleccionado.exists()) {
                archivoSeleccionado.mkdirs();
            }

            // Crear archivo si no existe
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
        }
    }

    /**
     * Escribe dentro del archivo usando la conexion 
     * @param elemento elemento Array string  que contiene el nombre , puntaje,
     * tiempo
     */
    @Override
    public void insertar(Object elemento) {
        if (!(elemento instanceof String[])) {
            throw new IllegalArgumentException("Debe ser String[] con {nombre,"
                    + " puntaje, tiempo}");
        }

        String[] datos = (String[]) elemento;
        RandomAccessFile raf = null;

        try {
            raf = conexion.conexion();
            // Ir al final del archivo
            raf.seek(raf.length());

            // Escribir datos con longitud fija
            escribirCadenaFija(raf, datos[0], 30);
            escribirCadenaFija(raf, datos[1], 10);
            escribirCadenaFija(raf, datos[2], 10);

            raf.writeChar('\n');

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar puntuación: " +
                    e.getMessage(), e);
        } finally {
            cerrar(raf);
        }
    }

    /**
     * 
     * @param raf Raf donde escribir
     * @param texto Texto que se va a escribir
     * @param longitud longitud que se va a escribir
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    private void escribirCadenaFija(RandomAccessFile raf, String texto, int longitud)
            throws IOException {
        if (texto == null) {
            texto = "";
        }

        if (texto.length() > longitud) {
            texto = texto.substring(0, longitud);
        }

       
        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < longitud) {
            sb.append(' ');
        }

        raf.writeChars(sb.toString());
    }

    /**
     * Cierra el archivo
     * @param raf Archivo a cerrar
     */
    private void cerrar(RandomAccessFile raf) {
        if (raf != null) {
            try {
                raf.close();
            } catch (IOException e) {
            }
        }
    }
}
