package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.RegistrarUsuario;
import com.Clinica.ProyectoClinica.repository.RegistrarUsuarioRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutenticacionController {

    @Autowired
    private RegistrarUsuarioRepository registrarUsuarioRepo;

    @PostMapping("/login")
    public String procesarLogin(
        @RequestParam("username") String correo,
        @RequestParam("password") String password,
        Model model,
        HttpSession session
    ) {
        RegistrarUsuario ru = registrarUsuarioRepo.findByCorreo(correo);

        if (ru != null && ru.getPassword().equals(password)) {
            session.setAttribute("registrarUsuarioLogueado", ru);
            model.addAttribute("contenido", "fragments/bienvenida :: contenido");
            return "index";
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "login";
        }
        
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
