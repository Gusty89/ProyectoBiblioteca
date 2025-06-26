package com.demo.Biblioteca.Service;

import com.demo.Biblioteca.Model.Prestamo;
import com.demo.Biblioteca.Repository.PrestamoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Optional<Prestamo> buscarPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        validarPrestamo(prestamo);
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo actualizar(Long id, Prestamo prestamoActualizado) {
        Prestamo prestamoExistente = prestamoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Préstamo no encontrado con ID: " + id));

        validarPrestamo(prestamoActualizado);

        prestamoExistente.setFechaPrestamo(prestamoActualizado.getFechaPrestamo());
        prestamoExistente.setFechaDevolucion(prestamoActualizado.getFechaDevolucion());
        prestamoExistente.setEstado(prestamoActualizado.getEstado());

        return prestamoRepository.save(prestamoExistente);
    }

    @Override
    public void eliminar(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new EntityNotFoundException("Préstamo no encontrado con ID: " + id);
        }
        prestamoRepository.deleteById(id);
    }
    
    public void validarPrestamo(Prestamo prestamo) {
        if (prestamo.getFechaPrestamo() == null) {
            throw new IllegalArgumentException("La fecha de préstamo es obligatoria.");
        }

        if (prestamo.getFechaPrestamo().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de préstamo no puede ser en el futuro.");
        }

        if (prestamo.getFechaDevolucion() != null &&
            prestamo.getFechaDevolucion().isBefore(prestamo.getFechaPrestamo())) {
            throw new IllegalArgumentException("La fecha de devolución no puede ser anterior a la de préstamo.");
        }

        if (prestamo.getEstado() == null) {
            throw new IllegalArgumentException("El estado del préstamo es obligatorio.");
        }
    }
}
