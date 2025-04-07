package co.edu.unbosque.model.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.AccesoDatosException;

public class ExportadorCSV {
	public static void exportar(List<Producto> productos, String ruta) throws AccesoDatosException{
		try(FileWriter writer = new FileWriter(ruta)){
			writer.append("Nombre,PrecioVenta,CostoProduccion,Cantidad,Tipo,ConQueso,ConChispas\n");
			
			for(Producto p : productos) {
				ProductoDTO dto = MapHandler.productoADTO(p);
				writer.append(String.format("%s,%.2f,%.2f,%d,%s,%s,%s\\n",
						dto.getNombre(),
						dto.getPrecioVenta(),
						dto.getCostoProduccion(),
						dto.getCantidad(),
						dto.getTipo(),
						String.valueOf(dto.isConQueso()),
						String.valueOf(dto.isConChispas())
						));
				
			}
		} catch(IOException ex) {
			throw new AccesoDatosException("Error al leer el archivo", ex);
		}
	}
}
