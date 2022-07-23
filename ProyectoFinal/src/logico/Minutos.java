package logico;

public class Minutos extends Servicio {
	//Atributos
	private int cantMins;
	private String tipo;
	
	//Constructor
	public Minutos(String codigo, String descripcion, int duracion, boolean autocobro, int cantMins, String tipo) {
		super(codigo, descripcion, duracion, autocobro);
		this.cantMins = cantMins;
		this.tipo = tipo;
	}
	
	//Getters y Getters
	public int getCantMins() {
		return cantMins;
	}
	public void setCantMins(int cantMins) {
		this.cantMins = cantMins;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
