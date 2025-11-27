package com.biblioteca.bibliotecaApi.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.bibliotecaApi.exceptions.ResourceNotFoundException;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.model.Prestamo;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.LibroRepository;
import com.biblioteca.bibliotecaApi.repository.PrestamoRepository;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import com.biblioteca.bibliotecaApi.service.PrestamoService;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               UsuarioRepository usuarioRepository,
                               LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public Prestamo registrarPrestamo(Long usuarioId, Long libroId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + usuarioId));

        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + libroId));

        // Validar si el libro ya está prestado
        boolean prestado = prestamoRepository.existsByLibroIdAndFechaDevolucionIsNull(libroId);
        if (prestado) {
            throw new IllegalStateException("El libro ya se encuentra prestado.");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setDevuelto(false);

        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo registrarDevolucion(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado con id: " + prestamoId));

        prestamo.setDevuelto(true);
        prestamo.setFechaDevolucion(LocalDate.now());

        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo obtener(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado con id: " + id));
    }

    @Override
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> listarPorUsuario(Long usuarioId) {
        // Usamos el método directo del repositorio
        return prestamoRepository.findByUsuarioId(usuarioId);
    }
}
