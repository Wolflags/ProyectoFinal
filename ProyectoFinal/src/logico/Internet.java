package logico;

public class Internet extends Servicio {
	//Atributos
	private int velocidad;
	private int cantMB;
	private String tipo;
	
	//Constructor
	public Internet(String codigo, String descripcion, int duracion, boolean autocobro, int velocidad, int cantMB, String tipo) {
		super(codigo, descripcion, duracion, autocobro);
		this.velocidad = velocidad;
		this.cantMB = cantMB;
		this.tipo = tipo;
	}
	
	//Getters y Setters
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
