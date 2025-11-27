package com.biblioteca.bibliotecaApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.bibliotecaApi.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    
    boolean existsByNombre(String nombre);

    Optional<Rol> findByNombre(String nombre);
}
