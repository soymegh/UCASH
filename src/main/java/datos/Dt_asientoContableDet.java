package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Vw_asientoContableDet;

public class Dt_asientoContableDet {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsasientocontableDet = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_asientoContableDet() {
		
	}
	
	public void llenaRsAsientoContableDet(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_asientocontabledet;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsasientocontableDet = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Periodo Fiscal " + e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<Vw_asientoContableDet> listarasientocontableDET(){
		ArrayList<Vw_asientoContableDet> listasientocontableDet = new ArrayList<Vw_asientoContableDet>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_asientocontabledet;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_asientoContableDet asientoContableDET = new Vw_asientoContableDet();
				asientoContableDET.setIdAsientoContableDet(rs.getInt("idAsientoContableDet"));
				asientoContableDET.setIdAsientoContableDet(rs.getInt("idAsientoContable"));
				asientoContableDET.setIdCuentaContable(rs.getInt("idCuentaContable"));
				asientoContableDET.setDebe(rs.getDouble("debe"));
				asientoContableDET.setHaber(rs.getDouble("haber"));
				asientoContableDET.setSaldo(rs.getDouble("saldo"));
				listasientocontableDet.add(asientoContableDET);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Detalle de Asiento Contable"+e.getMessage());
				e.printStackTrace();
			}
		 finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }

	                if (ps != null) {
	                    ps.close();
	                }

	                if (c != null) {
	                    poolConexion.closeConnection(c);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

		}
		
		return listasientocontableDet;
	}
}