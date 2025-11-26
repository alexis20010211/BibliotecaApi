package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.dto.LibroDto;

public interface LibroService {
    List<LibroDto> listar();
    LibroDto obtener(Long id);
    LibroDto crear(LibroDto dto);
    LibroDto actualizar(Long id, LibroDto dto);
    void eliminar(Long id);
}
