package com.Clinica.ProyectoClinica.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String cmp; // Código médico

    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;

    //CONSTRUCTOR VACIO
	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}

    //CONSTRUCTOR LLENO
	public Medico(Integer id, String nombre, String cmp, Especialidad especialidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cmp = cmp;
		this.especialidad = especialidad;
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

	public String getCmp() {
		return cmp;
	}

	public void setCmp(String cmp) {
		this.cmp = cmp;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
    
    //HASHCODE

	@Override
	public int hashCode() {
		return Objects.hash(cmp, especialidad, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(cmp, other.cmp) && Objects.equals(especialidad, other.especialidad)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

    
}
