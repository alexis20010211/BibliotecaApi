package com.biblioteca.bibliotecaApi.dto;

import java.time.LocalDate;

public class PrestamoDTO {

    private Long id;
    private Long usuarioId;
    private Long libroId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // getters y setters
}
