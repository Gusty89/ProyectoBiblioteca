package com.demo.Biblioteca.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="prestamo_id", nullable=false)
    Long id;

    @Column(name="fechaPrestamo", nullable=false)
    LocalDate fechaPrestamo;
    @Column(name="fechaDevolucion", nullable=false)
    LocalDate fechaDevolucion;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado del préstamo no puede ser nulo")
    private EstadoPrestamo estado;
    //Cada préstamo está asociado a un único usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)//FK de usuario
    private Usuario usuario;

    //Cada préstamo está vinculado a un solo libro.
    @ManyToOne
    @JoinColumn(name = "libro_id")//FK de libro
    private Libro libro;

    public enum EstadoPrestamo {
    ACTIVO,
    DEVUELTO,
    ATRASADO
    }

    public Prestamo(){

    }
    public Prestamo(Long id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, EstadoPrestamo estado, Usuario usuario, Libro libro) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.usuario = usuario;
        this.libro = libro;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
