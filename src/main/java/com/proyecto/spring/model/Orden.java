package com.proyecto.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibido;
	private double total;

	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "orden")
	private List<DetalleOrden> detalleOrden;

	public Orden() {
	}

	public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibido, double total, Usuario usuario) {
		super();
		this.id = id;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibido = fechaRecibido;
		this.total = total;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibido() {
		return fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleOrden> getDetalleOrden() {
		return detalleOrden;
	}

	public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
		this.detalleOrden = detalleOrden;
	}

	@Override
	public String toString() {
		return "Orden [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibido="
				+ fechaRecibido + ", total=" + total + "]";
	}

}
