package com.demo.Biblioteca.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="autor_id", nullable=false)
    private int id;
    @Column(name="Nombre", nullable=false, length=50)
    private String nombre;
    @Column(name="Apellido", nullable=false, length=50)
    private String apellido;
    @Column(name="Nacimiento", nullable=false, length=10)
    private LocalDate fechaNacimiento;
    @Column(name="Nacionalidad")
    private String nacionalidad;

    public Autor() {
    }

    public Autor(int id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad) {
        this.id= id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
    }

    // Getters y Setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

}

