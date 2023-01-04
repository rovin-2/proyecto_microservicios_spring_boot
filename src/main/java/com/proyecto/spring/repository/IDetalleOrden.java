package com.proyecto.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.spring.model.DetalleOrden;

@Repository
public interface IDetalleOrden extends JpaRepository<DetalleOrden, Integer>{

}
