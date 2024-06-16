package com.backend.el_inversor_argentino.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;


public class CategoriaDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombre;

    private List<Long> articuloCategoriaArticuloes;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getArticuloCategoriaArticuloes() {
        return articuloCategoriaArticuloes;
    }

    public void setArticuloCategoriaArticuloes(final List<Long> articuloCategoriaArticuloes) {
        this.articuloCategoriaArticuloes = articuloCategoriaArticuloes;
    }

}
