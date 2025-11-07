package com.uDistrital.avanzada.parcialDos.control;

import com.uDistrital.avanzada.parcialDos.modelo.FrutaVO;
import com.uDistrital.avanzada.parcialDos.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import javax.swing.Timer;

/**
 * Controlador de la vista - maneja SOLO LÓGICA PURA
 * NO contiene referencias a componentes visuales (JLabel, JFileChooser, Color, etc.)
 * @author Steven
 */
public class ControlVista implements ActionListener, KeyListener {

    private VentanaPrincipal ventanaPrincipal;
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
        inicializarVentana();
    }

  
    private void inicializarVentana() {
        ventanaPrincipal = new VentanaPrincipal();
        
        
        ventanaPrincipal.getBtnCargarConfig().addActionListener(this);
        ventanaPrincipal.getBtnSalirInicio().addActionListener(this);
        
        ventanaPrincipal.getBotonIniciar().addActionListener(this);
        ventanaPrincipal.getBotonSalir().addActionListener(this);
        ventanaPrincipal.getPanelJuego().addKeyListener(this);
        
       
        ventanaPrincipal.setVisible(true);
    }

    
    private void solicitarArchivoProperties() {
        int resultado = ventanaPrincipal.abrirSelectorArchivo();
        
        
        if (resultado != 0) {
            ventanaPrincipal.setMensajeEstadoError("Debe seleccionar un archivo");
            return;
        }
        
        File archivo = ventanaPrincipal.getArchivoSeleccionado();
        
        
        if (archivo == null) {
            ventanaPrincipal.setMensajeEstadoError("Debe seleccionar un archivo");
            return;
        }

        procesarArchivo(archivo);
    }

    
    private void procesarArchivo(File archivo) {
        boolean exitoProperties = controlGeneral.procesarArchivoProperties(archivo);

        
        if (!exitoProperties) {
            ventanaPrincipal.setMensajeEstadoError("Error al cargar properties");
            return;
        }

        boolean exitoFrutas = controlGeneral.cargarFrutas();

        
        if (!exitoFrutas) {
            ventanaPrincipal.setMensajeEstadoError("Error al cargar el properties");
            return;
        }

        ventanaPrincipal.setMensajeEstadoExito("Properties cargado correctamente");

        
        Timer delay = new Timer(1000, e -> {
            ventanaPrincipal.mostrarPantallaJuego();
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

        
        if (src == ventanaPrincipal.getBtnCargarConfig()) {
            solicitarArchivoProperties();
            return;
        } else if (src == ventanaPrincipal.getBtnSalirInicio()) {
            System.exit(0);
            return;
        }

        
        if (src == ventanaPrincipal.getBotonIniciar()) {
            iniciarJuego();
        } else if (src == ventanaPrincipal.getBotonSalir()) {
            System.exit(0);
        }
    }

    
    private void iniciarJuego() {
        velX = 0;
        velY = 0;
        juegoActivo = true;

        
        ventanaPrincipal.limpiarFrutas();
        controlGeneral.reiniciarJuego();

        
        centrarPacman();

        
        int anchoPanel = ventanaPrincipal.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipal.getAltoPanelJuego();
        controlGeneral.generarFrutas(anchoPanel, altoPanel);

       
        mostrarFrutas();

        
        ventanaPrincipal.darFocoPanelJuego();
    }

    
    private void mostrarFrutas() {
        List<FrutaVO> frutas = controlGeneral.getFrutasEnJuego();

        for (FrutaVO fruta : frutas) {
            
            Object objetoFruta = ventanaPrincipal.agregarFruta(
                    fruta.getRutaImagen(),
                    fruta.getX(),
                    fruta.getY()
            );

            
            fruta.setLabel(objetoFruta);
        }
    }

    
    private void verificarColisiones() {
        int anchoPacman = ventanaPrincipal.getAnchoPacman();
        int altoPacman = ventanaPrincipal.getAltoPacman();
        
        FrutaVO frutaComida = controlGeneral.verificarColision(
                x, y,
                anchoPacman,
                altoPacman
        );

        
        if (frutaComida != null) {
            ventanaPrincipal.eliminarFruta(frutaComida.getLabel());

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

        String mensaje = "¡Juego Terminado!\n"
                + "Puntaje: " + puntaje + "\n"
                + "Tiempo: " + (tiempo / 1000) + " segundos";

        
        ventanaPrincipal.mostrarDialogo(mensaje, "Juego Terminado", 1);
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
       
        if (ventanaPrincipal == null) {
            return;
        }

        x += velX;
        y += velY;

        int anchoPanel = ventanaPrincipal.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipal.getAltoPanelJuego();
        int anchoPacman = ventanaPrincipal.getAnchoPacman();
        int altoPacman = ventanaPrincipal.getAltoPacman();

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

        ventanaPrincipal.setPosicionPacman(x, y);
    }

    
    public void centrarPacman() {
        int anchoPanel = ventanaPrincipal.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipal.getAltoPanelJuego();

        
        if (anchoPanel == 0 || altoPanel == 0) {
            anchoPanel = 900;
            altoPanel = 630;
        }

        int anchoPacman = ventanaPrincipal.getAnchoPacman();
        int altoPacman = ventanaPrincipal.getAltoPacman();

        
        x = (anchoPanel - anchoPacman) / 2;
        y = (altoPanel - altoPacman) / 2;

        ventanaPrincipal.setPosicionPacman(x, y);
    }

    
    public VentanaPrincipal getVentana() {
        return ventanaPrincipal;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
