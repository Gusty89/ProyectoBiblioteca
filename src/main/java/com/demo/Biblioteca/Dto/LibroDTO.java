package com.demo.Biblioteca.Dto;

import com.demo.Biblioteca.Model.Libro;
import java.util.List;
import java.util.stream.Collectors;

public class LibroDTO {
    private Long id;
    private String titulo;
    private String anio;
    private String estado; // Enum como String

    private List<AutorDTO> autores; // lista de DTOs de autores
    private EditorialDTO editorial; // DTO simplificado de editorial

    public LibroDTO() {}

    public LibroDTO(Long id, String titulo, String anio, String estado, List<AutorDTO> autores, EditorialDTO editorial) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.estado = estado;
        this.autores = autores;
        this.editorial = editorial;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAnio() { return anio; }
    public void setAnio(String anio) { this.anio = anio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<AutorDTO> getAutores() { return autores; }
    public void setAutores(List<AutorDTO> autores) { this.autores = autores; }

    public EditorialDTO getEditorial() { return editorial; }
    public void setEditorial(EditorialDTO editorial) { this.editorial = editorial; }

    // Método para mapear Libro a LibroDTO
    public static LibroDTO fromEntity(Libro libro) {
        if (libro == null) return null;

        List<AutorDTO> autoresDto = null;
        if (libro.getAutores() != null) {
            autoresDto = libro.getAutores()
                .stream()
                .map(AutorDTO::fromEntity)
                .collect(Collectors.toList());
        }

        EditorialDTO editorialDto = null;
        if (libro.getEditorial() != null) {
            editorialDto = new EditorialDTO(
                libro.getEditorial().getId(),
                libro.getEditorial().getNombre(),
                libro.getEditorial().getTelefono(),
                libro.getEditorial().getEmail()
            );
        }

        return new LibroDTO(
            libro.getId(),
            libro.getTitulo(),
            libro.getAnio(),
            libro.getEstado() != null ? libro.getEstado() : null,
            autoresDto,
            editorialDto
        );
    }
}
