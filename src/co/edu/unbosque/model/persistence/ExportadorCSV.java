package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.AccesoDatosException;

public class ExportadorCSV {
	private static final File ubicacionArchivo = new File(ConstanteArchivo.ARCHIVO_CSV);
	
	public static void exportar(List<ProductoDTO> productos) throws AccesoDatosException{
		try(FileWriter writer = new FileWriter(ubicacionArchivo)){
			writer.append("Nombre,PrecioVenta,CostoProduccion,Cantidad,Tipo,ConQueso,ConChispas\n");
			
			for(ProductoDTO p : productos) {
				
				writer.append(String.format("%s,%.2f,%.2f,%d,%s,%s,%s\\n",
						p.getNombre(),
						p.getPrecioVenta(),
						p.getCostoProduccion(),
						p.getCantidad(),
						p.getTipo(),
						String.valueOf(p.isConQueso()),
						String.valueOf(p.isConChispas())
						));
				
			}
		} catch(IOException ex) {
			throw new AccesoDatosException("Error al leer el archivo", ex);
		}
	}
}
