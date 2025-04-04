package co.edu.unbosque.model.dao;

import java.util.List;

import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.persistence.Archivo;

public class PanaderiaDAO implements IPanaderiaDAO<Producto>{
	private List<Producto> productos;
	private Archivo archivo;
	
	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Producto producto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Producto producto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String nombreActualizar, Producto actualizado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Producto findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> filtrarPorNombre(String nombreParcial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> filtrarPorCantidad(int cantidadMinima) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> filtrarPorPrecio(double precioMin, double precioMax) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

}
