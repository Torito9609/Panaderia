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

	public List<ProductoDTO> listarProductos() {
		return null;
	}

	public void agregarProducto(ProductoDTO nuevoProducto)
			throws NombreProductoDuplicadoException, PrecioInvalidoException, CantidadInvalidaException {

	}

	public void editarProducto(ProductoDTO productoEditar, ProductoDTO productoNuevo)
			throws NombreProductoDuplicadoException, PrecioInvalidoException, CantidadInvalidaException {

	}
	
	public void eliminarProducto(String nombre) throws ProductoNoEncontradoException{
		
	}
	
	public List<ProductoDTO> filtrarPorNombre(String nombre){
		return null;
	}

	public List<ProductoDTO> filtrarPorCantidad(int cantidad) {
		return null;
	}
	
	public List<ProductoDTO> filtrarPorPrecio(double limInferior, double limSuperior) {
		return null;
	}
	
	public void guardarProductos() {
		
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
