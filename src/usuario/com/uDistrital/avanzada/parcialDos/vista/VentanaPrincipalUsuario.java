/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package usuario.com.uDistrital.avanzada.parcialDos.vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 *
 * @author Steven,Jard,Alex
 */
public class VentanaPrincipalUsuario extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelContenedor;

    private JPanel panelConexion;
    private JButton btnCargarProperties;
    private JButton btnConectar;
    private JLabel lblEstadoConexion;
    private JFileChooser fileChooser;

    private JPanel panelLogin;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;
    private JLabel lblMensajeLogin;

    private JPanel panelJuego;
    private JTextArea txtAreaMovimientos;
    private JScrollPane scrollMovimientos;
    private JLabel lblUsuarioActual;
    private JLabel lblEstadoJuego;

    public VentanaPrincipalUsuario() {
        inicializarVentana();
        inicializarComponentes();
        ensamblarComponentes();
    }

    private void inicializarVentana() {
        setTitle("Cliente Pac-Man - Usuario");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        inicializarPanelConexion();
        inicializarPanelLogin();
        inicializarPanelJuego();

        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo Properties");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos Properties (*.properties)", "properties"
        );
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    private void inicializarPanelConexion() {
        panelConexion = new JPanel();
        panelConexion.setLayout(null);
        panelConexion.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("Cliente Pac-Man");
        lblTitulo.setBounds(150, 30, 250, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(70, 130, 180));
        panelConexion.add(lblTitulo);

        JLabel lblInstrucciones = new JLabel("");
        lblInstrucciones.setBounds(100, 90, 350, 50);
        lblInstrucciones.setFont(new Font("Arial", Font.PLAIN, 13));
        lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
        panelConexion.add(lblInstrucciones);

        btnCargarProperties = new JButton("1. Cargar Archivo Properties");
        btnCargarProperties.setBounds(100, 170, 350, 45);
        btnCargarProperties.setFont(new Font("Arial", Font.BOLD, 14));
        btnCargarProperties.setBackground(new Color(70, 130, 180));
        btnCargarProperties.setForeground(Color.WHITE);
        btnCargarProperties.setFocusPainted(false);
        panelConexion.add(btnCargarProperties);

        btnConectar = new JButton("2. Conectar al Servidor");
        btnConectar.setBounds(100, 240, 350, 45);
        btnConectar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConectar.setBackground(new Color(34, 139, 34));
        btnConectar.setForeground(Color.WHITE);
        btnConectar.setFocusPainted(false);
        btnConectar.setEnabled(false);
        panelConexion.add(btnConectar);

        lblEstadoConexion = new JLabel("Estado: Desconectado");
        lblEstadoConexion.setBounds(100, 310, 350, 30);
        lblEstadoConexion.setFont(new Font("Arial", Font.BOLD, 14));
        lblEstadoConexion.setForeground(Color.RED);
        lblEstadoConexion.setHorizontalAlignment(SwingConstants.CENTER);
        panelConexion.add(lblEstadoConexion);

        JTextArea txtInfo = new JTextArea();
        txtInfo.setBounds(75, 370, 400, 200);
        txtInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        txtInfo.setBackground(new Color(255, 255, 230));
        txtInfo.setEditable(false);
        txtInfo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 150), 2));
        panelConexion.add(txtInfo);
    }

    private void inicializarPanelLogin() {
        panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("Autenticación de Usuario");
        lblTitulo.setBounds(120, 50, 350, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(34, 139, 34));
        panelLogin.add(lblTitulo);

        JLabel lblConexionExitosa = new JLabel("Conexión establecida con el servidor");
        lblConexionExitosa.setBounds(120, 100, 350, 30);
        lblConexionExitosa.setFont(new Font("Arial", Font.BOLD, 13));
        lblConexionExitosa.setForeground(new Color(34, 139, 34));
        panelLogin.add(lblConexionExitosa);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(80, 170, 120, 25);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panelLogin.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(200, 170, 270, 35);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        panelLogin.add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(80, 230, 120, 25);
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 14));
        panelLogin.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(200, 230, 270, 35);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        panelLogin.add(txtContrasena);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(150, 300, 250, 45);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 15));
        btnIniciarSesion.setBackground(new Color(70, 130, 180));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFocusPainted(false);
        panelLogin.add(btnIniciarSesion);

        lblMensajeLogin = new JLabel("");
        lblMensajeLogin.setBounds(80, 370, 390, 30);
        lblMensajeLogin.setFont(new Font("Arial", Font.BOLD, 12));
        lblMensajeLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelLogin.add(lblMensajeLogin);

        JTextArea txtInstrucciones = new JTextArea();
        txtInstrucciones.setText("Ingrese su usuario y contraseña");
        txtInstrucciones.setBounds(100, 430, 350, 100);
        txtInstrucciones.setFont(new Font("Arial", Font.PLAIN, 12));
        txtInstrucciones.setBackground(new Color(230, 245, 255));
        txtInstrucciones.setEditable(false);
        txtInstrucciones.setBorder(BorderFactory.createLineBorder(new Color(150, 200, 220), 2));
        panelLogin.add(txtInstrucciones);
    }

    private void inicializarPanelJuego() {
        panelJuego = new JPanel();
        panelJuego.setLayout(null);
        panelJuego.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("Pac-Man - Modo Juego");
        lblTitulo.setBounds(150, 20, 300, 35);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(70, 130, 180));
        panelJuego.add(lblTitulo);

        lblUsuarioActual = new JLabel("Usuario: ");
        lblUsuarioActual.setBounds(50, 70, 450, 25);
        lblUsuarioActual.setFont(new Font("Arial", Font.BOLD, 13));
        panelJuego.add(lblUsuarioActual);

        lblEstadoJuego = new JLabel("Estado: ✓ Conectado y Autenticado");
        lblEstadoJuego.setBounds(50, 100, 450, 25);
        lblEstadoJuego.setFont(new Font("Arial", Font.BOLD, 13));
        lblEstadoJuego.setForeground(new Color(34, 139, 34));
        panelJuego.add(lblEstadoJuego);

        JLabel lblMovimientos = new JLabel("Registro de Movimientos:");
        lblMovimientos.setBounds(50, 140, 450, 25);
        lblMovimientos.setFont(new Font("Arial", Font.BOLD, 14));
        panelJuego.add(lblMovimientos);

        txtAreaMovimientos = new JTextArea();
        txtAreaMovimientos.setEditable(false);
        txtAreaMovimientos.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtAreaMovimientos.setBackground(Color.WHITE);
        txtAreaMovimientos.setLineWrap(true);
        txtAreaMovimientos.setWrapStyleWord(true);

        scrollMovimientos = new JScrollPane(txtAreaMovimientos);
        scrollMovimientos.setBounds(50, 170, 450, 380);
        scrollMovimientos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelJuego.add(scrollMovimientos);

        JLabel lblControles = new JLabel("Use las teclas de flecha (↑ ↓ ← →) para mover su personaje");
        lblControles.setBounds(70, 560, 410, 25);
        lblControles.setFont(new Font("Arial", Font.ITALIC, 12));
        lblControles.setForeground(new Color(100, 100, 100));
        panelJuego.add(lblControles);
    }

    private void ensamblarComponentes() {
        panelContenedor.add(panelConexion, "CONEXION");
        panelContenedor.add(panelLogin, "LOGIN");
        panelContenedor.add(panelJuego, "JUEGO");

        add(panelContenedor);
        cardLayout.show(panelContenedor, "CONEXION");
    }

    public void mostrarPantallaConexion() {
        cardLayout.show(panelContenedor, "CONEXION");
    }

    public void mostrarPantallaLogin() {
        cardLayout.show(panelContenedor, "LOGIN");
        txtUsuario.requestFocus();
    }

    public void mostrarPantallaJuego() {
        cardLayout.show(panelContenedor, "JUEGO");
        txtAreaMovimientos.requestFocus();
    }

    /**
     * Muestra diálogo de advertencia
     *
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra diálogo de error
     *
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra diálogo de información/éxito
     *
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra diálogo de éxito al cargar properties
     *
     * @param nombreArchivo Nombre del archivo cargado
     */
    public void mostrarPropertiesCargado(String nombreArchivo) {
        JOptionPane.showMessageDialog(
                this,
                "Properties cargado correctamente\n\nArchivo: " + nombreArchivo
                + "\n\nYa puede conectarse al servidor",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra diálogo de conexión exitosa
     */
    public void mostrarConexionExitosa() {
        JOptionPane.showMessageDialog(
                this,
                "¡Conexión establecida con éxito!\n\n"
                + "Ahora se le solicitarán sus credenciales\n"
                + "para acceder al juego",
                "Conectado con Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra diálogo de error de conexión
     */
    public void mostrarErrorConexion() {
        JOptionPane.showMessageDialog(
                this,
                "No se pudo conectar al servidor\n\n"
                + "Posibles causas:\n"
                + "• El servidor no está activo\n"
                + "• La IP o puerto en el properties son incorrectos\n"
                + "• Problemas de red o firewall",
                "Error de Conexión",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra diálogo de autenticación exitosa
     *
     * @param usuario Nombre del usuario autenticado
     */
    public void mostrarAutenticacionExitosa(String usuario) {
        JOptionPane.showMessageDialog(
                this,
                "¡Autenticación exitosa!\n\n"
                + "Bienvenido: " + usuario + "\n\n"
                + "Ya puede comenzar a jugar.\n"
                + "Use las teclas de flecha para mover su personaje.",
                "Acceso Concedido",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra diálogo de error de autenticación
     */
    public void mostrarErrorAutenticacion() {
        JOptionPane.showMessageDialog(
                this,
                "Las credenciales ingresadas son incorrectas\n\n"
                + "Verifique su usuario y contraseña e intente nuevamente",
                "Error de Autenticación",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra diálogo de pérdida de conexión
     */
    public void mostrarConexionPerdida() {
        JOptionPane.showMessageDialog(
                this,
                "Se perdió la conexión con el servidor\n\n"
                + "La aplicación volverá a la pantalla inicial",
                "Conexión Perdida",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public int abrirSelectorArchivo() {
        return fileChooser.showOpenDialog(this);
    }

    public File getArchivoSeleccionado() {
        return fileChooser.getSelectedFile();
    }

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

    public void setUsuarioActual(String usuario) {
        lblUsuarioActual.setText("Usuario: " + usuario);
    }

    public void agregarMovimiento(String mensaje) {
        txtAreaMovimientos.append(mensaje + "\n");
        txtAreaMovimientos.setCaretPosition(txtAreaMovimientos.getDocument().getLength());
    }

    public void limpiarMovimientos() {
        txtAreaMovimientos.setText("");
    }

    public void setEstadoConexion(boolean conectado) {
        if (conectado) {
            lblEstadoConexion.setText("Estado:  Conectado al Servidor");
            lblEstadoConexion.setForeground(new Color(34, 139, 34));
        } else {
            lblEstadoConexion.setText("Estado:  Desconectado");
            lblEstadoConexion.setForeground(Color.RED);
        }
    }

    public void setEstadoJuego(String mensaje, boolean esError) {
        lblEstadoJuego.setText("Estado: " + mensaje);
        if (esError) {
            lblEstadoJuego.setForeground(Color.RED);
        } else {
            lblEstadoJuego.setForeground(new Color(34, 139, 34));
        }
    }

    public void setMensajeLogin(String mensaje, boolean esError) {
        lblMensajeLogin.setText(mensaje);
        if (esError) {
            lblMensajeLogin.setForeground(Color.RED);
        } else {
            lblMensajeLogin.setForeground(new Color(34, 139, 34));
        }
    }

    public void setBotonConectarHabilitado(boolean habilitado) {
        btnConectar.setEnabled(habilitado);
    }

    // ==================== GETTERS PARA LISTENERS ====================
    public JButton getBtnCargarProperties() {
        return btnCargarProperties;
    }

    public JButton getBtnConectar() {
        return btnConectar;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public JTextArea getTxtAreaMovimientos() {
        return txtAreaMovimientos;
    }
}
