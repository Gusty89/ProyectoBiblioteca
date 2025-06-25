package com.demo.Biblioteca.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Biblioteca.Model.Usuario;
import com.demo.Biblioteca.Repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Método de validacion para el GET
    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    //Método de validacion para el GET
    @Override
    public Usuario buscarIdUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        return usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    }

    //Método de validacion para POST
    @Override
    public Usuario registrar(Usuario usuario) {
        // Validar campos obligatorios
        if (isNuloOVacio(usuario.getNombre()) || isNuloOVacio(usuario.getApellido()) ||
            isNuloOVacio(usuario.getEmail()) || isNuloOVacio(usuario.getPassword()) || 
            isNuloOVacio(usuario.getTelefono())) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        // Validación de email único
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
}

        // Establecer fecha actual si no viene
        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(LocalDate.now());
        }

        // Guardar usuario sin cifrado (solo para pruebas)
        return usuarioRepository.save(usuario);
    }

    //Método de validacion para POST
    @Override
    public boolean validarLogin(String email, String password) {
    if (isNuloOVacio(email) || isNuloOVacio(password)) {
        throw new IllegalArgumentException("Email y contraseña son obligatorios");
    }

    return usuarioRepository.findByEmail(email)
        .map(usuario -> password.equals(usuario.getPassword()))
        .orElse(false);
}

    //Método que valida que los campos del login no esten vacios
    @Override
    public boolean isNuloOVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    //Método de validacion para PUT
    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
    Usuario existente = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    existente.setNombre(usuarioActualizado.getNombre());
    existente.setApellido(usuarioActualizado.getApellido());
    existente.setTelefono(usuarioActualizado.getTelefono());
    existente.setEmail(usuarioActualizado.getEmail());
    // NO actualices password ni fecha si no cambia
    return usuarioRepository.save(existente);
}

    
    //Método de validacion para DELETE
    @Override
    public void eliminarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido para eliminación");
        }

        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. Usuario con ID " + id + " no existe");
        }

        usuarioRepository.deleteById(id);
    }
    
}
