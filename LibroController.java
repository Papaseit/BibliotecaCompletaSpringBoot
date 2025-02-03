package org.example.crudbibliotecaspringboot.Controlador;

import jakarta.validation.Valid;
import org.example.crudbibliotecaspringboot.Service.LibroService;
import org.example.crudbibliotecaspringboot.Modelo.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/libros")
@CacheConfig(cacheNames = {"libros"})
public class LibroController {
    @Autowired
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listar() {
        return libroService.obtenerTodos();
    }

    @GetMapping("/{isbn}")
    @Cacheable
    public Optional<Libro> obtener(@PathVariable String isbn) {
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return libroService.obtenerPorId(isbn);
    }

    @PostMapping
    public Libro crear(@Valid @RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    @PutMapping("/{isbn}")
    public Libro actualizar(@PathVariable String isbn, @Valid @RequestBody Libro libro) {
        return libroService.obtenerPorId(isbn).map(l->{
            l.setTitulo(libro.getTitulo());
            l.setAutor(libro.getAutor());
            return libroService.guardar(l);
        }).orElse(null);
    }

    @DeleteMapping("/{isbn}")
    public void eliminar(@PathVariable String isbn) {
      libroService.eliminar(isbn);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}