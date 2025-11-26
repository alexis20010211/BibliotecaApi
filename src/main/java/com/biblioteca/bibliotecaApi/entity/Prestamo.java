package com.biblioteca.bibliotecaApi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevolucion;
    private Boolean devuelto = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public LocalDateTime getFechaPrestamo(){return fechaPrestamo;}
    public void setFechaPrestamo(LocalDateTime f){this.fechaPrestamo=f;}
    public LocalDateTime getFechaDevolucion(){return fechaDevolucion;}
    public void setFechaDevolucion(LocalDateTime f){this.fechaDevolucion=f;}
    public Boolean getDevuelto(){return devuelto;}
    public void setDevuelto(Boolean d){this.devuelto=d;}
    public Usuario getUsuario(){return usuario;}
    public void setUsuario(Usuario u){this.usuario=u;}
    public Libro getLibro(){return libro;}
    public void setLibro(Libro l){this.libro=l;}
}
