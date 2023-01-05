package com.proyecto.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.spring.model.Usuario;
import com.proyecto.spring.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	public Optional<Usuario> findById(Integer id) {
		return iUsuarioRepository.findById(id);
	}
	
	

}
