package com.proyecto.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.spring.model.Producto;
import com.proyecto.spring.service.IOrdenService;
import com.proyecto.spring.service.IUsuarioService;
import com.proyecto.spring.service.ProductoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private IUsuarioService iUsuarioService;
	@Autowired
	private IOrdenService iOrdenService;
	
	@GetMapping("")
	public String home(Model model) {
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model  model) {
		model.addAttribute("usuarios", iUsuarioService.findAll());
		return "administrador/usuario";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("ordenes", iOrdenService.findAll());
		
		return "administrador/ordenes";
	}
}
