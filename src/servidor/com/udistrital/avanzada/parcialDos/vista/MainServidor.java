package servidor.com.udistrital.avanzada.parcialDos.vista;

import servidor.com.udistrital.avanzada.parcialDos.control.ControlGeneralServidor;

public class MainServidor {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Inicia todo el servidor (controladores y vista)
            ControlGeneralServidor controlGeneralServidor = new ControlGeneralServidor();
        });
    }
}
