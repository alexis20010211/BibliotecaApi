package com.biblioteca.bibliotecaApi.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(String mensaje) {
        super(mensaje);
    }
}
