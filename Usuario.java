package org.example.crudbibliotecaspringboot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 15)
    @NotBlank(message = "el campo DNI no puede estar vacio")
    @Pattern(regexp = "[0-9]{8}[A-Z]", message ="el DNI debe contener 8 digitos y una letra mayuscula")
    private String dni;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "el campo nombre no puede estar vacio")
    @Pattern(regexp = "[A-Za-z ]+", message ="El nombre deben ser letras mayusculas o minusculas")
    private String nombre;

    @Column(name = "email", nullable = false, length = 100)
    @NotBlank(message = "el campo email no puede estar vacio")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "El email debe contener caracteres alfanuméricos y debe ser un correo de Gmail")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "el campo password no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message ="la password debe contener entre 4 y 12 caracteres alfanumericos")
    private String password;

    @Lob
    @Column(name = "tipo", nullable = false)
    @NotBlank(message = "el campo tipo no puede estar vacio")
    @Pattern(regexp = "normal|administrador", message ="el tipo de usuario debe ser normal o administrador")
    private String tipo;

    @Column(name = "penalizacionhasta")
    private LocalDate penalizacionhasta;


    public Usuario(){

    }

    public Usuario(Integer id, String dni, String nombre, String email, String password, String tipo, LocalDate penalizacionHasta) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
        this.penalizacionhasta = penalizacionHasta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPenalizacionHasta() {
        return penalizacionhasta;
    }

    public void setPenalizacionHasta(LocalDate penalizacionHasta) {
        this.penalizacionhasta = penalizacionHasta;
    }

}