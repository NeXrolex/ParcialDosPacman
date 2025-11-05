/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.vista;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Steven
 */
public class VentanaJuego extends JFrame {

    public VentanaJuego() {
        setTitle("Pac-Man");
        setSize(900, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imgPlaceholder = new ImageIcon(getClass().getResource("/Specs/ImagesAndGifs/pacman24px.png"));
        JLabel placeholderLabel = new JLabel(imgPlaceholder);
        add(placeholderLabel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
