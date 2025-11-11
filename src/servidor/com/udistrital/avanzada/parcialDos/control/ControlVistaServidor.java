/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.Timer;
import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;
import servidor.com.udistrital.avanzada.parcialDos.vista.VentanaPrincipalServidor;

/**
 *
 * @author santi
 */
public class ControlVistaServidor implements ActionListener {

    private ControlGeneralServidor cGeneralServidor;
    private VentanaPrincipalServidor ventanaPrincipalServidor;

    private int velX;
    private int velY;
    private int x = 0;
    private int y = 0;
    private int maxX;
    private int maxY;
    private int movPixeles = 16;
    private Timer timer;
    private boolean juegoActivo = false;
    private Timer timerTiempo;
    
    /**
     * Constructor que recibe la inyeccion del control general
     * e incia la ventana
     * 
     * @param cGeneralServidor ConTrol General del Servidor
     */
    public ControlVistaServidor(ControlGeneralServidor cGeneralServidor) {
        this.cGeneralServidor = cGeneralServidor;
        inicializarVentana();
    }
    
    /**
     * Inicia la ventana y configura los botones 
     * que se usan en la vista
     * 
     */
    private void inicializarVentana() {
        ventanaPrincipalServidor = new VentanaPrincipalServidor();

        ventanaPrincipalServidor.getBtnCargarConfig().addActionListener(this);
        ventanaPrincipalServidor.getBtnSalirInicio().addActionListener(this);

        ventanaPrincipalServidor.getBotonIniciar().addActionListener(this);
        ventanaPrincipalServidor.getBotonSalir().addActionListener(this);

        ventanaPrincipalServidor.setVisible(true);
    }
    
    /**
     * pide el archivo de propiedaes para cumplir con el 
     * open close
     * 
     */
    private void solicitarArchivoProperties() {
        int resultado = ventanaPrincipalServidor.abrirSelectorArchivo();

        if (resultado != 0) {
            ventanaPrincipalServidor.setMensajeEstadoError("Debe seleccionar"
                    + " un archivo");
            return;
        }

        File archivo = ventanaPrincipalServidor.getArchivoSeleccionado();

        if (archivo == null) {
            ventanaPrincipalServidor.setMensajeEstadoError("Debe seleccionar"
                    + " un archivo");
            return;
        }

        procesarArchivo(archivo);//Delega a un metodo privado
    }
    
    /**
     * Metodo privado que delega para cargar los properties
     * y comprabar que sea el archivo correcto
     * 
     * @param archivo 
     */
    private void procesarArchivo(File archivo) {
        boolean exitoProperties = cGeneralServidor
                .procesarArchivoProperties(archivo);

        if (!exitoProperties) {
            ventanaPrincipalServidor.setMensajeEstadoError("Error al"
                    + " cargar properties");
            return;
        }

        boolean exitoFrutas = cGeneralServidor.cargarFrutas();

        if (!exitoFrutas) {
            ventanaPrincipalServidor.setMensajeEstadoError("Error al cargar"
                    + " el properties");
            return;
        }
        cGeneralServidor.iniciarServidor();
        List<String[]> recursos = cGeneralServidor.cargarGif();

        for (String[] recurso : recursos) {
            String clave = recurso[0];
            String ruta = recurso[1];

            if ("GIF_PACMAN".equalsIgnoreCase(clave)) {
                ventanaPrincipalServidor.establecerGifPacman(ruta);
                //Desde el dao toma la ruta y la establece
            }
        }

        ventanaPrincipalServidor.setMensajeEstadoExito("Properties cargado"
                + " correctamente");

        Timer delay = new Timer(1000, e -> ventanaPrincipalServidor
                .mostrarPantallaJuego());
        delay.setRepeats(false);
        delay.start();
    }
    
