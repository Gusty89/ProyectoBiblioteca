package com.demo.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import com.demo.Biblioteca.Model.Libro;

public interface LibroService {
    List<Libro> obtenerLibros();
    Optional<Libro> obtenerPorId(Long id);
    Libro guardarLibro(Libro libro);
    Libro actualizarLibro(Long id, Libro nuevoLibro);
    void eliminarLibro(Long id);
}
