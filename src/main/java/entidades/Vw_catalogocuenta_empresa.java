package entidades;

import java.sql.Date;

public class Vw_catalogocuenta_empresa {
	private int idCatalogoCuenta;
	private int idEmpresa;
	private String nombreComercial;
	private String titulo;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Date fechaEliminacion;
	private int usuarioCreacion;
	private int usuarioModificacion;
	private int usuarioEliminacion;
	
	
	public int getIdCatalogoCuenta() {
		return idCatalogoCuenta;
	}
	public void setIdCatalogoCuenta(int idCatalogoCuenta) {
		this.idCatalogoCuenta = idCatalogoCuenta;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getnombreComercial() {
		return nombreComercial;
	}
	public void setnombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
