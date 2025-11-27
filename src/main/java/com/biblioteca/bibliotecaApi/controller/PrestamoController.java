package com.biblioteca.bibliotecaApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.bibliotecaApi.model.Prestamo;
import com.biblioteca.bibliotecaApi.service.PrestamoService;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @Autowired
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Prestamo> registrarPrestamo(@RequestParam Long usuarioId,
                                                      @RequestParam Long libroId) {
        Prestamo prestamo = prestamoService.registrarPrestamo(usuarioId, libroId);
        return ResponseEntity.ok(prestamo);
    }

    @PostMapping("/devolver/{prestamoId}")
    public ResponseEntity<Prestamo> registrarDevolucion(@PathVariable Long prestamoId) {
        Prestamo prestamo = prestamoService.registrarDevolucion(prestamoId);
        return ResponseEntity.ok(prestamo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtener(@PathVariable Long id) {
        Prestamo prestamo = prestamoService.obtener(id);
        return ResponseEntity.ok(prestamo);
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> listar() {
        List<Prestamo> prestamos = prestamoService.listar();
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Prestamo> prestamos = prestamoService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(prestamos);
    }
}
