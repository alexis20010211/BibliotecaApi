package com.biblioteca.bibliotecaApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.bibliotecaApi.dto.PrestamoDto;
import com.biblioteca.bibliotecaApi.service.PrestamoService;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService service;
    public PrestamoController(PrestamoService service){ this.service = service; }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PrestamoDto> registrar(@RequestBody PrestamoDto dto){ return ResponseEntity.ok(service.registrar(dto)); }

    @PutMapping("/{id}/devolver")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PrestamoDto> devolver(@PathVariable Long id){ return ResponseEntity.ok(service.devolver(id)); }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public ResponseEntity<List<PrestamoDto>> historial(@PathVariable Long usuarioId){ return ResponseEntity.ok(service.historialPorUsuario(usuarioId)); }
}
