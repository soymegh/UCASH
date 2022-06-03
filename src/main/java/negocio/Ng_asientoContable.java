package negocio;

public class Ng_asientoContable {
	
	public static boolean verificarSaldo(float saldo) {
		boolean saldoCero = false;
		
		saldoCero = saldo == 0;
		
		return saldoCero;
	}

}
