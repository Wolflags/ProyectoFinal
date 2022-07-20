package logico;

import java.util.Date;

public class Empleado extends Persona {
	//Atributos
	private String usuario;
	private String password;
	private float salario;
	private int cantHijos;
	private char estadoCivil;
	private int annosExperiencia;
	private String tipoEmpleado;
	private String puestoTrabajo;
	private boolean estado;

	//Constructor
	public Empleado(String cedula, String nombre, String direccion, String telefono, String usuario,
			String password, float salario, int cantHijos, char estadoCivil, int annosExperiencia,
			String tipoEmpleado, String puestoTrabajo, String apellido, Date fechaNacimiento) {
		super(cedula, nombre, direccion, telefono, apellido, fechaNacimiento);
		this.usuario = usuario;
		this.password = password;
		this.salario = salario;
		this.cantHijos = cantHijos;
		this.estadoCivil = estadoCivil;
		this.annosExperiencia = annosExperiencia;
		this.tipoEmpleado = tipoEmpleado;
		this.puestoTrabajo = puestoTrabajo;
		this.setEstado(true);
	}
	
	//Getters y Setters
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getCantHijos() {
		return cantHijos;
	}

	public void setCantHijos(int cantHijos) {
		this.cantHijos = cantHijos;
	}

	public char getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(char estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getAnnosExperiencia() {
		return annosExperiencia;
	}

	public void setAnnosExperiencia(int annosExperiencia) {
		this.annosExperiencia = annosExperiencia;
	}

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
