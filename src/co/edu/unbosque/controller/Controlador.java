package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import co.edu.unbosque.model.Panaderia;
import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.exception.AccesoDatosException;
import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoDuplicadoException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;
import co.edu.unbosque.model.exception.ProductoNoEncontradoException;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;
import co.edu.unbosque.view.PanelGalleta;
import co.edu.unbosque.view.PanelPan;
import co.edu.unbosque.view.Vista;

public class Controlador implements ActionListener {
	private Panaderia panaderia;
	private Vista vista;

	public Controlador() {

		vista = new Vista();
		asignaOyentes();

		try {
			try {
				panaderia = new Panaderia();
			} catch (ClassNotFoundException e) {
				vista.mostrarMensajeError("La clase no existe en persistencia: " + e.getMessage());
				e.printStackTrace();
			} catch (TipoProductoInvalidoException e) {
				vista.mostrarMensajeError("El Tipo de producto de un DTO es invalido: " + e.getMessage());
				e.printStackTrace();
			} catch (PrecioInvalidoException e) {
				vista.mostrarMensajeError("El precio de un producto es inválido: " + e.getMessage());
				e.printStackTrace();
			} catch (CantidadInvalidaException e) {
				vista.mostrarMensajeError("La cantidad de un producto es inválida: " + e.getMessage());
				e.printStackTrace();
			} catch (NombreProductoInvalidoException e) {
				vista.mostrarMensajeError("El nombre de un producto es inválido: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (AccesoDatosException e) {
			vista.mostrarMensajeError("Error al acceder al archivo de persistencia: " + e.getMessage());
			e.printStackTrace();
		}

		reiniciarTabla();

	}

	public void reiniciarTabla() {
		List<ProductoDTO> todosProductos = new ArrayList<ProductoDTO>();
		try {
			try {
				todosProductos = panaderia.listarProductos();
			} catch (AccesoDatosException e) {
				vista.mostrarMensajeError("Error leyendo el archivo: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			vista.mostrarMensajeError("La clase no existe en persistencia: " + e.getMessage());
			e.printStackTrace();
		} catch (TipoProductoInvalidoException e) {
			vista.mostrarMensajeError("El Tipo de producto de un DTO es invalido: " + e.getMessage());
			e.printStackTrace();
		} catch (PrecioInvalidoException e) {
			vista.mostrarMensajeError("El precio de un producto es inválido: " + e.getMessage());
			e.printStackTrace();
		} catch (CantidadInvalidaException e) {
			vista.mostrarMensajeError("La cantidad de un producto es inválida: " + e.getMessage());
			e.printStackTrace();
		} catch (NombreProductoInvalidoException e) {
			vista.mostrarMensajeError("El nombre de un producto es inválido: " + e.getMessage());
			e.printStackTrace();
		}
		vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(todosProductos);
	}

	public void asignaOyentes() {
		vista.getVentanaPrincipal().getPanelBusqueda().getBuscarButton().addActionListener(this);
		vista.getVentanaPrincipal().getPanelBusqueda().getBuscarPorComboBox().addActionListener(this);
		vista.getVentanaPrincipal().getPanelBusqueda().getFiltroComboBox().addActionListener(this);
		vista.getVentanaPrincipal().getPanelInferior().getCrearButton().addActionListener(this);
		vista.getVentanaPrincipal().getPanelInferior().getEditarButton().addActionListener(this);
		vista.getVentanaPrincipal().getPanelInferior().getEliminarButton().addActionListener(this);
		vista.getVentanaPrincipal().getPanelInferior().getExportarCsvButton().addActionListener(this);
		vista.getVentanaProducto().getPanelSuperior().getTipoProductoComboBox().addActionListener(this);
		vista.getVentanaProducto().getPanelDinamico().getEditarButton().addActionListener(this);
		vista.getVentanaProducto().getPanelDinamico().getCancelarButton().addActionListener(this);
		vista.getVentanaProducto().getPanelDinamico().getCrearButton().addActionListener(this);

	}

	private String obtenerTextoBusqueda() {
		return vista.getVentanaPrincipal().getPanelBusqueda().getBuscarTextField().getText().trim().toLowerCase();
	}

	private void reiniciarCamposEdicionCreacion() {
		vista.getVentanaProducto().getPanelSuperior().limpiarCamposFormulario();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("BUSCAR")) {
			reiniciarTabla();
			filtarPorNombreCoincidencia();

		} else if (comando.equals("FILTRO_POR")) {
			reiniciarTabla();
			vista.getVentanaPrincipal().getPanelBusqueda().getBuscarButton().setVisible(false);
			String seleccion = vista.getVentanaPrincipal().getPanelBusqueda().getFiltroComboBox().getSelectedItem()
					.toString();

			switch (seleccion) {
			case "Tipo" -> {
				vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorTipo();
				vista.getVentanaPrincipal().getPanelBusqueda().getTipoProductoFiltroComboBox().addActionListener(this);
				if (comando.equals("FILTRO_TIPO")) {
					filtrarPorTipo();
				}

			}
			case "Cantidad" -> {
				vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorCantidad();
				for (ActionListener al : vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton()
						.getActionListeners()) {
					vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton().removeActionListener(al);
				}
				vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton().addActionListener(this);

			}
			case "Precio" -> {
				vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorPrecio();
				for (ActionListener al : vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton()
						.getActionListeners()) {
					vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton().removeActionListener(al);
				}
				vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton().addActionListener(this);

			}
			default -> {
				reiniciarTabla();
				vista.getVentanaPrincipal().getPanelBusqueda().limpiarFiltroDinamico();
				vista.getVentanaPrincipal().getPanelBusqueda().getBuscarButton().setVisible(true);
			}
			}

		} else if (comando.equals("FILTRAR_BUTTON")) {
			String seleccion = vista.getVentanaPrincipal().getPanelBusqueda().getFiltroComboBox().getSelectedItem()
					.toString();

			switch (seleccion) {
			case "Cantidad" -> filtrarPorCantidad();
			case "Precio" -> filtrarPorPrecio();
			}

		}

		else if (comando.equals("FILTRO_TIPO")) {
			filtrarPorTipo();

		} else if (comando.equals("ELIMINAR_PRODUCTO")) {
			eliminarProducto();

		} else if (comando.equals("CREAR_PRODUCTO")) {
			vista.getVentanaProducto().setVisible(true);
			vista.getVentanaProducto().getPanelDinamico().getEditarButton().setVisible(false);
			reiniciarCamposEdicionCreacion();

		} else if (comando.equals("CREAR")) {
			crearProducto();

		} else if (comando.equals("CANCELAR")) {
			vista.getVentanaProducto().setVisible(false);

		} else if (comando.equals("EDITAR_PRODUCTO")) {
			editarProducto();

		} else if (comando.equals("TIPO_PRODUCTO")) {
			String tipoProducto = vista.getVentanaProducto().getPanelSuperior().getTipoProductoComboBox()
					.getSelectedItem().toString();
			vista.getVentanaProducto().getPanelDinamico().mostrarPanel(tipoProducto);

		} else if (comando.equals("EDITAR")) {
			finalizarEdicionProducto();
		} else if (comando.equals("EXPORTAR_CSV")) {
			exportarCSV();
		}
	}

	public void crearProducto() {
		vista.getVentanaProducto().getPanelDinamico().getCrearButton().setVisible(true);
		String[] datos = vista.getVentanaProducto().getPanelSuperior().obtenerCamposFormulario();
		String nombre = datos[0];
		double precioVenta = Double.parseDouble(datos[1]);
		double costoProduccion = Double.parseDouble(datos[2]);
		int cantidad = Integer.parseInt(datos[3]);

		String tipo = datos[4];

		if (tipo.equals("Pan")) {
			PanelPan panelPan = (PanelPan) vista.getVentanaProducto().getPanelDinamico().getPanelActual();
			boolean conQueso = panelPan.isConQueso();

			int confirmacion = vista.mostrarMensajeConfirmacion("¿Está seguro de crear el nuevo producto?");

			if (confirmacion == JOptionPane.YES_OPTION) {
				ProductoDTO nuevoProducto = new ProductoDTO();
				nuevoProducto.setNombre(nombre);
				nuevoProducto.setPrecioVenta(precioVenta);
				nuevoProducto.setCostoProduccion(costoProduccion);
				nuevoProducto.setCantidad(cantidad);
				nuevoProducto.setTipo(tipo);
				nuevoProducto.setConQueso(conQueso);
				try {
					panaderia.crearProducto(nuevoProducto);
				} catch (NombreProductoDuplicadoException e) {
					vista.mostrarMensajeError("Error en el nombre del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (PrecioInvalidoException e) {
					vista.mostrarMensajeError("Error en el precio del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (CantidadInvalidaException e) {
					vista.mostrarMensajeError("Error en la cantidad del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (TipoProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el tipo de producto:" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (NombreProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el nombre del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (AccesoDatosException e) {
					vista.mostrarMensajeError("Error guardando el archivo en BD:\n" + e.getMessage());
					e.printStackTrace();
					return;
				}
				reiniciarTabla();
				vista.mostrarMensajeExito("Producto creado exitosamente!");
				vista.getVentanaProducto().setVisible(false);
			} else {
				vista.mostrarMensajeExito("Operación cancelada exitosamente.");
				vista.getVentanaProducto().setVisible(false);
			}

		} else if (tipo.equals("Galleta")) {
			PanelGalleta panelGalleta = (PanelGalleta) vista.getVentanaProducto().getPanelDinamico().getPanelActual();
			boolean conChispas = panelGalleta.isConChispas();

			int confirmacion = vista.mostrarMensajeConfirmacion("¿Está seguro de crear el nuevo producto?");

			if (confirmacion == JOptionPane.YES_OPTION) {
				ProductoDTO nuevoProducto = new ProductoDTO();
				nuevoProducto.setNombre(nombre);
				nuevoProducto.setPrecioVenta(precioVenta);
				nuevoProducto.setCostoProduccion(costoProduccion);
				nuevoProducto.setCantidad(cantidad);
				nuevoProducto.setTipo(tipo);
				nuevoProducto.setConChispas(conChispas);
				try {
					panaderia.crearProducto(nuevoProducto);
				} catch (NombreProductoDuplicadoException e) {
					vista.mostrarMensajeError("Error en el nombre del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (PrecioInvalidoException e) {
					vista.mostrarMensajeError("Error en el precio del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (CantidadInvalidaException e) {
					vista.mostrarMensajeError("Error en la cantidad del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (TipoProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el tipo de producto:" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (NombreProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el nombre del producto:\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (AccesoDatosException e) {
					vista.mostrarMensajeError("Error guardando el archivo en BD:\n" + e.getMessage());
					e.printStackTrace();
					return;
				}
				reiniciarTabla();
				vista.mostrarMensajeExito("Producto creado exitosamente!");
				vista.getVentanaProducto().setVisible(false);
			} else {
				vista.mostrarMensajeExito("Operación cancelada exitosamente.");
				vista.getVentanaProducto().setVisible(false);
			}
		}

	}

	public void editarProducto() {
		vista.getVentanaProducto().getPanelDinamico().getCrearButton().setVisible(false);
		vista.getVentanaProducto().getPanelDinamico().getEditarButton().setVisible(true);
		int filaSeleccionada = vista.getVentanaPrincipal().getPanelTabla().getTablaProductos().getSelectedRow();
		if (filaSeleccionada == -1) {
			vista.mostrarMensajeError("Por favor, seleccione un producto en la tabla para editar.");
		} else {
			String nombreEditar = vista.getVentanaPrincipal().getPanelTabla().getTablaProductos()
					.getValueAt(filaSeleccionada, 0).toString();
			try {
				ProductoDTO productoEditar = panaderia.buscarPorNombre(nombreEditar);
				reiniciarCamposEdicionCreacion();
				vista.getVentanaProducto().getPanelSuperior().rellenarCamposFormulario(productoEditar);
				vista.getVentanaProducto().getPanelSuperior().getNombreField().setEditable(false);
				vista.getVentanaProducto().getPanelSuperior().getTipoProductoComboBox().setEnabled(false);
				if(productoEditar.getTipo().equals("Pan")) {
					PanelPan panelPan = (PanelPan) vista.getVentanaProducto().getPanelDinamico().getPanelActual();
					panelPan.setConQueso(productoEditar.isConQueso());
				}else if(productoEditar.getTipo().equals("Galleta")) {
					PanelGalleta panelGalleta = (PanelGalleta) vista.getVentanaProducto().getPanelDinamico().getPanelActual();
					panelGalleta.setConChispas(productoEditar.isConChispas());
				}
				vista.getVentanaProducto().setVisible(true);
			} catch (ProductoNoEncontradoException e) {
				vista.mostrarMensajeError("Error buscando el producto:" + e.getMessage());
				e.printStackTrace();
				return;
			}
		}
	}

	public void finalizarEdicionProducto() {
		String[] datos = vista.getVentanaProducto().getPanelSuperior().obtenerCamposFormulario();
		String nombre = datos[0];
		double precioVenta = Double.parseDouble(datos[1]);
		double costoProduccion = Double.parseDouble(datos[2]);
		int cantidad = Integer.parseInt(datos[3]);
		String tipo = datos[4];
		ProductoDTO productoNuevo = new ProductoDTO();
		productoNuevo.setNombre(nombre);
		productoNuevo.setPrecioVenta(precioVenta);
		productoNuevo.setCostoProduccion(costoProduccion);
		productoNuevo.setCantidad(cantidad);
		productoNuevo.setTipo(tipo);

		if (tipo.equals("Pan")) {
			PanelPan panelPan = (PanelPan) vista.getVentanaProducto().getPanelDinamico().getPanelActual();
			boolean conQueso = panelPan.isConQueso();
			productoNuevo.setConQueso(conQueso);

			int confirmacion = vista.mostrarMensajeConfirmacion("¿Está seguro de editar el producto?");

			if (confirmacion == JOptionPane.YES_OPTION) {
				try {
					panaderia.editarProducto(nombre, productoNuevo);
				} catch (NombreProductoDuplicadoException e) {
					vista.mostrarMensajeError("Error en el nombre: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (PrecioInvalidoException e) {
					vista.mostrarMensajeError("Error en el precio: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (CantidadInvalidaException e) {
					vista.mostrarMensajeError("Error en la cantidad: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (TipoProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el tipo de producto: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (NombreProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el nombre: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (AccesoDatosException e) {
					vista.mostrarMensajeError("Error guardando cambios en BBDD " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (IOException e) {
					vista.mostrarMensajeError("Error accediendo al archivo " + e.getMessage());
					e.printStackTrace();
					return;
				}
				reiniciarTabla();
				vista.mostrarMensajeExito("Producto editado exitosamente.");
				vista.getVentanaProducto().setVisible(false);

			} else {
				vista.mostrarMensajeExito("Operación cancelada exitosamente.");
				vista.getVentanaProducto().setVisible(false);
			}

		} else if (tipo.equals("Galleta")) {
			PanelGalleta panelGalleta = (PanelGalleta) vista.getVentanaProducto().getPanelDinamico().getPanelActual();
			boolean conChispas = panelGalleta.isConChispas();
			productoNuevo.setConChispas(conChispas);

			int confirmacion = vista.mostrarMensajeConfirmacion("¿Está seguro de editar el producto?");

			if (confirmacion == JOptionPane.YES_OPTION) {
				try {
					panaderia.editarProducto(nombre, productoNuevo);
				} catch (NombreProductoDuplicadoException e) {
					vista.mostrarMensajeError("Error en el nombre: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (PrecioInvalidoException e) {
					vista.mostrarMensajeError("Error en el precio: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (CantidadInvalidaException e) {
					vista.mostrarMensajeError("Error en la cantidad: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (TipoProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el tipo de producto: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (NombreProductoInvalidoException e) {
					vista.mostrarMensajeError("Error en el nombre: " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (AccesoDatosException e) {
					vista.mostrarMensajeError("Error guardando cambios en BBDD " + e.getMessage());
					e.printStackTrace();
					return;
				} catch (IOException e) {
					vista.mostrarMensajeError("Error accediendo al archivo " + e.getMessage());
					e.printStackTrace();
					return;
				}
				reiniciarTabla();
				vista.mostrarMensajeExito("Producto editado exitosamente.");
				vista.getVentanaProducto().setVisible(false);

			} else {
				vista.mostrarMensajeExito("Operación cancelada exitosamente.");
				vista.getVentanaProducto().setVisible(false);
			}
		}

	}

	public void eliminarProducto() {
		int filaSeleccionada = vista.getVentanaPrincipal().getPanelTabla().getTablaProductos().getSelectedRow();
		if (filaSeleccionada == -1) {
			vista.mostrarMensajeError("Por favor, seleccione un producto de la tabla para eliminar.");
		} else {
			int opcion = vista.mostrarMensajeConfirmacion("¿Está seguro que desea eliminar el producto?");

			if (opcion == JOptionPane.YES_OPTION) {
				String nombreEliminar = vista.getVentanaPrincipal().getPanelTabla().getTablaProductos()
						.getValueAt(filaSeleccionada, 0).toString();
				try {
					panaderia.eliminarProducto(nombreEliminar);
				} catch (ProductoNoEncontradoException e) {
					vista.mostrarMensajeError("Verifique el producto\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (AccesoDatosException e) {
					vista.mostrarMensajeError("Error actualizando la BBDD\n" + e.getMessage());
					e.printStackTrace();
					return;
				} catch (IOException e) {
					vista.mostrarMensajeError("Error accediendo al archivo de persistencia\n" + e.getMessage());
					e.printStackTrace();
					return;
				}
				vista.mostrarMensajeExito("Producto eliminado exitosamente.");
				reiniciarTabla();
			} else {
				vista.mostrarMensajeExito("Operacion cancelada exitosamente.");
			}
		}
	}

	public void filtarPorNombreCoincidencia() {
		String texto = obtenerTextoBusqueda();
		int pos = vista.getVentanaPrincipal().getPanelBusqueda().getBuscarPorComboBox().getSelectedIndex();
		String tipoBusqueda = vista.getVentanaPrincipal().getPanelBusqueda().getBuscarPorComboBox().getItemAt(pos)
				.toLowerCase();
		List<ProductoDTO> coincidencias = new ArrayList<ProductoDTO>();

		if (tipoBusqueda == null || tipoBusqueda.isEmpty() || tipoBusqueda.equals("Seleccionar")) {
			vista.mostrarMensajeError("Por favor seleccion un tipo de búsqueda.");

		} else if (texto == null || texto.isEmpty()) {
			vista.mostrarMensajeError("Por favor ingrese algo para buscar");

		} else if (tipoBusqueda.equals("nombre")) {
			String nombreBuscar = texto;
			coincidencias = panaderia.filtrarPorNombre(nombreBuscar);

			if (coincidencias.isEmpty()) {
				vista.mostrarMensajeAdvertencia("No se encontró ningún producto.");
			} else {
				vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(coincidencias);
			}
		}
	}

	public void filtrarPorCantidad() {
		String entrada = vista.getVentanaPrincipal().getPanelBusqueda().getCantidadMinimaField().getText();
		int cantidad = Integer.parseInt(entrada);
		List<ProductoDTO> coincidencias = panaderia.filtrarPorCantidad(cantidad);

		if (coincidencias.isEmpty()) {
			vista.mostrarMensajeAdvertencia("No se encontró ningun producto en ese rango de precios.");
		} else {
			vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(coincidencias);
		}
	}

	public void filtrarPorPrecio() {
		String entradaInferior = vista.getVentanaPrincipal().getPanelBusqueda().getPrecioMinimoField().getText();
		double precioMinimo = Double.parseDouble(entradaInferior);
		String entradaSuperior = vista.getVentanaPrincipal().getPanelBusqueda().getPrecioMaximoField().getText();
		double precioMaximo = Double.parseDouble(entradaSuperior);
		List<ProductoDTO> datos = panaderia.filtrarPorPrecio(precioMinimo, precioMaximo);
		vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(datos);
	}

	public void filtrarPorTipo() {
		String seleccion = vista.getVentanaPrincipal().getPanelBusqueda().getTipoProductoFiltroComboBox()
				.getSelectedItem().toString();

		switch (seleccion) {
		case "Pan" -> {
			try {
				List<ProductoDTO> coincidencias = panaderia.filtrarPorTipo(seleccion);
				vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(coincidencias);
			} catch (TipoProductoInvalidoException e1) {
				vista.mostrarMensajeError(e1.getMessage());
			}
		}
		case "Galleta" -> {
			try {
				List<ProductoDTO> coincidencias = panaderia.filtrarPorTipo(seleccion);
				vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(coincidencias);
			} catch (TipoProductoInvalidoException e1) {
				vista.mostrarMensajeError(e1.getMessage());
			}
		}

		default -> {
			reiniciarTabla();
			vista.mostrarMensajeError("Opcion no válida");
		}
		}
	}

	public void exportarCSV() {
		List<ProductoDTO> productos;
		try {
			productos = panaderia.listarProductos();
			try {
				panaderia.exportarProductosCSV(productos);
			} catch (AccesoDatosException e) {
				vista.mostrarMensajeError("Error accediendo al archivo:" + e.getMessage());
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			vista.mostrarMensajeError("Error con el archivo csv" + e.getMessage());
			e.printStackTrace();
			return;
		} catch (AccesoDatosException e) {
			vista.mostrarMensajeError("Error accediendo al archivo: " + e.getMessage());
			e.printStackTrace();
			return;
		} catch (TipoProductoInvalidoException e) {
			vista.mostrarMensajeError("Error en tipo de producto" + e.getMessage());
			e.printStackTrace();
			return;
		} catch (PrecioInvalidoException e) {
			vista.mostrarMensajeError("Error en el precio del producto: " + e.getMessage());
			e.printStackTrace();
			return;
		} catch (CantidadInvalidaException e) {
			vista.mostrarMensajeError("Error en la cantidad de un producto: " + e.getMessage());
			e.printStackTrace();
			return;
		} catch (NombreProductoInvalidoException e) {
			vista.mostrarMensajeError("Error en el nombre de un producto: " + e.getMessage());
			e.printStackTrace();
		}

		vista.mostrarMensajeExito("Archivo CSV creado con exito.");

	}

}
