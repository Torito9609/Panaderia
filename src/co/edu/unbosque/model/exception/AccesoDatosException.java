package co.edu.unbosque.model.exception;

public class AccesoDatosException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccesoDatosException() {
		super("Error en el acceso a los datos.");
	}

	public AccesoDatosException(String mensaje) {
		super(mensaje);
	}

	public AccesoDatosException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public AccesoDatosException(Throwable causa) {
		super(causa);
	}
}
