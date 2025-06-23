package com.Clinica.ProyectoClinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutasController {


    @GetMapping("/")
    public String mostrarInicio(Model model) {
    	//contenido: nombre que le damos a la variable REPLACE para que dentro tenga contenido 
    	//formaPaciente: es el nombre de la pagina 
    	//contenido: el nombre que se le da al FRAGMENT que esta dentro de la pagina
        model.addAttribute("contenido", "fragments/bienvenida :: contenido"); 
        return "index"; 
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; 
    }
    
   
    
}
