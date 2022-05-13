package entidades;

import java.sql.Date;

public class Tbl_tasaCambioDet {
	private int idTasaCambioDetalle;
	private int idTasaCambio;
	private Date fecha;
	private float tipoCambio;
	private int estado;
	
	//Metodos
	public int getIdTasaCambioDetalle() {
		return idTasaCambioDetalle;
	}
	public void setIdTasaCambioDetalle(int idTasaCambioDetalle) {
		this.idTasaCambioDetalle = idTasaCambioDetalle;
	}
	public int getIdTasaCambio() {
		return idTasaCambio;
	}
	public void setIdTasaCambio(int idTasaCambio) {
		this.idTasaCambio = idTasaCambio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(float tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
