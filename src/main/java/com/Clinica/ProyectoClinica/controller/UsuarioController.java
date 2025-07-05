package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/usuarios")
	public String listarUsuarios(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		model.addAttribute("contenido", "fragments/listUsuarios :: contenido");
		return "index";
	}
}
