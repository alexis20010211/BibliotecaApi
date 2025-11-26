package com.biblioteca.bibliotecaApi.service.impl;

import com.biblioteca.bibliotecaApi.dto.UsuarioDto;
import com.biblioteca.bibliotecaApi.exceptions.BadRequestException;
import com.biblioteca.bibliotecaApi.exceptions.ResourceNotFoundException;
import com.biblioteca.bibliotecaApi.model.Rol;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.RolRepository;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import com.biblioteca.bibliotecaApi.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, RolRepository rolRepo, PasswordEncoder encoder){
        this.usuarioRepo = usuarioRepo; this.rolRepo = rolRepo; this.encoder = encoder;
    }

    @Override
    public UsuarioDto registrar(String username, String email, String password) {
        if(usuarioRepo.existsByUsername(username)) throw new BadRequestException("Username ya existe");
        if(usuarioRepo.existsByEmail(email)) throw new BadRequestException("Email ya existe");
        Usuario u = new Usuario();
        u.setUsername(username); u.setEmail(email); u.setPassword(encoder.encode(password));
        Rol r = rolRepo.findByNombre("USUARIO").orElseThrow();
        u.setRoles(new HashSet<>()); u.getRoles().add(r);
        Usuario saved = usuarioRepo.save(u);
        UsuarioDto dto = new UsuarioDto(); dto.setId(saved.getId()); dto.setUsername(saved.getUsername()); dto.setEmail(saved.getEmail());
        return dto;
    }

    @Override
    public UsuarioDto obtener(Long id) {
        Usuario u = usuarioRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        UsuarioDto d = new UsuarioDto(); d.setId(u.getId()); d.setUsername(u.getUsername()); d.setEmail(u.getEmail());
        return d;
    }

    @Override
    public List<UsuarioDto> listar() {
        return usuarioRepo.findAll().stream().map(u -> {
            UsuarioDto d = new UsuarioDto(); d.setId(u.getId()); d.setUsername(u.getUsername()); d.setEmail(u.getEmail()); return d;
        }).toList();
    }
}
