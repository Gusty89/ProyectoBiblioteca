package com.demo.Biblioteca.Dto;

import com.demo.Biblioteca.Model.Editorial;

public class EditorialDTO {
    private Long id;
    private String nombre;
    private String telefono;
    private String email;

    public EditorialDTO() {}

    public EditorialDTO(Long id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
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

    // Método para mapear entidad Editorial a DTO
    public static EditorialDTO fromEntity(Editorial editorial) {
        if (editorial == null) return null;
        return new EditorialDTO(
            editorial.getId(),
            editorial.getNombre(),
            editorial.getTelefono(),
            editorial.getEmail()
        );
    }
}