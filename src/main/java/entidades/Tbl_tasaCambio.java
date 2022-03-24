package entidades;

import java.util.Date;

public class Tbl_tasaCambio {

	private int idTasaCambio;
	private int idMonedaO;
	private int idMonedaD;
	private Date fecha;
	private double tipoCambio;
	private double valor;
	private int estado;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Date fechaEliminacion;
	private int usuarioCreacion;
	private int usuarioModificacion;
	private int usuarioEliminacion;
	
	public Tbl_tasaCambio() {
		
	}

	public int getIdTasaCambio() {
		return idTasaCambio;
	}

	public void setIdTasaCambio(int idTasaCambio) {
		this.idTasaCambio = idTasaCambio;
	}

	public int getIdMonedaO() {
		return idMonedaO;
	}

	public void setIdMonedaO(int idMonedaO) {
		this.idMonedaO = idMonedaO;
	}

	public int getIdMonedaD() {
		return idMonedaD;
	}

	public void setIdMonedaD(int idMonedaD) {
		this.idMonedaD = idMonedaD;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public int getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(int usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public int getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(int usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public int getUsuarioEliminacion() {
		return usuarioEliminacion;
	}

	public void setUsuarioEliminacion(int usuarioEliminacion) {
		this.usuarioEliminacion = usuarioEliminacion;
	}
}
