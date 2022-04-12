package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import entidades.Tbl_periodoContable;


public class Dt_periodoContable {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsperiodocontable = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_periodoContable() {
		
	}
	
	public void llenaRsPeriodoContable(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.periodocontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsperiodocontable = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Periodo Contable" + e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<Tbl_periodoContable> listarperiodoContable(){
		ArrayList<Tbl_periodoContable> listperiodoContable = new ArrayList<Tbl_periodoContable>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.periodocontable;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_periodoContable periodocontable = new Tbl_periodoContable();
				periodocontable.setIdPeriodoContable(rs.getInt("idPeriodoContable"));
				periodocontable.setIdPeriodoFiscal(rs.getInt("idPeriodoFiscal"));
				periodocontable.setFechaInicio(rs.getDate("fechaInicio"));
				periodocontable.setFechaFinal(rs.getDate("fechaFinal"));
				periodocontable.setEstado(rs.getInt("estado"));
				listperiodoContable.add(periodocontable);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Periodos Contables"+e.getMessage());
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
		
		return listperiodoContable;
	}
	
	public boolean agregarPeriodoContable(Tbl_periodoContable tpc) {
		boolean guardado = false;
		
		try {
			c = poolConexion.getConnection();
			this.llenaRsPeriodoContable(c);
			this.rsperiodocontable.moveToInsertRow();
			rsperiodocontable.updateInt("idPeriodoContable", tpc.getIdPeriodoContable());
			rsperiodocontable.updateInt("idPeriodoFiscal", tpc.getIdPeriodoFiscal());
			rsperiodocontable.updateDate("fechaInicio", tpc.getFechaInicio());
			rsperiodocontable.updateDate("fechaFinal", tpc.getFechaFinal());
			rsperiodocontable.updateInt("estado", tpc.getEstado());
			rsperiodocontable.insertRow();
			rsperiodocontable.moveToCurrentRow();
			guardado = true;
			
			
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR tbl_periodoContable "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if (rsperiodocontable != null) {
					rsperiodocontable.close();
				}
				if (c != null) {
					poolConexion.closeConnection(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return guardado;
	}
}