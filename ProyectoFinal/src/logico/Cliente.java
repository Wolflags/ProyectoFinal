package logico;

import java.util.ArrayList;

public class Cliente extends Persona {
	//Atributos
	private ArrayList<Plan> misPlanes = new ArrayList<Plan>();
	private ArrayList<Factura> misFacturas = new ArrayList<Factura>();
	
	//Constructor
	public Cliente(String cedula, String nombre, String direccion, String telefono, String apellido) {
		super(cedula, nombre, direccion, telefono, apellido);
	}
	
	//Getters y Setters
	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	

}
