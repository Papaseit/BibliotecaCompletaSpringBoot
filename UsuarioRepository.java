package org.example.crudbibliotecaspringboot.Repository;

import org.example.crudbibliotecaspringboot.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
