package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.model.Usuario;

public interface UsuarioService {

    Usuario registrar(Usuario usuario);

    Usuario obtenerPorId(Long id);

    Usuario obtenerPorEmail(String email);

    List<Usuario> listar();

    void eliminar(Long id);
}
