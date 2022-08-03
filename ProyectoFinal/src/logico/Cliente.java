package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Persona implements Serializable{
	/**
	 * 
	 */
	//Atributos
	private ArrayList<Plan> misPlanes = new ArrayList<Plan>();
	private ArrayList<Factura> misFacturas = new ArrayList<Factura>();
	
	//Constructor
	public Cliente(String cedula, String nombre, String direccion, String telefono, String apellido, Date fechaNacimiento) {
		super(cedula, nombre, direccion, telefono, apellido, fechaNacimiento);
	}
	
	//Getters y Setters
	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	

}
