package com.biblioteca.bibliotecaApi.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.repository.LibroRepository;
import com.biblioteca.bibliotecaApi.service.LibroService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final @NonNull LibroRepository libroRepository;

    private @NonNull LibroDto mapToDto(@NonNull Libro libro) {
        return new LibroDto(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getStock(),
                libro.getDisponible()
        );
    }

    private @NonNull Libro mapToEntity(@NonNull LibroDto dto) {
        Libro libro = new Libro();
        libro.setId(dto.getId());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setStock(dto.getStock());
        libro.setDisponible(dto.getDisponible());
        return libro;
    }

    @Override
    public @NonNull List<LibroDto> listar() {
        return libroRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public @NonNull LibroDto obtener(@NonNull Long id) {
        Libro libro = Objects.requireNonNull(
                libroRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id))
        );
        return mapToDto(libro);
    }

    @Override
    public @NonNull LibroDto crear(@NonNull LibroDto dto) {
        Libro libro = mapToEntity(dto);
        Libro guardado = Objects.requireNonNull(libroRepository.save(libro));
        return mapToDto(guardado);
    }

    @Override
    public @NonNull LibroDto actualizar(@NonNull Long id, @NonNull LibroDto dto) {
        Libro libroExistente = Objects.requireNonNull(
                libroRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id))
        );

        libroExistente.setTitulo(dto.getTitulo());
        libroExistente.setAutor(dto.getAutor());
        libroExistente.setStock(dto.getStock());
        libroExistente.setDisponible(dto.getDisponible());

        Libro actualizado = Objects.requireNonNull(libroRepository.save(libroExistente));
        return mapToDto(actualizado);
    }

    @Override
    public void eliminar(@NonNull Long id) {
        Libro libro = Objects.requireNonNull(
                libroRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id))
        );
        libroRepository.delete(libro);
    }
}
