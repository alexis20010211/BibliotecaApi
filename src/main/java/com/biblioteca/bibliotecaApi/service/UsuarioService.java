package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.dto.UsuarioDto;

public interface UsuarioService {

    // Registra un nuevo usuario
    UsuarioDto registrar(UsuarioDto usuarioDto);

    // Busca un usuario por ID
    UsuarioDto obtenerPorId(Long id);

    // Busca un usuario por email
    UsuarioDto obtenerPorEmail(String email);

    // Lista todos los usuarios
    List<UsuarioDto> listar();

    // Elimina un usuario por ID
    void eliminar(Long id);
}
