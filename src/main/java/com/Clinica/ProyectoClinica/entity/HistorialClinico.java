package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    private LocalDate fecha;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamiento;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

	public HistorialClinico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistorialClinico(Integer id, Paciente paciente, Medico medico, Cita cita, LocalDate fecha,
			String diagnostico, String tratamiento, String observaciones) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.cita = cita;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.observaciones = observaciones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cita, diagnostico, fecha, id, medico, observaciones, paciente, tratamiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistorialClinico other = (HistorialClinico) obj;
		return Objects.equals(cita, other.cita) && Objects.equals(diagnostico, other.diagnostico)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(id, other.id)
				&& Objects.equals(medico, other.medico) && Objects.equals(observaciones, other.observaciones)
				&& Objects.equals(paciente, other.paciente) && Objects.equals(tratamiento, other.tratamiento);
	}

	
    
    
}
