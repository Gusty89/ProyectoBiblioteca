package com.demo.Biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Biblioteca.Model.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial, Long>{
    boolean existsByNombreAndTelefonoAndEmail(String nombre, String telefono, String email);

}
