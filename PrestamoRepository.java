package org.example.crudbibliotecaspringboot.Repository;

import org.example.crudbibliotecaspringboot.Modelo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByUsuarioId(Integer usuarioId);

    List<Prestamo> findByEjemplarId(Integer ejemplarId);
}