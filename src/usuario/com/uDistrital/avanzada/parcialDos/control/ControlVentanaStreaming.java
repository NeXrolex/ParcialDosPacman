/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.control;

import usuario.com.uDistrital.avanzada.parcialDos.vista.VentanaStreaming;

/**
 *
 * @author Steven
 */
public class ControlVentanaStreaming {

    private VentanaStreaming vistaStream;
    private ControlGeneralUsuario cGeneral;

    public ControlVentanaStreaming(ControlGeneralUsuario cGeneral) {
        this.cGeneral = cGeneral;
        inicializarVentana();
    }

    public void inicializarVentana() {
        this.vistaStream = new VentanaStreaming();
        vistaStream.setVisible(true);
    }

    public VentanaStreaming getVistaStream() {
        return vistaStream;
    }

}
