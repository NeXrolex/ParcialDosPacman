/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.io.File;
import java.util.List;
import servidor.com.udistrital.avanzada.parcialDos.modelo.DAO.PropertiesDAO;
import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;

/**
 * Maneja el flujo y logica del archivo de propieades del servidor
 *
 * @author Alex
 */
public class ControlProperties {
    private final PropertiesDAO propertiesDAO;

    public ControlProperties() {
        this.propertiesDAO = new PropertiesDAO();
    }
    /**
     * Recibe el archivo properties y se lo pasa al dao para mantener la
     * referencia
     *
     * @param archivo
     */
    public void setArchivoProperties(File archivo) {
        propertiesDAO.setArchivoProperties(archivo);
    }
    
    /**
     * Busca algo en el archivo de propiedades
     * por la clave
     * 
     * @param clave Clave a buscar
     * @return consulta
     */
    public String buscarClave(String clave) {
        return propertiesDAO.consultar(clave);
    }
    
    /**
     * Establece los valores para que los usuarios
     * se conecten 
     * 
     */
    public void establecerValoresServerSocket(){
        propertiesDAO.configurarServerSocketDesdeArchivo();
    }
    
    /**
     * Establece los valores de la base de datos
     *
     */
    public void establecerValoresBaseDatos() {
        propertiesDAO.configurarConexionBDDesdeArchivo();
    }
    public List<FrutaVO> cargarFrutas() {
        return propertiesDAO.cargarFrutas();
    }

    public List<String[]> extraerGif() {
        return propertiesDAO.extraerGif();
    }
}
