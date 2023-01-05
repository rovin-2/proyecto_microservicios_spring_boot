package com.proyecto.spring.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.spring.model.Usuario;
import com.proyecto.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private IUsuarioService iUsuarioService;
	
	//Url = /usuario/registro
	@GetMapping("/registro")
	public String createUsuario() {
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String saveUsuario(Usuario usuario) {
		
		log.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		iUsuarioService.save(usuario);
		return "usuario/login";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceso(Usuario usuario, HttpSession sesscion) {
		log.info("Acceso: {}", usuario);
		Optional<Usuario> user = iUsuarioService.findByEmail(usuario.getEmail());
		//log.info("Usuario: {}", user.get());
		
		if(user.isPresent()) {
			sesscion.setAttribute("idUsuario", user.get().getId());
			if(user.get().getTipo() == "ADMIN") {
				return "redirect:/administrador";
			}else {
				return"redirect:/";
			}
		}else {
			log.info("Usuario no existe!!");
		}
		return"redirect:/";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

