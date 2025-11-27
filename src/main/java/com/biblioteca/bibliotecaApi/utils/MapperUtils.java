package com.biblioteca.bibliotecaApi.utils;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.model.Libro;

public class MapperUtils {

    public static LibroDto mapLibroToDto(Libro libro) {
        LibroDto dto = new LibroDto();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setAnio(libro.getAnio());

        // Si el libro tiene usuario, asignamos su ID
        if (libro.getUsuario() != null) {
            dto.setUsuarioId(libro.getUsuario().getId());
        }

        return dto;
    }

    public static Libro mapDtoToLibro(LibroDto dto) {
        Libro libro = new Libro();
        libro.setId(dto.getId());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnio(dto.getAnio());
        // El usuario se puede asignar después si es necesario
        return libro;
    }
}
