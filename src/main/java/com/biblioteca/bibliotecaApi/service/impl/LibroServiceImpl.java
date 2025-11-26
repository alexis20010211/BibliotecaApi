package com.biblioteca.bibliotecaApi.service.impl;

import com.biblioteca.bibliotecaApi.dto.LibroDto;
import com.biblioteca.bibliotecaApi.exceptions.ResourceNotFoundException;
import com.biblioteca.bibliotecaApi.model.Libro;
import com.biblioteca.bibliotecaApi.repository.LibroRepository;
import com.biblioteca.bibliotecaApi.service.LibroService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repo;
    public LibroServiceImpl(LibroRepository repo){ this.repo = repo; }

    private static LibroDto toDto(Libro l){
        LibroDto d = new LibroDto();
        d.setId(l.getId()); d.setTitulo(l.getTitulo()); d.setAutor(l.getAutor());
        d.setStock(l.getStock()); d.setDisponible(l.getDisponible());
        return d;
    }

    private static Libro toEntity(LibroDto d){
        Libro l = new Libro();
        l.setTitulo(d.getTitulo()); l.setAutor(d.getAutor());
        l.setStock(d.getStock() == null ? 1 : d.getStock()); l.setDisponible(d.getDisponible() == null ? true : d.getDisponible());
        return l;
    }

    @Override public List<LibroDto> listar(){ return repo.findAll().stream().map(LibroServiceImpl::toDto).toList(); }
    @Override public LibroDto obtener(Long id){ return repo.findById(id).map(LibroServiceImpl::toDto).orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado")); }
    @Override public LibroDto crear(LibroDto dto){ return toDto(repo.save(toEntity(dto))); }
    @Override public LibroDto actualizar(Long id, LibroDto dto){
        Libro l = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
        l.setTitulo(dto.getTitulo()); l.setAutor(dto.getAutor()); l.setStock(dto.getStock()); l.setDisponible(dto.getDisponible());
        return toDto(repo.save(l));
    }
    @Override public void eliminar(Long id){ if(!repo.existsById(id)) throw new ResourceNotFoundException("Libro no encontrado"); repo.deleteById(id); }
}
