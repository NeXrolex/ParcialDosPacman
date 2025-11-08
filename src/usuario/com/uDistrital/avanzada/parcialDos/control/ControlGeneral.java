/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.control;

import servidor.com.udistrital.avanzada.parcialDos.control.ControlJuego;
import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;
import java.io.File;
import java.util.List;

/**
 * Controlador principal que coordina todo el sistema Actúa como intermediario
 * entre todos los controladores
 *
 * @author Steven,Jard,Alex
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
            cProperties.establecerValoresSocket();
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

    /**
     * Delega la generación de frutas al controlador de juego
     *
     * @param anchoPanel Ancho del panel
     * @param altoPanel Alto del panel
     */
    public void generarFrutas(int anchoPanel, int altoPanel) {
        cJuego.generarFrutas(anchoPanel, altoPanel);
    }

    public List<String[]> cargarGif() {
        try {
            return cProperties.extraerGif();
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * Obtiene las frutas en juego
     *
     * @return Lista de frutas
     */
    public List<FrutaVO> getFrutasEnJuego() {
        return cJuego.getFrutasEnJuego();
    }

    /**
     * Verifica colisiones entre Pac-Man y frutas
     *
     * @param pacmanX Posición X de Pac-Man
     * @param pacmanY Posición Y de Pac-Man
     * @param pacmanAncho Ancho de Pac-Man
     * @param pacmanAlto Alto de Pac-Man
     * @return Fruta comida o null
     */
    public FrutaVO verificarColision(int pacmanX, int pacmanY,
            int pacmanAncho, int pacmanAlto) {
        return cJuego.verificarColision(pacmanX, pacmanY,
                pacmanAncho, pacmanAlto);
    }

    /**
     * Verifica si el juego terminó
     *
     * @return true si terminó
     */
    public boolean juegoTerminado() {
        return cJuego.juegoTerminado();
    }

    /**
     * Obtiene el puntaje total acumulado
     *
     * @return Puntaje
     */
    public int getPuntajeTotal() {
        return cJuego.getPuntajeTotal();
    }

    /**
     * Obtiene el tiempo transcurrido en milisegundos
     *
     * @return Tiempo en ms
     */
    public long getTiempoTranscurrido() {
        return cJuego.getTiempoTranscurrido();
    }

    /**
     * Reinicia el estado del juego
     */
    public void reiniciarJuego() {
        cJuego.reiniciar();
    }
}
