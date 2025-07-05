package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.Usuario;
import com.Clinica.ProyectoClinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/registro")
    public String registrarUsuario(
            String nombre,
            String apellido,
            String username,
            String password,
            Model model) {

      
        if (usuarioRepo.findByUsername(username) != null) {
            model.addAttribute("mensaje", "El usuario ya existe.");
            return "registro";
        }

        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setPassword(password);
        nuevo.setRol(Usuario.Rol.RECEPCIONISTA); // rol por defecto
        nuevo.setEstado(Usuario.Estado.ACTIVO);

        usuarioRepo.save(nuevo);

        model.addAttribute("mensaje", "Usuario registrado correctamente. Inicie sesión.");
        return "login";
    }
}
