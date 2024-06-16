package com.backend.el_inversor_argentino.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    /**
     * El nombre del rol.
     */
    @Column(name = "rol", nullable = false, unique = true)
    private String rol;

    /**
     * La fecha en que se registró el rol.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;

    /**
     * Constructor para crear un rol con un nombre específico.
     *
     * @param rol El nombre del rol.
     */
    public Role(String rol) {
        this.rol = rol;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
}
