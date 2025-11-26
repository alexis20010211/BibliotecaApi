package com.biblioteca.bibliotecaApi;

import com.biblioteca.bibliotecaApi.model.Rol;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.RolRepository;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BibliotecaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Seed inicial: roles y admin
    @Bean
    CommandLineRunner init(RolRepository rolRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (rolRepository.findByNombre("ADMIN").isEmpty()) {
                rolRepository.save(new Rol(null, "ADMIN"));
            }
            if (rolRepository.findByNombre("USUARIO").isEmpty()) {
                rolRepository.save(new Rol(null, "USUARIO"));
            }
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Rol adminRole = rolRepository.findByNombre("ADMIN").orElseThrow();
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setEmail("admin@biblioteca.edu");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.getRoles().add(adminRole);
                usuarioRepository.save(admin);
                System.out.println("Admin creado: user=admin pass=admin123");
            }
        };
    }
}
