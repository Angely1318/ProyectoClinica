package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.Medico;
import com.Clinica.ProyectoClinica.entity.Especialidad;
import com.Clinica.ProyectoClinica.service.MedicoService;
import com.Clinica.ProyectoClinica.service.EspecialidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/new-medico")
    public String nuevoMedico(Model model) {
        model.addAttribute("medico", new Medico());
        model.addAttribute("listaEspecialidades", especialidadService.listarTodos());
        model.addAttribute("contenido", "fragments/formMedico :: contenido");
        return "index";
    }

    @GetMapping("/list-medico")
    public String listarMedicos(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        List<Medico> lista = (filtro != null && !filtro.isEmpty()) ?
                medicoService.buscarPorNombreODni(filtro) :
                medicoService.listarTodos();

        model.addAttribute("listaMedicos", lista);
        model.addAttribute("filtro", filtro);
        model.addAttribute("contenido", "fragments/listMedico :: contenido");
        return "index";
    }

    @GetMapping("/edit-medico/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        try {
        	Medico medico = medicoService.buscarPorId(id);
            model.addAttribute("medico", medico);
            model.addAttribute("listaEspecialidades", especialidadService.listarTodos());
            model.addAttribute("modo", "editar");
            model.addAttribute("contenido", "fragments/formMedico :: contenido");
            return "index";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
    }

    @GetMapping("/view-medico/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        try {
        	Medico medico = medicoService.buscarPorId(id);
            model.addAttribute("medico", medico);
            model.addAttribute("modo", "ver");
            model.addAttribute("contenido", "fragments/formMedico :: contenido");
            return "index";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
    }

    @GetMapping("/remove-medico/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes attrs) {
        try {
            medicoService.eliminar(id);
            attrs.addFlashAttribute("msgExito", "Médico eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Error al eliminar médico.");
        }
        return "redirect:/list-medico";
    }

    @PostMapping("/save-new-medico")
    public String guardarNuevo(@ModelAttribute Medico medico, RedirectAttributes attrs) {
        try {
        	Especialidad especialidad = especialidadService.buscarPorId(medico.getEspecialidad().getId());
        	medico.setEspecialidad(especialidad);
            medicoService.crear(medico);
            attrs.addFlashAttribute("msgExito", "Médico registrado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Error al registrar médico.");
        }
        return "redirect:/list-medico";
    }

    @PostMapping("/save-edit-medico")
    public String guardarEdicion(@ModelAttribute Medico medico, RedirectAttributes attrs) {
        try {
            medicoService.editar(medico);
            attrs.addFlashAttribute("msgExito", "Médico actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "No se pudo actualizar el médico.");
        }
        return "redirect:/list-medico";
    }
}
