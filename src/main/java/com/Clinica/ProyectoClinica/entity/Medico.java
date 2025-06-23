package com.Clinica.ProyectoClinica.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 10)
    private String dni;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 20)
    private String cmp;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Genero genero;

    @Column(length = 100)
    private String horarioAtencion;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    public enum Genero {
        MASCULINO, FEMENINO, OTRO
    }

	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medico(Integer id, String dni, String nombres, String apellidos, String cmp, String telefono, String correo,
			Genero genero, String horarioAtencion, Especialidad especialidad) {
		super();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
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
		return Objects.hash(apellidos, cmp, correo, dni, especialidad, genero, horarioAtencion, id, nombres, telefono);
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
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(cmp, other.cmp)
				&& Objects.equals(correo, other.correo) && Objects.equals(dni, other.dni)
				&& Objects.equals(especialidad, other.especialidad) && genero == other.genero
				&& Objects.equals(horarioAtencion, other.horarioAtencion) && Objects.equals(id, other.id)
				&& Objects.equals(nombres, other.nombres) && Objects.equals(telefono, other.telefono);
	}
    
    
}
