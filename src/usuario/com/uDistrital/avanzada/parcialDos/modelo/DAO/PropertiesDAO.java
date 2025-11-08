/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.modelo.DAO;

import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;
import servidor.com.udistrital.avanzada.parcialDos.modelo.conexion.ConexionBaseDatos;
import com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionProperties;
import com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionSocket;
import com.uDistrital.avanzada.parcialDos.modelo.interfaces.IRead;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Lee los valores del archivo de propiedades del usuario  
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

    
}
