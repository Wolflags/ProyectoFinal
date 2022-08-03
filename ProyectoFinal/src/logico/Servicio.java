package logico;

import java.io.Serializable;

public abstract class Servicio implements Serializable{
	
	/**
	 * 
	 */
	protected String codigo;
	protected String descripcion;
	protected int duracion;
	protected boolean autocobro;
	public int genIdServicio;
	
	public Servicio(String codigo, String descripcion, int duracion, boolean autocobro) {
		super();
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.autocobro = autocobro;
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
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public boolean isAutocobro() {
		return autocobro;
	}
	public void setAutocobro(boolean autocobro) {
		this.autocobro = autocobro;
	}
	public int getGenIdServicio() {
		return genIdServicio;
	}
	
	public abstract float getPrecio();
	
	
}
