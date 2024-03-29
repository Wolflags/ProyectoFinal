package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Altice implements Serializable{
	/**
	 * 
	 */
	//Atributos
	private  ArrayList<Persona> personas;
	private  ArrayList<Plan> planes;
	private  ArrayList<Servicio> servicios;
	private  ArrayList<Factura> facturas;
	private  static Altice alti = null;
	private int genIdServicio;
	private int genIdFactura;
	private int genIdPlan;
	
	//Constructor
	private Altice() {
		super();
		personas = new ArrayList<Persona>();
		planes = new ArrayList<Plan>();
		setServicios(new ArrayList<Servicio>());
		facturas = new ArrayList<Factura>();
		Date nacimiento = new Date();
		this.genIdServicio = 1;
		this.genIdFactura = 1;
		this.genIdPlan = 1;
		/*Empleado mainAdmin = new Empleado("admin", "Patrick", "Woerden", "123-456-7890", "1234", 
				(float)60000.0, 2, "Casado", 10, "Administrador", "Oficina 01", "Drahi", nacimiento);
		personas.add(mainAdmin);*/
		
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
	public int getGenIdServicio() {
		return genIdServicio;
	}
	public int getGenIdFactura() {
		return genIdFactura;
	}
	public int getGenIdPlan() {
		return genIdPlan;
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
	
	public Servicio buscarServicioByCodigo(String codigo) {
		Servicio aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < servicios.size() && !encontrado) {
				if(servicios.get(i).getCodigo().equalsIgnoreCase(codigo)) {
					aux = servicios.get(i);
					encontrado = true;
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
	
	public void insertarPersona(Persona persona) {
		personas.add(persona);
	}
	
	public void insertarServicio(Servicio servicio) {
		servicios.add(servicio);
		genIdServicio++;
	}
	
	public void insertarFactura(Factura factura) {
		facturas.add(factura);
		genIdFactura++;
	}
	
	public void insertarPlan(Plan plan) {
		planes.add(plan);
		genIdPlan++;
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
			if (empleado.getCedula().equalsIgnoreCase("admin")) {
				continue;
			}
			if (empleado instanceof Empleado ) {
				nombreCompleto = empleado.getNombre()+" "+empleado.getApellido();
				if(nombreCompleto.substring(0, nombre.length()).equalsIgnoreCase(nombre)) {
					empleados.add(empleado);
				}
			}
		}
		return empleados;
	}
	
	public ArrayList<Cliente> buscarTodosClientesByNombre(String nombre) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String nombreCompleto = "";
		for (Persona cliente : personas) {
			if (cliente.getCedula().equalsIgnoreCase("admin")) {
				continue;
			}
			if (cliente instanceof Cliente) {
				nombreCompleto = cliente.getNombre()+" "+cliente.getApellido();
				if(nombreCompleto.substring(0, nombre.length()).equalsIgnoreCase(nombre)) {
					clientes.add((Cliente)cliente);
				}
			}
		}
		return clientes;
	}

	public ArrayList<Plan> buscarTodosPlanesByNombre(String nombre){
		ArrayList<Plan> planesBuscados = new ArrayList<Plan>();
		for (Plan plan : planes) {
			if(plan.getNombre().substring(0, nombre.length()).equalsIgnoreCase(nombre)) {
				planesBuscados.add(plan);
			}
		}
		return planesBuscados;
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

	public static void setInstance(Altice altice) {
		Altice.alti=altice;
		
	}
	
	public Plan buscarPlanByCodigo(String codigo) {
		Plan aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < planes.size() && !encontrado) {
			if(planes.get(i).getIdplan().equalsIgnoreCase(codigo)) {
				aux = planes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public static float formatearDecimales(Float numero, Integer numeroDecimales) {
	    return (float) (Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales));
	}
	
	public float dineroClientePendiente(String cedula) {
		float dineroPendiente = 0;
		Cliente aux = buscarClienteByCedula(cedula);
		if(aux != null) {
			for(Factura factura : aux.getMisFacturas()) {
				if(!factura.isEstado()) {
					dineroPendiente += factura.getSubtotal();
				}
			}
		}
		return dineroPendiente;
	}
	
}













