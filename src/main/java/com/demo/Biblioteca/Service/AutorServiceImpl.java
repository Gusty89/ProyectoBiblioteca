package com.demo.Biblioteca.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.demo.Biblioteca.Model.Autor;
import com.demo.Biblioteca.Repository.AutorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AutorServiceImpl implements AutorService{
@Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> obtenerAutores() {
        return autorRepository.findAll();
}

    @Override
    public Optional<Autor> obtenerPorId(Long id) {
        return autorRepository.findById(id);
}

    //Guarda el autor solo si no se repite por nombre y apellido
    @Override
    public Autor guardarAutor(Autor autor) {
        boolean existe = autorRepository.existsByNombreAndApellido(
                autor.getNombre(),
                autor.getApellido()
        );

        if (existe) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El autor ya existe");
        }
        return autorRepository.save(autor);
    }

    @Override
    public Autor actualizar(Long id, Autor actualizarAutor) {
    Autor autor = autorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + id));

    autor.setNombre(actualizarAutor.getNombre());
    autor.setApellido(actualizarAutor.getApellido());

    return autorRepository.save(autor);
}

    @Override
    public void eliminar(Long id) {
        Autor autor = autorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + id));

        autorRepository.delete(autor);
    }
}


