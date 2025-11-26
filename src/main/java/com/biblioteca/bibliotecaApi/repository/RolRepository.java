package com.biblioteca.bibliotecaApi.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.bibliotecaApi.entity.Rol;
public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByName(String name);
}
