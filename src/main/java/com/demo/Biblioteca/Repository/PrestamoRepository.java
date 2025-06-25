package com.demo.Biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Biblioteca.Model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
