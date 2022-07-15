package logico;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private String codigo;
	private Date fecha;
	private float subtotal;
	private Persona empleado;
	private Persona cliente;
	private ArrayList<Plan> planes;
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
	public ArrayList<Plan> getPlanes() {
		return planes;
	}
	

}
