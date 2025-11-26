package com.biblioteca.bibliotecaApi.dto;

public class LibroDto {
    private Long id;
    private String titulo;
    private String autor;
    private Integer stock;
    private Boolean disponible;

    public LibroDto() {}

    public LibroDto(Long id, String titulo, String autor, Integer stock, Boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.stock = stock;
        this.disponible = disponible;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
}
