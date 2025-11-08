/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.Timer;
import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;
import servidor.com.udistrital.avanzada.parcialDos.vista.VentanaPrincipalServidor;

/**
 *
 * @author Steven
 */
public class ControlVistaServidor implements ActionListener {

    private ControlGeneralServidor cGeneralServidor;
    private VentanaPrincipalServidor ventanaPrincipalServidor;

    private int velX;
    private int velY;
    private int x = 0;
    private int y = 0;
    private int maxX;
    private int maxY;
    private int movPixeles = 16;
    private Timer timer;
    private boolean juegoActivo = false;
    private Timer timerTiempo;

    public ControlVistaServidor(ControlGeneralServidor cGeneralServidor) {
        this.cGeneralServidor = cGeneralServidor;
        inicializarVentana();
    }

    private void inicializarVentana() {
        ventanaPrincipalServidor = new VentanaPrincipalServidor();

        ventanaPrincipalServidor.getBtnCargarConfig().addActionListener(this);
        ventanaPrincipalServidor.getBtnSalirInicio().addActionListener(this);

        ventanaPrincipalServidor.getBotonIniciar().addActionListener(this);
        ventanaPrincipalServidor.getBotonSalir().addActionListener(this);

        ventanaPrincipalServidor.setVisible(true);
    }

    private void solicitarArchivoProperties() {
        int resultado = ventanaPrincipalServidor.abrirSelectorArchivo();

        if (resultado != 0) {
            ventanaPrincipalServidor.setMensajeEstadoError("Debe seleccionar"
                    + " un archivo");
            return;
        }

        File archivo = ventanaPrincipalServidor.getArchivoSeleccionado();

        if (archivo == null) {
            ventanaPrincipalServidor.setMensajeEstadoError("Debe seleccionar"
                    + " un archivo");
            return;
        }

        procesarArchivo(archivo);
    }

    private void procesarArchivo(File archivo) {
        boolean exitoProperties = cGeneralServidor
                .procesarArchivoProperties(archivo);

        if (!exitoProperties) {
            ventanaPrincipalServidor.setMensajeEstadoError("Error al cargar"
                    + " properties");
            return;
        }

        boolean exitoFrutas = cGeneralServidor.cargarFrutas();

        if (!exitoFrutas) {
            ventanaPrincipalServidor.setMensajeEstadoError("Error al cargar"
                    + " el properties");
            return;
        }

        List<String[]> recursos = cGeneralServidor.cargarGif();

        for (String[] recurso : recursos) {
            String clave = recurso[0];
            String ruta = recurso[1];

            if ("GIF_PACMAN".equalsIgnoreCase(clave)) {
                ventanaPrincipalServidor.establecerGifPacman(ruta);
            }
        }

        ventanaPrincipalServidor.setMensajeEstadoExito("Properties cargado"
                + " correctamente");

        Timer delay = new Timer(1000, e -> {
            ventanaPrincipalServidor.mostrarPantallaJuego();
            inicializarTimer();
        });
        delay.setRepeats(false);
        delay.start();
    }

    private void inicializarTimer() {
        this.timer = new Timer(50, e -> {
            // LÓGICA: Solo mover si el juego está activo
            if (juegoActivo) {
                moverPacman();
                verificarColisiones();
            }
        });
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == ventanaPrincipalServidor.getBtnCargarConfig()) {
            solicitarArchivoProperties();
            return;
        } else if (src == ventanaPrincipalServidor.getBtnSalirInicio()) {
            System.exit(0);
            return;
        }

