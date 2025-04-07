package co.edu.unbosque.model.exception;

public class TipoProductoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public TipoProductoInvalidoException() {
		super("El tipo de producto no es válido.");
	}

	public TipoProductoInvalidoException(String mensaje) {
		super(mensaje);
	}

	public TipoProductoInvalidoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public TipoProductoInvalidoException(Throwable causa) {
		super(causa);
	}

}
