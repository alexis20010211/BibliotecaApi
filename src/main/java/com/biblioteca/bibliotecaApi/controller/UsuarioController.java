package com.biblioteca.bibliotecaApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.bibliotecaApi.dto.UsuarioDto;
import com.biblioteca.bibliotecaApi.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Solo ADMIN puede crear usuarios (alternativa: usar AuthController)
    @PreAuthorize("hasRol('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioDto> registrar(@Valid @RequestBody UsuarioDto usuarioDto) {
        UsuarioDto creado = usuarioService.registrar(usuarioDto);
        return ResponseEntity.ok(creado);
    }

    // Solo ADMIN puede ver usuarios
    @PreAuthorize("hasRol('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtenerPorId(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // Solo ADMIN puede consultar por email
    @PreAuthorize("hasRol('ADMIN')")
    @GetMapping("/email")
    public ResponseEntity<UsuarioDto> obtenerPorEmail(@RequestParam String email) {
        UsuarioDto usuario = usuarioService.obtenerPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    // Solo ADMIN puede listar usuarios
    @PreAuthorize("hasRol('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {
        List<UsuarioDto> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }

    // Solo ADMIN puede eliminar usuarios
    @PreAuthorize("hasRol('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
