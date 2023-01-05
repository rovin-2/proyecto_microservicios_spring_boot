package com.proyecto.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.spring.model.DetalleOrden;
import com.proyecto.spring.repository.IDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

	
	@Autowired
	private IDetalleOrdenRepository iDetalleOrdenRepository;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return iDetalleOrdenRepository.save(detalleOrden);
	}

}
