package com.biblioteca.bibliotecaApi.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.bibliotecaApi.dto.UsuarioDto;
import com.biblioteca.bibliotecaApi.model.Rol;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.RolRepository;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import com.biblioteca.bibliotecaApi.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDto registrar(UsuarioDto dto) {

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setActivo(dto.isActivo());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        // ============================
        // Asignación de roles
        // ============================
        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {

            Set<Rol> roles = dto.getRoles().stream()
                    .map(nombreRol -> rolRepository.findByNombre(nombreRol)
                            .orElseThrow(() ->
                                    new RuntimeException("El rol " + nombreRol + " no existe")))
                    .collect(Collectors.toSet());

            usuario.setRoles(roles);

        } else {
            // Rol por defecto
            Rol defaultRol = rolRepository.findByNombre("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("El rol ROLE_USER no existe"));

            usuario.setRoles(Set.of(defaultRol));
        }

        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioDto.fromEntity(guardado);
    }

    @Override
    public UsuarioDto obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDto::fromEntity)
                .orElse(null);
    }

    @Override
    public UsuarioDto obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(UsuarioDto::fromEntity)
                .orElse(null);
    }

    @Override
    public List<UsuarioDto> listar() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
