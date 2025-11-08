package usuario.com.udistrital.avanzada.parcialDos.vista;
import usuario.com.uDistrital.avanzada.parcialDos.control.ControlGeneralUsuario;
public class MainCliente{

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Inicia todo el servidor (controladores y vista)
            ControlGeneralUsuario controlGeneralUsuario = new ControlGeneralUsuario();
        });
    }
}
