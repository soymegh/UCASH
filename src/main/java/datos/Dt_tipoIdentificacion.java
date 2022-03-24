package datos;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Tbl_tipoIdentificacion;

public class Dt_tipoIdentificacion {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTI = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_tipoIdentificacion() {
			
	}
	
	public void llenaRsTipoIdentificacion(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_tipoIdentificacion;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsTI = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR TIPOIDENTIFICACION " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_tipoIdentificacion> listarDepartamento(){
		ArrayList<Tbl_tipoIdentificacion> listTI = new ArrayList<Tbl_tipoIdentificacion>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_tipoIdentificacion WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_tipoIdentificacion TI = new Tbl_tipoIdentificacion();
				
				TI.setIdTipoIdentificacion(rs.getInt("idTipoIdentificacion"));
				TI.setTipo(rs.getString("tipo"));
				TI.setEstado(rs.getInt("Estado"));
				
				listTI.add(TI);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR TIPOIDENTIFICACION "+e.getMessage());
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
		
		return listTI;
	}

}
