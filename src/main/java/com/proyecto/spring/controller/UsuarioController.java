package com.proyecto.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.spring.model.Orden;
import com.proyecto.spring.model.Usuario;
import com.proyecto.spring.service.IOrdenService;
import com.proyecto.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private IUsuarioService iUsuarioService;
	@Autowired
	private IOrdenService iOrdenService;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	// Url = /usuario/registro
	@GetMapping("/registro")
	public String createUsuario() {
		return "usuario/registro";
	}

	@PostMapping("/save")
	public String saveUsuario(Usuario usuario) {

		log.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		iUsuarioService.save(usuario);
		return "usuario/login";
	}

	@GetMapping("/login")
	public String login() {

		return "usuario/login";
	}

	@GetMapping("/acceder")
	public String acceso(Usuario usuario, HttpSession sesscion) {
		log.info("Acceso: {}", usuario);
		Optional<Usuario> user = iUsuarioService.findById(Integer.parseInt(sesscion.getAttribute("idUsuario").toString()));
		// log.info("Usuario: {}", user.get());

		if (user.isPresent()) {
			sesscion.setAttribute("idUsuario", user.get().getId());
			if (user.get().getTipo() == "ADMIN") {
				return "redirect:/administrador";
			} else {
				return "redirect:/";
			}
		} else {
			log.info("Usuario no existe!!");
		}
		return "redirect:/";
	}

	@GetMapping("/compras")
	public String ObtenerCompras(Model model, HttpSession session) {

		model.addAttribute("sesion", session.getAttribute("idUsuario"));
		Usuario usuario = iUsuarioService.findById(Integer.parseInt(session.getAttribute("idUsuario").toString()))
				.get();
		List<Orden> ordenes = iOrdenService.findByUsuario(usuario);
		model.addAttribute("ordenes", ordenes);
		return "usuario/compras";
	}

	@GetMapping("detalle/{id}")
	public String verDetalle(@PathVariable Integer id, HttpSession session, Model model) {

		log.info("Id de la orden: {}", id);
		Optional<Orden> orden = iOrdenService.findById(id);
		model.addAttribute("detalles", orden.get().getDetalle());
		model.addAttribute("sesion", session.getAttribute("idUsuario"));

		return "usuario/detalleCompra";
	}

	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idUsuario");
		return "redirect:/";
	}

}
