/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialDos.control;

import com.uDistrital.avanzada.parcialDos.vista.VentanaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author Steven
 */
public class ControlVista implements ActionListener{

    private VentanaJuego vista;
    private ControlGeneral controlGeneral;
    private int velX;
    private int velY;

    public ControlVista(ControlGeneral general) {
        this.controlGeneral = general;
        this.vista = new VentanaJuego();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
    }

    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        // NOTA: No estoy seguro si puse el movimiento al reves xd
        if (c == KeyEvent.VK_UP) {
            velX = 0;
            velY = 1;
        } else if (c == KeyEvent.VK_DOWN) {
            velX = 0;
            velY = -1;
        } else if (c == KeyEvent.VK_LEFT) {
            velX = -1;
            velY = 0;
        } else if (c == KeyEvent.VK_RIGHT) {
            velX = 1;
            velY = 0;
        }
    }
}
