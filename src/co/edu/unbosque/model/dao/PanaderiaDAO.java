package co.edu.unbosque.model.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.entity.Galleta;
import co.edu.unbosque.model.entity.Pan;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.AccesoDatosException;
import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;
import co.edu.unbosque.model.persistence.Archivo;

public class PanaderiaDAO implements IPanaderiaDAO<Producto>{
	private List<Producto> productos;
	private Archivo archivo;
	
	public PanaderiaDAO() throws AccesoDatosException, ClassNotFoundException, TipoProductoInvalidoException, PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException {
		this.productos = new ArrayList<Producto>();
		this.archivo = new Archivo();
		actualizarBD();
	}
	
	private void actualizarBD() throws ClassNotFoundException, AccesoDatosException, TipoProductoInvalidoException, PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException {
		productos = archivo.cargar();
	}
	
	@Override
	public List<Producto> getAll()
			throws ClassNotFoundException, AccesoDatosException, TipoProductoInvalidoException, PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException {
		productos = archivo.cargar();
		return productos.isEmpty() ? List.of() : productos;
	}

	@Override
	public boolean add(Producto producto) throws AccesoDatosException {
		if(findByNombre(producto.getNombre()) == null) {
			productos.add(producto);
			archivo.guardar(productos);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String nombre) throws AccesoDatosException, IOException {
		Producto productoBorrar = findByNombre(nombre);
		if(productoBorrar != null) {
			productos.remove(productoBorrar);
			recrearArchivo();
			archivo.guardar(productos);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(String nombreActualizar, Producto actualizado) throws AccesoDatosException, IOException {
		Producto productoActualizar = findByNombre(nombreActualizar);
		if(productoActualizar != null) {
			int indice = productos.indexOf(productoActualizar);
			if(indice != -1) {
				productos.set(indice, actualizado);
				recrearArchivo();
				archivo.guardar(productos);
				return true;
			}
		}
		return false;
	}

	@Override
	public Producto findByNombre(String nombre) { 
		for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
	}

	@Override
	public List<Producto> filtrarPorNombre(String nombreParcial) { 
		List<Producto> resultados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(nombreParcial.toLowerCase())) {
                resultados.add(producto);
            }
        }
        return resultados;
	}

	@Override
	public List<Producto> filtrarPorCantidad(int cantidadMinima) {
		List<Producto> resultados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCantidad() >= cantidadMinima) {
                resultados.add(producto);
            }
        }
        return resultados;
	}

	@Override
	public List<Producto> filtrarPorPrecio(double precioMin, double precioMax) {
		List<Producto> resultados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getPrecioVenta() >= precioMin && producto.getPrecioVenta() <= precioMax) {
                resultados.add(producto);
            }
        }
        return resultados;
	}
	
	@Override
	public List<Producto> filtrarPorTipo(String tipo) {
	    List<Producto> filtrados = new ArrayList<>();

	    for (Producto p : productos) {
	        if (tipo.equalsIgnoreCase("Pan") && p instanceof Pan) {
	            filtrados.add(p);
	        } else if (tipo.equalsIgnoreCase("Galleta") && p instanceof Galleta) {
	            filtrados.add(p);
	        }
	    }

	    return filtrados;
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
	
	private void recrearArchivo() throws IOException {
		if (archivo.getUbicacionArchivo().exists()) {
			archivo.getUbicacionArchivo().delete();
		}
		archivo.getUbicacionArchivo().createNewFile();
	}

}
