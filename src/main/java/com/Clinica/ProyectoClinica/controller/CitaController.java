package com.Clinica.ProyectoClinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.entity.Paciente;
import com.Clinica.ProyectoClinica.service.CitaService;

@Controller
public class CitaController {

	private CitaService citaService;
	
	//entrar al formulario cita
    @GetMapping("/new-cita")
    public String newCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("contenido", "fragments/formCita :: contenido");
        return "index";
    }
    
}
