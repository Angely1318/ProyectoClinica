package com.Clinica.ProyectoClinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Clinica.ProyectoClinica.entity.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Integer> {}
