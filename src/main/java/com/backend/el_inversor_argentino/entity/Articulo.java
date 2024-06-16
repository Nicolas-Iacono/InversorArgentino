package com.backend.el_inversor_argentino.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Entity
public class Articulo {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;

    @Column(columnDefinition = "longtext")
    private String imagenes;

    @Column(nullable = false, columnDefinition = "longtext")
    private String parrafo;

    @Column
    private String subtitulo;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToMany(mappedBy = "articuloCategoriaArticuloes")
    private Set<Categoria> articuloCategoriaCategorias;

    @OneToMany(mappedBy = "articulo")
    private Set<Comentario> articuloComentarios;

    @ManyToMany(mappedBy = "favoritosArticuloes")
    private Set<Usuario> favoritosUsuarios;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(final String imagenes) {
        this.imagenes = imagenes;
    }

    public String getParrafo() {
        return parrafo;
    }

    public void setParrafo(final String parrafo) {
        this.parrafo = parrafo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(final String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(final Autor autor) {
        this.autor = autor;
    }

    public Set<Categoria> getArticuloCategoriaCategorias() {
        return articuloCategoriaCategorias;
    }

    public void setArticuloCategoriaCategorias(final Set<Categoria> articuloCategoriaCategorias) {
        this.articuloCategoriaCategorias = articuloCategoriaCategorias;
    }

    public Set<Comentario> getArticuloComentarios() {
        return articuloComentarios;
    }

    public void setArticuloComentarios(final Set<Comentario> articuloComentarios) {
        this.articuloComentarios = articuloComentarios;
    }

    public Set<Usuario> getFavoritosUsuarios() {
        return favoritosUsuarios;
    }

    public void setFavoritosUsuarios(final Set<Usuario> favoritosUsuarios) {
        this.favoritosUsuarios = favoritosUsuarios;
    }

}
