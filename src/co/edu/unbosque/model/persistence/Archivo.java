package co.edu.unbosque.model.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.AccesoDatosException;
import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;

public class Archivo {

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private final File ubicacionArchivo = new File(ConstanteArchivo.ARCHIVO_PRODUCTOS);

	public Archivo() throws AccesoDatosException {
	    try {
	        File parentDir = ubicacionArchivo.getParentFile();
	        if (parentDir != null && !parentDir.exists()) {
	            if (!parentDir.mkdirs()) {
	                throw new AccesoDatosException("No se pudo crear el directorio para el archivo.");
	            }
	        }

	        if (!ubicacionArchivo.exists()) {
	            if (!ubicacionArchivo.createNewFile()) {
	                throw new AccesoDatosException("No se pudo crear el archivo");
	            }
	        }

	    } catch (IOException ex) {
	        throw new AccesoDatosException("Error al cargar el archivo de datos", ex);
	    }
	}


	public void guardar(List<Producto> productos) throws AccesoDatosException {
		try {
			salida = new ObjectOutputStream(new FileOutputStream(ubicacionArchivo));
			List<ProductoDTO> datos = MapHandler.todosProductoADTO(productos);
			salida.writeObject(datos);
			salida.close();
		} catch (IOException e) {
			throw new AccesoDatosException("Error al escribir en el archivo de datos.", e);
		}
	}

	public List<Producto> cargar() throws AccesoDatosException, ClassNotFoundException, TipoProductoInvalidoException,
			PrecioInvalidoException, CantidadInvalidaException, NombreProductoInvalidoException, ClassNotFoundException {
		if (ubicacionArchivo.length() == 0) {
			return new ArrayList<Producto>();
		}

		try {
			entrada = new ObjectInputStream(new FileInputStream(ubicacionArchivo));
			@SuppressWarnings("unchecked")
			List<ProductoDTO> datos = (List<ProductoDTO>) entrada.readObject();
			entrada.close();
			List<Producto> datosSalida = MapHandler.todosDtoAProducto(datos);
			return datosSalida;
		} catch (IOException ex) {
			throw new AccesoDatosException("Error al leer el archivo de datos", ex);
		}

	}

	public File getUbicacionArchivo() {
		return ubicacionArchivo;
	}

}
