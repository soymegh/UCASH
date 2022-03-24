package entidades;

import java.sql.Date;

public class Vw_asientoContable {
	private int idAsientoContable;
	private String descripcion;
	private Double saldo;
	private int estado;
	private int idPeriodoContable;
	private int idEmpresa;
	private int idTasaCambio;
	private int idTipoDocumento;
	private Double debe;
	private Double haber;
	private Date fecha;
	
	private String periodoContable;
	private String NombreComercial;
	private Double tipoCambio;
	private String tipo;
	
	public int getIdAsientoContable() {
		return idAsientoContable;
	}
	public void setIdAsientoContable(int idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdPeriodoContable() {
		return idPeriodoContable;
	}
	public void setIdPeriodoContable(int idPeriodoContable) {
		this.idPeriodoContable = idPeriodoContable;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdTasaCambio() {
		return idTasaCambio;
	}
	public void setIdTasaCambio(int idTasaCambio) {
		this.idTasaCambio = idTasaCambio;
	}
	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getPeriodoContable() {
		return periodoContable;
	}
	public void setPeriodoContable(String periodoContable) {
		this.periodoContable = periodoContable;
	}
	public String getNombreComercial() {
		return NombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		NombreComercial = nombreComercial;
	}
	public Double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

}