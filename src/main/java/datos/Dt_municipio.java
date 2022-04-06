package datos;

import java.sql.Connection;




import entidades.Vw_municipio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Dt_municipio {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsMun = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_municipio() {
			
	}
	
	public void llenarRsMunicipio(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.vw_municipio;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsMun = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR MUNICIPIO " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Vw_municipio> listarMunicipio(){
		ArrayList<Vw_municipio> listarMunicipio = new ArrayList<Vw_municipio>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.vw_municipio ;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_municipio mun = new Vw_municipio();
				
				mun.setIdMunicipio(this.rs.getInt("idMunicipio"));
				mun.setMunicipio(this.rs.getString("municipio"));
				mun.setDepartamento(this.rs.getString("departamento"));
				 		
				
				listarMunicipio.add(mun);
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
		
		return listarMunicipio;
	}

}
