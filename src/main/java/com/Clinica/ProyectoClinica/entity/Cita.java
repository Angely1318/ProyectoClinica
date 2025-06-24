package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @OneToOne(mappedBy = "cita")
    private HistorialClinico historial;

    private LocalDate fecha;

    @Column(length = 10, nullable = false)
    private String hora;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Estado estado = Estado.SOLICITADA;

    @Column(columnDefinition = "TEXT")
    private String motivoConsulta;

    public enum Estado {
        SOLICITADA, CONFIRMADA, ATENDIDA, CANCELADA
    }

	public Cita() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cita(Integer id, Paciente paciente, Medico medico, HistorialClinico historial, LocalDate fecha, String hora,
			Estado estado, String motivoConsulta) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.historial = historial;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.motivoConsulta = motivoConsulta;
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

	public HistorialClinico getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialClinico historial) {
		this.historial = historial;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
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
		return Objects.hash(estado, fecha, historial, hora, id, medico, motivoConsulta, paciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return estado == other.estado && Objects.equals(fecha, other.fecha)
				&& Objects.equals(historial, other.historial) && Objects.equals(hora, other.hora)
				&& Objects.equals(id, other.id) && Objects.equals(medico, other.medico)
				&& Objects.equals(motivoConsulta, other.motivoConsulta) && Objects.equals(paciente, other.paciente);
	}

	
    
    
    
}
