package com.proyecto.spring.model;

public class Producto {
	private Integer id;
	private String nombre;
	private String descripcion;
	private String imagen;
	private Double precio;
	private int cantidad;

	public Producto() {
	}

	public Producto(Integer id, String nombre, String descripcion, String imagen, Double precio, int cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}

}
