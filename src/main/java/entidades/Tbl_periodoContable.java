package entidades;

import java.sql.Date;

public class Tbl_periodoContable {
	private int idPeriodoContable;
	private int idPeriodoFiscal;
	private Date fechaInicio;
	private Date fechaFinal;
	private int estado;
	public static Date fechaInicioActual;
	public static Date fechaFinalActual;
	public static int idPeriodoActual;
	
	
		
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
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}