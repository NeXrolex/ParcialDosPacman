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
public class ControlGeneralServidor{

    private ControlVistaServidor cVista;
    private ControlProperties cProperties;
    private ControlJuego cJuego;
    private ControlUsuario cUsuario;
    private ControlSocketServidor cSocket;

    public ControlGeneralServidor() {
        this.cProperties = new ControlProperties();
        this.cJuego = new ControlJuego();
        this.cUsuario = new ControlUsuario();
        this.cSocket = new ControlSocketServidor(this);
        // ControlVista recibe esta instancia
        this.cVista = new ControlVistaServidor(this);
    }

    /**
     * Inicia el servidor de sockets en un hilo separado
     */
    public void iniciarServidor() {
        Thread hiloServidor = new Thread(cSocket);
        hiloServidor.setName("ServidorSockets");
        hiloServidor.start();
    }

    /**
     * Autentica un usuario
     */
    public boolean autenticarUsuario(String nombre, String contrasena) {
        return cUsuario.iniciarSesion(nombre, contrasena);
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

    /**
     * Delega la generación de frutas al controlador de juego
     *
     * @param anchoPanel Ancho del panel
     * @param altoPanel Alto del panel
     */
    public void generarFrutas(int anchoPanel, int altoPanel) {
        cJuego.generarFrutas(anchoPanel, altoPanel);
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

    public void detenerServidor() {
        if (cSocket != null) {
            cSocket.detenerServidor();
        }
    }
}
