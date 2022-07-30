package entidades;

public class Tbl_cuentaContable_Det {
	
	private int idCuentaContableDet;
	private int idCuenta;
	private double saldoFinal;
	private double saldoInicial;
	private double debe;
	private double haber;
	
	
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
	@Override
	public String toString() {
		return "Tbl_cuentaContable_Det [idCuentaContableDet=" + idCuentaContableDet + ", idCuenta=" + idCuenta
				+ ", saldoFinal=" + saldoFinal + ", saldoInicial=" + saldoInicial + ", debe=" + debe + ", haber="
				+ haber + "]";
	}
	
	
	
	

}
