package org.example.crudbibliotecaspringboot.Controlador;

import jakarta.validation.Valid;
import org.example.crudbibliotecaspringboot.Modelo.Libro;
import org.example.crudbibliotecaspringboot.Service.EjemplarService;
import org.example.crudbibliotecaspringboot.Modelo.Ejemplar;
import org.example.crudbibliotecaspringboot.Service.LibroService;
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
@RequestMapping("/ejemplares")
@CacheConfig(cacheNames = {"ejemplares"})
public class EjemplarController {

    @Autowired
    private final EjemplarService ejemplarService;

    @Autowired
    private LibroService libroService;

    public EjemplarController(EjemplarService ejemplarService, LibroService libroService) {
        this.ejemplarService = ejemplarService;
        this.libroService = libroService;
    }

    @GetMapping
    public List<Ejemplar> listar() {
        return ejemplarService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Cacheable
    public Optional<Ejemplar> obtener(@PathVariable Integer id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ejemplarService.obtenerPorId(id);
    }

    @PostMapping
    public Ejemplar crear(@RequestBody Map<String, String> datos) {
        String isbn = datos.get("isbn");
        String estado = datos.get("estado");

        Libro libro = libroService.obtenerPorId(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setIsbn(libro);
        ejemplar.setEstado(estado);

        return ejemplarService.guardar(ejemplar);
    }

    @PutMapping("/{id}")
    public Ejemplar actualizar(@PathVariable Integer id, @Valid @RequestBody Ejemplar ejemplar) {
        return ejemplarService.obtenerPorId(id).map(e -> {
            e.setEstado(ejemplar.getEstado());
            e.setIsbn(ejemplar.getIsbn());
            return ejemplarService.guardar(e);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ejemplarService.eliminar(id);
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
