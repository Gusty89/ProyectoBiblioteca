package com.demo.Biblioteca.Service;

import java.util.List;

import com.demo.Biblioteca.Model.Usuario;

public interface UsuarioService {
    List<Usuario> obtenerUsuarios();
    Usuario buscarIdUsuario(Long id);
    Usuario registrar(Usuario usuario);
    Usuario actualizarUsuario(Long id, Usuario usuarioActualizado);
    void eliminarPorId(Long id);
    boolean validarLogin(String email, String password);
    boolean isNuloOVacio(String valor);
}
