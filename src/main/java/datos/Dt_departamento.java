package datos;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Tbl_departamento;

public class Dt_departamento {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsDp = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_departamento() {
			
	}
	
	public void llenaRsDepartamento(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_departamento;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsDp = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR DEPARTAMENTO " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_departamento> listarDepartamento(){
		ArrayList<Tbl_departamento> listDp = new ArrayList<Tbl_departamento>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_departamento", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_departamento dp = new Tbl_departamento();
				dp.setIdDepartamento(rs.getInt("idDepartamento"));
				dp.setDepartamento(rs.getString("departamento"));
				listDp.add(dp);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR DEPARTAMENTO "+e.getMessage());
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
		
		return listDp;
	}

}

