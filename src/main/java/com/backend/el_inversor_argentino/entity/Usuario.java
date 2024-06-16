package com.backend.el_inversor_argentino.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Set;


@Entity
public class Usuario {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate fechaRegistro;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> usuarioComentarios;

    @ManyToMany
    @JoinTable(
            name = "Favoritos",
            joinColumns = @JoinColumn(name = "usuarioId"),
            inverseJoinColumns = @JoinColumn(name = "articuloId")
    )
    private Set<Articulo> favoritosArticuloes;

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

    public Set<Comentario> getUsuarioComentarios() {
        return usuarioComentarios;
    }

    public void setUsuarioComentarios(final Set<Comentario> usuarioComentarios) {
        this.usuarioComentarios = usuarioComentarios;
    }

    public Set<Articulo> getFavoritosArticuloes() {
        return favoritosArticuloes;
    }

    public void setFavoritosArticuloes(final Set<Articulo> favoritosArticuloes) {
        this.favoritosArticuloes = favoritosArticuloes;
    }

}
