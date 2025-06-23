package com.Clinica.ProyectoClinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.entity.Medico;
import com.Clinica.ProyectoClinica.service.MedicoService;

@Controller
public class MedicoController {

	private MedicoService medicoService;
	
	//entrar al formulario medico
    @GetMapping("/new-medico")
    public String newMedico(Model model) {
        model.addAttribute("medico", new Medico());
        model.addAttribute("contenido", "fragments/formMedico :: contenido");
        return "index";
    }
}
