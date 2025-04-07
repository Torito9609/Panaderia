package co.edu.unbosque.model.exception;

public class AccesoDatosException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccesoDatosException(String mensaje) {
		super(mensaje);
	}

	public AccesoDatosException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

}
