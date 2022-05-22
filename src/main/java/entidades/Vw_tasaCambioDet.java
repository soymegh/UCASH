package entidades;

import java.sql.Date;

public class Vw_tasaCambioDet {
	
	private int idTasaCambioDet;
	private int idTasaCambio;
	private int idMonedaO;
	private String nombreO;
	private int idMonedaC;
	private String nombreC;
	private Date fecha;
	private double tipoCambio;
	
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
	
	

}
