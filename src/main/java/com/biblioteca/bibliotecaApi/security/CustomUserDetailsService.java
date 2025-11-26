package com.biblioteca.bibliotecaApi.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí normalmente iría la consulta a la base de datos.
        // Para prueba, devolvemos un usuario fijo.
        if ("admin".equals(username)) {
            return new CustomUserDetails(
                    "admin",
                    "{noop}admin123", // {noop} indica que la contraseña no está codificada
                    Collections.emptyList()
            );
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
    }
}
