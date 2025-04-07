package co.edu.unbosque.model.exception;

public class PrecioInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public PrecioInvalidoException() {
		super("El precio es inválido.");
	}

	public PrecioInvalidoException(String mensaje) {
		super(mensaje);
	}

	public PrecioInvalidoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public PrecioInvalidoException(Throwable causa) {
		super(causa);
	}

}
