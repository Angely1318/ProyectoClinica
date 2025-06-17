package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.Usuario;
import com.Clinica.ProyectoClinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@GetMapping("/login")
	public String mostrarFormulario() {
		return "login";
	}

	@PostMapping("/login")
	public String loginORegistro(@RequestParam String username, @RequestParam String password,
			@RequestParam(required = false) String rol, @RequestParam(required = false) String nombre,
			@RequestParam(required = false) String apellido, @RequestParam String registroActivo, Model model) {
		if ("true".equals(registroActivo)) {
			// LÓGICA DE REGISTRO
			if (usuarioRepo.findByUsername(username) != null) {
				model.addAttribute("error", "El correo electrónico ya está registrado.");
				return "login";
			}

			Usuario nuevo = new Usuario();
			nuevo.setUsername(username);
			nuevo.setPassword(password);
			nuevo.setRol(rol != null ? rol : "Paciente");
			nuevo.setNombre(nombre != null ? nombre + " " + (apellido != null ? apellido : "") : username);
			nuevo.setDireccion("No especificada");

			usuarioRepo.save(nuevo);
			model.addAttribute("mensaje", "Registro exitoso. Ahora puede iniciar sesión.");
			return "login";
		} else {
			// LÓGICA DE LOGIN
			Usuario usuario = usuarioRepo.findByUsername(username);
			if (usuario != null && usuario.getPassword().equals(password)) {
				model.addAttribute("usuario", usuario);
				return "redirect:/usuarios";
			} else {
				model.addAttribute("error", "Correo o contraseña inválidos.");
				return "login";
			}
		}
	}
}
