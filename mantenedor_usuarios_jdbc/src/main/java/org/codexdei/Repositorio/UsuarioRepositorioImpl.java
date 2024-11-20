package org.codexdei.Repositorio;

import org.codexdei.modelo.Usuario;
import org.codexdei.util.ConexionBaseDatos;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioRepositorioImpl implements Repositorio<Usuario> {

    private final static Logger LOGGER = Logger.getLogger(UsuarioRepositorioImpl.class.getName());

    private Connection generarConexionBD() throws SQLException {

        return ConexionBaseDatos.generarConexion();
    }

    @Override
    public List<Usuario> listar() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        try(Statement stmt = generarConexionBD().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
        ){
            while(rs.next()){

                Usuario usuario = crearUsuario(rs);

                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaUsuarios;
    }

    @Override
    public Usuario buscarId(Long id) {

        if (id == null){

            throw new IllegalArgumentException("El id no puede ser nulo");
        }

        String sql = "Select * From usuarios WHERE idusuarios=?";

        try(PreparedStatement stmt = generarConexionBD().prepareStatement(sql);
        ){

            stmt.setLong(1,id);

            try(ResultSet rs = stmt.executeQuery()){

                if(rs.next()){

                    return crearUsuario(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.severe(
                    "Error al obtener el producto por id: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void actualizar(Usuario usuario) {

        String sql;
        boolean isUpdate = usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0;
        boolean isNullEmpy = usuario.getNombreUsuario() == null || usuario.getPassword() == null || usuario.getEmail() == null ||
                             usuario.getNombreUsuario().isEmpty() || usuario.getPassword().isEmpty() || usuario.getEmail().isEmpty() ||
                             usuario.getNombreUsuario().isBlank() || usuario.getPassword().isBlank() || usuario.getEmail().isBlank();
        if(isUpdate){

            sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE idusuarios=?";

        }else{

            sql = "INSERT INTO usuarios(username,password,email) VALUES(?,?,?)";
        }

        try(PreparedStatement stmt = generarConexionBD().
              prepareStatement(sql);
        ){
                stmt.setString(1,usuario.getNombreUsuario());
                stmt.setString(2,usuario.getPassword());
                stmt.setString(3,usuario.getEmail());

            if(isUpdate){

                stmt.setLong(4,usuario.getIdUsuario());

            }

            int validarNullEmpy = isNullEmpy ? 0 : stmt.executeUpdate();

            if (validarNullEmpy > 0){
                System.out.println("Registros gestionados correctamente");

            }else if(validarNullEmpy <= 0) {
                System.out.println("Los datos o algunos de los datos es nulo, esta vacio o en blanco, NO se ha registrado el usuario");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {

        String sql = "DELETE FROM usuarios WHERE idusuarios=?";

        try(PreparedStatement stmt = generarConexionBD().prepareStatement(sql)){

            stmt.setLong(1,id);

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0){

                LOGGER.log(Level.INFO, "Usuario con ID {0} eliminado correctamente.", id);
            } else {
                LOGGER.log(Level.WARNING, "No se encontró ningún usuario con ID {0} para eliminar.", id);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,"Error al eliminar el usuario con ID: " + id,e);
            throw new RuntimeException(e);
        }
    }

    private Usuario crearUsuario(ResultSet rs) throws SQLException {

        Usuario us = new Usuario();
        us.setIdUsuario(rs.getLong("idusuarios"));
        us.setNombreUsuario(rs.getString("username"));
        us.setPassword(rs.getString("password"));
        us.setEmail(rs.getString("email"));

        return us;
    }
}
