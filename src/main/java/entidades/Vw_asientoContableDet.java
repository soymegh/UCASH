package entidades;

import java.sql.Date;

public class Vw_asientoContableDet {
	private int idAsientoContableDet;
	private int idCuenta;
	private String numeroCuenta;
	private String SC;
	private String SsC;
	private String SssC;
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
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	public String getSC() {
		return SC;
	}
	public void setSC(String sC) {
		SC = sC;
	}
	public String getSsC() {
		return SsC;
	}
	public void setSsC(String ssC) {
		SsC = ssC;
	}
	public String getSssC() {
		return SssC;
	}
	public void setSssC(String sssC) {
		SssC = sssC;
	}
}
