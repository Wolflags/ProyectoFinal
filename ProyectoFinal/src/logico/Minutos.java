package logico;

import java.io.Serializable;

public class Minutos extends Servicio implements Serializable{
	/**
	 * 
	 */
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

	@Override
	public float getPrecio() {
		float precio = 0;
		if(this.tipo=="Minutos Móvil") {
			precio = (float) (precio+(4.75*this.cantMins));
		}else {
			precio = (float) (precio+(3.75*this.cantMins));
		}
		return precio;
	}
	
}
