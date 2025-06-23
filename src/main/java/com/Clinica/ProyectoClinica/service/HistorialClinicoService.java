package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.HistorialClinico;
import com.Clinica.ProyectoClinica.repository.HistorialClinicoRepository;

@Service
public class HistorialClinicoService {

    @Autowired
    private HistorialClinicoRepository historialRepository;

    public HistorialClinico crear(HistorialClinico h) throws Exception {
        if (h.getFecha() == null)
            throw new Exception("Debe ingresar una fecha");
        return historialRepository.save(h);
    }

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

    public void eliminar(Integer id) throws Exception {
        historialRepository.delete(buscarPorId(id));
    }

    public HistorialClinico buscarPorId(Integer id) throws Exception {
        return historialRepository.findById(id)
                .orElseThrow(() -> new Exception("Historial clínico con ID " + id + " no existe"));
    }

    public List<HistorialClinico> listarTodos() {
        return historialRepository.findAll();
    }
}
