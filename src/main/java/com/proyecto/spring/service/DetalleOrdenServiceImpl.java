package com.proyecto.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.spring.model.DetalleOrden;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

	
	@Autowired
	private IDetalleOrdenService iDetalleOrdenService;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return iDetalleOrdenService.save(detalleOrden);
	}

}
