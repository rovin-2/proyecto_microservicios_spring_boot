package com.proyecto.spring.service;

import java.util.Optional;

import com.proyecto.spring.model.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> findById(Integer id);
	Usuario save(Usuario usuario);
	Optional<Usuario> findByEmail(String email);
}
