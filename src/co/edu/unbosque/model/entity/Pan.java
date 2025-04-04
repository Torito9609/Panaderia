package co.edu.unbosque.model.entity;

public class Pan extends Producto {
	private boolean conQueso;

	public Pan(String nombre, double precioVenta, double costoProduccion, int cantidad, boolean conQueso) {
		super(nombre, precioVenta, costoProduccion, cantidad);
		this.conQueso = conQueso;
	}

	public boolean isConQueso() {
		return conQueso;
	}

	public void setConQueso(boolean conQueso) {
		this.conQueso = conQueso;
	}

}
