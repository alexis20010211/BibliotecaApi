package com.biblioteca.bibliotecaApi.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.biblioteca.bibliotecaApi.model.Rol;
import com.biblioteca.bibliotecaApi.model.Usuario;

public class UsuarioDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean activo;
    private Set<String> roles;

    public UsuarioDto() {
    }

    public UsuarioDto(Long id, String username, String email, String password, boolean activo, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.roles = roles;
    }

    // ---------------------------
    // Getters y setters
    // ---------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    // ---------------------------
    // Conversión de Entity → DTO
    // ---------------------------

    public static UsuarioDto fromEntity(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();

        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername()); // ← CORREGIDO
        dto.setEmail(usuario.getEmail());
        dto.setPassword(usuario.getPassword());
        dto.setActivo(usuario.isActivo());

        if (usuario.getRoles() != null) {
            dto.setRoles(
                usuario.getRoles().stream()
                        .map(Rol::getNombre)
                        .collect(Collectors.toSet())
            );
        }

        return dto;
    }
}
