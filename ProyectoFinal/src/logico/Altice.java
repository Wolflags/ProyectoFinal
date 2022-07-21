package logico;

import java.util.ArrayList;
import java.util.Date;

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
		Date nacimiento = new Date(63, 8, 20);
		Empleado mainAdmin = new Empleado("admin", "Patrick", "Woerden", "123-456-7890", "1234", 
				(float)60000.0, 2, 'C', 10, "Admin", "Oficina 01", "Drahi", nacimiento);
		personas.add(mainAdmin);
		
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
	
	public Empleado buscarEmpleadoByCedula(String cedula) {
		Empleado aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < personas.size() && !encontrado) {
			if(personas.get(i) instanceof Empleado) {
				if(personas.get(i).getCedula().equalsIgnoreCase(cedula)) {
					aux = (Empleado) personas.get(i);
					encontrado = true;
				}
			}
			i++;
		}
		return aux;
	}
	
	public boolean validarClave(String usuario, String password) {
		boolean validar = false;
		int i = 0;
		while(i < personas.size() && !validar) {
			if(personas.get(i) instanceof Empleado) {
				if(personas.get(i).getCedula().equalsIgnoreCase(usuario) 
						&& ((Empleado)personas.get(i)).getPassword().equals(password)) {
					validar = true;
				}
			}
			i++;
		}
		return validar;
	}
	

}
