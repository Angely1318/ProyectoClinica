package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.entity.HistorialClinico;
import com.Clinica.ProyectoClinica.repository.HistorialClinicoRepository;

@Service
public class HistorialClinicoService {

    @Autowired
    private HistorialClinicoRepository historialRepository;

    // Guardar nuevo historial, asegurando que tenga cita y no esté duplicado
    public HistorialClinico guardar(HistorialClinico h) throws Exception {
        if (h.getCita() == null)
            throw new Exception("Debe estar vinculada a una cita");

        if (historialRepository.existsByCita(h.getCita()))
            throw new Exception("Ya existe un historial para esta cita");

        return historialRepository.save(h);
    }

    // Editar historial clínico
    public HistorialClinico editar(HistorialClinico h) throws Exception {
        HistorialClinico existente = buscarPorId(h.getId());

        existente.setFecha(h.getFecha());
        existente.setDiagnostico(h.getDiagnostico());
        existente.setTratamiento(h.getTratamiento());
        existente.setObservaciones(h.getObservaciones());
        existente.setPaciente(h.getPaciente());
        existente.setMedico(h.getMedico());

        return historialRepository.save(existente);
    }

    // Buscar por ID
    public HistorialClinico buscarPorId(Integer id) throws Exception {
        return historialRepository.findById(id)
                .orElseThrow(() -> new Exception("Historial clínico con ID " + id + " no existe"));
    }

    // Eliminar historial
    public void eliminar(Integer id) throws Exception {
        historialRepository.delete(buscarPorId(id));
    }

    // Listar todos
    public List<HistorialClinico> listarTodos() {
        return historialRepository.findAll();
    }

    // Buscar por filtro de paciente o médico
    public List<HistorialClinico> buscarPorPacienteOMedico(String filtro) {
        return historialRepository.buscarPorPacienteOMedico(filtro);
    }

    // Validar si existe historial por cita
    public boolean existePorCita(Cita cita) {
        return historialRepository.existsByCita(cita);
    }
}
