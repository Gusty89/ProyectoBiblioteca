package com.demo.Biblioteca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.demo.Biblioteca.Model.Editorial;
import com.demo.Biblioteca.Service.EditorialService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {
    @Autowired
    private EditorialService editorialService;

    //Método que devuelve toda la lista de editoriales
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping
    public ResponseEntity<?> obtenerEditoriales() {
    try {
        List<Editorial> editoriales = editorialService.obtenerEditoriales();
            return ResponseEntity.ok(editoriales);
    }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún autor: " + e.getMessage());
    }
}


    // Obtener editorial por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/{id}")
    public Editorial buscarPorId(@PathVariable Long id) {
        return editorialService.obtenerPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Editorial no encontrado con ID: " + id));
    }

    // Crear una editorial
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/crearEditorial")
    public Editorial crearEditorial(@RequestBody Editorial editorial) {
        return editorialService.guardarEditorial(editorial);
    }


    // Actualizar editorial por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PutMapping("/{id}")
    public ResponseEntity<Editorial> actualizar(@PathVariable Long id, @RequestBody Editorial editorialActualizada) {
        Editorial editorial = editorialService.actualizarEditorial(id, editorialActualizada);
        return ResponseEntity.ok(editorial);
    }

    // Eliminar editorial por ID
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        editorialService.eliminarEditorial(id);
        return ResponseEntity.ok("Editorial eliminada correctamente.");
    }
}