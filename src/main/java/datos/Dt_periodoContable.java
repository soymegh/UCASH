package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Vw_periodoContable;

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
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.vw_periodocontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsperiodocontable = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Periodo Contable" + e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<Vw_periodoContable> listarperiodoContable(){
		ArrayList<Vw_periodoContable> listperiodoContable = new ArrayList<Vw_periodoContable>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM sistemacontablebd.vw_periodocontable;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_periodoContable periodocontable = new Vw_periodoContable();
				periodocontable.setIdPeriodoContable(rs.getInt("idPeriodoContable"));
				periodocontable.setFechaInicio(rs.getDate("fechaInicio"));
				periodocontable.setFechaFinal(rs.getDate("fechaFinal"));
				periodocontable.setProrroga(rs.getString("prorroga"));
				periodocontable.setTipoPeriodoContable(rs.getInt("tipoPeriodoContable"));
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
}