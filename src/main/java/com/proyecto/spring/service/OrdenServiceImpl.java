package com.proyecto.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.spring.model.Orden;

@Service
public class OrdenServiceImpl implements IOrdenService {

	@Autowired
	private IOrdenService iOrdenService;
	
	@Override
	public Orden save(Orden orden) {
		return iOrdenService.save(orden);
	}

}
