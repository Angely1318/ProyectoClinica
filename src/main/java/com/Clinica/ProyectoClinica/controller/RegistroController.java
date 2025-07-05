package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.RegistrarUsuario;
import com.Clinica.ProyectoClinica.repository.RegistrarUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired
    private RegistrarUsuarioRepository registrarUsuarioRepo;

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombres,
            @RequestParam String apellidos,
            @RequestParam String correo,
            @RequestParam String password,
            Model model) {


        if (registrarUsuarioRepo.findByCorreo(correo) != null) {
            model.addAttribute("mensaje", "Ya existe un usuario con este correo.");
            return "registro";
        }

        RegistrarUsuario nuevo = new RegistrarUsuario();
        nuevo.setNombres(nombres);
        nuevo.setApellidos(apellidos);
        nuevo.setCorreo(correo);
        nuevo.setPassword(password);

        registrarUsuarioRepo.save(nuevo);

        model.addAttribute("mensaje", "Usuario registrado correctamente. Ahora puede iniciar sesión.");
        return "login";
    }
}
