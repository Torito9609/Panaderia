package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class PanelLateral extends JPanel {


    public PanelLateral() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  
        setBackground(Color.LIGHT_GRAY);

        inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        JLabel logoLabel = new JLabel("Logo de la empresa");

        

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
       

        add(logoLabel);

    }
    
    
}
