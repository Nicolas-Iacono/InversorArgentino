package com.backend.el_inversor_argentino.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@Entity
public class Comentario {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contenido;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(final Articulo articulo) {
        this.articulo = articulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(final Usuario usuario) {this.usuario = usuario;
    }

}
