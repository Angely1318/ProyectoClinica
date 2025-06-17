package com.Clinica.ProyectoClinica.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String dni;
    private String direccion;
    
    //CONSTRUCTOR VACIO
	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//CONSTRUCTOR LLENO
	public Paciente(Integer id, String nombre, String dni, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	//HASHCODE
	@Override
	public int hashCode() {
		return Objects.hash(direccion, dni, id, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(direccion, other.direccion) && Objects.equals(dni, other.dni)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	
}

