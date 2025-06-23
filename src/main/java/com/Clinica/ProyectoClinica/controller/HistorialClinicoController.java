package com.Clinica.ProyectoClinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Clinica.ProyectoClinica.entity.HistorialClinico;
import com.Clinica.ProyectoClinica.service.HistorialClinicoService;

@Controller
public abstract class HistorialClinicoController {
	
	private HistorialClinicoService historialClinicoService;
	
	//entrar al formulario cita
    @GetMapping("/new-historialClinico")
    public String newhistorialClinico(Model model) {
        model.addAttribute("historial", new HistorialClinico());
        model.addAttribute("contenido", "fragments/formHistorialClinico :: contenido");
        return "index";
    }
}
