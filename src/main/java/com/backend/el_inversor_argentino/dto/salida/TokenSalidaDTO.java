package com.backend.el_inversor_argentino.dto.salida;

import com.backend.el_inversor_argentino.entity.Role;
import lombok.Builder;

import java.util.List;

public class TokenSalidaDTO {

    private Long idUser;
    private String name;
    private String lastName;
    private List<Role> roles;
    private String token;
    @Builder.Default
    private String tokenType = "Bearer ";

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
