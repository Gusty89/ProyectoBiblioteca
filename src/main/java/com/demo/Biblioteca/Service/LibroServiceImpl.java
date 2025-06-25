package com.demo.Biblioteca.Service;

import com.demo.Biblioteca.Model.Autor;
import com.demo.Biblioteca.Model.Editorial;
import com.demo.Biblioteca.Model.Libro;
import com.demo.Biblioteca.Repository.AutorRepository;
import com.demo.Biblioteca.Repository.EditorialRepository;
import com.demo.Biblioteca.Repository.LibroRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public List<Libro> obtenerLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            throw new IllegalArgumentException("No se ha creado ningún libro.");
        }
        return libros;
    }

    @Override
    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        // Validación de campos básicos
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()
            || libro.getAnio() == null || libro.getAnio().trim().isEmpty()
            || libro.getEstado() == null) {
            throw new IllegalArgumentException("Título, año y estado del libro son obligatorios.");
        }

        // Validar autores
        if (libro.getAutores() == null || libro.getAutores().isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar al menos un autor.");
        }

        List<Autor> autoresValidos = new ArrayList<>();
        for (Autor autor : libro.getAutores()) {
            if (autor.getId() == null) {
                throw new IllegalArgumentException("Cada autor debe tener un ID.");
            }
            Autor a = autorRepository.findById(autor.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + autor.getId()));
            autoresValidos.add(a);
        }
        libro.setAutores(autoresValidos);

        // Validar editorial
        if (libro.getEditorial() == null || libro.getEditorial().getId() == null) {
            throw new IllegalArgumentException("Debe proporcionar una editorial válida.");
        }

        Editorial editorial = editorialRepository.findById(libro.getEditorial().getId())
                .orElseThrow(() -> new EntityNotFoundException("Editorial no encontrada con ID: " + libro.getEditorial().getId()));

        libro.setEditorial(editorial);

        // (Opcional) Podrías verificar duplicados manualmente si no tenés un método existsBy personalizado

        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro nuevoLibro) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado con ID: " + id));

        libroExistente.setTitulo(nuevoLibro.getTitulo());
        libroExistente.setAnio(nuevoLibro.getAnio());
        libroExistente.setEstado(nuevoLibro.getEstado());

        // Reasignar autores
        List<Autor> autoresActualizados = new ArrayList<>();
        for (Autor autor : nuevoLibro.getAutores()) {
            Autor a = autorRepository.findById(autor.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + autor.getId()));
            autoresActualizados.add(a);
        }
        libroExistente.setAutores(autoresActualizados);

        // Reasignar editorial
        Editorial editorial = editorialRepository.findById(nuevoLibro.getEditorial().getId())
                .orElseThrow(() -> new EntityNotFoundException("Editorial no encontrada con ID: " + nuevoLibro.getEditorial().getId()));
        libroExistente.setEditorial(editorial);

        return libroRepository.save(libroExistente);
    }

    @Override
    public void eliminarLibro(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado con ID: " + id));

        libroRepository.delete(libro);
    }
}
