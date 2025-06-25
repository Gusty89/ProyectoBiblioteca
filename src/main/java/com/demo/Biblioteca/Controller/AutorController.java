package com.demo.Biblioteca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.Biblioteca.Model.Autor;
import com.demo.Biblioteca.Service.AutorService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // Obtener todos los autores
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping
    public ResponseEntity<?> obtenerAutores() {
    try {
        List<Autor> autores = autorService.obtenerAutores();
            return ResponseEntity.ok(autores);
    }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún autor: " + e.getMessage());
    }
}


    // Obtener autor por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/{id}")
    public Autor buscarPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + id));
    }

    // Crear un solo autor
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/crearAutor")
    public Autor crearAutor(@RequestBody Autor autor) {
        return autorService.guardarAutor(autor);
    }


    // Actualizar autor por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long id, @RequestBody Autor autorActualizado) {
        Autor autor = autorService.actualizar(id, autorActualizado);
        return ResponseEntity.ok(autor);
    }

    // Eliminar autor por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id) {
        autorService.eliminar(id);
        return ResponseEntity.ok("Autor eliminado correctamente.");
    }
}
