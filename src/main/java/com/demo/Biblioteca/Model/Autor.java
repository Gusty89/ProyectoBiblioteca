package com.demo.Biblioteca.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="autor_id", nullable=false)
    private Long id;
    @Column(name="nombre", nullable=false, length=50)
    private String nombre;
    @Column(name="apellido", nullable=false, length=50)
    private String apellido;

    //Un autor puede haber escrito varios libros
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;//FK de la tabla libro, es una relación bidireccional

    public Autor() {
    }

    public Autor(Long id, String nombre, String apellido, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.libros = libros;
    }

    // Getters y Setters
    
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
}

}
