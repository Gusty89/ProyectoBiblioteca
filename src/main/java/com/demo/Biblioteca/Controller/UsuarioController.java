package com.demo.Biblioteca.Controller;

import java.util.List;
import java.util.Map;

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

import com.demo.Biblioteca.Dto.LoginRequest;
import com.demo.Biblioteca.Model.Usuario;
import com.demo.Biblioteca.Service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping
    public ResponseEntity<?> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Usuario user = usuarioService.buscarIdUsuario(id);
            return (user != null)
                    ? ResponseEntity.ok(user)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con ID " + id + " no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar usuario: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()
            || usuario.getApellido() == null || usuario.getApellido().trim().isEmpty()
            || usuario.getTelefono() == null || usuario.getTelefono().trim().isEmpty()
            || usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()
            || usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()
            || usuario.getFechaRegistro() == null) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios.");
        }

        try {
            Usuario nuevo = usuarioService.registrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
        boolean valido = usuarioService.validarLogin(loginRequest.getEmail(), loginRequest.getPassword());

        if (valido) {
            return ResponseEntity.ok(Map.of("mensaje", "Login exitoso"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Ocurrió un error inesperado"));
    }
}
    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
    try {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok(actualizado);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar usuario: " + e.getMessage());
    }
}

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Usuario user = usuarioService.buscarIdUsuario(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario con ID " + id + " no existe");
            }
            usuarioService.eliminarPorId(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
