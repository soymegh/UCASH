package entidades;

public class Vw_cuentacontable_cuentacontable_det {

	private int idCuentaContableDet;
	private double debe;
	private double haber;
	private double saldoFinal;
	private int saldo;
	private String nombreCuenta;
	
	
	public Vw_cuentacontable_cuentacontable_det() {
	}


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


	public int getSaldo() {
		return saldo;
	}


	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}


	public String getNombreCuenta() {
		return nombreCuenta;
	}


	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}


}
