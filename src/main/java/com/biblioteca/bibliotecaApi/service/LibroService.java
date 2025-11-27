package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.model.Libro;

public interface LibroService {

    Libro crear(Libro libro);

    Libro obtener(Long id);

    List<Libro> listar();

    Libro actualizar(Long id, Libro libro);

    void eliminar(Long id);
}
