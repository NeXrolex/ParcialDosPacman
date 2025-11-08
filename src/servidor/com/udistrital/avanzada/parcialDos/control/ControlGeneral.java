/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.io.File;
import java.util.List;
import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;

/**
 * Maneja la informacion del servidor
 *
 * @author Alex
 */
public class ControlGeneral {
    
    private ControlVista cVista;
    private ControlProperties cProperties;
    private ControlJuego cJuego;

    public ControlGeneral() {
        this.cProperties = new ControlProperties();
        this.cJuego = new ControlJuego();

        // ControlVista recibe esta instancia
        this.cVista = new ControlVista(this);
    }
    
    /**
     * Procesa el archivo properties seleccionado por el usuario
     *
     * @param archivo Archivo seleccionado
     * @return true si se cargó correctamente, false si hubo error
     */
    public boolean procesarArchivoProperties(File archivo) {
        try {
            cProperties.setArchivoProperties(archivo);
            cProperties.establecerValoresBaseDatos();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Carga las frutas desde el archivo properties
     *
     * @return true si se cargaron correctamente, false si hubo error
     */
    public boolean cargarFrutas() {
        try {
            List<FrutaVO> frutas = cProperties.cargarFrutas();
            cJuego.setFrutasDisponibles(frutas);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<String[]> cargarGif() {
        try {
            return cProperties.extraerGif();
        } catch (Exception e) {
            return List.of();
        }
    }
}
