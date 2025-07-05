package com.Clinica.ProyectoClinica.repository;

import com.Clinica.ProyectoClinica.entity.RegistrarUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrarUsuarioRepository extends JpaRepository<RegistrarUsuario, Long> {
    RegistrarUsuario findByCorreo(String correo);
}
