package com.uDistrital.avanzada.parcialDos.control;

import com.uDistrital.avanzada.parcialDos.modelo.FrutaVO;
import com.uDistrital.avanzada.parcialDos.vista.VentanaInicio;
import com.uDistrital.avanzada.parcialDos.vista.VentanaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author Steven
 */
public class ControlVista implements ActionListener, KeyListener {

    private VentanaInicio vistaInicio;
    private VentanaJuego vistaJuego;
    private ControlGeneral controlGeneral;

    private int velX;
    private int velY;
    private int x = 0;
    private int y = 0;
    private int maxX;
    private int maxY;
    private int movPixeles = 24;
    private Timer timer;
    private boolean juegoActivo = false;


    public ControlVista(ControlGeneral general) {
        this.controlGeneral = general;
        mostrarVentanaInicio();
    }

    private void mostrarVentanaInicio() {
        vistaInicio = new VentanaInicio();
        vistaInicio.getBtnCargarConfig().addActionListener(this);
        vistaInicio.getBtnSalir().addActionListener(this);
        vistaInicio.setVisible(true);
    }


    private void solicitarArchivoProperties() {
        File archivo = vistaInicio.mostrarSelectorArchivo();

        if (archivo == null) {
            vistaInicio.setMensajeEstado("Debe seleccionar un archivo", true);
            return;
        }

        procesarArchivo(archivo);
    }


    private void procesarArchivo(File archivo) {

        boolean exitoProperties = controlGeneral.procesarArchivoProperties(archivo);

        if (!exitoProperties) {
            vistaInicio.setMensajeEstado("Error al cargar properties", true);
            return;
        }

        boolean exitoFrutas = controlGeneral.cargarFrutas();

        if (!exitoFrutas) {
            vistaInicio.setMensajeEstado("Error al cargar el properties", true);
            return;
        }

        vistaInicio.setMensajeEstado("Properties cargado correctamente", false);

        Timer delay = new Timer(1000, e -> {
            vistaInicio.dispose();
            inicializarVentanaJuego();
        });
        delay.setRepeats(false);
        delay.start();
    }


    private void inicializarVentanaJuego() {
        vistaJuego = new VentanaJuego();
        vistaJuego.setVisible(true);

        inicializarEventosJuego();
        inicializarTimer();
    }


    private void inicializarTimer() {
        this.timer = new Timer(50, e -> {
            if (juegoActivo) {
                moverPacman();
                verificarColisiones();
            }
        });
        this.timer.start();
    }


    private void inicializarEventosJuego() {
        vistaJuego.getBotonIniciar().addActionListener(this);
        vistaJuego.getBotonSalir().addActionListener(this);
        vistaJuego.getPanelJuego().addKeyListener(this);
        vistaJuego.getPanelJuego().setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

  
        if (vistaInicio != null) {
            if (src == vistaInicio.getBtnCargarConfig()) {
                solicitarArchivoProperties();
                return;
            } else if (src == vistaInicio.getBtnSalir()) {
                System.exit(0);
                return;
            }
        }


        if (vistaJuego != null) {
            if (src == vistaJuego.getBotonIniciar()) {
                iniciarJuego();
            } else if (src == vistaJuego.getBotonSalir()) {
                System.exit(0);
            }
        }
    }


    private void iniciarJuego() {
        velX = 0;
        velY = 0;
        juegoActivo = true;

        // Limpiar estado anterior
        vistaJuego.limpiarFrutas();
        controlGeneral.reiniciarJuego(); 


        centrarPacman();


        int anchoPanel = vistaJuego.getPanelJuego().getWidth();
        int altoPanel = vistaJuego.getPanelJuego().getHeight();
        controlGeneral.generarFrutas(anchoPanel, altoPanel);


        mostrarFrutas();

        vistaJuego.getPanelJuego().requestFocusInWindow();
    }


    private void mostrarFrutas() {

        List<FrutaVO> frutas = controlGeneral.getFrutasEnJuego();

        for (FrutaVO fruta : frutas) {
            JLabel labelFruta = vistaJuego.agregarFruta(
                    fruta.getRutaImagen(),
                    fruta.getX(),
                    fruta.getY()
            );

            fruta.setLabel(labelFruta);
        }
    }


    private void verificarColisiones() {
        FrutaVO frutaComida = controlGeneral.verificarColision(
                x, y,
                vistaJuego.getLabelPacman().getWidth(),
                vistaJuego.getLabelPacman().getHeight()
        );

        if (frutaComida != null) {

            vistaJuego.eliminarFruta(frutaComida.getLabel());

            if (controlGeneral.juegoTerminado()) {
                finalizarJuego();
            }
        }
    }

    private void finalizarJuego() {
        juegoActivo = false;
        velX = 0;
        velY = 0;

        int puntaje = controlGeneral.getPuntajeTotal();
        long tiempo = controlGeneral.getTiempoTranscurrido();

        vistaJuego.mostrarExito(
                "¡Juego Terminado!\n"
                + "Puntaje: " + puntaje + "\n"
                + "Tiempo: " + (tiempo / 1000) + " segundos"
        );
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!juegoActivo) {
            return;
        }

        int c = e.getKeyCode();
        if (c == KeyEvent.VK_UP) {
            velX = 0;
            velY = -movPixeles;
        } else if (c == KeyEvent.VK_DOWN) {
            velX = 0;
            velY = movPixeles;
        } else if (c == KeyEvent.VK_LEFT) {
            velX = -movPixeles;
            velY = 0;
        } else if (c == KeyEvent.VK_RIGHT) {
            velX = movPixeles;
            velY = 0;
        }
    }

    public void moverPacman() {
        if (vistaJuego == null) {
            return;
        }

        x += velX;
        y += velY;

        maxX = vistaJuego.getPanelJuego().getWidth()
                - vistaJuego.getLabelPacman().getWidth();
        maxY = vistaJuego.getPanelJuego().getHeight()
                - vistaJuego.getLabelPacman().getHeight();

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

        vistaJuego.getLabelPacman().setLocation(x, y);
    }


    public void centrarPacman() {
    int anchoPanel = vistaJuego.getPanelJuego().getWidth();
    int altoPanel = vistaJuego.getPanelJuego().getHeight();
    
    
    if (anchoPanel == 0 || altoPanel == 0) {
        anchoPanel = 900;  
        altoPanel = 630;   
    }
    
    int anchoPacman = vistaJuego.getLabelPacman().getWidth();
    int altoPacman = vistaJuego.getLabelPacman().getHeight();
    
    x = (anchoPanel - anchoPacman) / 2;
    y = (altoPanel - altoPacman) / 2;
    
    vistaJuego.getLabelPacman().setLocation(x, y);
}


    public VentanaJuego getVista() {
        return vistaJuego;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
