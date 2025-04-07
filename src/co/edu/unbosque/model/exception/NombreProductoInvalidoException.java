package co.edu.unbosque.model.exception;

public class NombreProductoInvalidoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NombreProductoInvalidoException() {
		super("Error en el nombre del producto");
	}
	
	public NombreProductoInvalidoException(String mensaje) {
		super(mensaje);
	}
	
	public NombreProductoInvalidoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
	
	public NombreProductoInvalidoException(Throwable causa) {
		super(causa);
	}

}
