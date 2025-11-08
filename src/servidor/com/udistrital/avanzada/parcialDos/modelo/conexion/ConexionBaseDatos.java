/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.uDistrital.avanzada.parcialDos.modelo.interfaces.IConexion;

/**
 * Se encarga de realizar la conexion a la base de datos Implementa nuestro
 * contrato para la conexion
 *
 * @author Alex
 */
public class ConexionBaseDatos {
    
    /*De esta manera podemos conectarnos a la base de datos en cualquier 
    momento despues de iniciado el programa, sin tener que tener un flujo 
    largo de la informacion*/
    private static final cBaseDatos INSTANCIA = new cBaseDatos();
    
    /**
     * Setea la configuracion por defecto de la base de datos
     * 
     * @param url URL de la base de datos
     * @param usuario Usuario de la base de datos
     * @param contrasena Contrasena del usuario
     */
    public static void configurar(String url, String usuario,
            String contrasena) {
        INSTANCIA.setURLBD(url);
        INSTANCIA.setUsuario(usuario);
        INSTANCIA.setContrasena(contrasena);
    }

    /**
     * Obtiene la conexion de la base de datos
     * 
     * @return Conexion de la base de datos
     */
    public static Connection getConnection() {
        return INSTANCIA.conexion();
    }

    /**
     * Obtiene la instancia de la conexion a
     * la bd
     * @return intancia de la bd
     */
    public static IConexion getProveedor() {
        return INSTANCIA;
    }
    
    /**
     * 
     */
    public static class cBaseDatos implements IConexion {

        private String usuario;
        private String contrasena;
        private String URLBD;
        private Connection cn = null;

        /**
         * Conecta con la base de datos y cumple el contrato de servicios de
         * IConexion
         *
         * @return Conexion a la base de datos
         */
        @Override
        public Connection conexion() {
            try {
                //Establecemos la conexion con la base de datos
                cn = DriverManager.getConnection(URLBD, usuario, contrasena);
                //Usamos los parametros asignados anteriormente
            } catch (SQLException ex) {
            }
            return cn;
        }

        /**
         * Obtiene el nombre del usuario de la base de datos
         *
         * @return Usuario
         */
        public String getUsuario() {
            return usuario;
        }

        /**
         * Asigna el usuario a la base de datos
         *
         * @param usuario Usuario
         */
        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        /**
         * Obtiene la contrasenia
         *
         * @return Contrasena
         */
        public String getContrasena() {
            return contrasena;
        }

        /**
         * Asigana la contrasena
         *
         * @param contrasena Contrasena
         */
        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        /**
         * Obtiene la direccion de la base de datos
         *
         * @return URLBaseDatos
         */
        public String getURLBD() {
            return URLBD;
        }

        /**
         * Asigna la URL de la base de datos
         *
         * @param URLBD URLBaseDatos
         */
        public void setURLBD(String URLBD) {
            this.URLBD = URLBD;
        }
    }

}
