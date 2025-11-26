package com.biblioteca.bibliotecaApi.dto;

public class LibroDto {
    private Long id;
    private String titulo;
    private String autor;
    private Integer stock;
    private Boolean disponible;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitulo(){return titulo;} public void setTitulo(String t){this.titulo=t;}
    public String getAutor(){return autor;} public void setAutor(String a){this.autor=a;}
    public Integer getStock(){return stock;} public void setStock(Integer s){this.stock=s;}
    public Boolean getDisponible(){return disponible;} public void setDisponible(Boolean d){this.disponible=d;}
}
