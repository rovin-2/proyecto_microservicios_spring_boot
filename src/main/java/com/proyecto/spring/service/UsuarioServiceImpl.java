package com.proyecto.spring.service;

import java.util.List;
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

	@Override
	public Usuario save(Usuario usuario) {
		return iUsuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return iUsuarioRepository.findByEmail(email);
	}

	@Override
	public List<Usuario> findAll() {
		return iUsuarioRepository.findAll();
	}
	
	

}
