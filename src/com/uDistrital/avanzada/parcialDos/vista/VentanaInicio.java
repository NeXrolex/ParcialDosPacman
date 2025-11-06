/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Ventana inicial para cargar archivo de configuración
 * Responsable de TODOS los componentes visuales incluido JFileChooser
 * 
 * @author Tu nombre
 */
public class VentanaInicio extends JFrame {
    
    private JButton btnCargarProperties;
    private JButton btnSalir;
    private JLabel lblEstado;
    private JPanel panelPrincipal;
    private JFileChooser fileChooser;
    
    public VentanaInicio() {
        setTitle("Pac-Man - Configuración Inicial");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        inicializarComponentes();
        configurarFileChooser();
        
        setLocationRelativeTo(null);
    }
    
    /**
     * Inicializa todos los componentes visuales
     */
    private void inicializarComponentes() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(0, 0, 20));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 0, 20));
        JLabel lblTitulo = new JLabel("PAC-MAN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(255, 204, 0));
        panelTitulo.add(lblTitulo);
        
        // Panel central con mensaje
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(0, 0, 20));
        
        JLabel lblMensaje = new JLabel("Seleccione el archivo de configuración");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMensaje.setForeground(Color.WHITE);
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblEstado = new JLabel(" ");
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 14));
        lblEstado.setForeground(new Color(255, 100, 100));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelCentro.add(Box.createVerticalGlue());
        panelCentro.add(lblMensaje);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentro.add(lblEstado);
        panelCentro.add(Box.createVerticalGlue());
        
        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(0, 0, 20));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnCargarProperties = crearBoton("Cargar properties", new Color(255, 204, 0));
        btnSalir = crearBoton("Salir", new Color(204, 0, 0));
        
        panelBotones.add(btnCargarProperties);
        panelBotones.add(btnSalir);
        
        // Agregar paneles al principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar al frame
        add(panelPrincipal);
        
        // Icono
        try {
            Image icono = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/Specs/ImagesAndGifs/fantasma.png")
            );
            setIconImage(icono);
        } catch (Exception e) {
            // Icono no disponible
        }
    }
    
    /**
     * Configura el JFileChooser (componente visual)
     */
    private void configurarFileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo de configuración");
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
            "Archivos Properties (*.properties)", "properties"
        );
        fileChooser.setFileFilter(filtro);
    }
    
    /**
     * Crea un botón estilizado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(200, 45));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return boton;
    }
    
    /**
     * Muestra el JFileChooser y retorna el archivo seleccionado
     * 
     * @return File seleccionado o null si se canceló
     */
    public File mostrarSelectorArchivo() {
        int resultado = fileChooser.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        
        return null;
    }
    
    /**
     * Actualiza el mensaje de estado
     * 
     * @param mensaje Mensaje a mostrar
     * @param esError true si es un mensaje de error
     */
    public void setMensajeEstado(String mensaje, boolean esError) {
        lblEstado.setText(mensaje);
        if (esError) {
            lblEstado.setForeground(new Color(255, 100, 100));
        } else {
            lblEstado.setForeground(new Color(100, 255, 100));
        }
    }
    
    /**
     * Muestra un mensaje de error
     * 
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de éxito
     * 
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Getters
    public JButton getBtnCargarConfig() {
        return btnCargarProperties;
    }
    
    public JButton getBtnSalir() {
        return btnSalir;
    }
}
