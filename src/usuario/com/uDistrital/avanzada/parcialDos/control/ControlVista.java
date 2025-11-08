package usuario.com.uDistrital.avanzada.parcialDos.control;

import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;
import usuario.com.uDistrital.avanzada.parcialDos.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import javax.swing.Timer;

/**
 * Controlador de la vista - maneja SOLO LÓGICA PURA NO contiene referencias a
 * componentes visuales (JLabel, JFileChooser, Color, etc.)
 *
 * @author Steven
 */
public class ControlVista implements ActionListener, KeyListener {

    private VentanaPrincipal ventanaPrincipal;
    private ControlGeneral controlGeneral;

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

        List<String[]> recursos = controlGeneral.cargarGif();

        for (String[] recurso : recursos) {
            String clave = recurso[0];
            String ruta = recurso[1];

            if ("GIF_PACMAN".equalsIgnoreCase(clave)) {
                ventanaPrincipal.establecerGifPacman(ruta);
            }
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
