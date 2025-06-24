package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.repository.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public Cita crear(Cita cita) throws Exception {
        if (cita.getFecha() == null)
            throw new Exception("Debe ingresar la fecha");
        if (cita.getHora() == null)
            throw new Exception("Debe ingresar la hora");
        return citaRepository.save(cita);
    }

    public Cita editar(Cita cita) throws Exception {
        Cita existente = buscarPorId(cita.getId());
        existente.setFecha(cita.getFecha());
        existente.setHora(cita.getHora());
        existente.setEstado(cita.getEstado());
        existente.setMotivoConsulta(cita.getMotivoConsulta());
        existente.setPaciente(cita.getPaciente());
        existente.setMedico(cita.getMedico());
        return citaRepository.save(existente);
    }

    public void eliminar(Integer id) throws Exception {
        citaRepository.delete(buscarPorId(id));
    }

    public Cita buscarPorId(Integer id) throws Exception {
    	return citaRepository.findById(id).orElseThrow(() -> new Exception("Cita con ID " + id + " no existe"));
    }

    public List<Cita> listarTodos() {
        return citaRepository.findAll();
    }
    
    public List<Cita> buscarPorPacienteOMedico(String filtro) {
        return citaRepository.buscarPorPacienteOMedico(filtro);
    }
    
    public void marcarComoAtendida(Integer id) throws Exception {
        Cita cita = buscarPorId(id);
        cita.setEstado(Cita.Estado.ATENDIDA);
        citaRepository.save(cita);
    }

    public void cancelarCita(Integer id) throws Exception {
        Cita cita = buscarPorId(id);
        cita.setEstado(Cita.Estado.CANCELADA);
        citaRepository.save(cita);
    }


}
