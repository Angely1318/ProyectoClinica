package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.entity.HistorialClinico;
import com.Clinica.ProyectoClinica.entity.Medico;
import com.Clinica.ProyectoClinica.entity.Paciente;
import com.Clinica.ProyectoClinica.repository.CitaRepository;
import com.Clinica.ProyectoClinica.repository.HistorialClinicoRepository;
import com.Clinica.ProyectoClinica.repository.MedicoRepository;
import com.Clinica.ProyectoClinica.repository.PacienteRepository;

@Service
public class HistorialClinicoService {

    @Autowired
    private HistorialClinicoRepository historialRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CitaRepository citaRepository;
    
    // Guardar nuevo historial, asegurando que tenga cita y no esté duplicado
    public HistorialClinico guardar(HistorialClinico h) throws Exception {
        if (h.getCita() == null || h.getCita().getId() == null)
            throw new Exception("Debe estar vinculada a una cita");

        if (historialRepository.existsByCita(h.getCita()))
            throw new Exception("Ya existe un historial para esta cita");

        // ✅ Recuperar entidades persistidas por ID
        Paciente paciente = pacienteRepository.findById(h.getPaciente().getId())
            .orElseThrow(() -> new Exception("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(h.getMedico().getId())
            .orElseThrow(() -> new Exception("Médico no encontrado"));
        Cita cita = citaRepository.findById(h.getCita().getId())
            .orElseThrow(() -> new Exception("Cita no encontrada"));

        // ✅ Reemplazar con entidades persistidas
        h.setPaciente(paciente);
        h.setMedico(medico);
        h.setCita(cita);

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
