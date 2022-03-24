package entidades;

public class Tbl_opciones {

	private int idOpciones;
	private String descripcion;
	private int estado;
	
	public Tbl_opciones() {
		
	}

	public int getIdOpciones() {
		return idOpciones;
	}

	public void setIdOpciones(int idOpciones) {
		this.idOpciones = idOpciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
