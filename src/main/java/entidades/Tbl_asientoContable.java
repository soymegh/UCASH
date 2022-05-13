package entidades;

import java.sql.Date;

public class Tbl_asientoContable {
	private int idAsientoContable;
	private int idPeriodoContable;
	private int idEmpresa;
	private int idTipoDocumento;
	private int idMoneda;
	private int idTasaCambioDet;
	private Date fecha;
	private String descripcion;
	private int usuarioCreacion;
	private Date fechaCreacion;
	private int usuarioModificacion;
	private Date fechaModificacion;
	private int usuarioEliminacion;
	private Date fechaEliminacion;

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
	public int getIdTasaCambioDet() {
		return idTasaCambioDet;
	}
	public void setIdTasaCambioDet(int idTasaCambio) {
		this.idTasaCambioDet = idTasaCambio;
	}
	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	
	
}
