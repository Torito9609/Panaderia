package co.edu.unbosque.view;

import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private PanelBusqueda panelBusqueda;
    private PanelLateral panelLateral;
    private PanelTabla panelTabla;
    private PanelInferior panelInferior;

    public VentanaPrincipal() {
        setTitle("Gestión de Productos");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void inicializarComponentes() {
        panelBusqueda = new PanelBusqueda();
        add(panelBusqueda, BorderLayout.NORTH);

        panelLateral = new PanelLateral();
        add(panelLateral, BorderLayout.WEST);

        panelTabla = new PanelTabla();
        add(panelTabla, BorderLayout.CENTER);

        panelInferior = new PanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }

	public PanelBusqueda getPanelBusqueda() {
		return panelBusqueda;
	}

	public void setPanelBusqueda(PanelBusqueda panelBusqueda) {
		this.panelBusqueda = panelBusqueda;
	}

	public PanelLateral getPanelLateral() {
		return panelLateral;
	}

	public void setPanelLateral(PanelLateral panelSidebar) {
		this.panelLateral = panelSidebar;
	}

	public PanelTabla getPanelTabla() {
		return panelTabla;
	}

	public void setPanelTabla(PanelTabla panelTabla) {
		this.panelTabla = panelTabla;
	}

	public PanelInferior getPanelInferior() {
		return panelInferior;
	}

	public void setPanelInferior(PanelInferior panelDetalles) {
		this.panelInferior = panelDetalles;
	}
    
    
}
