/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.io.File;
import servidor.com.udistrital.avanzada.parcialDos.modelo.DAO.RAFDAO;

/**
 * Controlador para manejar el RAF de puntuaciones
 * 
 * @author Jard
 */
public class ControlRAF {
    
    private RAFDAO rafDAO;
    
    /**
     * Configura el archio a guardar
     * @param archivo Archivo a guardadar
     */
    public void configurarArchivo(File archivo) {
        this.rafDAO = new RAFDAO(archivo);
    }
    
    /**
     * Metodo encargado de guardar la puntuacion
     * @param nombreJugador nombre del jugador
     * @param puntaje puntaje obtenido
     * @param tiempoSegundos tiempo transcurrido
     * @return 
     */
    public boolean guardarPuntuacion(String nombreJugador, int puntaje, long tiempoSegundos) {
        if (rafDAO == null) {
            return false;
        }
        
        try {
            // Crear array con los datos
            String[] datos = {
                nombreJugador,
                String.valueOf(puntaje),
                String.valueOf(tiempoSegundos)
            };
            
            rafDAO.insertar(datos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
