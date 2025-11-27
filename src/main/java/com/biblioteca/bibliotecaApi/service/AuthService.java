package com.biblioteca.bibliotecaApi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import com.biblioteca.bibliotecaApi.security.CustomUserDetails;
import com.biblioteca.bibliotecaApi.security.CustomUserDetailsService;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    // =========================================
    // REGISTRAR USUARIO
    // =========================================
    public String register(Usuario usuario) {
        // Validaciones básicas
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        // Guardar usuario en BD
        usuarioRepository.save(usuario);

        // Cargar detalles del usuario y generar token JWT
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
                .loadUserByUsername(usuario.getUsername());

        return jwtService.generateToken(userDetails);
    }

    // =========================================
    // LOGIN USUARIO
    // =========================================
    public String login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar contraseña
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Cargar detalles del usuario y generar token JWT
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
                .loadUserByUsername(username);

        return jwtService.generateToken(userDetails);
    }
}
