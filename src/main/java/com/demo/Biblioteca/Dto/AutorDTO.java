package com.demo.Biblioteca.Dto;

import com.demo.Biblioteca.Model.Autor;

public class AutorDTO {
    private Long id;
    private String nombre;
    private String apellido;

    public AutorDTO() {
        // Constructor vacío necesario para frameworks como Jackson
    }

    public AutorDTO(Long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters y setters
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

    // Método estático para convertir entidad Autor a AutorDTO
    public static AutorDTO fromEntity(Autor autor) {
        if (autor == null) {
            return null;
        }
        return new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido());
    }
}
