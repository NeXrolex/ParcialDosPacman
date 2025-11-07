/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario.com.uDistrital.avanzada.parcialDos.modelo.DAO;

import usuario.com.uDistrital.avanzada.parcialDos.modelo.UsuarioVO;
import usuario.com.uDistrital.avanzada.parcialDos.modelo.conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import usuario.com.uDistrital.avanzada.parcialDos.modelo.interfaces.ICreate;
import usuario.com.uDistrital.avanzada.parcialDos.modelo.interfaces.IRead;
import usuario.com.uDistrital.avanzada.parcialDos.modelo.interfaces.IUpdateDelete;

/**
 * Cumple las funciones DAO para hacer consultas, incersiones, eliminaciones y
 * actualizaciones de elementos de la base de datos
 *
 * @author Alex
 */
public class UsuarioDAO implements ICreate, IRead, IUpdateDelete {

    //Valores sql para hacer todos los crud
    private static final String SQL_INSERT
            = "INSERT INTO usuario (nombre, contrasena) VALUES (?, ?)";

    private static final String SQL_SELECT_BY_NOMBRE
            = "SELECT nombre, contrasena FROM usuario WHERE nombre = ?";

    private static final String SQL_UPDATE_BY_NOMBRE
            = "UPDATE usuario SET contrasena = ? WHERE nombre = ?";

    private static final String SQL_DELETE_BY_NOMBRE
            = "DELETE FROM usuario WHERE nombre = ?";

    /**
     * Cumple el contrato de servicios para insertar elementos a una bd para
     * este ejercio no es utiliza pero acata el open close
     *
     * @param elemento Usuario a insertar
     */
    @Override
    public void insertar(Object elemento) {
        if (!(elemento instanceof UsuarioVO)) {

        }
        UsuarioVO u = (UsuarioVO) elemento;

        /*Validaciones mínimas*/
        if (u.getNombre() == null || u.getNombre().trim().isEmpty()) {

        }
        if (u.getContrasena() == null) {

        }
        //Coneccion a la bd, importante que sea dentro de un try
        //para que se cierre despues
        try (Connection cn = ConexionBaseDatos.getConnection();
                PreparedStatement ps = cn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, u.getNombre().trim());
            ps.setString(2, u.getContrasena());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario.", e);
        }
    }

    /**
     * Consulta un Usuario por el nombre
     *
     * @param elemento Usuario
     * @return null o Usuario encontrado
     */
    public Object consultar(Object elemento) {
        if (!(elemento instanceof String)) {

        }
        String nombre = ((String) elemento).trim();
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        //Coneccion a la bd, importante que sea dentro de un try
        //para que se cierre despues
        try (Connection cn = ConexionBaseDatos.getConnection();
                PreparedStatement ps =
                        cn.prepareStatement(SQL_SELECT_BY_NOMBRE)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UsuarioVO u = new UsuarioVO(rs.getString("nombre"),
                            rs.getString("contrasena"),
                            0 /* puntaje en memoria */);
                    return u;
                }
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar usuario.", e);
        }
    }
    
    /**
     * Se conecta a la base de datos y modifica a un usario por su identificador
     * nombre
     * 
     * @param elemento Usuario a modificar 
     */
    @Override
    public void modificar(Object elemento) {
        if (!(elemento instanceof UsuarioVO)) {

        }
        UsuarioVO u = (UsuarioVO) elemento;

        if (u.getNombre() == null || u.getNombre().trim().isEmpty()) {

        }
        if (u.getContrasena() == null) {

        }
        //Coneccion a la bd, importante que sea dentro de un try
        //para que se cierre despues
        try (Connection cn = ConexionBaseDatos.getConnection();
                PreparedStatement ps =
                        cn.prepareStatement(SQL_UPDATE_BY_NOMBRE)) {

            ps.setString(1, u.getContrasena());
            ps.setString(2, u.getNombre().trim());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar usuario.", e);
        }
    }
    
    /**
     * Se conecta a la base de datos e elimina a un usario 
     * identificado con ese nombre
     * 
     * @param elemento Usuario a eliminar
     */
    @Override
     public void eliminar(Object elemento) {
        if (!(elemento instanceof String)) {
            throw new IllegalArgumentException("Se esperaba el nombre (String)"
                    + " para eliminar.");
        }
        String nombre = ((String) elemento).trim();
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        //Coneccion a la bd, importante que sea dentro de un try
        //para que se cierre despues
        try (Connection cn = ConexionBaseDatos.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_DELETE_BY_NOMBRE)) {

            ps.setString(1, nombre);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario.", e);
        }
    }

}
