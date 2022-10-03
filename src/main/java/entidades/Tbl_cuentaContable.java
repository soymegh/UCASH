package entidades;

public class Tbl_cuentaContable {

	private int idCuenta;
	private String numeroCuenta;
	private String sC;
	private String ssC;
	private String sssC;
	private String nombreCuenta;
	private int nivel;
	private int rubro;
	private int idTipoCuenta;
	private int idSubCategoria; 
	private int idCatalogo;
	private int estado;
	
	
	
	
	
	public int getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public Tbl_cuentaContable() {
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getsC() {
		return sC;
	}

	public void setsC(String sC) {
		this.sC = sC;
	}

	public String getSsC() {
		return ssC;
	}

	public void setSsC(String ssC) {
		this.ssC = ssC;
	}

	public String getSssC() {
		return sssC;
	}

	public void setSssC(String sssC) {
		this.sssC = sssC;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public int getRubro() {
		return rubro;
	}

	public void setRubro(int rubro) {
		this.rubro = rubro;
	}

	public int getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}
	
}
