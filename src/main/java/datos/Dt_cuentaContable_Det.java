package datos;

import entidades.Vw_cuentacontable_cuentacontable_det;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dt_cuentaContable_Det {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCuentaContableDet = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_cuentaContable_Det() {
		
	}
	
	public void llenarRsCuentaContableDet(Connection c ) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.vw_cuentacontable_cuentacontable_det;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsCuentaContableDet = this.ps.executeQuery();
		}
		catch(Exception var3)
		{
			System.out.println("DATOS: ERROR EN LISTAR DETALLE DE CUENTAS CONTABLES " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Vw_cuentacontable_cuentacontable_det> listaCuentasContablesDet(){
		ArrayList<Vw_cuentacontable_cuentacontable_det> listCuentaContableDet = new ArrayList<Vw_cuentacontable_cuentacontable_det>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM sistemacontablebd.vw_cuentacontable_cuentacontable_det;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_cuentacontable_cuentacontable_det ccD = new Vw_cuentacontable_cuentacontable_det();
				ccD.setIdCuentaContableDet(this.rs.getInt("idCuentaContableDet"));
				ccD.setDebe(this.rs.getDouble("debe"));
				ccD.setHaber(this.rs.getDouble("haber"));
				ccD.setSaldoFinal(this.rs.getDouble("saldoFinal"));
				ccD.setSaldo(this.rs.getInt("saldo"));
				ccD.setNombreCuenta(this.rs.getString("nombreCuenta"));
				listCuentaContableDet.add(ccD);
			}
			} catch(Exception var11) {
				System.out.println("DATOS: ERROR EN LISTAR DETALLE DE CUENTAS CONTABLES "+var11.getMessage());
				var11.printStackTrace();
			}
		 finally {
	            try {
	                if (this.rs != null) {
	                    this.rs.close();
	                }

	                if (this.ps != null) {
	                    this.ps.close();
	                }

	                if (this.c != null) {
	                    poolConexion.closeConnection(this.c);
	                }
	            } catch (SQLException var10) {
	                var10.printStackTrace();
	            }

		}
		
		return listCuentaContableDet;
	}
}