        if (src == ventanaPrincipalServidor.getBotonIniciar()) {
            iniciarJuego();
        } else if (src == ventanaPrincipalServidor.getBotonSalir()) {
            System.exit(0);
        }
    }

    private void iniciarJuego() {

        velX = 0;
        velY = 0;
        juegoActivo = true;

        ventanaPrincipalServidor.limpiarFrutas();
        cGeneralServidor.reiniciarJuego();
        actualizarTiempo();

        centrarPacman();

        int anchoPanel = ventanaPrincipalServidor.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipalServidor.getAltoPanelJuego();
        cGeneralServidor.generarFrutas(anchoPanel, altoPanel);

        mostrarFrutas();

        ventanaPrincipalServidor.darFocoPanelJuego();
        if (timerTiempo != null && timerTiempo.isRunning()) {
            timerTiempo.stop();
        }

        timerTiempo = new Timer(1000, e -> actualizarTiempo());
        timerTiempo.start();
    }

    private void mostrarFrutas() {
        List<FrutaVO> frutas = cGeneralServidor.getFrutasEnJuego();

        for (FrutaVO fruta : frutas) {

            Object objetoFruta = ventanaPrincipalServidor.agregarFruta(
                    fruta.getRutaImagen(),
                    fruta.getX(),
                    fruta.getY()
            );

            fruta.setLabel(objetoFruta);
        }
    }

    private void verificarColisiones() {
        int anchoPacman = ventanaPrincipalServidor.getAnchoPacman();
        int altoPacman = ventanaPrincipalServidor.getAltoPacman();

        FrutaVO frutaComida = cGeneralServidor.verificarColision(
                x, y,
                anchoPacman,
                altoPacman
        );

        if (frutaComida != null) {
            ventanaPrincipalServidor.eliminarFruta(frutaComida.getLabel());
            actualizarPuntaje();
            if (cGeneralServidor.juegoTerminado()) {
                finalizarJuego();
            }
        }
    }

    private void finalizarJuego() {
        juegoActivo = false;
        velX = 0;
        velY = 0;
        int puntaje = cGeneralServidor.getPuntajeTotal();
        long tiempo = cGeneralServidor.getTiempoTranscurrido();
        String mensaje = "¡Juego Terminado!\n"
                + "Puntaje: " + puntaje + "\n"
                + "Tiempo: " + (tiempo / 1000) + " segundos";

        if (timerTiempo != null) {
            timerTiempo.stop();
        }
        ventanaPrincipalServidor.mostrarDialogo(mensaje, "Juego Terminado", 1);

    }

    public void moverPacman() {

        if (ventanaPrincipalServidor == null) {
            return;
        }

        x += velX;
        y += velY;

        int anchoPanel = ventanaPrincipalServidor.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipalServidor.getAltoPanelJuego();
        int anchoPacman = ventanaPrincipalServidor.getAnchoPacman();
        int altoPacman = ventanaPrincipalServidor.getAltoPacman();

        maxX = anchoPanel - anchoPacman;
        maxY = altoPanel - altoPacman;

        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > maxX) {
            x = maxX;
        }
        if (y > maxY) {
            y = maxY;
        }

        ventanaPrincipalServidor.setPosicionPacman(x, y);
    }

    public void centrarPacman() {
        int anchoPanel = ventanaPrincipalServidor.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipalServidor.getAltoPanelJuego();

        if (anchoPanel == 0 || altoPanel == 0) {
            anchoPanel = 900;
            altoPanel = 630;
        }

        int anchoPacman = ventanaPrincipalServidor.getAnchoPacman();
        int altoPacman = ventanaPrincipalServidor.getAltoPacman();

        x = (anchoPanel - anchoPacman) / 2;
        y = (altoPanel - altoPacman) / 2;

        ventanaPrincipalServidor.setPosicionPacman(x, y);
    }

    private void actualizarPuntaje() {
        int puntaje = cGeneralServidor.getPuntajeTotal();
        ventanaPrincipalServidor.getLblPuntaje().setText(String.valueOf(puntaje));
    }

    public void actualizarTiempo() {
        long tiempo = cGeneralServidor.getTiempoTranscurrido() / 1000;
        ventanaPrincipalServidor.getLblTiempo().setText("Tiempo: " + tiempo + " s");
    }

}
