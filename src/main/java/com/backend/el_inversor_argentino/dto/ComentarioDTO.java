package com.backend.el_inversor_argentino.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public class ComentarioDTO {

    private Long id;

    @NotNull
    private String contenido;

    private LocalDate fecha;

    private Long articulo;

    private Long usuario;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(final String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(final LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getArticulo() {
        return articulo;
    }

    public void setArticulo(final Long articulo) {
        this.articulo = articulo;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(final Long usuario) {
        this.usuario = usuario;
    }

}

