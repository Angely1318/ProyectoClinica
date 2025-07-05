package com.Clinica.ProyectoClinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Clinica.ProyectoClinica.entity.Usuario;
import com.Clinica.ProyectoClinica.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario crear(Usuario usuario) throws Exception {
		if (usuario.getUsername() == null || usuario.getUsername().isBlank())
			throw new Exception("Debe ingresar un username");
		if (usuario.getPassword() == null || usuario.getPassword().isBlank())
			throw new Exception("Debe ingresar una contraseña");
		return usuarioRepository.save(usuario);
	}

	public Usuario editar(Usuario usuario) throws Exception {
		Usuario existente = buscarPorId(usuario.getId());
		existente.setUsername(usuario.getUsername());
		existente.setPassword(usuario.getPassword());
		existente.setRol(usuario.getRol());
		existente.setEstado(usuario.getEstado());
		return usuarioRepository.save(existente);
	}

	public void eliminar(Long id) throws Exception {
		Usuario existente = buscarPorId(id);
		usuarioRepository.delete(existente);
	}

	public Usuario buscarPorId(Long id) throws Exception {
		return usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuario con ID " + id + " no existe"));
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
}
