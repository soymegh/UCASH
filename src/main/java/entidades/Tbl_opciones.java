package entidades;

public class Tbl_opciones {

	private int idOpciones;
	private String nombreOpcion;
	private String descripcion;
	private int estado;
	
	public Tbl_opciones() {
		
	}
	

	public String getNombreOpcion() {
		return nombreOpcion;
	}


	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
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
