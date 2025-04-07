package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.entity.Galleta;
import co.edu.unbosque.model.entity.Pan;
import co.edu.unbosque.model.entity.Producto;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;

public class MapHandler {

	public static ProductoDTO productoADTO(Producto producto) {
		ProductoDTO dto = new ProductoDTO();
		dto.setNombre(producto.getNombre());
		dto.setPrecioVenta(producto.getPrecioVenta());
		dto.setCostoProduccion(producto.getCostoProduccion());
		dto.setCantidad(producto.getCantidad());

		if (producto instanceof Pan) {
			dto.setTipo("Pan");
			dto.setConQueso(((Pan) producto).isConQueso());
		} else if (producto instanceof Galleta) {
			dto.setTipo("Galleta");
			dto.setConChispas(((Galleta) producto).isConChispas());
		}

		return dto;
	}
	
	public static List<ProductoDTO> todosProductoADTO(List<Producto> productos){
		List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
		for(Producto p : productos) {
			productosDTO.add(productoADTO(p));
		}
		return productosDTO;
	}

	public static Producto dtoAProducto(ProductoDTO productoDTO) throws TipoProductoInvalidoException {
		String tipo = productoDTO.getTipo();

		switch (tipo) {
		case "Pan":
			return new Pan(productoDTO.getNombre(), productoDTO.getPrecioVenta(), productoDTO.getCostoProduccion(),
					productoDTO.getCantidad(), productoDTO.isConQueso());
		case "Galleta":
			return new Galleta(productoDTO.getNombre(), productoDTO.getPrecioVenta(), productoDTO.getCostoProduccion(),
					productoDTO.getCantidad(), productoDTO.isConChispas());
		default:
			throw new TipoProductoInvalidoException("Tipo de producto inválido: " + tipo);
		}
	}
	
	public static List<Producto> todosDtoAProducto(List<ProductoDTO> productosDTO) throws TipoProductoInvalidoException{
		List<Producto> productos = new ArrayList<Producto>();
		for(ProductoDTO p : productosDTO) {
			productos.add(dtoAProducto(p));
		}
		return productos;
	}
}
