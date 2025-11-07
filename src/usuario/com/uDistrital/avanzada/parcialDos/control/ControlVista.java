package usuario.com.uDistrital.avanzada.parcialDos.control;

import usuario.com.uDistrital.avanzada.parcialDos.vista.VentanaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author Steven
 */
public class ControlVista implements ActionListener, KeyListener {

    private VentanaJuego vista;
    private ControlGeneral controlGeneral;

    private int velX;
    private int velY;

    private int x = 0;
    private int y = 0;
    private int maxX;
    private int maxY;
    private int movPixeles = 24;
    private Timer timer;

    public ControlVista(ControlGeneral general) {
        this.controlGeneral = general;
        this.vista = new VentanaJuego();
        this.vista.setVisible(true);
        inicializarEventos();
        inicializarTimer();

    }

    private void inicializarTimer() {
        this.timer = new Timer(50, e -> moverPacman());
        this.timer.start();
    }

    private void inicializarEventos() {
        this.vista.getBotonIniciar().addActionListener(this);
        this.vista.getBotonSalir().addActionListener(this);
        this.vista.getPanelJuego().addKeyListener(this);
        this.vista.getPanelJuego().setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == vista.getBotonIniciar()) {
            velX = 0;
            velY = 0;
            centrarPacman();
            vista.getPanelJuego().requestFocusInWindow();
        } else if (src == vista.getBotonSalir()) {
            System.exit(0);
        }
    }

    public void keyPressed(KeyEvent e) {
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
        x += velX;
        y += velY;

        maxX = vista.getPanelJuego().getWidth() - vista.getLabelPacman().getWidth();
        maxY = vista.getPanelJuego().getHeight() - vista.getLabelPacman().getHeight();

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
        vista.getLabelPacman().setLocation(x, y);
    }

    public void centrarPacman() {
        int anchoPanel = vista.getPanelJuego().getWidth();
        int altoPanel = vista.getPanelJuego().getHeight();
        int anchoPacman = vista.getLabelPacman().getWidth();
        int altoPacman = vista.getLabelPacman().getHeight();

        x = (anchoPanel - anchoPacman) / 2;
        y = (altoPanel - altoPacman) / 2;

        vista.getLabelPacman().setLocation(x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
