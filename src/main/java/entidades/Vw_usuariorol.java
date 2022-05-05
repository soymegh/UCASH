package entidades;

public class Vw_usuariorol {

	private int id_user;
	private int idUsuarioRol;
	private String descripcion;
	private String nombre;
	private String apellido;
	private String usuario; 
	private String password;
	private String key;
	private String codVerificacion;
	private String email;
	private String password_temp;
	private int estado;
	private String rol;
	private int id_rol;

	
	
	
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getIdUsuarioRol() {
		return idUsuarioRol;
	}
	public void setIdUsuarioRol(int idUsuarioRol) {
		this.idUsuarioRol = idUsuarioRol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCodVerificacion() {
		return codVerificacion;
	}
	public void setCodVerificacion(String codVerificacion) {
		this.codVerificacion = codVerificacion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword_temp() {
		return password_temp;
	}
	public void setPassword_temp(String password_temp) {
		this.password_temp = password_temp;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	 
	
	
}
