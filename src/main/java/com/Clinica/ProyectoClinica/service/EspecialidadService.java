package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.Especialidad;
import com.Clinica.ProyectoClinica.repository.EspecialidadRepository;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public Especialidad crear(Especialidad esp) throws Exception {
        if (esp.getNombre() == null || esp.getNombre().isBlank())
            throw new Exception("Debe ingresar el nombre de la especialidad");
        return especialidadRepository.save(esp);
    }

    public Especialidad editar(Especialidad esp) throws Exception {
        Especialidad existente = buscarPorId(esp.getId());
        existente.setNombre(esp.getNombre());
        existente.setDescripcion(esp.getDescripcion());
        return especialidadRepository.save(existente);
    }

    public void eliminar(Integer id) throws Exception {
        especialidadRepository.delete(buscarPorId(id));
    }

    public Especialidad buscarPorId(Integer id) throws Exception {
        return especialidadRepository.findById(id)
                .orElseThrow(() -> new Exception("Especialidad con ID " + id + " no existe"));
    }

    public List<Especialidad> listarTodos() {
        return especialidadRepository.findAll();
    }
}
