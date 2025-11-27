package com.biblioteca.bibliotecaApi.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String mensaje) {
        super(mensaje);
    }
}
