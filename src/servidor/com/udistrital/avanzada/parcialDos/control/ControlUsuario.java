/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.com.udistrital.avanzada.parcialDos.control;

import servidor.com.udistrital.avanzada.parcialDos.modelo.DAO.UsuarioDAO;
import servidor.com.udistrital.avanzada.parcialDos.modelo.UsuarioVO;

/**
 * Maneja todo lo relacionado a la logica de un usuario
 *
 * @author Alex
 */
public class ControlUsuario {
    
    private final UsuarioDAO usuarioDAO;
    
    /**
     * Constructor que intancia un
     * dao para poder hacer consultas sql
     */
    public ControlUsuario() {
        this.usuarioDAO = new UsuarioDAO();
    }
    /**
     * Verifica si las credenciales proporcionadas son válidas.
     * Lee el usuario desde la BD y compara la contraseña ingresada.
     *
     * @param nombre nombre ingresado por el usuario
     * @param contrasena contraseña ingresada por el usuario
     * @return true si coinciden; false si no existe o no coincide
     */
    public boolean iniciarSesion(String nombre, String contrasena) {
        if (nombre == null || nombre.isEmpty() || contrasena == null ||
                contrasena.isEmpty()) {
            return false;
        }

        UsuarioVO usuarioBD = (UsuarioVO) usuarioDAO.consultar(nombre);
        if (usuarioBD == null) {
            return false; // usuario no encontrado
        }

        return contrasena.equals(usuarioBD.getContrasena());
    }
    
}
