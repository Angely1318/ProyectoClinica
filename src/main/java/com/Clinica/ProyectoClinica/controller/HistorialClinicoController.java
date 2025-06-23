package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.HistorialClinico;
import com.Clinica.ProyectoClinica.service.HistorialClinicoService;
import com.Clinica.ProyectoClinica.service.MedicoService;
import com.Clinica.ProyectoClinica.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoService historialService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/new-historialClinico")
    public String nuevoHistorial(Model model) {
        model.addAttribute("historial", new HistorialClinico());
        model.addAttribute("listaPacientes", pacienteService.listarTodos());
        model.addAttribute("listaMedicos", medicoService.listarTodos());
        model.addAttribute("contenido", "fragments/formHistorialClinico :: contenido");
        return "index";
    }

    @GetMapping("/list-historial")
    public String listarHistorial(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        List<HistorialClinico> lista = (filtro != null && !filtro.isEmpty()) ?
                historialService.buscarPorPacienteOMedico(filtro) :
                historialService.listarTodos();

        model.addAttribute("listaHistoriales", lista);
        model.addAttribute("filtro", filtro);
        model.addAttribute("contenido", "fragments/listHistorialClinico :: contenido");
        return "index";
    }

    @GetMapping("/edit-historial/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        try {
        	HistorialClinico historial = historialService.buscarPorId(id);
            model.addAttribute("historial", historial);
            model.addAttribute("listaPacientes", pacienteService.listarTodos());
            model.addAttribute("listaMedicos", medicoService.listarTodos());
            model.addAttribute("modo", "editar");
            model.addAttribute("contenido", "fragments/formHistorial :: contenido");
            return "index";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
    }

    @GetMapping("/view-historial/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        try {
        	HistorialClinico historial = historialService.buscarPorId(id);
            model.addAttribute("historial", historial);
            model.addAttribute("modo", "ver");
            model.addAttribute("contenido", "fragments/formHistorial :: contenido");
            return "index";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
    }

    @GetMapping("/remove-historial/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes attrs) {
        try {
            historialService.eliminar(id);
            attrs.addFlashAttribute("msgExito", "Historial eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Error al eliminar historial.");
        }
        return "redirect:/list-historial";
    }

    @PostMapping("/save-new-historial")
    public String guardarNuevo(@ModelAttribute HistorialClinico historial, RedirectAttributes attrs) {
        try {
            historialService.crear(historial);
            attrs.addFlashAttribute("msgExito", "Historial registrado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Error al registrar historial.");
        }
        return "redirect:/list-historial";
    }

    @PostMapping("/save-edit-historial")
    public String guardarEdicion(@ModelAttribute HistorialClinico historial, RedirectAttributes attrs) {
        try {
            historialService.editar(historial);
            attrs.addFlashAttribute("msgExito", "Historial actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "No se pudo actualizar el historial.");
        }
        return "redirect:/list-historial";
    }
}
