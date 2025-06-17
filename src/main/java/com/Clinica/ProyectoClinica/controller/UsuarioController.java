package com.Clinica.ProyectoClinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // <- usa este
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Clinica.ProyectoClinica.repository.UsuarioRepository;


@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios"; 
    }
}
