package co.edu.unbosque.model.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.AccesoDatosException;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;

public class Archivo {

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private final File ubicacionArchivo = new File(ConstanteArchivo.ARCHIVO_PRODUCTOS);

	public Archivo() throws AccesoDatosException {
		if (!ubicacionArchivo.exists()) {
			try {
				if (!ubicacionArchivo.createNewFile()) {
					throw new AccesoDatosException("No se pudo crear el archivo");
				}
			} catch (IOException ex) {
				throw new AccesoDatosException("Error al cargar el archivo de datos", ex);
			}
		}

	}

	public void guardar(List<Producto> productos) throws AccesoDatosException {
		try {
			salida = new ObjectOutputStream(new FileOutputStream(ubicacionArchivo));
			List<ProductoDTO> datos = new ArrayList<ProductoDTO>();
			for (Producto p : productos) {
				datos.add(MapHandler.productoADTO(p));
			}
			salida.writeObject(datos);
			salida.close();
		} catch (IOException e) {
			throw new AccesoDatosException("Error al escribir en el archivo de datos.", e);
		}

	}

	public List<Producto> cargar() throws AccesoDatosException, ClassNotFoundException, TipoProductoInvalidoException {
		if (ubicacionArchivo.length() == 0) {
			return new ArrayList<Producto>();
		}

		try {
			entrada = new ObjectInputStream(new FileInputStream(ubicacionArchivo));
			@SuppressWarnings("unchecked")
			List<ProductoDTO> datos = (List<ProductoDTO>) entrada.readObject();
			entrada.close();
			List<Producto> datosSalida = new ArrayList<Producto>();
			for (ProductoDTO p : datos) {
				datosSalida.add(MapHandler.dtoAProducto(p));
			}
			return datosSalida;
		} catch (IOException | ClassNotFoundException ex) {
			throw new AccesoDatosException("Error al leer el archivo de datos", ex);
		}

	}

	public File getUbicacionArchivo() {
		return ubicacionArchivo;
	}

}
