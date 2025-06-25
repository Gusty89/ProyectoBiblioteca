package com.demo.Biblioteca.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;


@Entity
public class Libro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="libro_id", nullable=false)
    private Long id;
    @Column(name="titulo", nullable=false)
    private String titulo;
    @Column(name="anio", nullable= false)
    private String anio;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado del libro no puede ser nulo")
    private EstadoLibro estado;

    //Un libro puede estar escrito por varios autores
    @ManyToMany
    private List<Autor> autores;//FK de la tabla autor, es una relación bidireccional
    
    //Cada libro tiene una única editorial
    @ManyToOne
    @JoinColumn(name = "editorial_id") // FK de editorial
    private Editorial editorial;

    //Un libro puede estar asociado a muchos préstamos.
    @OneToMany(mappedBy = "libro") // Esta es la FK en la tabla préstamo
    private List<Prestamo> prestamos;

    public Libro(){

    }

    public enum EstadoLibro {
    DISPONIBLE,
    PRESTADO,
    EN_REPARACION,
    PERDIDO
    }

    public Libro(Long id, String titulo, List<Autor> autores,String anio, EstadoLibro estado, Editorial editorial, List<Prestamo> prestamos) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.anio = anio;
        this.estado = estado;
        this.editorial = editorial;
        this.prestamos = prestamos;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }

    
    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
