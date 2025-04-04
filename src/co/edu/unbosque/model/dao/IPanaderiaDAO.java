package co.edu.unbosque.model.dao;

import java.util.List;

public interface IPanaderiaDAO<T> {

	List<T> getAll();

	boolean add(T x);

	boolean delete(T x);

	boolean update(String nombreActualizar, T actualizado);

	T findByNombre(String nombre);

	List<T> filtrarPorNombre(String nombreParcial);

	List<T> filtrarPorCantidad(int cantidadMinima);

	List<T> filtrarPorPrecio(double precioMin, double precioMax);
}
