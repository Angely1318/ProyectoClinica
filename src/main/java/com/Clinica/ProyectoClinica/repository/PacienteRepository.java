package com.Clinica.ProyectoClinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Clinica.ProyectoClinica.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    
    // para búsquedas con filtro
    List<Paciente> findByDniContainingIgnoreCaseOrNombresContainingIgnoreCase(String dni, String nombres);
    
    // para el login, obtener paciente por correo
    Paciente findByCorreo(String correo);

}
