package entidades;
import java.util.Date;

public class Tbl_representanteLegal {
	
	private int idRepresentante;
	private int idTipoIdentifiacion;
	private String nombre;
	private String apellido;
	private String correo;
	private String telefono;
	private int estado;
	
	public int getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(int idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public int getIdTipoIdentifiacion() {
		return idTipoIdentifiacion;
	}
	public void setIdTipoIdentifiacion(int idTipoIdentifiacion) {
		this.idTipoIdentifiacion = idTipoIdentifiacion;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	

}
