package com.backend.el_inversor_argentino.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


public class UsuarioDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String apellido;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String email;

    private LocalDate fechaRegistro;

    @NotNull
    @Size(max = 255)
    private String nombre;

    private List<Long> favoritosArticuloes;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(final LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getFavoritosArticuloes() {
        return favoritosArticuloes;
    }

    public void setFavoritosArticuloes(final List<Long> favoritosArticuloes) {
        this.favoritosArticuloes = favoritosArticuloes;
    }

}
