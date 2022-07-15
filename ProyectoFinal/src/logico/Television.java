package logico;

public class Television extends Servicio {
	private int cantCanales;
	private String tipo;
	public Television(String nombre, String descripcion, int duracion, int cantCanales, String tipo) {
		super(nombre, descripcion, duracion);
		this.cantCanales = cantCanales;
		this.tipo = tipo;
	}
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
