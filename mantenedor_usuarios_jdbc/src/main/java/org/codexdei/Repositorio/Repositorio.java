package org.codexdei.Repositorio;

import java.util.List;

public interface Repositorio<T> {

    List<T> listar();
    T buscarId(Long id);
    void actualizar(T t);
    void eliminar(Long id);
}
