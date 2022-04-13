package datos;

import entidades.Tbl_tipocuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dt_tipocuenta {
	
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTipocuenta = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_tipocuenta() {
			
	}
	
	public void llenaRsTipocuenta(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.tipocuenta;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsTipocuenta = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR TIPO DE CUENTA " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_tipocuenta> listaTipocuentaActivos(){
		ArrayList listTipocuenta = new ArrayList();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.tipocuenta;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_tipocuenta tipocuenta = new Tbl_tipocuenta();
				tipocuenta.setIdTipoCuenta(this.rs.getInt("idTipoCuenta"));
				tipocuenta.setTipoCuenta(this.rs.getString("tipoCuenta"));
				listTipocuenta.add(tipocuenta);
			}
			} catch(Exception var11) {
				System.out.println("DATOS: ERROR EN LISTAR TIPO DE CUENTA "+var11.getMessage());
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
		
		return listTipocuenta;
	}
}
