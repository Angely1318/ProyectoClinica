package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.service.CitaService;
import com.Clinica.ProyectoClinica.service.MedicoService;
import com.Clinica.ProyectoClinica.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    // Entrar al formulario para nueva cita
    @GetMapping("/new-cita")
    public String nuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("listaPacientes", pacienteService.listarTodos());
        model.addAttribute("listaMedicos", medicoService.listarTodos());
        model.addAttribute("contenido", "fragments/formCita :: contenido");
        return "index";
    }

    // Lista de citas con filtro
    @GetMapping("/list-cita")
    public String listarCitas(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        List<Cita> lista = (filtro != null && !filtro.isEmpty())
                ? citaService.buscarPorPacienteOMedico(filtro)
                : citaService.listarTodos();

        model.addAttribute("listaCitas", lista);
        model.addAttribute("filtro", filtro);
        model.addAttribute("contenido", "fragments/listCita :: contenido");
        return "index";
    }

    // Editar cita
    @GetMapping("/edit-cita/{id}")
    public String editarCita(@PathVariable Integer id, Model model) {
        try {
        	Cita cita = citaService.buscarPorId(id);
            model.addAttribute("cita", cita);
            model.addAttribute("listaPacientes", pacienteService.listarTodos());
            model.addAttribute("listaMedicos", medicoService.listarTodos());
            model.addAttribute("modo", "editar");
            model.addAttribute("contenido", "fragments/formCita :: contenido");
            return "index";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
    }

    // Ver cita
    @GetMapping("/view-cita/{id}")
    public String verCita(@PathVariable Integer id, Model model) {
        try {
        	Cita cita = citaService.buscarPorId(id);
            model.addAttribute("cita", cita);
            model.addAttribute("modo", "ver");
            model.addAttribute("contenido", "fragments/formCita :: contenido");
            return "index";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
    }

    // Eliminar cita
    @GetMapping("/remove-cita/{id}")
    public String eliminarCita(@PathVariable Integer id, RedirectAttributes attrs) {
        try {
            citaService.eliminar(id);
            attrs.addFlashAttribute("msgExito", "Cita eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Error al eliminar la cita.");
        }
        return "redirect:/list-cita";
    }

    // Guardar nueva cita
    @PostMapping("/save-new-cita")
    public String guardarNuevaCita(@ModelAttribute Cita cita, RedirectAttributes attrs) {
        try {
            citaService.crear(cita);
            attrs.addFlashAttribute("msgExito", "Cita registrada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Hubo un error al registrar la cita.");
        }
        return "redirect:/list-cita";
    }

    // Guardar edición de cita
    @PostMapping("/save-edit-cita")
    public String guardarEdicionCita(@ModelAttribute Cita cita, RedirectAttributes attrs) {
        try {
            citaService.editar(cita);
            attrs.addFlashAttribute("msgExito", "Cita actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "No se pudo actualizar la cita.");
        }
        return "redirect:/list-cita";
    }
}