    /**
     * Acciones de los botones 
     * 
     * @param e evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == ventanaPrincipalServidor.getBtnCargarConfig()) {
            solicitarArchivoProperties();
            return;
        } else if (src == ventanaPrincipalServidor.getBtnSalirInicio()) {
            System.exit(0);
            return;
        }

        if (src == ventanaPrincipalServidor.getBotonIniciar()) {
            iniciarJuego();
        } else if (src == ventanaPrincipalServidor.getBotonSalir()) {
            System.exit(0);
        }
    }
    
    /**
     * Inicia el juego de pacman
     * 
     */
    private void iniciarJuego() {

        velX = 0;
        velY = 0;
        juegoActivo = true;

        limpiarFrutas();
        cGeneralServidor.reiniciarJuego();
        actualizarTiempo();

        centrarPacman();

        int anchoPanel = ventanaPrincipalServidor.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipalServidor.getAltoPanelJuego();
        cGeneralServidor.generarFrutas(anchoPanel, altoPanel);

        mostrarFrutas();

        ventanaPrincipalServidor.darFocoPanelJuego();
        if (timerTiempo != null && timerTiempo.isRunning()) {
            timerTiempo.stop();
        }

        timerTiempo = new Timer(1000, e -> actualizarTiempo());
        timerTiempo.start();
    }
    
    /**
     * Limpia laas frutas en el campo
     * 
     */
    private void limpiarFrutas() {
        List<Object> frutasVisuales = ventanaPrincipalServidor
                .getFrutasVisuales();
        for (Object fruta : frutasVisuales) {
            ventanaPrincipalServidor.removerLabel(fruta);
        }
        ventanaPrincipalServidor.limpiarLista();
    }

    private void mostrarFrutas() {
        List<FrutaVO> frutas = cGeneralServidor.getFrutasEnJuego();

        for (FrutaVO fruta : frutas) {

            Object objetoFruta = ventanaPrincipalServidor.agregarFruta(
                    fruta.getRutaImagen(),
                    fruta.getX(),
                    fruta.getY()
            );

            fruta.setLabel(objetoFruta);
        }
    }
    
    /**
     * Verifica las colociones entres elementos para
     * que recoger las frutas
     * 
     */
    private void verificarColisiones() {
        int anchoPacman = ventanaPrincipalServidor.getAnchoPacman();
        int altoPacman = ventanaPrincipalServidor.getAltoPacman();

        FrutaVO frutaComida = cGeneralServidor.verificarColision(
                x, y,
                anchoPacman,
                altoPacman
        );

        if (frutaComida != null) {
            eliminarFruta(frutaComida.getLabel());
            actualizarPuntaje();
            if (cGeneralServidor.juegoTerminado()) {
                finalizarJuego();
            }
        }
    }
   
    /**
     * elimina una fruta del mapa
     * 
     * @param objetoFruta Fruta
     */
    private void eliminarFruta(Object objetoFruta) {
        if (objetoFruta != null) {
            ventanaPrincipalServidor.removerLabel(objetoFruta);
            ventanaPrincipalServidor.removerDeLista(objetoFruta);
        }
    }
    
    /**
     * Finaliza el juego e informa de los puntajes
     * y tiempos obtenidos
     * 
     */
    private void finalizarJuego() {
        juegoActivo = false;
        velX = 0;
        velY = 0;
        int puntaje = cGeneralServidor.getPuntajeTotal();
        long tiempo = cGeneralServidor.getTiempoTranscurrido();
        String mensaje = "¡Juego Terminado!\n"
                + "Puntaje: " + puntaje + "\n"
                + "Tiempo: " + (tiempo / 1000) + " segundos";

        if (timerTiempo != null) {
            timerTiempo.stop();
        }
        ventanaPrincipalServidor.mostrarDialogo(mensaje, "Juego Terminado", 1);
        guardarPuntuacionAutomatica(puntaje, tiempo);
    }
    
    /**
     * Guarda las puntaciones del jugador
     * 
     * @param puntaje Puntaje del usario
     * @param tiempoSegundos Tiempo del usuario
     */
    private void guardarPuntuacionAutomatica(int puntaje, long tiempoSegundos) {
        File archivo = ventanaPrincipalServidor.solicitarArchivoGuardar();

        if (archivo == null) {
            return; // Usuario canceló
        }

        boolean guardado = cGeneralServidor.guardarPuntuacion(
                archivo,
                puntaje,
                tiempoSegundos
        );

        if (guardado) {
            String nombreUsuario = cGeneralServidor.getUsuarioConectado();
            ventanaPrincipalServidor.setMensajeEstadoExito("Puntuación de "
                    + nombreUsuario + " guardada exitosamente en:\n"
                    + archivo.getAbsolutePath()
            );
        } else {
            ventanaPrincipalServidor.setMensajeEstadoError("Error al guardar");
        }
    }
    
