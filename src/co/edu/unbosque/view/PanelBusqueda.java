package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelBusqueda extends JPanel {

    private JComboBox<String> buscarPorComboBox, tipProductoComboBox;
    private JButton buscarButton;
    private JTextField buscarTextField;
    private JLabel buscarPor, filtrar;
    
    public PanelBusqueda() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));  
        setBackground(Color.LIGHT_GRAY);
        
        inicializarComponentes();
    }
    
    public void inicializarComponentes() {
    	buscarPorComboBox = new JComboBox<>(new String[]{"Seleccionar", "Nombre"});
    	buscarPorComboBox.setActionCommand("BUSCAR_POR");
        buscarTextField = new JTextField(20);
        buscarButton = new JButton("Buscar");
        buscarButton.setActionCommand("BUSCAR");
        tipProductoComboBox = new JComboBox<>(new String[]{"Seleccionar", "Pan", "Galleta"});
        tipProductoComboBox.setActionCommand("TIPO");
        buscarPor = new JLabel("Buscar por:");
        filtrar = new JLabel("Filtrar por:");
        
        add(buscarPor);
        add(buscarPorComboBox);
        add(buscarTextField);
        add(buscarButton);
        add(filtrar);
        add(tipProductoComboBox);
    }
    
    public void limpiar() {
    	
    }

	public JComboBox<String> getBuscarPorComboBox() {
		return buscarPorComboBox;
	}

	public void setBuscarPorComboBox(JComboBox<String> buscarPorComboBox) {
		this.buscarPorComboBox = buscarPorComboBox;
	}

	public JComboBox<String> getTipoEmpleadoComboBox() {
		return tipProductoComboBox;
	}

	public void setTipoEmpleadoComboBox(JComboBox<String> tipoEmpleadoComboBox) {
		this.tipProductoComboBox = tipoEmpleadoComboBox;
	}

	public JButton getBuscarButton() {
		return buscarButton;
	}

	public void setBuscarButton(JButton buscarButton) {
		this.buscarButton = buscarButton;
	}

	public JTextField getBuscarTextField() {
		return buscarTextField;
	}

	public void setBuscarTextField(JTextField buscarTextField) {
		this.buscarTextField = buscarTextField;
	}

	public JLabel getBuscarPor() {
		return buscarPor;
	}

	public void setBuscarPor(JLabel buscarPor) {
		this.buscarPor = buscarPor;
	}

	public JLabel getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(JLabel filtrar) {
		this.filtrar = filtrar;
	}
    
}
