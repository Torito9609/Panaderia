package co.edu.unbosque.model.exception;

public class CantidadInvalidaException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CantidadInvalidaException() {
		super("La cantidad del producto no es válida.");
	}
	
	public CantidadInvalidaException(String mensaje) {
		super(mensaje);
	}
	
	public CantidadInvalidaException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
	
	public CantidadInvalidaException(Throwable causa) {
		super(causa);
	}
	

}
