/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package usuario.com.uDistrital.avanzada.parcialDos.vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Ventana encargada de mostrar la panatalla del usuario implementato
 * cardslayout para lograr un cambio en el panel
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

    private JButton btnCargarProperties;
    private JButton btnConectar;
    private JLabel lblEstadoConexion;
    private JLabel lblTituloConexion;
    private JLabel lblTituloLogin;
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

    /**
     * Metodo encargado de inicializar la ventana del usuario y los componentes}
     * que la conforman
     */
    public VentanaPrincipalUsuario() {
        inicializarVentana();
        inicializarComponentes();
        ensamblarComponentes();
    }

    /**
     * metodo encargado de establecer las configuraciones de la ventana
     */
    private void inicializarVentana() {
        setTitle("Cliente Pac-Man - Usuario");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * metodo que establece los componentes de la ventana
     */
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

    /**
     * Establece el panel de la conexion
     */
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

    /**
     * Establece el panel que permite hacer login al usuario
     */
    private void inicializarPanelLogin() {
        panelLogin = new JPanel(new BorderLayout());
        panelLogin.setBackground(new Color(0, 0, 20));

        panelTituloLogin = new JPanel();
        panelTituloLogin.setBackground(new Color(0, 0, 20));

        lblTituloLogin = new JLabel("Autenticación de Usuario", SwingConstants.CENTER);
        lblTituloLogin.setFont(new Font("Arial", Font.BOLD, 36));
        lblTituloLogin.setForeground(Color.BLACK);
        lblTituloLogin.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        lblTituloLogin.setBackground(new Color(255, 204, 0));
        lblTituloLogin.setOpaque(true);

        panelTituloLogin.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panelTituloLogin.add(lblTituloLogin);
        panelLogin.add(panelTituloLogin, BorderLayout.NORTH);

        panelDatosLogin = new JPanel(new GridBagLayout());
        panelDatosLogin.setBackground(new Color(0, 0, 20));

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

        gbc.gridy++;
        JLabel lblInstrucciones = new JLabel("Ingrese su usuario y contraseña", SwingConstants.CENTER);
        lblInstrucciones.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstrucciones.setForeground(Color.WHITE);
        panelDatosLogin.add(lblInstrucciones, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setForeground(Color.WHITE);
        panelDatosLogin.add(lblUsuario, gbc);

        gbc.gridx = 1;
        txtUsuario = new JTextField(15);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        panelDatosLogin.add(txtUsuario, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 16));
        lblContrasena.setForeground(Color.WHITE);
        panelDatosLogin.add(lblContrasena, gbc);

        gbc.gridx = 1;
        txtContrasena = new JPasswordField(15);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        panelDatosLogin.add(txtContrasena, gbc);

        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx = 0;
        btnIniciarSesion = crearBoton("Iniciar Sesión", new Color(255, 204, 0));
        panelDatosLogin.add(btnIniciarSesion, gbc);

        gbc.gridy++;
        lblMensajeLogin = new JLabel("", SwingConstants.CENTER);
        lblMensajeLogin.setFont(new Font("Arial", Font.BOLD, 16));
        panelDatosLogin.add(lblMensajeLogin, gbc);

        panelDatosLogin.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        panelLogin.add(panelDatosLogin, BorderLayout.CENTER);

    }

    /**
     * establece el panel que permite la jugabilidad
     */
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
                + "(↑ ↓ ← →) para mover su personaje", SwingConstants.CENTER);
        lblControles.setFont(new Font("Arial", Font.ITALIC, 12));
        lblControles.setForeground(new Color(100, 100, 100));
        panelJuego.add(lblControles);
    }

    /**
     * Se encarga de crear un boton
     *
     * @param texto texto correspondiente del boton
     * @param color color del boton
     * @return un boton
     */
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

    /**
     * Ensambla los paneles que se van a mostrar y asigna una palabra clave para
     * reconocelos con el CardLayout
     */
    private void ensamblarComponentes() {
        panelContenedor.add(panelConexion, "CONEXION");
        panelContenedor.add(panelLogin, "LOGIN");
        panelContenedor.add(panelJuego, "JUEGO");
        add(panelContenedor);
    }

    /**
     * muestra la pantalla de conexion al servidor
     */
    public void mostrarPantallaConexion() {
        cardLayout.show(panelContenedor, "CONEXION");
    }

    /**
     * Muestra la pantalla de autenticacion o login
     */
    public void mostrarPantallaLogin() {
        cardLayout.show(panelContenedor, "LOGIN");
        txtUsuario.requestFocus();
    }

    /**
     * Mustra la pantalla de juego(en donde se dan las direcciones)
     */
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

    /**
     * abre el selector de archivos para que el usuario elija el properties
     *
     * @return
     */
    public int abrirSelectorArchivo() {
        return fileChooser.showOpenDialog(this);
    }

    /**
     * Obtiene el archivo seleccionado
     *
     * @return archivo seleccionado
     */
    public File getArchivoSeleccionado() {
        return fileChooser.getSelectedFile();
    }
    /**
     * Obtiene el nombre del usuario en el campo de texto 
     * @return String con el nombre del usuario
     */
    public String getUsuario() {
        return txtUsuario.getText();
    }
    /**
     * obtiene la contraseña de el usuario
     * @return String con la contraseña
     */
    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }
    /**
     * Establece el nombre del usuario
     * @param usuario 
     */
    public void setUsuarioActual(String usuario) {
        lblUsuarioActual.setText("Usuario: " + usuario);
    }
    /**
     * Agrega un registro de movimiento
     * @param mensaje 
     */
    public void agregarMovimiento(String mensaje) {
        txtAreaMovimientos.append(mensaje + "\n");
        txtAreaMovimientos.setCaretPosition(txtAreaMovimientos
                .getDocument().getLength());
    }
    /**
     * Limpia el area de texto
     */
    public void limpiarMovimientos() {
        txtAreaMovimientos.setText("");
    }
    /**
     * Actualiza la etiqueta de de estado de conexion
     */
    public void estadoConexionExitosa() {
        lblEstadoConexion.setText("Estado: Conectado al Servidor");
        lblEstadoConexion.setForeground(new Color(34, 139, 34));
    }
    /**
     * Actualiza la etiqueta de estado a error de conexión
     * Cambia el color a rojo y el texto apropiado
     */
    public void estadoErrorConexion() {
        lblEstadoConexion.setText("Estado: Desconectado");
        lblEstadoConexion.setForeground(Color.RED);
    }
    /**
     * Actualiza el estado del juego
     * @param mensaje Mensaje de estado
     * @param esError 
     */
    public void estadoJuegoExitoso(String mensaje, boolean esError) {
        lblEstadoJuego.setText("Estado: " + mensaje);
        lblEstadoJuego.setForeground(new Color(34, 139, 34));
    }
    /**
     * Muestra exito en la pantalla login
     * @param mensaje Mensaje de estado
     * @param esError 
     */
    public void estadoErrorJuego(String mensaje, boolean esError) {
        lblEstadoJuego.setText("Estado: " + mensaje);
        lblEstadoJuego.setForeground(Color.RED);
    }
    /**
     * Muestra una pantalla de exito en la pantalla de login
     * @param mensaje  mensaje de exito
     */
    public void mensajeLoginExitoso(String mensaje) {
        lblMensajeLogin.setText(mensaje);
        lblMensajeLogin.setForeground(new Color(34, 139, 34));
    }
    /**
     * Muestra una pantalla de error en el login
     * @param mensaje mensaje de error
     */
    public void mensajeErrorLogin(String mensaje) {
        lblMensajeLogin.setText(mensaje);
        lblMensajeLogin.setForeground(Color.RED);
    }
    /**
     * Habilita o desabilita el boton de conectar al servidor
     * @param habilitado 
     */
    public void setBotonConectarHabilitado(boolean habilitado) {
        btnConectar.setEnabled(habilitado);
    }
    /**
     * Obtiene el boton de cargar properties
     * @return boton de carga de archivos 
     */
    public JButton getBtnCargarProperties() {
        return btnCargarProperties;
    }
    /**
     * Obtiene el boton de conectar con el servidor
     * @return boton de conectar
     */
    public JButton getBtnConectar() {
        return btnConectar;
    }
    /**
     * Obtiene el boton de iniciar sesion
     * @return boton de iniciar sesion
     */
    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }
    /**
     * Obtiene el campo de texto del usuario
     * @return txt usuario
     */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }
    /**
     * Obtiene la contraseña del usuario
     * @return contraseña dek usuario
     */
    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }
    /**
     * Obtiene el area de movimientos del juego
     * @return area de juego
     */
    public JTextArea getTxtAreaMovimientos() {
        return txtAreaMovimientos;
    }

}
