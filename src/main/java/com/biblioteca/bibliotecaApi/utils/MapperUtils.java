package com.biblioteca.bibliotecaApi.utils;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.dto.PrestamoDto;
import com.biblioteca.bibliotecaApi.dto.UsuarioDto;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.model.Prestamo;
import com.biblioteca.bibliotecaApi.model.Usuario;

public class MapperUtils {

    public static LibroDto libroToDto(Libro l){
        LibroDto d = new LibroDto();
        d.setId(l.getId()); d.setTitulo(l.getTitulo()); d.setAutor(l.getAutor());
        d.setStock(l.getStock()); d.setDisponible(l.getDisponible());
        return d;
    }

    public static Libro libroFromDto(LibroDto d){
        Libro l = new Libro();
        l.setTitulo(d.getTitulo()); l.setAutor(d.getAutor());
        l.setStock(d.getStock()); l.setDisponible(d.getDisponible());
        return l;
    }

    public static UsuarioDto usuarioToDto(Usuario u){
        UsuarioDto d = new UsuarioDto();
        d.setId(u.getId()); d.setUsername(u.getUsername()); d.setEmail(u.getEmail());
        return d;
    }

    public static PrestamoDto prestamoToDto(Prestamo p){
        PrestamoDto d = new PrestamoDto();
        d.setId(p.getId()); d.setUsuarioId(p.getUsuario()==null?null:p.getUsuario().getId());
        d.setLibroId(p.getLibro()==null?null:p.getLibro().getId());
        d.setFechaPrestamo(p.getFechaPrestamo()); d.setFechaDevolucion(p.getFechaDevolucion());
        d.setDevuelto(p.getDevuelto());
        return d;
    }
}
