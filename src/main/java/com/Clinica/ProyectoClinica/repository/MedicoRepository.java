package com.Clinica.ProyectoClinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Clinica.ProyectoClinica.entity.Medico;
import com.Clinica.ProyectoClinica.entity.Paciente;



public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    List<Medico> findByDniContainingIgnoreCaseOrNombresContainingIgnoreCase(String dni, String nombres);

}
