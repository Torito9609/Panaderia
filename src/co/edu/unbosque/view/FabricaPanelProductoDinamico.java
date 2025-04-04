package co.edu.unbosque.view;

import javax.swing.*;

public class FabricaPanelProductoDinamico {
    
	public static JPanel crearPanel(String tipoProducto) {
	    if (tipoProducto == null) return new JPanel();

	    switch (tipoProducto.trim().toLowerCase()) {
	        case "pan":
	            return new PanelPan();
	        case "galleta":
	            return new PanelGalleta();
	        default:
	            return new JPanel();
	    }
	}

}
