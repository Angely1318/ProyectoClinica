package com.Clinica.ProyectoClinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Clinica.ProyectoClinica.entity.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByDniContainingIgnoreCaseOrNombresContainingIgnoreCase(String dni, String nombres);

}
