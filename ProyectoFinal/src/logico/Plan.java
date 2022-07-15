package logico;

import java.util.ArrayList;
import java.util.Date;

public class Plan {
	private String idplan;
	private ArrayList<Servicio> misServicios;
	private float precio;
	public Plan(String idplan, ArrayList<Servicio> misServicios, float precio) {
		super();
		this.idplan = idplan;
		this.misServicios = new ArrayList<Servicio>();
		this.precio = precio;
	}
	public String getIdplan() {
		return idplan;
	}
	public ArrayList<Servicio> getMisServicios() {
		return misServicios;
	}
	public float getPrecio() {
		return precio;
	}
	


}
