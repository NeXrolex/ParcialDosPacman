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
public class ConexionBaseDatos implements IConexion {

    //Atributos staticos
    private static String usuario;
    private static String contrasena;
    private static String URLBD;
    private static Connection cn = null;

    /**
     * Setea la configuracion por defecto de la base de datos
     *
     * @param url URL de la base de datos
     * @param user Usuario de la base de datos
     * @param pass Contrasena del usuario
     */
    public static void configurar(String url, String user, String pass) {
        URLBD = url;
        usuario = user;
        contrasena = pass;
    }

    /**
     * Cumple el contrato de iconexion y conecta a la base de datos
     *
     * @return Conexion a la base datos
     */
    @Override
    public Connection conexion() {
        try {
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cn;
    }

    /**
     * Obtiene el nombre del usuario de la base de datos
     *
     * @return Usuario
     */
    public static String getUsuario() {
        return usuario;
    }

    /**
     * Asigna el usuario a la base de datos
     *
     * @param user Usuario
     */
    public static void setUsuario(String user) {
        usuario = user;
    }

    /**
     * Obtiene la contrasena
     *
     * @return Contrasena
     */
    public static String getContrasena() {
        return contrasena;
    }

    /**
     * Asigna la contrasena
     *
     * @param pass Contrasena
     */
    public static void setContrasena(String pass) {
        contrasena = pass;
    }

    /**
     * Obtiene la direccion de la base de datos
     *
     * @return URLBaseDatos
     */
    public static String getURLBD() {
        return URLBD;
    }

    /**
     * Asigna la URL de la base de datos
     *
     * @param url URLBaseDatos
     */
    public static void setURLBD(String url) {
        URLBD = url;
    }

    /**
     * Obtiene la conexion
     *
     * @return conexion
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URLBD, usuario, contrasena);
    }

}
