package com.demo.Biblioteca.Model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="usuario_id", nullable=false)
    Long id;

    @Column(name="nombre", nullable=false, length=50)
    String nombre;
    @Column(name="apellido", nullable=false, length=50)
    String apellido;
    @Column(name="telefono", nullable=false, length=25)
    String telefono;
    @Column(name="email", unique=true, nullable=false, length=50)
    String email;
    @Column(name="password", nullable=false)
    String password;
    @Column(name="fechaRegistro", nullable=false)
    LocalDate fechaRegistro;

    //Un usuario puede tener muchos préstamos
    @OneToMany(mappedBy = "usuario")//Esta es la FK en la tabla prestamo
    
    public List<Prestamo> prestamos;
    public Usuario(){

    }

    public Usuario(Long id, String nombre, String apellido, String telefono, String email, String password, LocalDate fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    
}
