package com.biblioteca.bibliotecaApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.biblioteca.bibliotecaApi.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            //  Deshabilitar CSRF por usar JWT 
            .csrf(csrf -> csrf.disable())

            //  CORS deshabilitado 
            .cors(cors -> cors.disable())

            // AUTORIZACIONES
            .authorizeHttpRequests(auth -> auth

                // Endpoints públicos (login / registro / swagger)
                .requestMatchers(
                    "/auth/login",
                    "/auth/register",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/public/**",
                    "/error",
                    "/actuator/health"
                ).permitAll()

                // Rutas solo accesibles por administradores
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // Rutas para bibliotecarios
                .requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")

                // Cualquier otra ruta requiere autenticación con JWT
                .anyRequest().authenticated()
            )

            // Manejo de sesiones: stateless (sin sesiones)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Proveedor de autenticación (UserDetailsService + PasswordEncoder)
            .authenticationProvider(authenticationProvider)

            //  Filtro JWT antes del UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

            //  Desactivar login básico y formulario
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable())

            //  Dejar usar H2-console y otros iframes
            .headers(headers -> headers.frameOptions(options -> options.disable()))

            //  Manejo opcional de errores de seguridad
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, e) -> {
                    res.setStatus(401);
                    res.getWriter().write("No autorizado: necesitas un JWT válido.");
                })
                .accessDeniedHandler((req, res, e) -> {
                    res.setStatus(403);
                    res.getWriter().write("Acceso denegado: no tienes permisos suficientes.");
                })
            );

        return http.build();
    }

    // PasswordEncoder para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
