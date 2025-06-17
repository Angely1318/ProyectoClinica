package com.Clinica.ProyectoClinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Clinica.ProyectoClinica.entity.Paciente;
import com.Clinica.ProyectoClinica.repository.PacienteRepository;



@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", pacienteRepo.findAll());
        return "pacientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "formPaciente";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Paciente paciente) {
        pacienteRepo.save(paciente);
        return "redirect:/pacientes";
    }
}
