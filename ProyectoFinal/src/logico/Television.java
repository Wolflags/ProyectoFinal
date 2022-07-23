package logico;

public class Television extends Servicio {
	//Atributos
	private int cantCanales;
	private String tipo;
	
	//Constructor
	public Television(String codigo, String descripcion, int duracion, boolean autocobro, int cantCanales, String tipo) {
		super(codigo, descripcion, duracion, autocobro);
		this.cantCanales = cantCanales;
		this.tipo = tipo;
	}
	
	//Getters y Setters
	public int getCantCanales() {
		return cantCanales;
	}
	public void setCantCanales(int cantCanales) {
		this.cantCanales = cantCanales;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
