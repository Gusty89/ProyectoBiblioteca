package com.demo.Biblioteca.Controller;

import java.util.List;

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

import com.demo.Biblioteca.Dto.AutorDTO;
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
    @GetMapping("/listaAutores")
    public ResponseEntity<List<AutorDTO>> obtenerAutores() {
    List<Autor> autores = autorService.obtenerAutores();

    // Mapear cada Autor a AutorDTO usando el método estático fromEntity()
    List<AutorDTO> autoresDto = autores.stream()
                                       .map(AutorDTO::fromEntity)
                                       .toList();

    return ResponseEntity.ok(autoresDto);
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
