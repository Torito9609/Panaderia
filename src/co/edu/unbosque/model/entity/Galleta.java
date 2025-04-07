package co.edu.unbosque.model.entity;

import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;

public class Galleta extends Producto {
	private boolean conChispas;

	public Galleta(String nombre, double precioVenta, double costoProduccion, int cantidad, boolean conChispas)
			throws CantidadInvalidaException, NombreProductoInvalidoException, PrecioInvalidoException {
		super(nombre, precioVenta, costoProduccion, cantidad);
		this.conChispas = conChispas;
		validarDatos();
	}

	public boolean isConChispas() {
		return conChispas;
	}

	public void setConChispas(boolean conChispas) {
		this.conChispas = conChispas;
	}

}
