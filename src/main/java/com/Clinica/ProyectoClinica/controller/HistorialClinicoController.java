package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.entity.Cita.Estado;
import com.Clinica.ProyectoClinica.entity.HistorialClinico;
import com.Clinica.ProyectoClinica.service.CitaService;
import com.Clinica.ProyectoClinica.service.HistorialClinicoService;

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
    private CitaService citaService;

    // Formulario de nuevo historial desde una cita ATENDIDA
    @GetMapping("/registrar-historial/{idCita}")
    public String registrarHistorial(@PathVariable("idCita") Integer idCita, Model model, RedirectAttributes attrs) {
        try {
            Cita cita = citaService.buscarPorId(idCita);

            if (cita.getEstado() != Estado.ATENDIDA) {
                attrs.addFlashAttribute("msgError", "La cita aún no ha sido atendida.");
                return "redirect:/list-cita";
            }

            if (historialService.existePorCita(cita)) {
                attrs.addFlashAttribute("msgError", "Ya se registró un historial para esta cita.");
                return "redirect:/list-cita";
            }

            HistorialClinico historial = new HistorialClinico();
            historial.setCita(cita); // Asociamos la cita

            model.addAttribute("historial", historial);
            model.addAttribute("contenido", "fragments/formHistorialClinico :: contenido");
            return "index";

        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Error al cargar la cita.");
            return "redirect:/list-cita";
        }
    }

    // Guardar historial clínico desde formulario
    @PostMapping("/guardar-historial")
    public String guardarHistorial(@ModelAttribute HistorialClinico historial, RedirectAttributes attrs) {
        try {
            historialService.guardar(historial);
            attrs.addFlashAttribute("msgExito", "Historial clínico registrado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", e.getMessage());
        }
        return "redirect:/list-cita";
    }

    // Lista de historiales con filtro
    @GetMapping("/list-historial")
    public String listarHistorial(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        List<HistorialClinico> lista = (filtro != null && !filtro.isEmpty())
                ? historialService.buscarPorPacienteOMedico(filtro)
                : historialService.listarTodos();

        model.addAttribute("listaHistoriales", lista);
        model.addAttribute("filtro", filtro);
        model.addAttribute("contenido", "fragments/listHistorialClinico :: contenido");
        return "index";
    }

    // Editar historial
    @GetMapping("/edit-historial/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        try {
            HistorialClinico historial = historialService.buscarPorId(id);
            model.addAttribute("historial", historial);
            model.addAttribute("modo", "editar");
            model.addAttribute("contenido", "fragments/formHistorialClinico :: contenido");
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Ver historial
    @GetMapping("/view-historial/{id}")
    public String ver(@PathVariable("id") Integer id, Model model) {
        try {
            HistorialClinico historial = historialService.buscarPorId(id);
            model.addAttribute("historial", historial);
            model.addAttribute("modo", "ver");
            model.addAttribute("contenido", "fragments/formHistorialClinico :: contenido");
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Eliminar historial
    @GetMapping("/remove-historial/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes attrs) {
        try {
            historialService.eliminar(id);
            attrs.addFlashAttribute("msgExito", "Historial eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            attrs.addFlashAttribute("msgError", "Error al eliminar historial.");
        }
        return "redirect:/list-historial";
    }

    // Guardar edición del historial
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
