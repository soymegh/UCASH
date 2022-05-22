package entidades;

import java.util.Date;

public class Tbl_tasaCambio {

	private int id_tasaCambio;
	private int id_monedaO;
	private int id_monedaC;
	private int mes;
	private int anio;
	private int estado;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Date fechaEliminacion;
	private int usuarioCreacion;
	private int usuarioModificacion;
	private int usuarioEliminacion;
	
	
	
	public int getId_tasaCambio() {
		return id_tasaCambio;
	}
	public void setId_tasaCambio(int id_tasaCambio) {
		this.id_tasaCambio = id_tasaCambio;
	}
	public int getId_monedaO() {
		return id_monedaO;
	}
	public void setId_monedaO(int id_monedaO) {
		this.id_monedaO = id_monedaO;
	}
	public int getId_monedaC() {
		return id_monedaC;
	}
	public void setId_monedaC(int id_monedaC) {
		this.id_monedaC = id_monedaC;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
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
