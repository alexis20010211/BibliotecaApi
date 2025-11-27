package com.biblioteca.bibliotecaApi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.bibliotecaApi.exceptions.ResourceNotFoundException;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.repository.LibroRepository;
import com.biblioteca.bibliotecaApi.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro crear(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro obtener(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + id));
    }

    @Override
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @Override
    public Libro actualizar(Long id, Libro libroActualizado) {
        Libro libroExistente = obtener(id);

        libroExistente.setTitulo(libroActualizado.getTitulo());
        libroExistente.setAutor(libroActualizado.getAutor());
        libroExistente.setAnio(libroActualizado.getAnio());
        libroExistente.setUsuario(libroActualizado.getUsuario());

        return libroRepository.save(libroExistente);
    }

    @Override
    public void eliminar(Long id) {
        Libro libroExistente = obtener(id);
        libroRepository.delete(libroExistente);
    }
}
