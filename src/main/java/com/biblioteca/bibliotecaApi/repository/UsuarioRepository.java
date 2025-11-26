package com.biblioteca.bibliotecaApi.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.bibliotecaApi.entity.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
