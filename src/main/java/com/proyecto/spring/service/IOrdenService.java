package com.proyecto.spring.service;

import java.util.List;

import com.proyecto.spring.model.Orden;

public interface IOrdenService {

	List<Orden> findAll();
	Orden save(Orden orden);
	String generarNumeroOrden();
}
