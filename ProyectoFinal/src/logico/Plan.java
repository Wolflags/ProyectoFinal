package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Plan implements Serializable, Comparable<Plan>{
	/**
	 * 
	 */
	private String idplan;
	private String nombre;
	private String numero;
	private ArrayList<Servicio> misServicios;
	private float precio;
	private boolean estado;
	
	public Plan(String idplan, String nombre,ArrayList<Servicio> misServicios, float precio) {
		super();
		this.idplan = idplan;
		this.nombre = nombre;
		this.numero = "0";
		this.misServicios = misServicios;
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
	
	public int cantServicios() {
		int cant = 0;
		for(Servicio servicio : misServicios) {
			if(servicio != null) {
				cant++;
			}
		}
		return cant;
	}
	
	@Override
	public int compareTo(Plan e) {
		if(e.getPrecio() < precio) {
			return -1;
		}
		else if(e.getPrecio() > precio){
			return 0;
		}
		else {
			return 1;
		}
	}
	public float cantidadDineroGenerado() {
		float cantidad = 0;
		
		for (Factura factura: Altice.getInstance().getFacturas()) {
			if(factura.getPlan().getIdplan().equalsIgnoreCase(getIdplan())) {
				if(factura.isEstado()) {
					cantidad+=factura.getPlan().getPrecio();
				}
			}
		}
		return cantidad;
	}
	public int cantidadServiciosPorPlan() {
		
			int cantidad = 0;
			for (Servicio servicio: getMisServicios()) {
				if (servicio != null) {
					cantidad++;
				}
			}
			
			return cantidad;
		
	}
	public float cantidadDineroDebioGenerar() {
		float cantidad = 0;
		
		for (Factura factura: Altice.getInstance().getFacturas()) {
			if(factura.getPlan().getIdplan().equalsIgnoreCase(getIdplan())) {
				
					cantidad+=factura.getPlan().getPrecio();
				
			}
		}
		return cantidad;
	}

}
