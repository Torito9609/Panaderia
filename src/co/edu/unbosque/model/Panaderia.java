package co.edu.unbosque.model;

import java.util.List;

import co.edu.unbosque.model.dao.PanaderiaDAO;
import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoDuplicadoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;
import co.edu.unbosque.model.exception.ProductoNoEncontradoException;

public class Panaderia {
	private PanaderiaDAO panaderiaDAO;

	public Panaderia() {
		panaderiaDAO = new PanaderiaDAO();
	}

	public List<ProductoDTO> listarProductos() { // retornar la lista de todos los productos (DTO)
		return null;
	}

	public void crearProducto(ProductoDTO nuevoProducto)
			throws NombreProductoDuplicadoException, PrecioInvalidoException, CantidadInvalidaException {

	}

	public void editarProducto(ProductoDTO productoEditar, ProductoDTO productoNuevo)
			throws NombreProductoDuplicadoException, PrecioInvalidoException, CantidadInvalidaException {

	}
	
	public void eliminarProducto(String nombre) throws ProductoNoEncontradoException{
		
	}
	
	public ProductoDTO buscarPorNombre(String nombre) { // Retorna un producto exacto con coincidencia exacta
		return null;
	}
	
	public List<ProductoDTO> filtrarPorNombre(String nombre){ // Retorna una lista de coincidencias posibles
		return null;
	}

	public List<ProductoDTO> filtrarPorCantidad(int cantidadMinima) { // Retorna una lista con productos de cantidad igual o mayor a la minima 
		return null;
	}
	
	public List<ProductoDTO> filtrarPorPrecio(double limInferior, double limSuperior) { // Retorna una lista de productos entre los límites incluyéndolos
		return null;
	}
	
	public void guardarProductos() { // guardar los productos 
		
	}
	
	public void cargarProductos() {
		
	}
	
	public void exportarProductosCSV() {
		
	}

	public PanaderiaDAO getPanaderiaDAO() {
		return panaderiaDAO;
	}

	public void setPanaderiaDAO(PanaderiaDAO panaderiaDAO) {
		this.panaderiaDAO = panaderiaDAO;
	}
	
	

}
