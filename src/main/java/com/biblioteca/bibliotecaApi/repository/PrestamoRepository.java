package com.biblioteca.bibliotecaApi.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.bibliotecaApi.entity.Prestamo;
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
    List<Prestamo> findByUsuarioId(Long usuarioId);
}
