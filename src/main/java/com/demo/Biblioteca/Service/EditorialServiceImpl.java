package com.demo.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.demo.Biblioteca.Model.Editorial;
import com.demo.Biblioteca.Repository.EditorialRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EditorialServiceImpl implements EditorialService{
    @Autowired
    private EditorialRepository editorialRepository;
    
    @Override
    public List<Editorial> obtenerEditoriales() {
    List<Editorial> editoriales = editorialRepository.findAll();

    if (editoriales.isEmpty()) {
        throw new IllegalArgumentException("No se ha creado ninguna editorial");
    }
    return editoriales;
}
    @Override
    public Optional<Editorial> obtenerPorId(Long id) {
        return editorialRepository.findById(id);
}

    //Guarda el autor solo si no se repite por nombre y apellido
    @Override
    public Editorial guardarEditorial(Editorial editorial) {
        boolean existe = editorialRepository.existsByNombreAndTelefonoAndEmail(
                editorial.getNombre(),
                editorial.getTelefono(),
                editorial.getEmail()
        );

        if (existe) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La editorial ya existe");
        }
        return editorialRepository.save(editorial);
    }

    @Override
    public Editorial actualizarEditorial(Long id, Editorial nuevaEditorial) {
    Editorial editorial = editorialRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Editorial no encontrado con ID: " + id));

    editorial.setNombre(nuevaEditorial.getNombre());
    editorial.setTelefono(nuevaEditorial.getTelefono());
    editorial.setEmail(nuevaEditorial.getEmail());

    return editorialRepository.save(editorial);
}

    @Override
    public void eliminarEditorial(Long id) {
        Editorial editorial = editorialRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Editorial no encontrada con ID: " + id));

        editorialRepository.delete(editorial);
    }
}