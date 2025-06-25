package com.demo.Biblioteca.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import com.demo.Biblioteca.Model.Libro;
import com.demo.Biblioteca.Model.Prestamo;
import com.demo.Biblioteca.Model.Usuario;
import com.demo.Biblioteca.Repository.LibroRepository;
import com.demo.Biblioteca.Repository.PrestamoRepository;
import com.demo.Biblioteca.Repository.UsuarioRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService{

    @Autowired
    private PrestamoRepository prestamoRepository;

    private UsuarioRepository usuarioRepository;

    private LibroRepository libroRepository;


    //Método para obtener una lista de prestamos (GET)
    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    //Valida si se ha encontrado un prestamo por id (GET)
    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado"));
    }
    
    //Valida la obtencion de un prestamo, antes de guardarlo (POST)
    // Buscar el usuario por ID
    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo){
    // Buscar el usuario por ID
    Usuario usuario = usuarioRepository.findById(prestamo.getUsuario().getId())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

    // Buscar el libro por ID
    Libro libro = libroRepository.findById(prestamo.getLibro().getId())
        .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));

    // Asignar al préstamo
    prestamo.setUsuario(usuario);
    prestamo.setLibro(libro);

    // Guardar préstamo
    return prestamoRepository.save(prestamo);
}
    //Valida si se actualizó correctamente un prestamo (PUT)
    @Override
    public Prestamo actualizar(Long id, Prestamo prestamoActualizado) {
    Prestamo prestamo = buscarPorId(id);

    if (prestamo.getEstado() == Prestamo.EstadoPrestamo.DEVUELTO) {
        throw new IllegalStateException("No se puede modificar un préstamo ya devuelto.");
    }

    if (prestamoActualizado.getFechaPrestamo().isAfter(prestamoActualizado.getFechaDevolucion())) {
        throw new IllegalArgumentException("La fecha de préstamo no puede ser posterior a la de devolución.");
    }

    prestamo.setFechaPrestamo(prestamoActualizado.getFechaPrestamo());
    prestamo.setFechaDevolucion(prestamoActualizado.getFechaDevolucion());
    prestamo.setUsuario(prestamoActualizado.getUsuario());
    prestamo.setLibro(prestamoActualizado.getLibro());
    prestamo.setEstado(prestamoActualizado.getEstado()); // opcional, si querés permitirlo

    return prestamoRepository.save(prestamo);
}


    //Valida si un prestamo fue devuelto (PUT)
    @Override
    public Prestamo fueDevuelto(Long id) {
    Prestamo prestamo = buscarPorId(id); //ya lanza excepción si no existe

    if (prestamo.getEstado() == Prestamo.EstadoPrestamo.DEVUELTO) {
        throw new IllegalStateException("Este préstamo ya fue devuelto.");
    }

    prestamo.setEstado(Prestamo.EstadoPrestamo.DEVUELTO);
    return prestamoRepository.save(prestamo);
    }

    //Valida si el prestamo puede eliminarse (DELETE)
    @Override
    public void eliminarPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado"));

    prestamoRepository.delete(prestamo);
    }
}