     /**
      * Actualiza la posicion del pacman 
      * y garantiza que el pacman no salma de los limetes del mapa
      * 
      */
    public void moverPacman() {

        if (ventanaPrincipalServidor == null) {
            return;
        }

        x += velX;
        y += velY;

        int anchoPanel = ventanaPrincipalServidor.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipalServidor.getAltoPanelJuego();
        int anchoPacman = ventanaPrincipalServidor.getAnchoPacman();
        int altoPacman = ventanaPrincipalServidor.getAltoPacman();
        
        //Calcula los limetes maximos permitidos
        maxX = anchoPanel - anchoPacman;
        maxY = altoPanel - altoPacman;
        
        //Mantiene al pacman dentro de los limites
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
        
        //Actializa la posicion en la vista
        ventanaPrincipalServidor.setPosicionPacman(x, y);
    }
    
    /**
     * Centra al pacman en el panel de juego
     * se utuliza para iniciar 
     * 
     */
    public void centrarPacman() {
        int anchoPanel = ventanaPrincipalServidor.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipalServidor.getAltoPanelJuego();
        
        //valores por defecto del panel
        if (anchoPanel == 0 || altoPanel == 0) {
            anchoPanel = 900;
            altoPanel = 630;
        }

        int anchoPacman = ventanaPrincipalServidor.getAnchoPacman();
        int altoPacman = ventanaPrincipalServidor.getAltoPacman();

        x = (anchoPanel - anchoPacman) / 2;
        y = (altoPanel - altoPacman) / 2;
        
        //Actualiza la posicion inicial del pacman
        ventanaPrincipalServidor.setPosicionPacman(x, y);
    }
    
    /**
     * Actualiza el puntaje del usuario
     * 
     */
    private void actualizarPuntaje() {
        int puntaje = cGeneralServidor.getPuntajeTotal();
        ventanaPrincipalServidor.getLblPuntaje().
                setText(String.valueOf(puntaje));
    }
    
    /**
     * Actualiza el tiempo
     * trabajados en segundos
     * 
     */
    public void actualizarTiempo() {
        long tiempo = cGeneralServidor.getTiempoTranscurrido() / 1000;
        ventanaPrincipalServidor.getLblTiempo()
                .setText("Tiempo: " + tiempo + " s");
    }

    /**
     * Aplica el movimiento en la direccion indicada 
     * por el cliente
     * 
     * @param movimiento Direccion a la que se dirige el pacman
     */
    public void aplicarMovimiento(String movimiento) {
        if (movimiento == null || movimiento.isBlank()) {
            return;
        }

        int desplazamiento = movPixeles * 4;

        switch (movimiento.trim().toUpperCase()) {
            case "ARRIBA":
                moverPacmanUnPaso(0, -desplazamiento);
                break;
            case "ABAJO":
                moverPacmanUnPaso(0, desplazamiento);
                break;
            case "IZQUIERDA":
                moverPacmanUnPaso(-desplazamiento, 0);
                break;
            case "DERECHA":
                moverPacmanUnPaso(desplazamiento, 0);
                break;
            default:
                return;
        }
        //Verifica si realizo una colicion con una frutaS
        verificarColisiones();
    }
    
    /**
     * Desplaza al pacman una cantidad de pixeles 
     * y garantiza que se mantengan los limites del mapa
     * 
     * @param dx Desplazmiento en el eje X
     * @param dy Desplazamiento en el eje Y
     */
    private void moverPacmanUnPaso(int dx, int dy) {
        x += dx;
        y += dy;

        int anchoPanel = ventanaPrincipalServidor.getAnchoPanelJuego();
        int altoPanel = ventanaPrincipalServidor.getAltoPanelJuego();
        int anchoPac = ventanaPrincipalServidor.getAnchoPacman();
        int altoPac = ventanaPrincipalServidor.getAltoPacman();

        int maxX = Math.max(0, anchoPanel - anchoPac);
        int maxY = Math.max(0, altoPanel - altoPac);

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
        
        //Refleja una nueva posicion del pacman
        ventanaPrincipalServidor.setPosicionPacman(x, y);
    }

}
