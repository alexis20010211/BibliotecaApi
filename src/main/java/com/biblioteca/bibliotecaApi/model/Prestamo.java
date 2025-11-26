package com.biblioteca.bibliotecaApi.model;

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

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public LocalDateTime getFechaPrestamo(){ return fechaPrestamo; }
    public void setFechaPrestamo(LocalDateTime fechaPrestamo){ this.fechaPrestamo = fechaPrestamo; }
    public LocalDateTime getFechaDevolucion(){ return fechaDevolucion; }
    public void setFechaDevolucion(LocalDateTime fechaDevolucion){ this.fechaDevolucion = fechaDevolucion; }
    public Boolean getDevuelto(){ return devuelto; }
    public void setDevuelto(Boolean devuelto){ this.devuelto = devuelto; }
    public Usuario getUsuario(){ return usuario; }
    public void setUsuario(Usuario usuario){ this.usuario = usuario; }
    public Libro getLibro(){ return libro; }
    public void setLibro(Libro libro){ this.libro = libro; }
}
