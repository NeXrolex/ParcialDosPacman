/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.modelo.DAO;

import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;
import com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionBaseDatos;
import com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionProperties;
import com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionSocket;
import com.uDistrital.avanzada.parcialDos.modelo.interfaces.IRead;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Lee los valores del archivo de propiedades
 *
 * @author Alex
 */
public class PropertiesDAO implements IRead<String> {

    private ConexionProperties conexionProps;
    //Archivo actual
    private File archivoActual;

    /**
     * Constructor que intancia a la conexion properties para poder acciones
     * comunes en los dao
     */
    public PropertiesDAO() {
        //Intancia la conexion a los properties
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
     * Consulta el valor asociado a una clave específica dentro del .properties.
     * Cada vez que se invoca: vuelve a pasar el File a ConexionProperties y
     * carga el archivo.
     *
     * @param clave Clave a buscar (ej.: "URLBD", "USERBD", "PASSBD").
     * @return Valor encontrado (trim) o null si no existe.
     */
    @Override
    public String consultar(String clave) {
        Properties p = cargarTodas();
        String v = p.getProperty(clave);
        return v != null ? v.trim() : null;
    }

    /**
     * Carga toda la informacion del archivo de propiedades Util para buscar
     * claves
     *
     * @return
     */
    public Properties cargarTodas() {
        if (archivoActual == null) {
            throw new IllegalStateException("No se ha seteado archivo"
                    + " .properties.");
        }
        conexionProps.setArchivo(archivoActual);
        Properties props = conexionProps.conexion();
        if (props == null || props.isEmpty()) {
            throw new IllegalStateException("El archivo .properties está "
                    + "vacío.");
        }
        return props;
    }

    /**
     * Necesario para establecer conexiones a la base de datos, antes de iniciar
     * una comunicacion con la base de datos establece los valores para iniciar
     * una conexion
     *
     */
    public void configurarConexionBDDesdeArchivo() {
        Properties props = cargarTodas();

        String url = props.getProperty("URLBD");
        String user = props.getProperty("USERBD");
        String pass = props.getProperty("PASSBD");

        if (url == null || user == null || pass == null
                || url.trim().isEmpty() || user.trim().isEmpty()
                || pass.trim().isEmpty()) {
            throw new IllegalStateException("Faltan una o más claves "
                    + "requeridas en el archivo .properties "
                    + "(URLBD, USERBD, PASSBD).");
        }

        // Se configuran directamente los valores en la clase de conexión
        ConexionBaseDatos.configurar(url.trim(), user.trim(), pass.trim());
    }

    /**
     * Carga todas las frutas desde el archivo properties
     *
     * @return Lista de frutas disponibles
     */
    public List<FrutaVO> cargarFrutas() {
        List<FrutaVO> frutas = new ArrayList<>();
        Properties props = cargarTodas();

        // Obtener el total de frutas
        String totalStr = props.getProperty("TOTAL_FRUTAS");
        if (totalStr == null) {
            throw new IllegalStateException("No se encontró "
                    + "TOTAL_FRUTAS en properties");
        }

        int total = Integer.parseInt(totalStr.trim());

        // Cargar cada fruta
        for (int i = 1; i <= total; i++) {
            String clave = "FRUTA_" + i;
            String valor = props.getProperty(clave);

            if (valor != null && !valor.trim().isEmpty()) {
                // Formato: nombre,puntos,rutaImagen
                String[] partes = valor.split(",");

                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    int puntos = Integer.parseInt(partes[1].trim());
                    String rutaImagen = partes[2].trim();

                    FrutaVO fruta = new FrutaVO(nombre, puntos, rutaImagen);
                    frutas.add(fruta);
                }
            }
        }

        return frutas;
    }

    /**
     * Saca los elementos que estan en los properties y asigana los valores para
     * poder conectar a los socket
     *
     */
    public void configurarConexionSocketDesdeArchivo() {
        Properties props = cargarTodas();

        String ip = props.getProperty("IP_SOCKET");
        String port = props.getProperty("PUERTO_SOCKET");

        if (ip == null || port == null || ip.trim().isEmpty() || port.trim()
                .isEmpty()) {
            throw new IllegalStateException("Faltan claves de conexión de"
                    + " socket (IP_SOCKET, PUERTO_SOCKET).");
        }

        try {
            int puerto = Integer.parseInt(port.trim());
            ConexionSocket.configurarSocket(ip.trim(), puerto);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("PUERTO_SOCKET no es un"
                    + " número válido.", e);
        }
    }

    public ArrayList<String[]> extraerGif() {
        if (this.archivoActual == null) {
            throw new IllegalStateException("El archivo .properties es null");
        }

        conexionProps.setArchivo(archivoActual);
        Properties props = conexionProps.conexion();

        ArrayList<String[]> gifDatos = new ArrayList<>();

        for (String key : props.stringPropertyNames()) {
            if (key.startsWith("GIF_")) {
                String valor = props.getProperty(key, "").trim();
                if (!valor.isEmpty()) {
                    gifDatos.add(new String[]{key, valor});
                }
            }
        }
        return gifDatos;
    }
}
