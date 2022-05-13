package entidades;

import java.util.Date;

public class Vw_tasacambio {
	private int idTasaCambio;
	private int idMonedaO;
	private String nombreO;
	private int idMonedaD;
	private String nombreD;
	private Date fecha;
	private float valor;
	private int usuarioCreacion;
	private Date fechaCreacion;
	private int usuarioModificacion;
	private Date fechaModificacion;
	private int usuarioEliminacion;
	private Date fechaEliminacion;
	private int estado;
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
	public String getNombreO() {
		return nombreO;
	}
	public void setNombreO(String nombreO) {
		this.nombreO = nombreO;
	}
	public int getIdMonedaD() {
		return idMonedaD;
	}
	public void setIdMonedaD(int idMonedaD) {
		this.idMonedaD = idMonedaD;
	}
	public String getNombreD() {
		return nombreD;
	}
	public void setNombreD(String nombreD) {
		this.nombreD = nombreD;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public int getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(int usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
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
	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}
	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
