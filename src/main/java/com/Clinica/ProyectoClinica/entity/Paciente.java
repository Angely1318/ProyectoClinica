package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String dni;
	private String nombres;
	private String apellidos;
	private String genero;
	private LocalDate fechaNacimiento;
	private String telefono;
	private String correo;
	private String direccion;
	private String pais;
	private Timestamp fechaRegistro;

	public Paciente() {
	}

	public Paciente(Long id, String dni, String nombres, String apellidos, String genero, LocalDate fechaNacimiento,
			String telefono, String correo, String direccion, String pais, Timestamp fechaRegistro) {
		this.id = id;
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.pais = pais;
		this.fechaRegistro = fechaRegistro;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Paciente))
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(id, other.id);
	}
}
