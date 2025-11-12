/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.vista;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Steven
 */
public class VentanaStreaming extends JFrame {

    public JPanel panelStreaming;
    private BufferedImage imagen;

    public VentanaStreaming() {
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        panelStreaming = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (imagen != null) {
                    g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        add(panelStreaming);
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
        panelStreaming.repaint(); // repintar solo el panel
    }

}
