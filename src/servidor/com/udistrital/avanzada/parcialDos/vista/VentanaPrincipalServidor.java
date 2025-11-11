/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Ventana principal que integra la pantalla de inicio y el juego Maneja
 * únicamente componentes visuales
 *
 * @author Steven
 */
public class VentanaPrincipalServidor extends JFrame {

    private CardLayout cardLayout;

    private JPanel panelInicio, panelBotones, panelJuego,
            panelContenedor, panelPuntaje, panelCentroPuntaje,
            panelCentro, panelTitulo, panelBotonesInicio;
    private JButton btnCargarProperties, botonSalir, botonIniciar,
            btnSalirInicio;
    private JLabel lblEstado, lblTextPuntaje, labelPacman, lblPuntaje,
            lblTiempo, lblMensaje, lblTitulo, placeholderPuntaje;
    private JFileChooser fileChooser;

    private List<JLabel> labelsFrutas;

    private static final String PANTALLA_INICIO = "INICIO";
    private static final String PANTALLA_JUEGO = "JUEGO";

    public VentanaPrincipalServidor() {
        setTitle("Pac-Man");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        labelsFrutas = new ArrayList<>();

        inicializarComponentes();
        configurarFileChooser();

        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        crearPantallaInicio();
        crearPantallaJuego();

        panelContenedor.add(panelInicio, PANTALLA_INICIO);
        panelContenedor.add(crearPanelJuegoCompleto(), PANTALLA_JUEGO);

        add(panelContenedor);

        try {
            Image icono = Toolkit.getDefaultToolkit().getImage(
                    getClass().getResource("/Specs/ImagesAndGifs/fantasma.png")
            );
            setIconImage(icono);
        } catch (Exception e) {

        }

        mostrarPantallaInicio();
    }

