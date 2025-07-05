package com.Clinica.ProyectoClinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutasController {

    @GetMapping("/")
    public String mostrarPanel(Model model) {
        model.addAttribute("contenido", "fragments/bienvenida :: contenido");
        return "index";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }
}
