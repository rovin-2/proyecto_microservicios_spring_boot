package com.proyecto.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProyectoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringBootApplication.class, args);
	}

}
