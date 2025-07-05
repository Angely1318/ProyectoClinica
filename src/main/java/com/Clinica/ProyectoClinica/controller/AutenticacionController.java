package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.Usuario;
import com.Clinica.ProyectoClinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutenticacionController {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@PostMapping("/login")
	public String procesarLogin(String username, String password, Model model) {
		Usuario usuario = usuarioRepo.findByUsername(username);
		if (usuario != null && usuario.getPassword().equals(password) && usuario.getEstado() == Usuario.Estado.ACTIVO) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("contenido", "fragments/bienvenida :: contenido");
			return "index";
		} else {
			model.addAttribute("mensaje", "Credenciales inválidas o usuario inactivo");
			return "login";
		}
	}
}
