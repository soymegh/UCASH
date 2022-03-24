package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_moneda;

public class Dt_moneda {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsMon = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_moneda() {
			
	}
	
	public void llenaRsMoneda(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_moneda;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsMon = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR MONEDA " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_moneda> listaMonedasActivas(){
		ArrayList<Tbl_moneda> listMoneda = new ArrayList<Tbl_moneda>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_moneda WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_moneda mon = new Tbl_moneda();
				mon.setIdMoneda(this.rs.getInt("idMoneda"));
				mon.setNombre(this.rs.getString("nombre"));
				mon.setSimbolo(this.rs.getString("simbolo"));
				mon.setEstado(this.rs.getInt("estado"));
				listMoneda.add(mon);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR MONEDA "+e.getMessage());
				e.printStackTrace();
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
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

		}
		
		return listMoneda;
	}
	
	public boolean addMoneda(Tbl_moneda Moneda){
		boolean guardado = false;
		try {
			c = poolConexion.getConnection();
			this.llenaRsMoneda(c);
			this.rsMon.moveToInsertRow();
			
			rsMon.updateString("nombre", Moneda.getNombre());
			rsMon.updateString("simbolo", Moneda.getSimbolo());
			rsMon.updateInt("estado", Moneda.getEstado());
			
			rsMon.insertRow();
			rsMon.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR OPCIONES: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsMon != null) {
					rsMon.close();
				}
				if(c != null) {
					poolConexion.closeConnection(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return guardado;
	}
}
