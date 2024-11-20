package org.codexdei;

import org.codexdei.Repositorio.Repositorio;
import org.codexdei.Repositorio.UsuarioRepositorioImpl;
import org.codexdei.modelo.Usuario;

public class Pruebas {

    public static void main(String[] args) {

        Repositorio repoUsuario = new UsuarioRepositorioImpl();

        repoUsuario.listar().forEach(System.out::println);

        System.out.println("********************** Obtener por id ******************");

        System.out.println(repoUsuario.buscarId(1L));

        System.out.println("******************** Actualizar Usuario *******************");
        Usuario us = new Usuario();
        us.setIdUsuario(3L);
        us.setNombreUsuario("YORKING");
        us.setPassword("yk4321");
        us.setEmail("yorking@yorking.com");

        repoUsuario.actualizar(us);

        System.out.println("Usuario guardado con exito");

        System.out.println("*************************** ELIMINAR USUARIO ********************");
        repoUsuario.eliminar(1L);
    }
}
