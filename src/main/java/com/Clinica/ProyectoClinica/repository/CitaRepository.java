package com.Clinica.ProyectoClinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Clinica.ProyectoClinica.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
	
	@Query("SELECT c FROM Cita c " +
		       "JOIN c.paciente p " +
		       "JOIN c.medico m " +
		       "WHERE LOWER(p.dni) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
		       "   OR LOWER(p.nombres) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
		       "   OR LOWER(p.apellidos) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
		       "   OR LOWER(m.dni) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
		       "   OR LOWER(m.nombres) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
		       "   OR LOWER(m.apellidos) LIKE LOWER(CONCAT('%', :filtro, '%'))")
		List<Cita> buscarPorPacienteOMedico(@Param("filtro") String filtro);
}