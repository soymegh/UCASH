package entidades;

import java.util.Date;

public class Historico {
	private int idAsientoContableDet;
	private String nombreCuenta;
	private String descripcion;
	private double debe;
	private double haber;
	private Date fecha; 
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdAsientoContableDet() {
		return idAsientoContableDet;
	}
	public void setIdAsientoContableDet(int idAsientoContableDet) {
		this.idAsientoContableDet = idAsientoContableDet;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getDebe() {
		return debe;
	}
	public void setDebe(double debe) {
		this.debe = debe;
	}
	public double getHaber() {
		return haber;
	}
	public void setHaber(double haber) {
		this.haber = haber;
	}
	
	
}
