package logico;

public class Internet extends Servicio {
	private int velocidad;
	private int cantMB;
	private String tipo;
	public Internet(String nombre, String descripcion, int duracion, boolean autocobro, int velocidad, int cantMB, String tipo) {
		super(nombre, descripcion, duracion, autocobro);
		this.velocidad = velocidad;
		this.cantMB = cantMB;
		this.tipo = tipo;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getCantMB() {
		return cantMB;
	}
	public void setCantMB(int cantMB) {
		this.cantMB = cantMB;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
