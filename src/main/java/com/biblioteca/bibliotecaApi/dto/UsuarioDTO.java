package com.biblioteca.bibliotecaApi.dto;

public class UsuarioDto {
    private Long id;
    private String username;
    private String email;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getUsername(){return username;} public void setUsername(String u){this.username=u;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
}
