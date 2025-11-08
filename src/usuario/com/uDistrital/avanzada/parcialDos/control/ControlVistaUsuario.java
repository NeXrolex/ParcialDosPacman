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

/**
 * @author Steven,Jard,Alex
 */
public class ControlVistaUsuario implements ActionListener, KeyListener {

    private VentanaPrincipalUsuario ventana;
    private ControlGeneralUsuario controlGeneral;
    private boolean conectado = false;
    private boolean autenticado = false;
    private String usuarioActual = "";

    public ControlVistaUsuario(ControlGeneralUsuario controlGeneral) {
        this.controlGeneral = controlGeneral;
        inicializarVentana();
    }

    private void inicializarVentana() {
        ventana = new VentanaPrincipalUsuario();

        ventana.getBtnCargarProperties().addActionListener(this);
        ventana.getBtnConectar().addActionListener(this);
        ventana.getBtnIniciarSesion().addActionListener(this);

        ventana.getTxtUsuario().addKeyListener(this);
        ventana.getTxtContrasena().addKeyListener(this);
        ventana.getTxtAreaMovimientos().addKeyListener(this);

        ventana.setVisible(true);
    }

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

    private void conectarAlServidor() {

        boolean conexionExitosa = controlGeneral.conectarAlServidor();

        if (!conexionExitosa) {

            ventana.mostrarErrorConexion();
            ventana.setEstadoConexion(false);
            return;
        }

        conectado = true;
        ventana.setEstadoConexion(true);

        ventana.mostrarConexionExitosa();

        ventana.mostrarPantallaLogin();
    }

    private void iniciarSesion() {

        String usuario = ventana.getUsuario();
        String contrasena = ventana.getContrasena();

        if (usuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
            ventana.setMensajeLogin("Por favor ingrese usuario y contraseña", true);
            return;
        }

        ventana.setMensajeLogin("Verificando usuario", false);

        boolean loginExitoso = controlGeneral.iniciarSesion(usuario, contrasena);

        if (!loginExitoso) {
            ventana.setMensajeLogin("Usuario o contraseña incorrectos", true);

            ventana.mostrarErrorAutenticacion();
            return;
        }

        autenticado = true;
        usuarioActual = usuario;

        ventana.mostrarAutenticacionExitosa(usuario);

        ventana.mostrarPantallaJuego();
        ventana.setUsuarioActual(usuarioActual);
        ventana.setEstadoJuego(" Conectado ", false);

        ventana.agregarMovimiento("═══════════════════════════════════════════════════");
        ventana.agregarMovimiento("  SESIÓN INICIADA");
        ventana.agregarMovimiento("  Usuario: " + usuarioActual);
        ventana.agregarMovimiento("═══════════════════════════════════════════════════");
        ventana.agregarMovimiento("");
        ventana.agregarMovimiento("Listo para jugar. Use las flechas (↑ ↓ ← →)");
        ventana.agregarMovimiento("");

        ventana.getTxtAreaMovimientos().requestFocus();
    }

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

    public void keyTyped(KeyEvent e) {

        // Ignorar si no hay conexión o autenticación
        if (!conectado || !autenticado) {
            return;
        }

        char tecla = e.getKeyChar();
        String movimiento = "";

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

        // Actualiza visualmente la ventana (solo decorativo, no afecta red)
        String timestamp = java.time.LocalTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")
        );

        ventana.agregarMovimiento(String.format(
                "[%s] Movimiento: %-10s | Enviado ✓",
                timestamp, movimiento
        ));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public VentanaPrincipalUsuario getVentana() {
        return ventana;
    }
}
