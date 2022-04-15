package entidades;

import java.sql.Date;

public class Vw_periodoContable {
	//Atributos 
	private int idPeriodoContable;
	private int idPeriodoFiscal;
	private Date fechaInicioPF;
	private Date fechaFinalPF;
	private Date fechaInicio; 
	private Date fechaFinal;
	private int estado;
	
	//Metodos
	public int getIdPeriodoContable() {
		return idPeriodoContable;
	}
	public void setIdPeriodoContable(int idPeriodoContable) {
		this.idPeriodoContable = idPeriodoContable;
	}
	public int getIdPeriodoFiscal() {
		return idPeriodoFiscal;
	}
	public void setIdPeriodoFiscal(int idPeriodoFiscal) {
		this.idPeriodoFiscal = idPeriodoFiscal;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechaInicioPF() {
		return fechaInicioPF;
	}
	public void setFechaInicioPF(Date fechaInicioPF) {
		this.fechaInicioPF = fechaInicioPF;
	}
	public Date getFechaFinalPF() {
		return fechaFinalPF;
	}
	public void setFechaFinalPF(Date fechaFinalPF) {
		this.fechaFinalPF = fechaFinalPF;
	}

}