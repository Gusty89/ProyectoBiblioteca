package com.demo.Biblioteca.Repository;
import com.demo.Biblioteca.Model.Autor;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long>{
    boolean existsByNombreAndApellido(String nombre, String apellido);
}
