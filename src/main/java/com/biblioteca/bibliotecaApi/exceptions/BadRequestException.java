package com.biblioteca.bibliotecaApi.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
