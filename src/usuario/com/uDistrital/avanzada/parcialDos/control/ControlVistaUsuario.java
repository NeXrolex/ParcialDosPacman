/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.control;

import usuario.com.uDistrital.avanzada.parcialDos.vista.VentanaPrincipalUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * Maneja la logica de la vista y todo el proceso de acciones, ya se de teclado
 * y botones
 *
 * @author Steven,Jard,Alex
 */
public class ControlVistaUsuario implements ActionListener, KeyListener {

    private VentanaPrincipalUsuario ventana;
    private ControlGeneralUsuario controlGeneral;
    private boolean conectado = false;
    private boolean autenticado = false;
    private String usuarioActual = "";

    /**
     * Constructor que revibe la inyeccion del coontrol general e inicializa la
     * ventana
     *
     * @param controlGeneral ControlGeneral
     */
    public ControlVistaUsuario(ControlGeneralUsuario controlGeneral) {
        this.controlGeneral = controlGeneral;
        inicializarVentana();
    }

    /**
     * Metodo que inicializa el panel principal e intancia la vista
     *
     */
    private void inicializarVentana() {
        this.ventana = new VentanaPrincipalUsuario();

        ventana.getBtnCargarProperties().addActionListener(this);
        ventana.getBtnConectar().addActionListener(this);
        ventana.getBtnIniciarSesion().addActionListener(this);

        ventana.getTxtUsuario().addKeyListener(this);
        ventana.getTxtContrasena().addKeyListener(this);
        
        ventana.getTxtAreaMovimientos().addKeyListener(this);
        ventana.getTxtAreaMovimientos().getInputMap().put(KeyStroke.getKeyStroke("UP"), "none");
        ventana.getTxtAreaMovimientos().getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "none");
        ventana.getTxtAreaMovimientos().getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "none");
        ventana.getTxtAreaMovimientos().getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "none");

        ventana.setBotonConectarHabilitado(false);
        ventana.setVisible(true);
        ventana.mostrarPantallaConexion();
    }

    /**
     * Acciones de los botones principales del usuario
     *
     * @param e Evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == ventana.getBtnCargarProperties()) {
            cargarArchivoProperties();
        } else if (src == ventana.getBtnConectar()) {
            conectarAlServidor();
        } else if (src == ventana.getBtnIniciarSesion()) {
            iniciarSesion();
        }
    }

    /**
     * Carga el archivo de propiedades para continuar con el open close
     *
     */
    private void cargarArchivoProperties() {
        int resultado = ventana.abrirSelectorArchivo();

        if (resultado != 0) {

            ventana.mostrarAdvertencia("Debe seleccionar un archivo");
            return;
        }

        File archivo = ventana.getArchivoSeleccionado();

        if (archivo == null) {

            ventana.mostrarAdvertencia("Debe seleccionar un archivo");
            return;
        }

        // LÓGICA: Procesar archivo
        boolean exito = controlGeneral.procesarArchivoProperties(archivo);

        if (exito) {
            // VISTA: Mostrar éxito
            ventana.mostrarPropertiesCargado(archivo.getName());
            ventana.setBotonConectarHabilitado(true);
        } else {
            // VISTA: Mostrar error
            ventana.mostrarError("Error al cargar el archivo properties");
        }
    }

    /**
     * Delega para conectarse al servidor y despues del accept muestra para
     * iniciar sesion
     *
     */
    private void conectarAlServidor() {

        boolean conexionExitosa = controlGeneral.conectarAlServidor();

        if (!conexionExitosa) {

            ventana.mostrarErrorConexion();
            ventana.estadoErrorConexion();
            return;
        }

        conectado = true;
        ventana.estadoConexionExitosa();
        ventana.mostrarConexionExitosa();
        ventana.mostrarPantallaLogin();
    }

    /**
     * Obtiene los datos del nombre y la contrasena para enviarlo al socket e
     * inicar session
     *
     */
    private void iniciarSesion() {

        String usuario = ventana.getUsuario();
        String contrasena = ventana.getContrasena();

        if (usuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
            ventana.mensajeErrorLogin("Por favor ingrese usuario y contraseña");
            return;
        }
        ventana.mensajeLoginExitoso("Verificando usuario...");
        boolean loginExitoso = controlGeneral.iniciarSesion(usuario, contrasena);

        if (!loginExitoso) {
            ventana.mensajeErrorLogin("Usuario o contraseña incorrectos");
            ventana.mostrarErrorAutenticacion();
            return;
        }

        autenticado = true;
        usuarioActual = usuario;

        ventana.mostrarAutenticacionExitosa(usuario);
        ventana.mostrarPantallaJuego();
        ventana.setUsuarioActual(usuarioActual);
        ventana.estadoJuegoExitoso("Conectado", false);

        ventana.agregarMovimiento("══════════════════════════"
                + "═════════════════════════");
        ventana.agregarMovimiento("  SESIÓN INICIADA");
        ventana.agregarMovimiento("  Usuario: " + usuarioActual);
        ventana.agregarMovimiento("═════════════════════════════════════"
                + "══════════════");
        ventana.agregarMovimiento("");
        ventana.agregarMovimiento("Listo para jugar. Use las"
                + " flechas (↑ ↓ ← →)");
        ventana.agregarMovimiento("");

        ventana.getTxtAreaMovimientos().requestFocus();
    }

    /**
     * Recibe los eventos del teclado para mover al pacman por el movimeinto del
     * las flechas
     *
     * @param e Evento de teclado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!conectado || !autenticado) {
            return;
        }

        String movimiento = "";
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                movimiento = "ARRIBA";
                break;
            case KeyEvent.VK_DOWN:
                movimiento = "ABAJO";
                break;
            case KeyEvent.VK_LEFT:
                movimiento = "IZQUIERDA";
                break;
            case KeyEvent.VK_RIGHT:
                movimiento = "DERECHA";
                break;
            default:
                return;
        }

        controlGeneral.enviarMovimiento(movimiento);
    }

    /**
     * Envia los eventos de las teclas awsd para mover al pacman
     *
     *
     * @param e Evento del teclado
     */
    public void keyTyped(KeyEvent e) {

        // Ignorar si no hay conexión o autenticación
        if (!conectado || !autenticado) {
            return;
        }

        char tecla = e.getKeyChar();
        String movimiento;

        // Mapeo de teclas WASD y flechas
        switch (Character.toLowerCase(tecla)) {
            case 'w':
                movimiento = "ARRIBA";
                break;
            case 's':
                movimiento = "ABAJO";
                break;
            case 'a':
                movimiento = "IZQUIERDA";
                break;
            case 'd':
                movimiento = "DERECHA";
                break;
            default:
                return; // ignora cualquier otra tecla
        }

        // Envía el movimiento al servidor
        controlGeneral.enviarMovimiento(movimiento);

        String movFinal = movimiento;
        // Actualiza visualmente la ventana (solo decorativo, no afecta red)
        SwingUtilities.invokeLater(() -> {
            String timestamp = java.time.LocalTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
            ventana.agregarMovimiento(
                    String.format("[%s] Movimiento: %-10s | Enviado ✓", timestamp, movFinal)
            );
        });
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Obtiene la ventana principal
     *
     * @return
     */
    public VentanaPrincipalUsuario getVentana() {
        return ventana;
    }
}
