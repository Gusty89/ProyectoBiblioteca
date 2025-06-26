package com.demo.Biblioteca.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Biblioteca.Dto.LibroDTO;
import com.demo.Biblioteca.Model.Libro;
import com.demo.Biblioteca.Service.LibroService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Obtener todos los libros
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/listaLibros")
    public ResponseEntity<List<LibroDTO>> obtenerLibros() {
        List<Libro> libros = libroService.obtenerLibros();
        List<LibroDTO> librosDto = libros.stream()
                                         .map(LibroDTO::fromEntity)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok(librosDto);
    }
    // Obtener libro por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> buscarPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado con ID: " + id));

        return ResponseEntity.ok(LibroDTO.fromEntity(libro));
    }

    // Crear un libro
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/crearLibro")
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }


    // Actualizar libro por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Libro libro = libroService.actualizarLibro(id, libroActualizado);
        return ResponseEntity.ok(libro);
    }

    // Eliminar autor por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.ok("Libro eliminado correctamente.");
    }
}