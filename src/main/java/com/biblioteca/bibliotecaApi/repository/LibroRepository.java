package com.biblioteca.bibliotecaApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.bibliotecaApi.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar todos los libros registrados por un usuario específico
    List<Libro> findByUsuarioId(Long usuarioId);

    // Ejemplo adicional opcional
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
