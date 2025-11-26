package com.biblioteca.bibliotecaApi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.bibliotecaApi.dto.AuthRequest;
import com.biblioteca.bibliotecaApi.dto.AuthResponse;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import com.biblioteca.bibliotecaApi.security.JwtService;
import com.biblioteca.bibliotecaApi.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService,
                          UsuarioRepository usuarioRepository,
                          UsuarioService usuarioService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    // ------------------- REGISTRO -------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {

        usuarioService.registrar(
                req.getUsername(),
                req.getUsername() + "@example.com",
                req.getPassword()
        );

        return ResponseEntity.ok("Registrado");
    }

    // ------------------- LOGIN -------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            // Autenticar credenciales
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()
                    )
            );

            // Registrar autenticación en el contexto
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Buscar usuario
            Usuario usuario = usuarioRepository.findByUsername(req.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Generar token JWT usando username
            String token = jwtService.generateToken(usuario.getUsername());

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
