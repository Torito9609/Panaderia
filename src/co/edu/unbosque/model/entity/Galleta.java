package co.edu.unbosque.model.entity;

public class Galleta extends Producto {
	private boolean conChispas;

	public Galleta(String nombre, double precioVenta, double costoProduccion, int cantidad) {
		super(nombre, precioVenta, costoProduccion, cantidad);
	}

	public boolean isConChispas() {
		return conChispas;
	}

	public void setConChispas(boolean conChispas) {
		this.conChispas = conChispas;
	}

}
