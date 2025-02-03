package org.example.crudbibliotecaspringboot.Modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    @NotBlank(message = "el campo isbn no puede estar vacio")
    @Pattern(regexp = "[0-9]{8}", message ="el ISBN debe contener 8 digitos")
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    @NotBlank(message = "el titulo no puede estar en blanco")
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    @NotBlank(message = "el autor no puede estar en blnaco")
    private String autor;

    @OneToMany(mappedBy = "isbn")
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();

    public Libro(){

    }

    public Libro(String isbn, String titulo, String autor, Set<Ejemplar> ejemplars) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplars = ejemplars;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Set<Ejemplar> getEjemplars() {
        return ejemplars;
    }

    public void setEjemplars(Set<Ejemplar> ejemplars) {
        this.ejemplars = ejemplars;
    }

}