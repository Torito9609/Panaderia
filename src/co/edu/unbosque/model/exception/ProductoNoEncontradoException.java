package co.edu.unbosque.model.exception;

public class ProductoNoEncontradoException extends Exception {
    
    private static final long serialVersionUID = 1L;

    
    public ProductoNoEncontradoException() {
        super("Producto no encontrado.");
    }

    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public ProductoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ProductoNoEncontradoException(Throwable causa) {
        super(causa);
    }
}
