package entidades;
import java.util.Date;

public class Tbl_representanteLegal {
	
	private int idRepresentanteLegal;
	private int idTipoIdentificacion;
	private String nombre;
	private String apellido;
	private String correo;
	private String telefono;
	private String estado;
	
	public int getIdRepresentanteLegal() {
		return idRepresentanteLegal;
	}
	public void setIdRepresentanteLegal(int idRepresentanteLegal) {
		this.idRepresentanteLegal = idRepresentanteLegal;
	}
	public int getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(int idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
