package org.example.crudbibliotecaspringboot.Service;

import org.example.crudbibliotecaspringboot.Modelo.Libro;
import org.example.crudbibliotecaspringboot.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public  Optional<Libro> obtenerPorId(String isbn) {
        return libroRepository.findById(isbn);
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminar(String isbn) {
        libroRepository.deleteById(isbn);
    }
}