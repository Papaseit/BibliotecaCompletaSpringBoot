package org.example.crudbibliotecaspringboot.Service;

import org.example.crudbibliotecaspringboot.Modelo.Ejemplar;
import org.example.crudbibliotecaspringboot.Modelo.Libro;
import org.example.crudbibliotecaspringboot.Repository.EjemplarRepository;
import org.example.crudbibliotecaspringboot.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;

    @Autowired
    public EjemplarService(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    public List<Ejemplar> obtenerTodos() {
        return ejemplarRepository.findAll();
    }

    public Optional<Ejemplar> obtenerPorId(Integer id) {
        return ejemplarRepository.findById(id);
    }

    public Ejemplar guardar(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    public void eliminar(Integer id) {
        ejemplarRepository.deleteById(id);
    }
}
