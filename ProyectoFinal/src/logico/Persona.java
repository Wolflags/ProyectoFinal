package logico;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable{
	/**
	 * 
	 */
	//Atributos
	protected String cedula;
	protected String nombre;
	protected String apellido;
	protected String direccion;
	protected String telefono;
	protected Date fechaNacimiento;
	
	//Constructor
	public Persona(String cedula, String nombre, String direccion, String telefono, String apellido, Date fechaNacimiento) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
}
