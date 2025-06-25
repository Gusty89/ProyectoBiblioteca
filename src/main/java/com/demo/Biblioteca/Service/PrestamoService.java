package com.demo.Biblioteca.Service;

import java.util.List;

import com.demo.Biblioteca.Model.Prestamo;

public interface PrestamoService {
    List<Prestamo> listarPrestamos();
    Prestamo buscarPorId(Long id);
    Prestamo guardarPrestamo(Prestamo prestamo);
    Prestamo actualizar(Long id, Prestamo prestamoActualizado);
    Prestamo fueDevuelto(Long id);
    void eliminarPrestamo(Long id);
}
