package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private String codigo;
	private Date fecha;
	private float subtotal;
	private Persona empleado;
	private Persona cliente;
	private Plan plan;
	private boolean estado;
	public static int genIdFact = 1;
	
	public Factura(String codigo, Date fecha, float subtotal, Persona empleado, Persona cliente, Plan plan) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.empleado = empleado;
		this.cliente = cliente;
		this.plan = plan;
		this.estado = true;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public Persona getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Persona empleado) {
		this.empleado = empleado;
	}
	public Persona getCliente() {
		return cliente;
	}
	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}
	public String getCodigo() {
		return codigo;
	}
	public Plan getPlan() {
		return plan;
	}
	

}
