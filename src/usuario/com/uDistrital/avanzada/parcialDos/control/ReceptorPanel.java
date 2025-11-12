package usuario.com.uDistrital.avanzada.parcialDos.control;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

public class ReceptorPanel {

    private Socket sc;
    private DataInputStream dInput;
    
    private BufferedImage frameActual;

    public ReceptorPanel(Socket sc) {
        this.sc = sc;
    }
    
    public void recibirCaptura() {
        try {
        dInput = new DataInputStream(sc.getInputStream());
            while (true) {
                int size = dInput.readInt();
                byte[] data = new byte[size];
                dInput.readFully(data);
                frameActual = ImageIO.read(new ByteArrayInputStream(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
