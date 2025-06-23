package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.Paciente;
import com.Clinica.ProyectoClinica.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente crear(Paciente paciente) throws Exception {
        if (paciente.getDni() == null || paciente.getDni().isBlank())
            throw new Exception("El DNI es obligatorio");
        return pacienteRepository.save(paciente);
    }

    public Paciente editar(Paciente paciente) throws Exception {
        Paciente existente = buscarPorId(paciente.getId());
        existente.setDni(paciente.getDni());
        existente.setNombres(paciente.getNombres());
        existente.setApellidos(paciente.getApellidos());
        existente.setGenero(paciente.getGenero());
        existente.setTelefono(paciente.getTelefono());
        existente.setCorreo(paciente.getCorreo());
        existente.setDireccion(paciente.getDireccion());
        existente.setFechaNacimiento(paciente.getFechaNacimiento());
        existente.setPais(paciente.getPais());
        return pacienteRepository.save(existente);
    }

    public void eliminar(Integer id) throws Exception {
        pacienteRepository.delete(buscarPorId(id));
    }

    public Paciente buscarPorId(Integer id) throws Exception {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Paciente con ID " + id + " no existe"));
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
    
    public List<Paciente> buscarPorNombreODni(String filtro) {
        return pacienteRepository.findByDniContainingIgnoreCaseOrNombresContainingIgnoreCase(filtro, filtro);
    }

}
