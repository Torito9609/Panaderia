package co.edu.unbosque.model.entity;

public class Galleta extends Producto {
	private boolean conChispas;

	public Galleta(String nombre, double precioVenta, double costoProduccion, int cantidad, boolean conChispas) {
		super(nombre, precioVenta, costoProduccion, cantidad);
		this.conChispas = conChispas;
	}

	public boolean isConChispas() {
		return conChispas;
	}

	public void setConChispas(boolean conChispas) {
		this.conChispas = conChispas;
	}

}
