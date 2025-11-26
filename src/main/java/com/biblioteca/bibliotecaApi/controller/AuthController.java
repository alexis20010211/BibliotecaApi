package com.biblioteca.bibliotecaApi.controller;

import com.biblioteca.bibliotecaApi.dto.AuthRequest;
import com.biblioteca.bibliotecaApi.dto.AuthResponse;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import com.biblioteca.bibliotecaApi.security.JwtService;
import com.biblioteca.bibliotecaApi.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req){
        usuarioService.registrar(req.getUsername(), req.getUsername()+"@example.com", req.getPassword());
        return ResponseEntity.ok("Registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req){
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            Usuario u = usuarioRepository.findByUsername(req.getUsername()).orElseThrow();
            String token = jwtService.generateToken(u.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException ex){
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
