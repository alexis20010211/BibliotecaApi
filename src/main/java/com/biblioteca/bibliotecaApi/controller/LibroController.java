package com.biblioteca.bibliotecaApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.service.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService service;
    public LibroController(LibroService service){ this.service = service; }

    @GetMapping
    public List<LibroDto> listar(){ return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDto> obtener(@PathVariable Long id){ return ResponseEntity.ok(service.obtener(id)); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LibroDto> crear(@RequestBody LibroDto dto){ return ResponseEntity.ok(service.crear(dto)); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LibroDto> actualizar(@PathVariable Long id, @RequestBody LibroDto dto){ return ResponseEntity.ok(service.actualizar(id, dto)); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable Long id){ service.eliminar(id); return ResponseEntity.noContent().build(); }
}
