package entidades;

public class Vw_cuentacontable_cuentacontable_det {

	private int idCuentaContableDet;
	private double debe;
	private double haber;
	private double saldoFinal;
	private double saldoInicial;
	private String nombreCuenta;
	public int getIdCuentaContableDet() {
		return idCuentaContableDet;
	}
	public void setIdCuentaContableDet(int idCuentaContableDet) {
		this.idCuentaContableDet = idCuentaContableDet;
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
	public double getSaldoFinal() {
		return saldoFinal;
	}
	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	public double getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
	
	
}
