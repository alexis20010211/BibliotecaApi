package com.biblioteca.bibliotecaApi.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "libros")
public class Libro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private String autor;

    @PositiveOrZero
    private Integer stock = 1;

    private Boolean disponible = true;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getTitulo(){return titulo;}
    public void setTitulo(String t){this.titulo=t;}
    public String getAutor(){return autor;}
    public void setAutor(String a){this.autor=a;}
    public Integer getStock(){return stock;}
    public void setStock(Integer s){this.stock=s;}
    public Boolean getDisponible(){return disponible;}
    public void setDisponible(Boolean d){this.disponible=d;}
}
