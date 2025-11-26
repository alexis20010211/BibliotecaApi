package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.dto.UsuarioDto;

public interface UsuarioService {
    UsuarioDto registrar(String username, String email, String password);
    UsuarioDto obtener(Long id);
    List<UsuarioDto> listar();
}
