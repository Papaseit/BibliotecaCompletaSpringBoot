package org.example.crudbibliotecaspringboot.Controlador;

import jakarta.validation.Valid;
import org.example.crudbibliotecaspringboot.Modelo.Usuario;
import org.example.crudbibliotecaspringboot.Service.UsuarioService;
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
@RequestMapping("/usuarios")
@CacheConfig(cacheNames = {"usuarios"})
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Cacheable
    public Optional<Usuario> obtener(@PathVariable Integer id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return usuarioService.obtenerPorId(id);
    }

    @PostMapping
    public Usuario crear(@Valid @RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.obtenerPorId(id).map(u -> {
            u.setDni(usuario.getDni());
            u.setNombre(usuario.getNombre());
            u.setEmail(usuario.getEmail());
            u.setPassword(usuario.getPassword());
            u.setTipo(usuario.getTipo());
            u.setPenalizacionHasta(usuario.getPenalizacionHasta());
            return usuarioService.guardar(u);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
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
