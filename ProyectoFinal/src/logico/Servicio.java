package logico;

public abstract class Servicio {
	
	protected String descripcion;
	protected int duracion;
	protected boolean autocobro;
	
	public Servicio(String descripcion, int duracion, boolean autocobro) {
		super();
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.autocobro = autocobro;
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
	
	
}
