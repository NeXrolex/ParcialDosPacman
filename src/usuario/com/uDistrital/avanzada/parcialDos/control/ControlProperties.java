package usuario.com.uDistrital.avanzada.parcialDos.control;

import usuario.com.uDistrital.avanzada.parcialDos.modelo.DAO.PropertiesDAO;
import java.io.File;


/**
 * Maneja la logica y flujo de informacion del archivo de propiedades
 *
 * @author Alex
 */
public class ControlProperties {

    private final PropertiesDAO propertiesDAO;

    public ControlProperties() {
        this.propertiesDAO = new PropertiesDAO();
    }

    /**
     * Recibe el archivo properties y se lo pasa al dao para mantener la
     * referencia
     *
     * @param archivo
     */
    public void setArchivoProperties(File archivo) {
        propertiesDAO.setArchivoProperties(archivo);
    }

    /**
     * Busca un valor en el archivo de propiedades
     *
     * @param clave Clave a buscar
     * @return
     */
    public String buscarClave(String clave) {
        return propertiesDAO.consultar(clave);
    }
    
    /**
     * Establece los valores para conectarse al socket por
     * medio de los properties
     * 
     */
    public void establecerValoresSocket(){
        propertiesDAO.configurarConexionSocketDesdeArchivo();
    }

}
