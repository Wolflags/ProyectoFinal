package logico;

import java.io.Serializable;

public abstract class Servicio implements Serializable{
	
	/**
	 * 
	 */
	protected String codigo;
	protected String descripcion;
	public int genIdServicio;
	
	public Servicio(String codigo, String descripcion) {
		super();
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.genIdServicio = 1;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getGenIdServicio() {
		return genIdServicio;
	}
	
	public abstract float getPrecio();
	
	
}
