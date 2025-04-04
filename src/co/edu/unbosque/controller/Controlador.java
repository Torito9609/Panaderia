package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Panaderia;
import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.view.Vista;

public class Controlador implements ActionListener {
	private Panaderia panaderia;
	private Vista vista;

	public Controlador() {
		panaderia = new Panaderia();
		vista = new Vista();
		asignaOyentes();
		reiniciarTabla();
	}

	private void reiniciarTabla() {
		List<ProductoDTO> todosEmpleados = new ArrayList();
		todosEmpleados = panaderia.listarProductos();
		vista.getVentanaPrincipal().getPanelTabla().actualizarTabla(todosEmpleados);
	}

	public void asignaOyentes() {
		vista.getVentanaPrincipal().getPanelBusqueda().getBuscarButton().addActionListener(this);
		vista.getVentanaPrincipal().getPanelBusqueda().getBuscarPorComboBox().addActionListener(this);
		vista.getVentanaPrincipal().getPanelBusqueda().getFiltroComboBox().addActionListener(this);
		vista.getVentanaPrincipal().getPanelInferior().getCrearButton().addActionListener(this);
		vista.getVentanaPrincipal().getPanelInferior().getEditarButton().addActionListener(this);
		vista.getVentanaPrincipal().getPanelInferior().getEliminarButton().addActionListener(this);
		vista.getVentanaProducto().getPanelSuperior().getTipoProductoComboBox().addActionListener(this);
		vista.getVentanaProducto().getPanelDinamico().getEditarButton().addActionListener(this);
		vista.getVentanaProducto().getPanelDinamico().getCancelarButton().addActionListener(this);
		vista.getVentanaProducto().getPanelDinamico().getCrearButton().addActionListener(this);

	}

	private String obtenerTextoBusqueda() {
		return vista.getVentanaPrincipal().getPanelBusqueda().getBuscarTextField().getText().trim().toLowerCase();
	}

	private void reiniciarCamposEdicionCreacion() {
		vista.getVentanaProducto().getPanelSuperior().limpiarCampos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("BUSCAR_POR")) {
			System.out.println(comando);
		}

		else if (comando.equals("BUSCAR")) {
			System.out.println(comando);

		} else if (comando.equals("FILTRO_POR")) {
			System.out.println(comando);
			String seleccion = vista.getVentanaPrincipal().getPanelBusqueda().getFiltroComboBox().getSelectedItem()
					.toString();
			switch (seleccion) {
			case "Tipo" -> {
				vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorTipo();
				vista.getVentanaPrincipal().getPanelBusqueda().getTipoProductoFiltroComboBox().addActionListener(this);
			}
			case "Cantidad" -> vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorCantidad();
			case "Precio" -> vista.getVentanaPrincipal().getPanelBusqueda().mostrarFiltroPorPrecio();
			default -> vista.getVentanaPrincipal().getPanelBusqueda().limpiarFiltroDinamico();
			}

		} else if (comando.equals("FILTRO_TIPO")) {
			System.out.println(comando);

		} else if (comando.equals("ELIMINAR_PRODUCTO")) {
			System.out.println(comando);

		} else if (comando.equals("CREAR_PRODUCTO")) {
			vista.getVentanaProducto().setVisible(true);
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

	}

	public void editarProducto() {

	}

	public void eliminarProducto(String nombre) {

	}

	public void filtarPorNombreCoincidencia(String nombre) {

	}

	public void filtrarPorNombreExacto(String nombre) {

	}

	public void filtrarPorCantidad(int cantidad) {

	}

	public void filtrarPorPrecio(double limInferior, double limSuperior) {

	}

	public void guardarProducto() {

	}

	public void exportarCSV() {

	}

}
