package co.edu.unbosque.model.exception;

public class NombreProductoDuplicadoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NombreProductoDuplicadoException() {
		super("El producto no fue encontrado.");
	}
	
	public NombreProductoDuplicadoException(String mensaje) {
		super(mensaje);
	}
	
	public NombreProductoDuplicadoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
	
	public NombreProductoDuplicadoException(Throwable causa) {
		super(causa);
	}

}
