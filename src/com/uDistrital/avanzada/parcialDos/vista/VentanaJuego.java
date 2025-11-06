package com.uDistrital.avanzada.parcialDos.vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ventana principal del juego
 *
 * @author Steven
 */
public class VentanaJuego extends JFrame {

    private JPanel panelJuego, panelBotones;
    private JLabel labelPacman;
    private JButton botonIniciar, botonSalir;
    private List<JLabel> labelsFrutas; // Labels de las frutas

    public VentanaJuego() {
        setTitle("Pac-Man");
        setSize(900, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelsFrutas = new ArrayList<>();

        inicializarComponentes();

        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // Panel de juego
        panelJuego = new JPanel(null);
        panelJuego.setBackground(new Color(0, 0, 20));
        panelJuego.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 255), 3),
                "",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 0, 255)
        ));
        panelJuego.setFocusable(true);

        ImageIcon imgPlaceholder = new ImageIcon(
                getClass().getResource("/Specs/ImagesAndGifs/placeholder24px.png")
        );
        labelPacman = new JLabel(imgPlaceholder);
        labelPacman.setSize(imgPlaceholder.getIconWidth(),
                imgPlaceholder.getIconHeight());
        panelJuego.add(labelPacman);

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(0, 0, 20));
        panelBotones.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 255), 3),
                "",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(0, 0, 255)
        ));

        botonIniciar = crearBoton("Iniciar Juego", new Color(255, 204, 0));
        botonSalir = crearBoton("Salir", new Color(255, 204, 0));

        panelBotones.add(botonIniciar);
        panelBotones.add(botonSalir);

        Image icono = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/Specs/ImagesAndGifs/fantasma.png")
        );
        setIconImage(icono);

        setLayout(new BorderLayout());
        add(panelJuego, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(200, 45));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(new Color(204, 0, 0), 3));
        return boton;
    }

    public JLabel agregarFruta(String rutaImagen, int x, int y) {
        try {
            ImageIcon iconoFruta = new ImageIcon(
                    getClass().getResource(rutaImagen)
            );

            Image img = iconoFruta.getImage();
            Image imgEscalada = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconoFruta = new ImageIcon(imgEscalada);

            JLabel labelFruta = new JLabel(iconoFruta);
            labelFruta.setBounds(x, y, 30, 30);

            panelJuego.add(labelFruta);
            labelsFrutas.add(labelFruta);

            panelJuego.repaint();

            return labelFruta;

        } catch (Exception e) {

            JLabel labelFruta = new JLabel("🍒");
            labelFruta.setBounds(x, y, 30, 30);
            labelFruta.setFont(new Font("Arial", Font.BOLD, 20));
            labelFruta.setForeground(Color.RED);

            panelJuego.add(labelFruta);
            labelsFrutas.add(labelFruta);

            panelJuego.repaint();

            return labelFruta;
        }
    }

    public void eliminarFruta(JLabel labelFruta) {
        if (labelFruta != null) {
            panelJuego.remove(labelFruta);
            labelsFrutas.remove(labelFruta);
            panelJuego.repaint();
        }
    }

    public void limpiarFrutas() {
        for (JLabel label : labelsFrutas) {
            panelJuego.remove(label);
        }
        labelsFrutas.clear();
        panelJuego.repaint();
    }

    public void mostrarExito(String mensaje) {
    JOptionPane.showMessageDialog(this, 
        mensaje, 
        "Juego Terminado", 
        JOptionPane.INFORMATION_MESSAGE);
}
    public JPanel getPanelJuego() {
        return panelJuego;
    }

    public JLabel getLabelPacman() {
        return labelPacman;
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public List<JLabel> getLabelsFrutas() {
        return labelsFrutas;
    }
}
