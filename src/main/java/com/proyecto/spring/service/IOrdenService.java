package com.proyecto.spring.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.spring.model.Orden;
import com.proyecto.spring.model.Usuario;

public interface IOrdenService {

	List<Orden> findAll();
	Optional<Orden> findById(Integer id);
	Orden save(Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario(Usuario usuario);
}
