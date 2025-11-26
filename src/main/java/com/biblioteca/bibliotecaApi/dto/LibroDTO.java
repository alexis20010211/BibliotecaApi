package com.biblioteca.bibliotecaApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LibroDTO {

    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotNull
    private Integer anio;

    @NotNull
    private Integer stock;

    // getters y setters
}
