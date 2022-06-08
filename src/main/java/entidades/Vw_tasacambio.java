package entidades;

import java.util.Date;

public class Vw_tasacambio {
	private int idTasaCambio;
	private int idMonedaO;
	private String nombreO;
	private int idMonedaC;
	private String nombreC;
	private int mes;
	private int anio;
	private int estado;
	
	public int getIdTasaCambio() {
		return idTasaCambio;
	}
	public void setIdTasaCambio(int idTasaCambio) {
		this.idTasaCambio = idTasaCambio;
	}
	public int getIdMonedaO() {
		return idMonedaO;
	}
	public void setIdMonedaO(int idMonedaO) {
		this.idMonedaO = idMonedaO;
	}
	public String getNombreO() {
		return nombreO;
	}
	public void setNombreO(String nombreO) {
		this.nombreO = nombreO;
	}
	public int getIdMonedaC() {
		return idMonedaC;
	}
	public void setIdMonedaC(int idMonedaD) {
		this.idMonedaC = idMonedaD;
	}
	public String getNombreC() {
		return nombreC;
	}
	public void setNombreC(String nombreD) {
		this.nombreC = nombreD;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
