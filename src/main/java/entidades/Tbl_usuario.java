package entidades;

import java.sql.Date;
import java.sql.Timestamp;

public class Tbl_usuario {

	private int idUsuario;
	private String usuario;
	private String nombre;
	private String apellidos;
	private String email;
	private String pwd;
	private String pwdTemp;
	private String urlFoto; 
	private String codVerificacion; 
	private int usuarioCreacion; 
	private Timestamp fechaCreacion; 
	private int usuarioModificacion; 
	private Timestamp fechaModificacion; 
	private int usuarioEliminacion; 
	private Timestamp fechaEliminacion; 
	private int estado;
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwdTemp() {
		return pwdTemp;
	}
	public void setPwdTemp(String pwdTemp) {
		this.pwdTemp = pwdTemp;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public String getCodVerificacion() {
		return codVerificacion;
	}
	public void setCodVerificacion(String codVerificacion) {
		this.codVerificacion = codVerificacion;
	}
	public int getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(int usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public int getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(int usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public int getUsuarioEliminacion() {
		return usuarioEliminacion;
	}
	public void setUsuarioEliminacion(int usuarioEliminacion) {
		this.usuarioEliminacion = usuarioEliminacion;
	}
	public Timestamp getFechaEliminacion() {
		return fechaEliminacion;
	}
	public void setFechaEliminacion(Timestamp fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
