package entidades;

public class Tbl_cuentaContable {

	private int idCuentaContable;
	private String numeroCuenta;
	private String sC;
	private String ssC;
	private String sssC;
	private String nombreCuenta;
	private String rubro;
	private int idTipoCuenta;
	private int idCatalogoCuenta;
	private int estado;
	
	public Tbl_cuentaContable() {
	}

	public int getIdCuentaContable() {
		return idCuentaContable;
	}

	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
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

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public int getIdCatalogoCuenta() {
		return idCatalogoCuenta;
	}

	public void setIdCatalogoCuenta(int idCatalogoCuenta) {
		this.idCatalogoCuenta = idCatalogoCuenta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
