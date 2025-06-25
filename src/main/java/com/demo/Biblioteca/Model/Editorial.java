package com.demo.Biblioteca.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Editorial {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="editorial_id", nullable= false)
    Long id;
    @Column(name="nombre", nullable=false) 
    String nombre;
    @Column(name="telefono", nullable=false)
    String telefono;
    @Column(name="email", nullable=false)
    String email;

    //Una editorial puede estar asociada a muchos libros
    @OneToMany(mappedBy = "editorial")//Esta en la FK en la tabla libro
    private List<Libro> libros;
    

    public Editorial(){

    }
    public Editorial(Long id, String nombre, String telefono,String email, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
}
}
