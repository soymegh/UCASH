package entidades;

public class Tbl_cuentaContable_Det {
	
	private int idCuentaContableDet;
	private int idCuentaContable;
	private double saldoFinal;
	private double saldoInicial;
	private double debe;
	private double haber;
	
	
	
	
	public Tbl_cuentaContable_Det() {
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


	public double getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public int getIdCuentaContable() {
		return idCuentaContable;
	}


	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

}
