package co.edu.unbosque.model.dto;

import java.io.Serializable;

public class ProductoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private double precioVenta;
	private double costoProduccion;
	private int cantidad;
	private String tipo;
	private boolean conQueso;
	private boolean conChispas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getCostoProduccion() {
		return costoProduccion;
	}

	public void setCostoProduccion(double costoProduccion) {
		this.costoProduccion = costoProduccion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isConQueso() {
		return conQueso;
	}

	public void setConQueso(boolean conQueso) {
		this.conQueso = conQueso;
	}

	public boolean isConChispas() {
		return conChispas;
	}

	public void setConChispas(boolean conChispas) {
		this.conChispas = conChispas;
	}

}
