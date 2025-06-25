package com.demo.Biblioteca.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Biblioteca.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);
}
