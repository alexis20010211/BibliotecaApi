package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.model.Libro;

public interface LibroService {
    Libro crear(Libro libro);
    Libro crearDesdeDto(LibroDto libroDto);  // método agregado
    Libro obtener(Long id);
    List<Libro> listar();
    Libro actualizar(Long id, Libro libro);
    Libro actualizarDesdeDto(Long id, LibroDto libroDto); // método agregado
    void eliminar(Long id);
}
