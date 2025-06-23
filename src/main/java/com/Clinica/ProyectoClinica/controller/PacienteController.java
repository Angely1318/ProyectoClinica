package com.Clinica.ProyectoClinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Clinica.ProyectoClinica.entity.Paciente;
import com.Clinica.ProyectoClinica.repository.PacienteRepository;



@Controller
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepo;

    //entrar al formulario pacientes
    @GetMapping("/new-paciente")
    public String newPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("contenido", "fragments/formPaciente :: contenido");
        return "index";
    }
    
    //listar a los pacientes
    public String listPaciente() {
    	return null;
    }

 
}
