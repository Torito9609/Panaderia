package co.edu.unbosque.model.entity;

import co.edu.unbosque.model.exception.CantidadInvalidaException;
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
	
	public void validarDatos() throws PrecioInvalidoException, CantidadInvalidaException{
		
	}

}
