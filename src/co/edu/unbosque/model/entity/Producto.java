package co.edu.unbosque.model.entity;

import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;

public abstract class Producto {
	protected String nombre;
	protected double precioVenta;
	protected double costoProduccion;
	protected int cantidad;

	public Producto(String nombre, double precioVenta, double costoProduccion, int cantidad) {
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.costoProduccion = costoProduccion;
		this.cantidad = cantidad;
	}

	public void validarDatos()
			throws PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new NombreProductoInvalidoException("El nombre no puede estar vacío.");
		}
		if (precioVenta <= 0 || costoProduccion < 0 || costoProduccion >= precioVenta) {
			throw new PrecioInvalidoException("Precio o costo inválidos.");
		}
		if (cantidad < 0) {
			throw new CantidadInvalidaException("Cantidad no puede ser negativa.");
		}
	}

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
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Producto producto = (Producto) obj;
	    return nombre.equalsIgnoreCase(producto.nombre);
	}


}