    private void crearPantallaInicio() {
        panelInicio = new JPanel();
        panelInicio.setLayout(new BorderLayout(10, 10));
        panelInicio.setBackground(new Color(0, 0, 20));
        panelInicio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 0, 20));
        lblTitulo = new JLabel("PAC-MAN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        lblTitulo.setBackground(new Color(255, 204, 0));
        lblTitulo.setOpaque(true);
        panelTitulo.add(lblTitulo);

        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(0, 0, 20));

        lblMensaje = new JLabel("Seleccione el archivo de configuración");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMensaje.setForeground(Color.WHITE);
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblEstado = new JLabel(" ");
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 14));
        lblEstado.setForeground(new Color(255, 100, 100));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCentro.add(Box.createVerticalGlue());
        panelCentro.add(lblMensaje);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentro.add(lblEstado);
        panelCentro.add(Box.createVerticalGlue());

        panelBotonesInicio = new JPanel();
        panelBotonesInicio.setBackground(new Color(0, 0, 20));
        panelBotonesInicio.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnCargarProperties = crearBotonJuego("Cargar Properties", new Color(255, 204, 0));
        btnSalirInicio = crearBotonJuego("Salir", new Color(255, 204, 0));

        panelBotonesInicio.add(btnCargarProperties);
        panelBotonesInicio.add(btnSalirInicio);

        panelInicio.add(panelTitulo, BorderLayout.NORTH);
        panelInicio.add(panelCentro, BorderLayout.CENTER);
        panelInicio.add(panelBotonesInicio, BorderLayout.SOUTH);
    }

    private void crearPantallaJuego() {

        panelPuntaje = new JPanel(new BorderLayout());
        panelPuntaje.setBackground(new Color(0, 0, 20));

        lblTiempo = new JLabel("Tiempo: 0", SwingConstants.LEFT);
        lblTiempo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTiempo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        lblTiempo.setForeground(Color.WHITE);

        panelPuntaje.add(lblTiempo, BorderLayout.WEST);
        panelCentroPuntaje = new JPanel(new GridLayout(2, 1, 0, 0));
        panelCentroPuntaje.setBackground(new Color(0, 0, 20));

        lblTextPuntaje = new JLabel("Puntaje", SwingConstants.CENTER);
        lblTextPuntaje.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextPuntaje.setForeground(Color.WHITE);

        lblPuntaje = new JLabel("0", SwingConstants.CENTER);
        lblTextPuntaje.setFont(new Font("Arial", Font.BOLD, 16));
        lblPuntaje.setForeground(Color.WHITE);

        panelCentroPuntaje.add(lblTextPuntaje);
        panelCentroPuntaje.add(lblPuntaje);

        panelPuntaje.add(panelCentroPuntaje, BorderLayout.CENTER);

        /**
         * Este label es para equilibrar el tiempo y el puntaje, pues sin él, el
         * puntaje no se ve centrado y daña la estetica del programa
         */
        placeholderPuntaje = new JLabel("Tiempo: 0");
        placeholderPuntaje.setForeground(new Color(0, 0, 20)); // invisible
        panelPuntaje.add(placeholderPuntaje, BorderLayout.EAST);

        panelJuego = new JPanel(null);
        panelJuego.setBackground(new Color(0, 0, 20));
        panelJuego.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 255), 3),
                "",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 0, 255)
        ));

        labelPacman = new JLabel();
        panelJuego.add(labelPacman);

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(0, 0, 20));

        botonIniciar = crearBotonJuego("Iniciar Juego", new Color(255, 204, 0));
        botonSalir = crearBotonJuego("Salir", new Color(255, 204, 0));

        panelBotones.add(botonIniciar);
        panelBotones.add(botonSalir);
        panelJuego.setFocusable(true);
    }

    private JPanel crearPanelJuegoCompleto() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panelPuntaje, BorderLayout.NORTH);
        panel.add(panelJuego, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        return panel;
    }

    private void configurarFileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo de configuración");

        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Archivos Properties (*.properties)", "properties"
        );
        fileChooser.setFileFilter(filtro);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    private JButton crearBotonJuego(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(200, 45));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(new Color(204, 0, 0), 3));
        return boton;
    }

    public void mostrarPantallaInicio() {
        cardLayout.show(panelContenedor, PANTALLA_INICIO);
        setTitle("Pac-Man - Configuración Inicial del Servidor");
    }

    public void mostrarPantallaJuego() {
        cardLayout.show(panelContenedor, PANTALLA_JUEGO);
        setTitle("Pac-Man");
        panelJuego.requestFocusInWindow();
    }

    public int abrirSelectorArchivo() {
        return fileChooser.showOpenDialog(this);
    }

    public java.io.File getArchivoSeleccionado() {
        return fileChooser.getSelectedFile();
    }

    public void setMensajeEstadoError(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.setForeground(new Color(255, 100, 100));
    }

    public void setMensajeEstadoExito(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.setForeground(new Color(100, 255, 100));
    }

    public Object agregarFruta(String rutaImagen, int x, int y) {
        JLabel labelFruta;

        try {
            ImageIcon iconoFruta = new ImageIcon(
                    getClass().getResource(rutaImagen)
            );

            Image img = iconoFruta.getImage();
            Image imgEscalada = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconoFruta = new ImageIcon(imgEscalada);

            labelFruta = new JLabel(iconoFruta);
            labelFruta.setBounds(x, y, 30, 30);
        } catch (Exception e) {
            labelFruta = new JLabel("🍎");
            labelFruta.setBounds(x, y, 30, 30);
            labelFruta.setFont(new Font("Arial", Font.BOLD, 20));
            labelFruta.setForeground(Color.RED);
        }

        panelJuego.add(labelFruta);
        labelsFrutas.add(labelFruta);
        panelJuego.repaint();

        return labelFruta;
    }

    public void removerLabel(Object label) {
        panelJuego.remove((Component) label);
        panelJuego.repaint();
    }

    public void removerDeLista(Object label) {
        labelsFrutas.remove(label);
    }

    public void limpiarLista() {
        labelsFrutas.clear();
    }
    public List<Object> getFrutasVisuales() {
        return new ArrayList<>(labelsFrutas);
    }

    public void mostrarDialogo(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
    }

    /**
     * Establece el gif del pacman
     *
     * @param rutaGif ruta de el gif
     */
    public void establecerGifPacman(String rutaGif) {

        ImageIcon gif = new ImageIcon(getClass().getResource(rutaGif));
        labelPacman.setIcon(gif);
        labelPacman.setSize(gif.getIconWidth(), gif.getIconHeight());

        int x = (getAnchoPanelJuego() - getAnchoPacman()) / 2;
        int y = (getAltoPanelJuego() - getAltoPacman()) / 2;

        labelPacman.setLocation(x, y);
    }
    public File solicitarArchivoGuardar() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar Puntuaciones");
    fileChooser.setSelectedFile(new File("puntuaciones.dat"));
    
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(
        "Archivos de datos (*.dat)", "dat"
    );
    fileChooser.setFileFilter(filtro);
    
    int resultado = fileChooser.showSaveDialog(this);
    
    if (resultado == JFileChooser.APPROVE_OPTION) {
        File archivo = fileChooser.getSelectedFile();
        // Asegurar extensión .dat
        if (!archivo.getName().endsWith(".dat")) {
            archivo = new File(archivo.getAbsolutePath() + ".dat");
        }
        return archivo;
    }
    
    return null;
}

    public int getAnchoPanelJuego() {
        return panelJuego.getWidth();
    }

    public int getAltoPanelJuego() {
        return panelJuego.getHeight();
    }

    public int getAnchoPacman() {
        return labelPacman.getWidth();
    }

    public int getAltoPacman() {
        return labelPacman.getHeight();
    }

    public void setPosicionPacman(int x, int y) {
        labelPacman.setLocation(x, y);
    }

    public void darFocoPanelJuego() {
        panelJuego.requestFocusInWindow();
    }

    public JButton getBtnCargarConfig() {
        return btnCargarProperties;
    }

    public JButton getBtnSalirInicio() {
        return btnSalirInicio;
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public JPanel getPanelJuego() {
        return panelJuego;
    }

    public JLabel getLblPuntaje() {
        return lblPuntaje;
    }

    public void limpiarPuntaje() {
        lblPuntaje.setText("");
    }

    public JLabel getLblTiempo() {
        return lblTiempo;
    }

    public void limpiarTiempo() {
        lblTiempo.setText("");
    }
}
