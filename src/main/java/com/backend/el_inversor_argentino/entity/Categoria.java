package com.backend.el_inversor_argentino.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;


@Entity
public class Categoria {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "ArticuloCategoria",
            joinColumns = @JoinColumn(name = "categoriaId"),
            inverseJoinColumns = @JoinColumn(name = "articuloId")
    )
    private Set<Articulo> articuloCategoriaArticuloes;

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

    public Set<Articulo> getArticuloCategoriaArticuloes() {
        return articuloCategoriaArticuloes;
    }

    public void setArticuloCategoriaArticuloes(final Set<Articulo> articuloCategoriaArticuloes) {
        this.articuloCategoriaArticuloes = articuloCategoriaArticuloes;
    }

}
