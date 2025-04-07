package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelProductoDinamico extends JPanel {

	private CardLayout cardLayout;
	private JPanel panelCentro;
	private JButton crearButton, cancelarButton, editarButton;

	public PanelProductoDinamico() {

		setLayout(new BorderLayout());

		cardLayout = new CardLayout();
		panelCentro = new JPanel(cardLayout);
		add(panelCentro, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		crearButton = new JButton("Crear");
		crearButton.setActionCommand("CREAR");
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setActionCommand("CANCELAR");
		editarButton = new JButton("Editar");
		editarButton.setActionCommand("EDITAR");
		panelBotones.add(crearButton);
		panelBotones.add(cancelarButton);
		panelBotones.add(editarButton);
		add(panelBotones, BorderLayout.SOUTH);
	}

	public void mostrarPanel(String tipoProducto) {
		JPanel nuevoPanel = FabricaPanelProductoDinamico.crearPanel(tipoProducto);
		panelCentro.removeAll();
		panelCentro.add(nuevoPanel, "panel");
		cardLayout.show(panelCentro, "panel");
		revalidate();
		repaint();
	}

	public JPanel getPanelActual() {
		for (Component comp : panelCentro.getComponents()) {
			if (comp.isVisible()) {
				return (JPanel) comp;
			}
		}
		return null;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getPanelCentro() {
		return panelCentro;
	}

	public void setPanelCentro(JPanel panelCentro) {
		this.panelCentro = panelCentro;
	}

	public JButton getCrearButton() {
		return crearButton;
	}

	public void setCrearButton(JButton crearButton) {
		this.crearButton = crearButton;
	}

	public JButton getCancelarButton() {
		return cancelarButton;
	}

	public void setCancelarButton(JButton cancelarButton) {
		this.cancelarButton = cancelarButton;
	}

	public JButton getEditarButton() {
		return editarButton;
	}

	public void setEditarButton(JButton editarButton) {
		this.editarButton = editarButton;
	}


}
