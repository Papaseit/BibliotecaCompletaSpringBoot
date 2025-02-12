package org.example.crudbibliotecaspringboot.Repository;

import org.example.crudbibliotecaspringboot.Modelo.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {

}
