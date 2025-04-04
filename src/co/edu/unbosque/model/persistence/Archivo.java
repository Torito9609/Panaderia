package co.edu.unbosque.model.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.ExcepcionAccesoDatos;

public class Archivo {

	private final File ubicacionArchivo = new File(ConstanteArchivo.ARCHIVO_PRODUCTOS);

	public Archivo() throws ExcepcionAccesoDatos {

	}

	public void guardar(List<Producto> clientes) throws ExcepcionAccesoDatos {

	}

	public List<Producto> cargar() throws ExcepcionAccesoDatos {
		return null;
	}
}
