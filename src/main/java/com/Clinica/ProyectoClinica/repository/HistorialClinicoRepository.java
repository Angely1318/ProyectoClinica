package com.Clinica.ProyectoClinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Clinica.ProyectoClinica.entity.Cita;
import com.Clinica.ProyectoClinica.entity.HistorialClinico;

public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Integer> {

    @Query
    ("SELECT h FROM HistorialClinico h " +
           "JOIN h.paciente p " +
           "JOIN h.medico m " +
           "WHERE LOWER(p.nombres) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
           "   OR LOWER(p.apellidos) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
           "   OR LOWER(m.nombres) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
           "   OR LOWER(m.apellidos) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<HistorialClinico> buscarPorPacienteOMedico(@Param("filtro") String filtro);
    
    boolean existsByCita(Cita cita);

}
