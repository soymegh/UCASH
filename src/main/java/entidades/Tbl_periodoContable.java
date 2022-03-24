package entidades;

import java.util.Date;

public class Tbl_periodoContable {
	private int idPeriodoContable;
	private Date fechaInicio;
	private Date fechaFinal;
	private String prorroga;
	private int tipoPeriodoContable;
	private int estado;
	
	public int getIdPeriodoContable() {
		return idPeriodoContable;
	}
	public void setIdPeriodoContable(int idPeriodoContable) {
		this.idPeriodoContable = idPeriodoContable;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getProrroga() {
		return prorroga;
	}
	public void setProrroga(String prorroga) {
		this.prorroga = prorroga;
	}
	public int getTipoPeriodoContable() {
		return tipoPeriodoContable;
	}
	public void setTipoPeriodoContable(int tipoPeriodoContable) {
		this.tipoPeriodoContable = tipoPeriodoContable;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
