package co.edu.unbosque.model.entity;

public class Pan extends Producto {
	private boolean conQueso;

	public Pan(String nombre, double precioVenta, double costoProduccion, int cantidad) {
		super(nombre, precioVenta, costoProduccion, cantidad);
	}

	public boolean isConQueso() {
		return conQueso;
	}

	public void setConQueso(boolean conQueso) {
		this.conQueso = conQueso;
	}

}
