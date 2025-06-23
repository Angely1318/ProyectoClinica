package com.Clinica.ProyectoClinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Clinica.ProyectoClinica.entity.Paciente;
import com.Clinica.ProyectoClinica.service.PacienteService;



@Controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    //entrar al formulario pacientes
    @GetMapping("/new-paciente")
    public String newPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("contenido", "fragments/formPaciente :: contenido");
        return "index";
    }
    
    @GetMapping("/list-paciente")
    public String listPaciente(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        List<Paciente> lista;
        if (filtro != null && !filtro.isEmpty()) {
            lista = pacienteService.buscarPorNombreODni(filtro);
        } else {
            lista = pacienteService.listarTodos();
        }
        model.addAttribute("listaPacientes", lista);
        model.addAttribute("filtro", filtro); // 👈 para mantener el valor en el input
        model.addAttribute("contenido", "fragments/listPaciente :: contenido");
        return "index";
    }


    
 // Página para editar paciente
    @GetMapping("/edit-paciente/{id}")
    public String editarPaciente(@PathVariable Integer id, Model model) {
        try {
            Paciente paciente = pacienteService.buscarPorId(id);
            model.addAttribute("paciente", paciente);
            model.addAttribute("modo", "editar");
            model.addAttribute("contenido", "fragments/formPaciente :: contenido");
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Página para visualizar paciente (modo solo lectura)
    @GetMapping("/view-paciente/{id}")
    public String verPaciente(@PathVariable Integer id, Model model) {
        try {
            Paciente paciente = pacienteService.buscarPorId(id);
            model.addAttribute("paciente", paciente);
            model.addAttribute("modo", "ver");
            model.addAttribute("contenido", "fragments/formPaciente :: contenido");
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Eliminar paciente
    @GetMapping("/remove-paciente/{id}")
    public String eliminarPaciente(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        try {
            pacienteService.eliminar(id);
            redirectAttrs.addFlashAttribute("msgExito", "Paciente eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("msgError", "Error al eliminar paciente.");
        }
        return "redirect:/list-paciente";
    }

    // Guardar nuevo paciente
    @PostMapping("/save-new-paciente")
    public String guardarNuevoPaciente(@ModelAttribute Paciente paciente, RedirectAttributes redirectAttrs) {
        try {
            pacienteService.crear(paciente);
            redirectAttrs.addFlashAttribute("msgExito", "Paciente registrado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("msgError", "Hubo un error al registrar el paciente.");
        }
        return "redirect:/list-paciente";
    }

    // Guardar edición de paciente
    @PostMapping("/save-edit-paciente")
    public String guardarEdicionPaciente(@ModelAttribute Paciente paciente, RedirectAttributes redirectAttrs) {
        try {
            pacienteService.editar(paciente);
            redirectAttrs.addFlashAttribute("msgExito", "Paciente actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("msgError", "No se pudo actualizar el paciente.");
        }
        return "redirect:/list-paciente";
    }


 
}
