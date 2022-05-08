package entidades;

import java.sql.Date;

public class Vw_asientoContableDet {
	private int idAsientoContableDet;
	private int idCuenta;
	private int numeroCuenta;
	private int SC;
	private int SsC;
	private int SssC;
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
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
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
	public int getSC() {
		return SC;
	}
	public void setSC(int sC) {
		SC = sC;
	}
	public int getSsC() {
		return SsC;
	}
	public void setSsC(int ssC) {
		SsC = ssC;
	}
	public int getSssC() {
		return SssC;
	}
	public void setSssC(int sssC) {
		SssC = sssC;
	}
}
