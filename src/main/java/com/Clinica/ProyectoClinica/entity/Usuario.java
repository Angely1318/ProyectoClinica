package com.Clinica.ProyectoClinica.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario") // buena práctica, explícito
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // mejor usar Long por consistencia con JpaRepository

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Rol rol;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Estado estado = Estado.ACTIVO;

	// ENUMS
	public enum Rol {
		ADMIN, RECEPCIONISTA, MEDICO
	}

	public enum Estado {
		ACTIVO, INACTIVO
	}

	// CONSTRUCTORES
	public Usuario() {
		// vacío para JPA
	}

	public Usuario(Long id, String username, String password, Rol rol, Estado estado) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.estado = estado;
	}

	// GETTERS & SETTERS
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	// equals y hashCode
	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, rol, estado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario other))
			return false;
		return Objects.equals(id, other.id) && Objects.equals(username, other.username)
				&& Objects.equals(password, other.password) && rol == other.rol && estado == other.estado;
	}

}
