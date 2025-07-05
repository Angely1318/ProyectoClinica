package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.RegistrarUsuario;
import com.Clinica.ProyectoClinica.repository.RegistrarUsuarioRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrarUsuarioController {

    @Autowired
    private RegistrarUsuarioRepository registrarUsuarioRepo;

    @GetMapping("/registrarusuario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarioNuevo", new RegistrarUsuario());
        return "formRegistrarUsuario";
    }

    @PostMapping("/registrarusuario")
    public String procesarRegistro(@ModelAttribute("usuarioNuevo") RegistrarUsuario usuarioNuevo, Model model) {
        // podrías agregar validaciones de correo duplicado aquí
        registrarUsuarioRepo.save(usuarioNuevo);
        model.addAttribute("mensaje", "Registro exitoso. Ahora puede iniciar sesión.");
        return "login";
    }

    @PostMapping("/login-registrado")
    public String loginRegistrado(String correo, String password, Model model, HttpSession session) {
        RegistrarUsuario ru = registrarUsuarioRepo.findByCorreo(correo);

        if (ru != null && ru.getPassword().equals(password)) {
            session.setAttribute("registrarUsuarioLogueado", ru);
            model.addAttribute("contenido", "fragments/bienvenidaRegistrado :: contenido");
            return "index";
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "login";
        }
    }
}
