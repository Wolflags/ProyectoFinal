package logico;

public class Servicio {
	protected String nombre;
	protected String descripcion;
	protected int duracion;
	protected boolean autocobro;
	
	public Servicio(String nombre, String descripcion, int duracion, boolean autocobro) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.autocobro = autocobro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
