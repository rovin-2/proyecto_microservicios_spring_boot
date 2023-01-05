package com.proyecto.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.spring.model.DetalleOrden;
import com.proyecto.spring.model.Orden;
import com.proyecto.spring.model.Producto;
import com.proyecto.spring.model.Usuario;
import com.proyecto.spring.service.IDetalleOrdenService;
import com.proyecto.spring.service.IOrdenService;
import com.proyecto.spring.service.IUsuarioService;
import com.proyecto.spring.service.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoService productoService;
	@Autowired
	private IUsuarioService iUsuarioService;
	@Autowired
	private IOrdenService iOrdenService;
	@Autowired
	private IDetalleOrdenService iDetalleOrdenService;

	// Para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// Datos de la orden
	Orden orden = new Orden();

	@GetMapping("")
	public String home(Model model, HttpSession session) {
		log.info("Sesion del usuario", session.getAttribute("idUsuario"));

		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
	}

	@GetMapping("productoHome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parametro {}", id);
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();
		model.addAttribute("producto", producto);

		return "usuario/productoHome";
	}

	@PostMapping("/cart")
	public String agregarCarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {

		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();

		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto anadido {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);

		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);

		// Validar que el producto no se anada dos veces
		Integer idProducto = producto.getId();
		boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

		if (!ingresado) {
			detalles.add(detalleOrden);
		}

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		orden.setTotal(sumaTotal);

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	@GetMapping("/delete/cart/{id}")
	public String deleteOrdenCarrito(@PathVariable Integer id, Model model) {

		// Se crea lista de nuevos productos
		List<DetalleOrden> nuevaOrden = new ArrayList<DetalleOrden>();
		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				nuevaOrden.add(detalleOrden);
			}
		}
		// poner la nueva lista con los productos restantes
		detalles = nuevaOrden;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	@GetMapping("/getCart")
	public String getCart(Model model) {
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	@GetMapping("/order")
	public String orden(Model model, HttpSession session) {

		Usuario usuario = iUsuarioService.findById(Integer.parseInt(session.getAttribute("idUsuario").toString()))
				.get();

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);

		return "usuario/resumenOrden";
	}

	// Guardar la orden
	@GetMapping("/saveOrden")
	public String saveOrden(HttpSession session) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(iOrdenService.generarNumeroOrden());

		// Usuario
		Usuario usuario = iUsuarioService.findById(Integer.parseInt(session.getAttribute("idUsuario").toString()))
				.get();

		orden.setUsuario(usuario);
		iOrdenService.save(orden);

		// Guardar detalle
		for (DetalleOrden dt : detalles) {
			dt.setOrden(orden);
			iDetalleOrdenService.save(dt);
		}

		// Limpiar lista y orden
		orden = new Orden();
		detalles.clear();

		return "redirect:/";
	}

	@GetMapping("/search")
	public String buscarProducto(@RequestParam String nombre, Model model) {
		log.info("Nombre del producto: {}", nombre);
		List<Producto> producto = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre))
				.collect(Collectors.toList());
		model.addAttribute("productos", producto);

		return "usuario/home";
	}

}
