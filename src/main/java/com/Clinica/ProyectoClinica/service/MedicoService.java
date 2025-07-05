package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.Medico;
import com.Clinica.ProyectoClinica.entity.Paciente;
import com.Clinica.ProyectoClinica.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico crear(Medico medico) throws Exception {
        if (medico.getDni() == null || medico.getDni().isBlank())
            throw new Exception("El DNI es obligatorio");
        return medicoRepository.save(medico);
    }

    public Medico editar(Medico medico) throws Exception {
        Medico existente = buscarPorId(medico.getId());
        existente.setDni(medico.getDni());
        existente.setNombres(medico.getNombres());
        existente.setApellidos(medico.getApellidos());
        existente.setCmp(medico.getCmp());
        existente.setCorreo(medico.getCorreo());
        existente.setTelefono(medico.getTelefono());
        existente.setGenero(medico.getGenero());
        existente.setHorarioAtencion(medico.getHorarioAtencion());
        existente.setEspecialidad(medico.getEspecialidad());
        return medicoRepository.save(existente);
    }

    public void eliminar(Integer id) throws Exception {
        medicoRepository.delete(buscarPorId(id));
    }

    public Medico buscarPorId(Integer id) throws Exception {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new Exception("Médico con ID " + id + " no existe"));
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }
    
    public List<Medico> buscarPorNombreODni(String filtro) {
        return medicoRepository.findByDniContainingIgnoreCaseOrNombresContainingIgnoreCase(filtro, filtro);
    }
    
    
}
