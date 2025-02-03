package org.example.crudbibliotecaspringboot.Repository;

import org.example.crudbibliotecaspringboot.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {

}