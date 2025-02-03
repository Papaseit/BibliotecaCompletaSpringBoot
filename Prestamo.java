package org.example.crudbibliotecaspringboot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "prestamo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ejemplar_id", nullable = false)
    private Ejemplar ejemplar;

    @Column(name = "fechainicio", nullable = false)
    private LocalDate fechainicio;

    @Column(name = "fechadevolucion")
    private LocalDate fechadevolucion;

    public Prestamo() {
    }

    public Prestamo(Integer id, Usuario usuario, Ejemplar ejemplar, LocalDate fechainicio, LocalDate fechadevolucion) {
        this.id = id;
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.fechainicio = fechainicio;
        this.fechadevolucion = fechadevolucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public LocalDate getFechaInicio() {
        return fechainicio;
    }

    public void setFechaInicio(LocalDate fechainicio) {
        this.fechainicio = fechainicio;
    }

    public LocalDate getFechaDevolucion() {
        return fechadevolucion;
    }

    public void setFechaDevolucion(LocalDate fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

}