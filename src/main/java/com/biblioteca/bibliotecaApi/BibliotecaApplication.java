package com.biblioteca.bibliotecaApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biblioteca.bibliotecaApi.model.Rol;
import com.biblioteca.bibliotecaApi.model.Usuario;
import com.biblioteca.bibliotecaApi.repository.RolRepository;
import com.biblioteca.bibliotecaApi.repository.UsuarioRepository;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @Bean(name = "initData")
    public CommandLineRunner initData(
            RolRepository rolRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // Crear roles si no existen
            if (rolRepository.findByNombre("ADMIN").isEmpty()) {
                rolRepository.save(new Rol(null, "ADMIN"));
            }
            if (rolRepository.findByNombre("USUARIO").isEmpty()) {
                rolRepository.save(new Rol(null, "USUARIO"));
            }

            // Crear usuario administrador si no existe
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Rol adminRole = rolRepository.findByNombre("ADMIN").orElseThrow();

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setEmail("admin@biblioteca.edu");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.getRoles().add(adminRole);

                usuarioRepository.save(admin);

                System.out.println("✔ Usuario admin creado: user=admin, pass=admin123");
            }
        };
    }
}
