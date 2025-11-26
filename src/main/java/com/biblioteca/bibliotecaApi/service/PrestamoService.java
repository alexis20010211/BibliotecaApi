package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.dto.PrestamoDto;

public interface PrestamoService {
    PrestamoDto registrar(PrestamoDto dto);
    PrestamoDto devolver(Long id);
    List<PrestamoDto> historialPorUsuario(Long usuarioId);
}
