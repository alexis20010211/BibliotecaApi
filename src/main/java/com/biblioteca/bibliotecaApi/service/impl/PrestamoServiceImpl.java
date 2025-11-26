package com.biblioteca.bibliotecaApi.service.impl;

import com.biblioteca.bibliotecaApi.dto.PrestamoDto;
import com.biblioteca.bibliotecaApi.exceptions.BadRequestException;
import com.biblioteca.bibliotecaApi.exceptions.ResourceNotFoundException;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.model.Prestamo;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.LibroRepository;
import com.biblioteca.bibliotecaApi.repository.PrestamoRepository;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import com.biblioteca.bibliotecaApi.service.PrestamoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepo;
    private final UsuarioRepository usuarioRepo;
    private final LibroRepository libroRepo;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepo, UsuarioRepository usuarioRepo, LibroRepository libroRepo){
        this.prestamoRepo = prestamoRepo; this.usuarioRepo = usuarioRepo; this.libroRepo = libroRepo;
    }

    private static PrestamoDto toDto(Prestamo p){
        PrestamoDto d = new PrestamoDto();
        d.setId(p.getId()); d.setUsuarioId(p.getUsuario()==null?null:p.getUsuario().getId());
        d.setLibroId(p.getLibro()==null?null:p.getLibro().getId());
        d.setFechaPrestamo(p.getFechaPrestamo()); d.setFechaDevolucion(p.getFechaDevolucion()); d.setDevuelto(p.getDevuelto());
        return d;
    }

    @Override
    public PrestamoDto registrar(PrestamoDto dto) {
        Usuario u = usuarioRepo.findById(dto.getUsuarioId()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Libro l = libroRepo.findById(dto.getLibroId()).orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
        if (l.getStock() == null || l.getStock() <= 0) throw new BadRequestException("No hay stock disponible");
        l.setStock(l.getStock() - 1); if (l.getStock() == 0) l.setDisponible(false); libroRepo.save(l);
        Prestamo p = new Prestamo(); p.setUsuario(u); p.setLibro(l); p.setFechaPrestamo(LocalDateTime.now()); p.setDevuelto(false);
        Prestamo saved = prestamoRepo.save(p); return toDto(saved);
    }

    @Override
    public PrestamoDto devolver(Long id) {
        Prestamo p = prestamoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado"));
        if (p.getDevuelto()) throw new BadRequestException("Prestamo ya devuelto");
        p.setDevuelto(true); p.setFechaDevolucion(LocalDateTime.now());
        Libro l = p.getLibro(); l.setStock(l.getStock() + 1); l.setDisponible(true); libroRepo.save(l);
        return toDto(prestamoRepo.save(p));
    }

    @Override
    public List<PrestamoDto> historialPorUsuario(Long usuarioId) {
        return prestamoRepo.findByUsuarioId(usuarioId).stream().map(PrestamoServiceImpl::toDto).toList();
    }
}
