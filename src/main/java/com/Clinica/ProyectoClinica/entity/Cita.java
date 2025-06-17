package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;

	private LocalDate fecha;
	private String hora;
	private String estado;
	private String motivoConsulta;

	public Cita() {
	}

	public Cita(Long id, Paciente paciente, Medico medico, LocalDate fecha, String hora, String estado,
			String motivoConsulta) {
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.motivoConsulta = motivoConsulta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cita))
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(id, other.id);
	}
}
