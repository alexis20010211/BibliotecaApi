package com.biblioteca.bibliotecaApi.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @Email @Column(unique = true, nullable=false)
    private String correo;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles",
      joinColumns = @JoinColumn(name = "usuario_id"),
      inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;

    // getters/setters
    // ...
    public Long getId(){ return id;}
    public void setId(Long id){ this.id=id;}
    public String getNombre(){return nombre;}
    public void setNombre(String n){this.nombre=n;}
    public String getCorreo(){return correo;}
    public void setCorreo(String c){this.correo=c;}
    public String getPassword(){return password;}
    public void setPassword(String p){this.password=p;}
    public Set<Rol> getRoles(){return roles;}
    public void setRoles(Set<Rol> r){this.roles=r;}
}