package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String dni;
	private String nombres;
	private String apellidos;
	private String cmp;
	private String telefono;
	private String correo;
	private String genero;
	private String horarioAtencion;

	@ManyToOne
	@JoinColumn(name = "especialidad_id", referencedColumnName = "id")
	private Especialidad especialidad;

	public Medico() {
	}

	public Medico(Long id, String dni, String nombres, String apellidos, String cmp, String telefono, String correo,
			String genero, String horarioAtencion, Especialidad especialidad) {
		this.id = id;
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cmp = cmp;
		this.telefono = telefono;
		this.correo = correo;
		this.genero = genero;
		this.horarioAtencion = horarioAtencion;
		this.especialidad = especialidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCmp() {
		return cmp;
	}

	public void setCmp(String cmp) {
		this.cmp = cmp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getHorarioAtencion() {
		return horarioAtencion;
	}

	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Medico))
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(id, other.id);
	}
}
