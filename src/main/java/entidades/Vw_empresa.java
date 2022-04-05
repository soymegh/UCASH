package entidades;

public class Vw_empresa {
	private int idEmpresa;
	private String ruc;
	private String razonSocial;
	private String nombreComercial;
	private String telefono;
	private String correo;
	private String direccion;
	private int idRepresentanteLegal;
	private int idDepartamento;
	private int idMunicipio;
	
	private String periodoFiscal; 
	private String Representante;
	private String municipioNombre;
	private String departamentoNombre;
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getIdRepresentanteLegal() {
		return idRepresentanteLegal;
	}
	public void setIdRepresentanteLegal(int idRepresentanteLegal) {
		this.idRepresentanteLegal = idRepresentanteLegal;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	
	public String getRepresentante() {
		return Representante;
	}
	public void setRepresentante(String representante) {
		Representante = representante;
	}
	public String getMunicipioNombre() {
		return municipioNombre;
	}
	public void setMunicipioNombre(String municipioNombre) {
		this.municipioNombre = municipioNombre;
	}
	public String getDepartamentoNombre() {
		return departamentoNombre;
	}
	public void setDepartamentoNombre(String departamentoNombre) {
		this.departamentoNombre = departamentoNombre;
	}
	public String getPeriodoFiscal() {
		return periodoFiscal;
	}
	public void setPeriodoFiscal(String periodoFiscal) {
		this.periodoFiscal = periodoFiscal;
	}
	
	
}
