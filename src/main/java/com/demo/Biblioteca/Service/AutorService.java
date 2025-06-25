package com.demo.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import com.demo.Biblioteca.Model.Autor;

public interface AutorService {
    List<Autor> obtenerAutores();
    Optional<Autor> obtenerPorId(Long id);
    Autor guardarAutor(Autor autor);
    Autor actualizar(Long id, Autor actualizarAutor);
    void eliminar(Long id);
}
