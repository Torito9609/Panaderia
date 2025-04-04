package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelProductoDinamico extends JPanel {

    private CardLayout cardLayout;
    private JPanel panelCentro; 
    private JButton crear;
    private JButton cancelar;
    private JButton editar;

    public PanelProductoDinamico() {

        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        panelCentro = new JPanel(cardLayout);
        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        crear = new JButton("Crear");
        crear.setActionCommand("CREAR");
        cancelar = new JButton("Cancelar");
        cancelar.setActionCommand("CANCELAR");
        editar = new JButton("Editar");
        editar.setActionCommand("EDITAR");
        panelBotones.add(crear);
        panelBotones.add(cancelar);
        panelBotones.add(editar);
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


    public JButton getCrearButton() {
        return crear;
    }
    
    public JButton getEditarButton() {
        return editar;
    }

    public JButton getCancelarButton() {
        return cancelar;
    }
}
