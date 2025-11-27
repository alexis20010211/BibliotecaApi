package com.biblioteca.bibliotecaApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.bibliotecaApi.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByUsuarioId(Long usuarioId);

    List<Prestamo> findByLibroId(Long libroId);

    boolean existsByLibroIdAndFechaDevolucionIsNull(Long libroId); // para saber si está prestado
}
