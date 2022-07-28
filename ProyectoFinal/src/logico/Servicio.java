package logico;

public abstract class Servicio {
	
	protected String codigo;
	protected String descripcion;
	protected int duracion;
	protected boolean autocobro;
	public static int genIdServicio = 1;
	
	public Servicio(String codigo, String descripcion, int duracion, boolean autocobro) {
		super();
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.autocobro = autocobro;
		this.codigo = codigo;
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
	
	public abstract float getPrecio();
	
	
}
