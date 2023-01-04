package com.proyecto.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.spring.model.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Override
	public Optional<Usuario> findById(Integer id) {
		return iUsuarioService.findById(id);
	}
	
	

}
