package com.biblioteca.bibliotecaApi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.repository.LibroRepository;
import com.biblioteca.bibliotecaApi.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro crear(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro crearDesdeDto(LibroDto libroDto) {
        Libro libro = new Libro();
        libro.setTitulo(libroDto.getTitulo());
        libro.setAutor(libroDto.getAutor());
        libro.setAnio(libroDto.getAnio());
        return libroRepository.save(libro);
    }

    @Override
    public Libro obtener(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    @Override
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        Libro existente = obtener(id);
        existente.setTitulo(libro.getTitulo());
        existente.setAutor(libro.getAutor());
        existente.setAnio(libro.getAnio());
        return libroRepository.save(existente);
    }

    @Override
    public Libro actualizarDesdeDto(Long id, LibroDto libroDto) {
        Libro libro = obtener(id);
        libro.setTitulo(libroDto.getTitulo());
        libro.setAutor(libroDto.getAutor());
        libro.setAnio(libroDto.getAnio());
        return libroRepository.save(libro);
    }

    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}
