package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelInferior extends JPanel {

	private JButton crearButton, editarButton, eliminarButton;

	public PanelInferior() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		setBackground(Color.LIGHT_GRAY);

		inicializarComponentes();
	}

	private void inicializarComponentes() {
		crearButton = new JButton("Crear nuevo producto");
		crearButton.setActionCommand("CREAR_PRODUCTO");
		add(crearButton);

		editarButton = new JButton("Editar producto");
		editarButton.setActionCommand("EDITAR_PRODUCTO");
		add(editarButton);

		eliminarButton = new JButton("Eliminar producto");
		eliminarButton.setActionCommand("ELIMINAR_PRODUCTO");
		add(eliminarButton);

	}

	public JButton getCrearButton() {
		return crearButton;
	}

	public void setCrearButton(JButton crearButton) {
		this.crearButton = crearButton;
	}

	public JButton getEditarButton() {
		return editarButton;
	}

	public void setEditarButton(JButton editarButton) {
		this.editarButton = editarButton;
	}

	public JButton getEliminarButton() {
		return eliminarButton;
	}

	public void setEliminarButton(JButton eliminarButton) {
		this.eliminarButton = eliminarButton;
	}

}
