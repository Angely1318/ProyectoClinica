package com.Clinica.ProyectoClinica.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Especialidad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

	
    //CONSTRUCTOR VACIO
	public Especialidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    //CONSTRUCTOR LLENO
	public Especialidad(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
    
    //GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    //HASHCODE
	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidad other = (Especialidad) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	
    
    
}
