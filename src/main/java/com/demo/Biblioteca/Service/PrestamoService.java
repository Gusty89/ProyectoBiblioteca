package com.demo.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import com.demo.Biblioteca.Model.Prestamo;

public interface PrestamoService {
    List<Prestamo> listarPrestamos();
    Optional<Prestamo> buscarPorId(Long id);
    Prestamo guardarPrestamo(Prestamo prestamo);
    Prestamo actualizar(Long id, Prestamo prestamoActualizado);
    void eliminar(Long id);
}
