package entidades;

public class Vw_SubCuentaContable {
	private int idCuentaContableDet; 
	private int idCuenta; 
	private double saldoInicial;
	private double saldoFinal; 
	private double debe; 
	private double haber; 
	private String numeroCuenta; 
	private int idEmpresa; 
	private int subCuenta;
	
	public int getIdCuentaContableDet() {
		return idCuentaContableDet;
	}
	public void setIdCuentaContableDet(int idCuentaContableDet) {
		this.idCuentaContableDet = idCuentaContableDet;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public double getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public double getSaldoFinal() {
		return saldoFinal;
	}
	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	public double getDebe() {
		return debe;
	}
	public void setDebe(double debe) {
		this.debe = debe;
	}
	public double getHaber() {
		return haber;
	}
	public void setHaber(double haber) {
		this.haber = haber;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getSubCuenta() {
		return subCuenta;
	}
	public void setSubCuenta(int subCuenta) {
		this.subCuenta = subCuenta;
	} 
	
	
}
