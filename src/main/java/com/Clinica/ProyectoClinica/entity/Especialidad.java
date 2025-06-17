package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Especialidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String descripcion;

	public Especialidad() {
	}

	public Especialidad(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Especialidad))
			return false;
		Especialidad other = (Especialidad) obj;
		return Objects.equals(id, other.id);
	}
}
