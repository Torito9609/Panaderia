package co.edu.unbosque.model.dao;

import java.io.IOException;
import java.util.List;

import co.edu.unbosque.model.exception.AccesoDatosException;
import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;

public interface IPanaderiaDAO<T> {

	List<T> getAll() throws ClassNotFoundException, AccesoDatosException, TipoProductoInvalidoException, PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException;

	boolean add(T x) throws AccesoDatosException;

	boolean delete(String nombre) throws AccesoDatosException, IOException;

	boolean update(String nombreActualizar, T actualizado) throws AccesoDatosException, IOException;

	T findByNombre(String nombre);

	List<T> filtrarPorNombre(String nombreParcial);

	List<T> filtrarPorCantidad(int cantidadMinima);

	List<T> filtrarPorPrecio(double precioMin, double precioMax);
	
	List<T> filtrarPorTipo(String tipo);
}
