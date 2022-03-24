package entidades;

public class Tbl_asientoContableDet {
	private int idAsientoContableDet;
	private int idAsientoContable;
	private int idCuentaContable;
	private double debe;
	private double haber;
	private double saldo;
	public int getIdAsientoContableDet() {
		return idAsientoContableDet;
	}
	public void setIdAsientoContableDet(int idAsientoContableDet) {
		this.idAsientoContableDet = idAsientoContableDet;
	}
	public int getIdAsientoContable() {
		return idAsientoContable;
	}
	public void setIdAsientoContable(int idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}
	public int getIdCuentaContable() {
		return idCuentaContable;
	}
	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
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
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
