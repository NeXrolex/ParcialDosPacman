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
    private JPanel panelTituloConexion;
    private JPanel panelBtnConexion;
    private JPanel panelTituloLogin;
    private JPanel panelDatosLogin;
    private JPanel panelBtnLogin;

    private JButton btnCargarProperties;
    private JButton btnConectar;
    private JLabel lblEstadoConexion;
    private JLabel lblTituloConexion;
    private JLabel lblTituloLogin;
    private JLabel lblInstruccionesLogin;
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
        panelConexion = new JPanel(new BorderLayout());
        panelConexion.setBackground(new Color(0, 0, 20));

        panelTituloConexion = new JPanel();
        panelTituloConexion.setBackground(new Color(0, 0, 20));
        panelTituloConexion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 255), 3),
                "",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 0, 255)
        ));
        panelTituloConexion.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        lblTituloConexion = new JLabel("CLIENTE PAC-MAN");
        lblTituloConexion.setFont(new Font("Arial", Font.BOLD, 36));
        lblTituloConexion.setForeground(Color.BLACK);
        lblTituloConexion.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        lblTituloConexion.setBackground(new Color(255, 204, 0));
        lblTituloConexion.setOpaque(true);
        panelTituloConexion.add(lblTituloConexion);

        btnCargarProperties = crearBoton("1. Cargar archivo .properties", new Color(255, 204, 0));
        btnConectar = crearBoton("2. Conectar al servidor", new Color(255, 204, 0));

        lblEstadoConexion = new JLabel("Estado: Desconectado", SwingConstants.CENTER);
        lblEstadoConexion.setFont(new Font("Arial", Font.BOLD, 16));
        lblEstadoConexion.setForeground(Color.RED);

        panelBtnConexion = new JPanel(new GridBagLayout());
        panelBtnConexion.setBorder(BorderFactory.createEmptyBorder(0, 20, 60, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 40, 10, 40);

        gbc.gridy = 0;
        panelBtnConexion.add(btnCargarProperties, gbc);

        gbc.gridy = 1;
        panelBtnConexion.add(btnConectar, gbc);

        gbc.gridy = 2;
        panelBtnConexion.add(lblEstadoConexion, gbc);
        panelBtnConexion.setBackground(new Color(0, 0, 20));

        panelConexion.add(panelTituloConexion, BorderLayout.NORTH);
        panelConexion.add(panelBtnConexion, BorderLayout.CENTER);
    }

    private void inicializarPanelLogin() {
        panelLogin = new JPanel(new BorderLayout());
        panelLogin.setBackground(new Color(0, 0, 20));

        panelTituloLogin = new JPanel();
        panelTituloLogin.setBackground(new Color(0, 0, 20));
        panelTituloLogin.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        lblTituloLogin = new JLabel("Autenticación de Usuario");
        lblTituloLogin.setFont(new Font("Arial", Font.BOLD, 36));
        lblTituloLogin.setForeground(Color.BLACK);
        lblTituloLogin.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        lblTituloLogin.setBackground(new Color(255, 204, 0));
        lblTituloLogin.setOpaque(true);
        panelTituloLogin.add(lblTituloLogin);

        panelDatosLogin = new JPanel(new GridBagLayout());
        panelDatosLogin.setBackground(new Color(0, 0, 20));
        panelDatosLogin.setBorder(BorderFactory.createEmptyBorder(60, 10, 0, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel lblConexionExitosa = new JLabel("Conexión establecida con el servidor", SwingConstants.CENTER);
        lblConexionExitosa.setFont(new Font("Arial", Font.BOLD, 16));
        lblConexionExitosa.setForeground(new Color(34, 139, 34));
        panelDatosLogin.add(lblConexionExitosa, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        lblInstruccionesLogin = new JLabel("Ingrese su usuario y contraseña", SwingConstants.CENTER);
        lblInstruccionesLogin.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccionesLogin.setForeground(Color.WHITE);
        panelDatosLogin.add(lblInstruccionesLogin, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setForeground(Color.WHITE);
        panelDatosLogin.add(lblUsuario, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        txtUsuario = new JTextField(15);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        panelDatosLogin.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 16));
        lblContrasena.setForeground(Color.WHITE);
        panelDatosLogin.add(lblContrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        txtContrasena = new JPasswordField(15);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        panelDatosLogin.add(txtContrasena, gbc);

        panelBtnLogin = new JPanel(new BorderLayout());
        panelBtnLogin.setBackground(new Color(0, 0, 20));
        panelBtnLogin.setBorder(BorderFactory
                .createEmptyBorder(30, 10, 10, 10));

        btnIniciarSesion = crearBoton("Iniciar Sesión", new Color(255, 204, 0));

        lblMensajeLogin = new JLabel("");
        lblMensajeLogin.setFont(new Font("Arial", Font.BOLD, 16));
        lblMensajeLogin.setHorizontalAlignment(SwingConstants.CENTER);

        panelBtnLogin.add(btnIniciarSesion, BorderLayout.NORTH);
        panelBtnLogin.add(lblMensajeLogin, BorderLayout.SOUTH);

        panelLogin.add(panelTituloLogin, BorderLayout.NORTH);
        panelLogin.add(panelDatosLogin, BorderLayout.CENTER);
        panelLogin.add(panelBtnLogin, BorderLayout.SOUTH);

    }

    private void inicializarPanelJuego() {
        panelJuego = new JPanel(new BorderLayout());
        panelJuego.setBackground(new Color(0, 0, 20));

        JLabel lblTitulo = new JLabel("Pac-Man - Modo Juego");

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(70, 130, 180));
        panelJuego.add(lblTitulo);

        lblUsuarioActual = new JLabel("Usuario: ");
        lblUsuarioActual.setFont(new Font("Arial", Font.BOLD, 13));
        panelJuego.add(lblUsuarioActual);

        lblEstadoJuego = new JLabel("Estado: ✓ Conectado y Autenticado");
        lblEstadoJuego.setFont(new Font("Arial", Font.BOLD, 13));
        lblEstadoJuego.setForeground(new Color(34, 139, 34));
        panelJuego.add(lblEstadoJuego);

        JLabel lblMovimientos = new JLabel("Registro de Movimientos:");
        lblMovimientos.setFont(new Font("Arial", Font.BOLD, 14));
        panelJuego.add(lblMovimientos);

        txtAreaMovimientos = new JTextArea();
        txtAreaMovimientos.setEditable(false);
        txtAreaMovimientos.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtAreaMovimientos.setBackground(Color.WHITE);
        txtAreaMovimientos.setLineWrap(true);
        txtAreaMovimientos.setWrapStyleWord(true);

        scrollMovimientos = new JScrollPane(txtAreaMovimientos);
        scrollMovimientos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelJuego.add(scrollMovimientos);

        JLabel lblControles = new JLabel("Use las teclas de flecha"
                + "(↑ ↓ ← →) para mover su personaje");
        lblControles.setFont(new Font("Arial", Font.ITALIC, 12));
        lblControles.setForeground(new Color(100, 100, 100));
        panelJuego.add(lblControles);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(300, 45));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory
                .createLineBorder(new Color(204, 0, 0), 3));
        return boton;
    }

    private void ensamblarComponentes() {
        panelContenedor.add(panelConexion, "CONEXION");
        panelContenedor.add(panelLogin, "LOGIN");
        panelContenedor.add(panelJuego, "JUEGO");
        add(panelContenedor);
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
        txtAreaMovimientos.setCaretPosition(txtAreaMovimientos
                .getDocument().getLength());
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
