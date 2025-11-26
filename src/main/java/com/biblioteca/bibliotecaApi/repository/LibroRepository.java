package com.biblioteca.bibliotecaApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.bibliotecaApi.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> { }
