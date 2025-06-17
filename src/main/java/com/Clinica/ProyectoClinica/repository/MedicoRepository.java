package com.Clinica.ProyectoClinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Clinica.ProyectoClinica.entity.Medico;



public interface MedicoRepository extends JpaRepository<Medico, Integer> {}
