package org.example.crudbibliotecaspringboot.Service;

import org.example.crudbibliotecaspringboot.Modelo.Prestamo;
import org.example.crudbibliotecaspringboot.Repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Transactional
    public List<Prestamo> obtenerTodos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        prestamos.forEach(prestamo -> {
            prestamo.getUsuario().getId();
            prestamo.getEjemplar().getId();
        });
        return prestamos;
    }

    public Optional<Prestamo> obtenerPorId(Integer id) {
        return prestamoRepository.findById(id);
    }

    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Integer id) {
        prestamoRepository.deleteById(id);
    }

    public List<Prestamo> obtenerPorUsuario(Integer usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public List<Prestamo> obtenerPorEjemplar(Integer ejemplarId) {
        return prestamoRepository.findByEjemplarId(ejemplarId);
    }
}
