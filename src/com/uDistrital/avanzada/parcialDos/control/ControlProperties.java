/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.control;

import com.uDistrital.avanzada.parcialDos.modelo.DAO.PropertiesDAO;
import com.uDistrital.avanzada.parcialDos.modelo.FrutaVO;
import java.io.File;
import java.util.List;

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
     * Busca un valor en el archivo de propiedades 
     * 
     * @param clave Clave a buscar
     * @return 
     */
   public String buscarClave(String clave){
       return propertiesDAO.consultar(clave);
   }
   
   /**
    * Establece los valores de la base de datos
    * 
    */
   public void establecerValoresBaseDatos(){
       propertiesDAO.configurarConexionBDDesdeArchivo();
   }
   public List<FrutaVO> cargarFrutas() {
       return propertiesDAO.cargarFrutas();
   }
}
