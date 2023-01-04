package com.proyecto.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.spring.model.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {

}
