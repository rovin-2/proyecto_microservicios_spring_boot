package com.proyecto.spring.service;

import java.util.Optional;

import com.proyecto.spring.model.Usuario;

public interface IUsuarioService {
	Optional<Usuario> findById(Integer id);

}