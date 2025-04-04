package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import co.edu.unbosque.model.Panaderia;
import co.edu.unbosque.model.dto.ProductoDTO;
import co.edu.unbosque.view.Vista;

public class Controlador implements ActionListener{
	private Panaderia panaderia;
	private Vista vista;
	
	public Controlador() {
		panaderia = new Panaderia();
		vista = new Vista();
	}
	
	public void run() {
		
	}
	
	public void agregarProducto() {
		
	}
	
	public void editarProducto() {
		
	}
	
	public void eliminarProducto(String nombre) {
		
	}
	
	public void filtarPorNombre(String nombre){
		
	}
	
	public void filtrarPorCantidad(int cantidad){
		
	}
	
	public void filtrarPorPrecio(double limInferior, double limSuperior) {
		
	}
	
	public void guardarProducto() {
		
	}
	
	public void exportarCSV() {
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
