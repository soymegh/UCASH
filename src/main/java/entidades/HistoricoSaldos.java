package entidades;

import java.util.Date;

public class HistoricoSaldos {
	private int idHistorico; 
	private int idEmpresa; 
	private int idCatalogo; 
	private int idCuenta; 
	private int idTipoCuenta; 
	private int idSupTipo; 
	private String nombreSubTipo; 
	private String nombreEmpresa; 
	private String nombreCuenta; 
	private double saldoInicial; 
	private double saldoFinal; 
	private double debe; 
	private double haber; 
	private String fecha;
	
	
	public String getNombreSubTipo() {
		return nombreSubTipo;
	}
	public void setNombreSubTipo(String nombreSubTipo) {
		this.nombreSubTipo = nombreSubTipo;
	}
	public int getIdHistorico() {
		return idHistorico;
	}
	public void setIdHistorico(int idHistorico) {
		this.idHistorico = idHistorico;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}
	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}
	public int getIdSupTipo() {
		return idSupTipo;
	}
	public void setIdSupTipo(int idSupTipo) {
		this.idSupTipo = idSupTipo;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public double getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public double getSaldoFinal() {
		return saldoFinal;
	}
	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	} 
	
	
}
