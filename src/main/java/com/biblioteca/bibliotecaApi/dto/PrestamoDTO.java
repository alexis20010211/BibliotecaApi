package com.biblioteca.bibliotecaApi.dto;

import java.time.LocalDateTime;
public class PrestamoDto {
    private Long id;
    private Long usuarioId;
    private Long libroId;
    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevolucion;
    private Boolean devuelto;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getUsuarioId(){return usuarioId;} public void setUsuarioId(Long u){this.usuarioId=u;}
    public Long getLibroId(){return libroId;} public void setLibroId(Long l){this.libroId=l;}
    public LocalDateTime getFechaPrestamo(){return fechaPrestamo;} public void setFechaPrestamo(LocalDateTime f){this.fechaPrestamo=f;}
    public LocalDateTime getFechaDevolucion(){return fechaDevolucion;} public void setFechaDevolucion(LocalDateTime f){this.fechaDevolucion=f;}
    public Boolean getDevuelto(){return devuelto;} public void setDevuelto(Boolean d){this.devuelto=d;}
}
