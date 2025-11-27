package com.biblioteca.bibliotecaApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Endpoint público de prueba
    @GetMapping("/")
    public String home() {
        return "Servidor funcionando!";
    }
}
