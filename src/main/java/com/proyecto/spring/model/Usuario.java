package com.proyecto.spring.model;

public class Usuario {
	private Integer id;
	private String nombre;
	private String username;
	private String email;
	private String password;
	private String direccion;
	private String telefono;
	private String tipo;

	public Usuario() {
	}

	public Usuario(Integer id, String nombre, String username, String email, String password, String direccion,
			String telefono, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.password = password;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", direccion=" + direccion + ", telefono=" + telefono + ", tipo=" + tipo
				+ "]";
	}

}
