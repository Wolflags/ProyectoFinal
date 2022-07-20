package logico;

public class Minutos extends Servicio {
	private int cantMins;
	public Minutos(String nombre, String descripcion, int duracion, boolean autocobro, int cantMins) {
		super(nombre, descripcion, duracion, autocobro);
		this.cantMins = cantMins;
	}
	public int getCantMins() {
		return cantMins;
	}
	public void setCantMins(int cantMins) {
		this.cantMins = cantMins;
	}
	
}
