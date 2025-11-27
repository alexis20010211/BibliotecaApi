package com.biblioteca.bibliotecaApi.dto;

import java.util.Set;

import com.biblioteca.bibliotecaApi.model.Rol;

public class UsuarioDto {

    private Long id;
    private String username;
    private Set<Rol> roles; // coincide con tu entidad Usuario

    public UsuarioDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Set<Rol> getRoles() { return roles; }
    public void setRoles(Set<Rol> roles) { this.roles = roles; }
}
