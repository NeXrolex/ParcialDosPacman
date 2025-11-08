package usuario.com.uDistrital.avanzada.parcialDos.control;

import usuario.com.uDistrital.avanzada.parcialDos.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

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

        ventanaPrincipal.setMensajeEstadoExito("Properties cargado correctamente");
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
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!juegoActivo) {
            return;
        }

        int c = e.getKeyCode();

        if (c == KeyEvent.VK_UP || c == KeyEvent.VK_W) {
            velX = 0;
            velY = -movPixeles;
        } else if (c == KeyEvent.VK_DOWN || c == KeyEvent.VK_S) {
            velX = 0;
            velY = movPixeles;
        } else if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_A) {
            velX = -movPixeles;
            velY = 0;
        } else if (c == KeyEvent.VK_RIGHT || c == KeyEvent.VK_D) {
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
