package com.biblioteca.bibliotecaApi.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.biblioteca.bibliotecaApi.model.Rol;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.RolRepository;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // ============================
        // CREAR ROLES SI NO EXISTEN
        // ============================
        crearRolSiNoExiste("ROLE_USER");
        crearRolSiNoExiste("ROLE_ADMIN");

        // ============================
        // CREAR ADMIN DEFAULT
        // ============================
        if (!usuarioRepo.existsByUsername("admin")) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@biblioteca.com"); // CORRECCIÓN
            admin.setActivo(true); // usuario activo por defecto

            Set<Rol> roles = new HashSet<>();
            roles.add(obtenerRol("ROLE_ADMIN"));
            roles.add(obtenerRol("ROLE_USER"));
            admin.setRoles(roles);

            usuarioRepo.save(admin);
            System.out.println("✔ Usuario ADMIN creado.");
        }

        // ============================
        // CREAR USUARIO DEFAULT
        // ============================
        if (!usuarioRepo.existsByUsername("user")) {
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setEmail("user@biblioteca.com"); // CORRECCIÓN
            user.setActivo(true);

            Set<Rol> roles = new HashSet<>();
            roles.add(obtenerRol("ROLE_USER"));
            user.setRoles(roles);

            usuarioRepo.save(user);
            System.out.println("✔ Usuario USER creado.");
        }

        System.out.println("✔ DataLoader ejecutado correctamente.");
    }

    // ============================
    // MÉTODOS AUXILIARES
    // ============================

    private void crearRolSiNoExiste(String nombreRol) {
        if (!rolRepo.existsByNombre(nombreRol)) {
            rolRepo.save(new Rol(nombreRol));
            System.out.println("✔ Rol creado: " + nombreRol);
        }
    }

    private Rol obtenerRol(String nombreRol) {
        return rolRepo.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));
    }
}
