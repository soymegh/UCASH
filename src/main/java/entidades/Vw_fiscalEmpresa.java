package entidades;

import java.sql.Date;

public class Vw_fiscalEmpresa {
	
	private int idPeriodoFiscal;
	private int idEmpresa;
	private String nombreEmpresa;
	private Date fechaInicio;
	private Date fechaFinal;
	
	public int getIdPeriodoFiscal() {
		return idPeriodoFiscal;
	}
	
	public void setIdPeriodoFiscal(int idPeriodoFiscal) {
		this.idPeriodoFiscal = idPeriodoFiscal;
	}
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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
	
}
