package logico;

import java.util.ArrayList;
import java.util.Date;

public class Plan {
	private String idplan;
	private String nombre;
	private String numero;
	private ArrayList<Servicio> misServicios;
	private float precio;
	private boolean estado;
	public static int genIdPlan = 1;
	
	public Plan(String idplan, ArrayList<Servicio> misServicios, float precio) {
		super();
		this.idplan = idplan;
		this.misServicios = new ArrayList<Servicio>();
		this.precio = precio;
		this.estado=true;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getIdplan() {
		return idplan;
	}
	public ArrayList<Servicio> getMisServicios() {
		return misServicios;
	}
	public float getPrecio() {
		return precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	


}
