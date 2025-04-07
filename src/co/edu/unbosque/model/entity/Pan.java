package co.edu.unbosque.model.entity;

import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;

public class Pan extends Producto {
	private boolean conQueso;

	public Pan(String nombre, double precioVenta, double costoProduccion, int cantidad, boolean conQueso)
			throws CantidadInvalidaException, NombreProductoInvalidoException, PrecioInvalidoException {
		super(nombre, precioVenta, costoProduccion, cantidad);
		this.conQueso = conQueso;
		validarDatos();
	}

	public boolean isConQueso() {
		return conQueso;
	}

	public void setConQueso(boolean conQueso) {
		this.conQueso = conQueso;
	}

}
