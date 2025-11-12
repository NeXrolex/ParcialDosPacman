/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import servidor.com.udistrital.avanzada.parcialDos.vista.VentanaPrincipalServidor;

/**
 *
 * @author Steven
 */
public class CapturaPanelJuego {

    private VentanaPrincipalServidor vistaServidor;
    private Robot robot;
    private Rectangle areaCaptura;

    public CapturaPanelJuego(VentanaPrincipalServidor vistaServidor) {
        try {
            this.vistaServidor = vistaServidor;
            this.robot = new Robot(); // <--- inicializar correctamente
            Dimension size = vistaServidor.getPanelJuego().getSize();
            Point location = vistaServidor.getPanelJuego().getLocationOnScreen();
            this.areaCaptura = new Rectangle(location.x, location.y, size.width, size.height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Captura el panel de juego y retorna un arreglo de bytes en formato JPEG.
     */
    public byte[] capturarImagen() {
        try {
            BufferedImage img = robot.createScreenCapture(areaCaptura);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
