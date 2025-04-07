package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelBusqueda extends JPanel {

    private JComboBox<String> buscarPorComboBox, filtroComboBox, tipoProductoFiltroComboBox;
    private JButton buscarButton;
    private JTextField buscarTextField, cantidadMinimaField, precioMinimoField, precioMaximoField;
    private JPanel panelFiltrosDinamico;

    public PanelBusqueda() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.LIGHT_GRAY);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        buscarPorComboBox = new JComboBox<>(new String[]{"Nombre"});
        buscarPorComboBox.setActionCommand("BUSCAR_POR");

        buscarTextField = new JTextField(20);
        buscarButton = new JButton("Buscar");
        buscarButton.setActionCommand("BUSCAR");

        add(new JLabel("Buscar por:"));
        add(buscarPorComboBox);
        add(buscarTextField);
        add(buscarButton);

        filtroComboBox = new JComboBox<>(new String[]{"Seleccionar", "Tipo", "Cantidad", "Precio"});
        filtroComboBox.setActionCommand("FILTRO_POR");

        add(new JLabel("Filtrar por:"));
        add(filtroComboBox);

        panelFiltrosDinamico = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelFiltrosDinamico.setOpaque(false);
        add(panelFiltrosDinamico);
    }

    public void mostrarFiltroPorTipo() {
        panelFiltrosDinamico.removeAll();
        tipoProductoFiltroComboBox = new JComboBox<>(new String[]{"Seleccionar", "Pan", "Galleta"});
        tipoProductoFiltroComboBox.setActionCommand("FILTRO_TIPO");
        panelFiltrosDinamico.add(tipoProductoFiltroComboBox);
        recargarPanel();
    }

    public void mostrarFiltroPorCantidad() {
        panelFiltrosDinamico.removeAll();
        panelFiltrosDinamico.add(new JLabel("Cantidad mínima:"));
        cantidadMinimaField = new JTextField(6);
        panelFiltrosDinamico.add(cantidadMinimaField);
        recargarPanel();
    }

    public void mostrarFiltroPorPrecio() {
        panelFiltrosDinamico.removeAll();
        panelFiltrosDinamico.add(new JLabel("Precio mínimo:"));
        precioMinimoField = new JTextField(6);
        panelFiltrosDinamico.add(precioMinimoField);

        panelFiltrosDinamico.add(new JLabel("Precio máximo:"));
        precioMaximoField = new JTextField(6);
        panelFiltrosDinamico.add(precioMaximoField);
        recargarPanel();
    }

    public void limpiarFiltroDinamico() {
        panelFiltrosDinamico.removeAll();
        recargarPanel();
    }

    private void recargarPanel() {
        panelFiltrosDinamico.revalidate();
        panelFiltrosDinamico.repaint();
    }

    public JComboBox<String> getFiltroComboBox() {
        return filtroComboBox;
    }

    public JComboBox<String> getTipoProductoFiltroComboBox() {
        return tipoProductoFiltroComboBox;
    }

    public JTextField getCantidadMinimaField() {
        return cantidadMinimaField;
    }

    public JTextField getPrecioMinimoField() {
        return precioMinimoField;
    }

    public JTextField getPrecioMaximoField() {
        return precioMaximoField;
    }

    public JComboBox<String> getBuscarPorComboBox() {
        return buscarPorComboBox;
    }

    public JTextField getBuscarTextField() {
        return buscarTextField;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }
}
