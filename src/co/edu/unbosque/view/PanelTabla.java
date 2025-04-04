package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.dto.ProductoDTO;

public class PanelTabla extends JPanel {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public PanelTabla() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inicializarComponentes();
    }

    public void inicializarComponentes() {
        String[] columnas = {"Nombre", "Tipo", "$ Venta", "$ Produccion", "Cantidad", "Queso", "Chispas"};
        
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void actualizarTabla(List<ProductoDTO> productos) {
        modeloTabla.setRowCount(0);

        for (ProductoDTO producto : productos) {
            Object[] fila = {
                producto.getNombre(),
                producto.getTipo(),
                producto.getPrecioVenta(),
                producto.getCostoProduccion(),
                producto.getCantidad(),
                producto.isConQueso() ? "✅" : "",
                producto.isConChispas() ? "✅" : ""
            };
            modeloTabla.addRow(fila);
        }
    }

    public JTable getTablaProductos() {
        return tablaProductos;
    }
}
