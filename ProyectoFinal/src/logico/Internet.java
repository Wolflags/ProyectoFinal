package logico;

import java.io.Serializable;

public class Internet extends Servicio implements Serializable{
	/**
	 * 
	 */
	//Atributos
	private int velocidad;
	private int cantMB;
	private String tipo;
	
	//Constructor
	public Internet(String codigo, String descripcion,  int velocidad, int cantMB, String tipo) {
		super(codigo, descripcion);
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

	@Override
	public float getPrecio() {
		float precio = 0;
		if(this.tipo=="Internet M�vil") {
		precio = (float) (precio+(0.07*this.cantMB)+(70*this.velocidad));
		}else {
		precio = (float) (precio+(0.03*this.cantMB)+(100*this.velocidad));
		}
		return precio;
	}
	
}
