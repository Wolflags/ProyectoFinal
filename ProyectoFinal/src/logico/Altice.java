package logico;

import java.util.ArrayList;
import java.util.Date;

public class Altice {
	//Atributos
	private ArrayList<Persona> personas;
	private ArrayList<Plan> planes;
	private ArrayList<Servicio> servicios;
	private ArrayList<Factura> facturas;
	private static Altice alti = null;
	
	//Constructor
	private Altice() {
		super();
		personas = new ArrayList<Persona>();
		planes = new ArrayList<Plan>();
		setServicios(new ArrayList<Servicio>());
		facturas = new ArrayList<Factura>();
		Date nacimiento = new Date();
		Empleado mainAdmin = new Empleado("admin", "Patrick", "Woerden", "123-456-7890", "1234", 
				(float)60000.0, 2, "Casado", 10, "Administrador", "Oficina 01", "Drahi", nacimiento);
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
	
	public Servicio buscarServicioByCod(String codServ) {
		Servicio auxServ = null;
		int i = 0;
		boolean encontrado = false;
		while(i < servicios.size() && !encontrado) {
			
				if(servicios.get(i).getCodigo().equalsIgnoreCase(codServ)) {
					auxServ = servicios.get(i);
					encontrado = true;
				}
			
			i++;
		}
		return auxServ;
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
	
	public void insertarPersona(Persona persona) {
		personas.add(persona);
	}
	
	public void cancelarEmpleado(String cedula) {
		Empleado aux = buscarEmpleadoByCedula(cedula);
		if(aux != null) {
			aux.setEstado(false);
		}
	}

	public boolean buscarDireccionExistente(String direccion) {
		boolean busqueda = false;
		int i = 0;
		while (i < personas.size() && !busqueda) {
			if (personas.get(i).getDireccion().equalsIgnoreCase(direccion))
				busqueda = true;
			i++;
		}
		return busqueda;
	}

	public boolean buscarTelefonoExistente(String telefono) {
		boolean busqueda = false;
		int i = 0;
		while (i < personas.size() && !busqueda) {
			if (personas.get(i).getTelefono().equalsIgnoreCase(telefono))
				busqueda = true;
			i++;
		}
		return busqueda;

	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}

	public ArrayList<Persona> buscarTodosEmpleadoByNombre(String nombre) {
		ArrayList<Persona> empleados = new ArrayList<Persona>();
		String nombreCompleto = "";
		for (Persona empleado : personas) {
			if (empleado instanceof Empleado ) {
				nombreCompleto = empleado.getNombre()+" "+empleado.getApellido();
				if(nombreCompleto.substring(0, nombre.length()).equalsIgnoreCase(nombre)) {
					empleados.add(empleado);
				}
			}
		}
		return empleados;
	}

	public Plan buscarPlanByCod(String codPlan) {
		Plan auxPlan = null;
		int i = 0;
		boolean encontrado = false;
		while(i < planes.size() && !encontrado) {
			
				if(planes.get(i).getIdplan().equalsIgnoreCase(codPlan)) {
					auxPlan = planes.get(i);
					encontrado = true;
				}
			
			i++;
		}
		return auxPlan;
	}

	public boolean buscarTelefonoExisteEnPlanes(String num) {
		boolean hacer = false;
		for (Persona cliente: personas) {
			if(cliente instanceof Cliente) {
				for (Plan plan : ((Cliente) cliente).getMisPlanes()) {
					if(plan.getNumero().equals(num)) {
						hacer=true;
					}
				}
			}
		}
		return hacer;
	}
	
	public Factura buscarFacturaByCodigo(String codigo) {
		Factura aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < facturas.size() && !encontrado) {
			if(facturas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				aux = facturas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
}












