package com.Clinica.ProyectoClinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Clinica.ProyectoClinica.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username); 
}
