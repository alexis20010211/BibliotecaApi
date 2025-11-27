package com.biblioteca.bibliotecaApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info().title("Biblioteca API").version("1.0")
                .description("API REST para gestión de biblioteca - autenticación JWT y roles"));
    }
}
