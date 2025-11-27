package com.biblioteca.bibliotecaApi.service;

import java.util.List;

import com.biblioteca.bibliotecaApi.model.Prestamo;

public interface PrestamoService {

    Prestamo registrarPrestamo(Long usuarioId, Long libroId);

    Prestamo registrarDevolucion(Long prestamoId);

    Prestamo obtener(Long id);

    List<Prestamo> listar();

    List<Prestamo> listarPorUsuario(Long usuarioId);
}
