package com.biblioteca.bibliotecaApi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.service.LibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<LibroDto> crear(@RequestBody LibroDto libroDto) {
        Libro creado = libroService.crearDesdeDto(libroDto);
        LibroDto creadoDto = mapToDto(creado);
        return ResponseEntity.status(201).body(creadoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDto> obtener(@PathVariable Long id) {
        Libro libro = libroService.obtener(id);
        return ResponseEntity.ok(mapToDto(libro));
    }

    @GetMapping
    public ResponseEntity<List<LibroDto>> listar() {
        List<LibroDto> libros = libroService.listar()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(libros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroDto> actualizar(@PathVariable Long id, @RequestBody LibroDto libroDto) {
        Libro actualizado = libroService.actualizarDesdeDto(id, libroDto);
        return ResponseEntity.ok(mapToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Mapeo simple de entidad a DTO
    private LibroDto mapToDto(Libro libro) {
        LibroDto dto = new LibroDto();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setAnio(libro.getAnio());
        if (libro.getUsuario() != null) {
            dto.setUsuarioId(libro.getUsuario().getId());
        }
        return dto;
    }
}
