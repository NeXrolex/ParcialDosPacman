package usuario.com.uDistrital.avanzada.parcialDos.control;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import usuario.com.uDistrital.avanzada.parcialDos.vista.VentanaStreaming;

public class ReceptorPanel {

    private VentanaStreaming vistaStream;
    private Socket sc;
    private DataInputStream dInput;

    private BufferedImage frameActual;

    public ReceptorPanel(Socket sc, VentanaStreaming ventana) {
        this.sc = sc;
        this.vistaStream = ventana;
    }

    public void recibirCaptura() {
        try {
            dInput = new DataInputStream(sc.getInputStream());
            while (true) {
                int size = dInput.readInt();
                byte[] data = new byte[size];
                dInput.readFully(data);
                frameActual = ImageIO.read(new ByteArrayInputStream(data));
                if (frameActual != null) {
                    vistaStream.setImagen(frameActual); // actualiza el panel
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
