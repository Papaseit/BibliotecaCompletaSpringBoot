package org.example.crudbibliotecaspringboot.Controlador;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.crudbibliotecaspringboot.Service.PrestamoService;
import org.example.crudbibliotecaspringboot.Modelo.Prestamo;
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
@RequestMapping("/prestamos")
@CacheConfig(cacheNames = {"prestamos"})
public class PrestamoController {

    @Autowired
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // Obtener todos los préstamos
    @GetMapping
    @Transactional
    public List<Prestamo> listar() {
        return prestamoService.obtenerTodos();
    }

    // Obtener un préstamo por ID
    @GetMapping("/{id}")
    @Cacheable
    public Optional<Prestamo> obtener(@PathVariable Integer id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return prestamoService.obtenerPorId(id);
    }

    // Crear un nuevo préstamo
    @PostMapping
    public Prestamo crear(@Valid @RequestBody Prestamo prestamo) {
        return prestamoService.guardar(prestamo);
    }

    // Actualizar un préstamo existente
    @PutMapping("/{id}")
    public Prestamo actualizar(@PathVariable Integer id, @Valid @RequestBody Prestamo prestamo) {
        return prestamoService.obtenerPorId(id).map(p -> {
            p.setUsuario(prestamo.getUsuario());
            p.setEjemplar(prestamo.getEjemplar());
            p.setFechaInicio(prestamo.getFechaInicio());
            p.setFechaDevolucion(prestamo.getFechaDevolucion());
            return prestamoService.guardar(p);
        }).orElse(null);
    }

    // Eliminar un préstamo por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        prestamoService.eliminar(id);
    }

    // Manejo de errores de validación
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
