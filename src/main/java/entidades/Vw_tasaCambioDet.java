package entidades;

import java.sql.Date;

public class Vw_tasaCambioDet {
	
	private int idTasaCambioDet;
	private int idTasaCambio;
	private String mes;
	private int idMonedaO;
	private String nombreO;
	private int idMonedaC;
	private String nombreC;
	private int anio;
	private Date fecha;
	private double tipoCambio;
	private int estado;
	
	public int getIdTasaCambioDet() {
		return idTasaCambioDet;
	}
	public void setIdTasaCambioDet(int idTasaCambioDet) {
		this.idTasaCambioDet = idTasaCambioDet;
	}
	public int getIdTasaCambio() {
		return idTasaCambio;
	}
	public void setIdTasaCambio(int idTasaCambio) {
		this.idTasaCambio = idTasaCambio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
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
	public void setIdMonedaC(int idMonedaC) {
		this.idMonedaC = idMonedaC;
	}
	public String getNombreC() {
		return nombreC;
	}
	public void setNombreC(String nombreC) {
		this.nombreC = nombreC;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
