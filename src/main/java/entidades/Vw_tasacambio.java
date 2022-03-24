package entidades;

import java.util.Date;

public class Vw_tasacambio {

	private int idTasaCambio;
	private String origen;
	private String destino;
	private Date fecha;
	private double tipoCambio;
	private double valor;
	private int estado;
	
	public Vw_tasacambio() {
		
	}

	public int getIdTasaCambio() {
		return idTasaCambio;
	}

	public void setIdTasaCambio(int idTasaCambio) {
		this.idTasaCambio = idTasaCambio;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
