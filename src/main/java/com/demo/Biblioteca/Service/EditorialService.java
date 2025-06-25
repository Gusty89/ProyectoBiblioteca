package com.demo.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import com.demo.Biblioteca.Model.Editorial;

public interface EditorialService {
    List<Editorial> obtenerEditoriales();
    Optional<Editorial> obtenerPorId(Long id);
    Editorial guardarEditorial(Editorial editorial);
    Editorial actualizarEditorial(Long id, Editorial nuevaEditorial);
    void eliminarEditorial(Long id);
}
