package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String password;

	private String rol; // 'ADMIN', 'RECEPCIONISTA', 'MEDICO'
	private String estado; // 'ACTIVO', 'INACTIVO'

	public Usuario() {
	}

	public Usuario(Long id, String username, String password, String rol, String estado) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
}
