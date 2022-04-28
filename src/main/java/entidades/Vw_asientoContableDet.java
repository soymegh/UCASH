package entidades;

import java.sql.Date;

public class Vw_asientoContableDet {
	private int idAsientoContableDet;
	private int idCuenta;
	private String nombreCuenta;
	private int idAsientoContable;
	private Date fecha;
	private String descripcion;
	private Double debe;
	private Double haber;
	//Metodos
	public int getIdAsientoContableDet() {
		return idAsientoContableDet;
	}
	public void setIdAsientoContableDet(int idAsientoContableDet) {
		this.idAsientoContableDet = idAsientoContableDet;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public int getIdAsientoContable() {
		return idAsientoContable;
	}
	public void setIdAsientoContable(int idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getDebe() {
		return debe;
	}
	public void setDebe(Double debe) {
		this.debe = debe;
	}
	public Double getHaber() {
		return haber;
	}
	public void setHaber(Double haber) {
		this.haber = haber;
	}
}
