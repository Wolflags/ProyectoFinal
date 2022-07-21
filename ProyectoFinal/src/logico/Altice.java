package logico;

import java.util.ArrayList;

public class Altice {
	//Atributos
	private ArrayList<Persona> personas;
	private ArrayList<Plan> planes;
	private ArrayList<Factura> facturas;
	private static Altice alti = null;
	
	//Constructor
	private Altice() {
		super();
		personas = new ArrayList<Persona>();
		planes = new ArrayList<Plan>();
		facturas = new ArrayList<Factura>();
		
	}
	
	//GetInstance
	public static Altice getInstance() {
		if(alti == null) {
			alti = new Altice();
		}
		return alti;
	}
	
	//Getters y Setters
	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	public ArrayList<Plan> getPlanes() {
		return planes;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	
	//Metodos
	public Cliente buscarClienteByCedula(String cedula) {
		Cliente aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < personas.size() && !encontrado) {
			if(personas.get(i) instanceof Cliente) {
				if(personas.get(i).getCedula().equalsIgnoreCase(cedula)) {
					aux = (Cliente) personas.get(i);
					encontrado = true;
				}
			}
			i++;
		}
		return aux;
	}
	

}
