package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Panaderia;
import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.model.exception.AccesoDatosException;
import co.edu.unbosque.model.exception.CantidadInvalidaException;
import co.edu.unbosque.model.exception.NombreProductoInvalidoException;
import co.edu.unbosque.model.exception.PrecioInvalidoException;
import co.edu.unbosque.model.exception.TipoProductoInvalidoException;
import co.edu.unbosque.view.Vista;

public class Controlador implements ActionListener {
	private Panaderia panaderia;
	private Vista vista;

	public Controlador() {
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
		vista = new Vista();
		asignaOyentes();
		reiniciarTabla();
	}

	public void reiniciarTabla() {
		List<ProductoDTO> todosEmpleados = new ArrayList();
		try {
			try {
				todosEmpleados = panaderia.listarProductos();
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
		vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(todosEmpleados);
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
			}
			case "Cantidad" -> {
				vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorCantidad();
				vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton().addActionListener(this);

				if (comando.equals("FILTRAR_BUTTON")) {
					filtrarPorCantidad();
				}
			}
			case "Precio" -> {
				vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorPrecio();
				vista.getVentanaPrincipal().getPanelBusqueda().getFiltrarButton().addActionListener(this);

				if (comando.equals("FILTRAR_BUTTON")) {
					filtrarPorPrecio();
				}
			}
			default -> {
				reiniciarTabla();
				vista.getVentanaPrincipal().getPanelBusqueda().limpiarFiltroDinamico();
				vista.getVentanaPrincipal().getPanelBusqueda().getBuscarButton().setVisible(true);
			}
			}

		} else if (comando.equals("FILTRO_TIPO")) {
			filtrarPorTipo();

		} else if (comando.equals("ELIMINAR_PRODUCTO")) {
			System.out.println(comando);

		} else if (comando.equals("CREAR_PRODUCTO")) {
			vista.getVentanaProducto().setVisible(true);
			reiniciarCamposEdicionCreacion();
			System.out.println(comando);

		} else if (comando.equals("CREAR")) {
			System.out.println(comando);

		} else if (comando.equals("CANCELAR")) {
			vista.getVentanaProducto().setVisible(false);
			System.out.println(comando);

		} else if (comando.equals("EDITAR_PRODUCTO")) {
			System.out.println(comando);

		} else if (comando.equals("TIPO_PRODUCTO")) {
			String tipoProducto = vista.getVentanaProducto().getPanelSuperior().getTipoProductoComboBox()
					.getSelectedItem().toString();
			vista.getVentanaProducto().getPanelDinamico().mostrarPanel(tipoProducto);
			System.out.println(comando);

		} else if (comando.equals("EDITAR")) {
			System.out.println(comando);
		}

	}

	public void agregarProducto() {
		String[] datos = vista.getVentanaProducto().getPanelSuperior().obtenerCamposFormulario();
		String nombre = datos[0];
		try {
			double precioVenta = Double.parseDouble(datos[1]);
			double costoProduccion = Double.parseDouble(datos[2]);
			int cantidadField = Integer.parseInt(datos[3]);
		} catch (NumberFormatException ex) {
			vista.mostrarMensajeError("El valor del precio debe ser numerico.");
		}
		String tipo = datos[4];
	}

	public void editarProducto() {

	}

	public void eliminarProducto(String nombre) {

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

	public void filtrarPorNombreExacto(String nombre) {

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
		String seleccion = vista.getVentanaPrincipal().getPanelBusqueda().getFiltroComboBox().getSelectedItem()
				.toString();

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

	public void guardarProducto() {

	}

	public void exportarCSV() {

	}

}
