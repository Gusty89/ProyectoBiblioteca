package com.demo.Biblioteca.Repository;
import com.demo.Biblioteca.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
