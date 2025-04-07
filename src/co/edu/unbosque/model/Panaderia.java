package co.edu.unbosque.model;

import java.io.IOException;
import java.util.List;

import co.edu.unbosque.model.dao.PanaderiaDAO;
import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.AccesoDatosException;
import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoDuplicadoException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;
import co.edu.unbosque.model.exception.ProductoNoEncontradoException;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;
import co.edu.unbosque.model.persistence.ExportadorCSV;
import co.edu.unbosque.model.persistence.MapHandler;

public class Panaderia {
	private PanaderiaDAO panaderiaDAO;

	public Panaderia() throws AccesoDatosException, ClassNotFoundException, TipoProductoInvalidoException, PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException {
		panaderiaDAO = new PanaderiaDAO();
	}

	public List<ProductoDTO> listarProductos()
			throws ClassNotFoundException, AccesoDatosException, TipoProductoInvalidoException, PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException {
	    List<Producto> productos = panaderiaDAO.getAll();
	    return MapHandler.todosProductoADTO(productos);
	}

	public void crearProducto(ProductoDTO nuevoProducto)
			throws NombreProductoDuplicadoException, PrecioInvalidoException, CantidadInvalidaException,
			TipoProductoInvalidoException, NombreProductoInvalidoException, AccesoDatosException {
		Producto productoExistente = panaderiaDAO.findByNombre(nuevoProducto.getNombre());

		if (productoExistente != null) {
			throw new NombreProductoDuplicadoException("El nombre del producto ya está registrado.");
		}
		Producto producto = MapHandler.dtoAProducto(nuevoProducto);
		panaderiaDAO.add(producto);
	}

	public void editarProducto(String nombreProductoEditar, ProductoDTO productoNuevo)
			throws NombreProductoDuplicadoException, PrecioInvalidoException, CantidadInvalidaException,
			TipoProductoInvalidoException, NombreProductoInvalidoException, AccesoDatosException, IOException {

		Producto productoConMismoNombre = panaderiaDAO.findByNombre(productoNuevo.getNombre());
		if (productoConMismoNombre != null && !productoConMismoNombre.getNombre().equals(nombreProductoEditar)) {
			throw new NombreProductoDuplicadoException("El nombre del producto ya está registrado.");
		}

		Producto producto = MapHandler.dtoAProducto(productoNuevo);
		panaderiaDAO.update(nombreProductoEditar, producto);

	}

	public void eliminarProducto(String nombre) throws ProductoNoEncontradoException, AccesoDatosException, IOException {
		Producto producto = panaderiaDAO.findByNombre(nombre);
		if (producto == null) {
			throw new ProductoNoEncontradoException("Producto no encontrado.");
		}
		panaderiaDAO.delete(producto.getNombre());
	}

	public ProductoDTO buscarPorNombre(String nombre) throws ProductoNoEncontradoException {
		Producto producto = panaderiaDAO.findByNombre(nombre);
		if (producto != null) {
			return MapHandler.productoADTO(producto);
		} else {
			throw new ProductoNoEncontradoException("Producto no encontrado.");
		}
	}

	public List<ProductoDTO> filtrarPorNombre(String nombre) {
		List<Producto> productos = panaderiaDAO.filtrarPorNombre(nombre);
		return MapHandler.todosProductoADTO(productos);
	}

	public List<ProductoDTO> filtrarPorCantidad(int cantidadMinima) {
		List<Producto> productos = panaderiaDAO.filtrarPorCantidad(cantidadMinima);
		return MapHandler.todosProductoADTO(productos);
	}

	public List<ProductoDTO> filtrarPorPrecio(double limInferior, double limSuperior) {
		List<Producto> productos = panaderiaDAO.filtrarPorPrecio(limInferior, limSuperior);
		return MapHandler.todosProductoADTO(productos);
	}

	public List<ProductoDTO> filtrarPorTipo(String tipo) throws TipoProductoInvalidoException {
	    List<Producto> productos = panaderiaDAO.filtrarPorTipo(tipo);
	    return MapHandler.todosProductoADTO(productos);
	}

	public void exportarProductosCSV(List<ProductoDTO> productos) throws AccesoDatosException { 
		ExportadorCSV.exportar(productos);
	}

	public PanaderiaDAO getPanaderiaDAO() {
		return panaderiaDAO;
	}

	public void setPanaderiaDAO(PanaderiaDAO panaderiaDAO) {
		this.panaderiaDAO = panaderiaDAO;
	}

}
