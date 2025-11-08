/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import servidor.com.udistrital.avanzada.parcialDos.modelo.FrutaVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Alex,Jard,Stiven
 */
public class ControlJuego {

    private List<FrutaVO> frutasEnJuego;
    private List<FrutaVO> frutasDisponibles;
    private int puntajeTotal;
    private long tiempoInicio;
    private int frutasComidas;

    public ControlJuego() {
        this.frutasEnJuego = new ArrayList<>();
        this.frutasDisponibles = new ArrayList<>();
        this.puntajeTotal = 0;
        this.frutasComidas = 0;
    }

    public void setFrutasDisponibles(List<FrutaVO> frutas) {
        this.frutasDisponibles = frutas;
    }

    /**
     * Genera 4 frutas aleatorias en posiciones aleatorias del panel
     *
     * @param anchoPanel
     * @param altoPanel
     */
    public void generarFrutas(int anchoPanel, int altoPanel) {
        if (frutasDisponibles.isEmpty()) {
            throw new IllegalStateException("No hay frutas disponibles. "
                    + "Debe cargar el archivo properties primero.");
        }

        Random rand = new Random();
        frutasEnJuego.clear();

        // --- Zona prohibida (donde está el Pac-Man) ---
        int pacmanAncho = 16;
        int pacmanAlto = 16;
        int zonaSegura = 60; // margen alrededor del centro (ajustable)

        int centroX = (anchoPanel - pacmanAncho) / 2;
        int centroY = (altoPanel - pacmanAlto) / 2;

        int zonaX1 = centroX - zonaSegura;
        int zonaY1 = centroY - zonaSegura;
        int zonaX2 = centroX + pacmanAncho + zonaSegura;
        int zonaY2 = centroY + pacmanAlto + zonaSegura;
        // ----------------------------------------------

        for (int i = 0; i < 4; i++) {

            int x, y;
            boolean posicionValida;

            do {
                x = 50 + rand.nextInt(anchoPanel - 100);
                y = 50 + rand.nextInt(altoPanel - 100);

                // Verificar que NO esté dentro de la zona central
                posicionValida = !(x > zonaX1 && x < zonaX2 && y > zonaY1 && y < zonaY2);

            } while (!posicionValida); // Reintentar si cae en el centro

            // Seleccionar una fruta aleatoria de la lista disponible
            int indice = rand.nextInt(frutasDisponibles.size());
            FrutaVO frutaOriginal = frutasDisponibles.get(indice);

            FrutaVO fruta = new FrutaVO(
                    frutaOriginal.getNombre(),
                    frutaOriginal.getPuntos(),
                    frutaOriginal.getRutaImagen()
            );

            fruta.setX(x);
            fruta.setY(y);
            frutasEnJuego.add(fruta);
        }

        tiempoInicio = System.currentTimeMillis();
    }

    public FrutaVO verificarColision(int pacmanX, int pacmanY,
            int pacmanAncho, int pacmanAlto) {

        for (FrutaVO fruta : frutasEnJuego) {
            if (!fruta.isComida()) {

                int frutaAncho = 36;
                int frutaAlto = 36;

                if (pacmanX < fruta.getX() + frutaAncho
                        && pacmanX + pacmanAncho > fruta.getX()
                        && pacmanY < fruta.getY() + frutaAlto
                        && pacmanY + pacmanAlto > fruta.getY()) {

                    fruta.setComida(true);
                    puntajeTotal += fruta.getPuntos();
                    frutasComidas++;
                    return fruta;
                }
            }
        }

        return null;
    }

    public boolean juegoTerminado() {
        return frutasComidas >= 4;
    }

    public long getTiempoTranscurrido() {
        return System.currentTimeMillis() - tiempoInicio;
    }

    public void reiniciar() {
        tiempoInicio = System.currentTimeMillis();
        frutasEnJuego.clear();
        puntajeTotal = 0;
        frutasComidas = 0;
    }

    public List<FrutaVO> getFrutasEnJuego() {
        return frutasEnJuego;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public int getFrutasComidas() {
        return frutasComidas;
    }
}
