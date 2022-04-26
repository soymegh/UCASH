package datos;

import java.sql.Connection;

import entidades.Tbl_municipio;
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
			this.ps = c.prepareStatement("SELECT * FROM dbucash.municipio;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsMun = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR MUNICIPIO " + e.getMessage());
			e.printStackTrace();
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
	
	public boolean guardarMunicipio(Tbl_municipio Mun){
		boolean guardado = false;
		
				try {
						c = poolConexion.getConnection();
						this.llenarRsMunicipio(c);
						this.rsMun.moveToInsertRow();
						
						rsMun.updateInt("idDepartamento", Mun.getIdDepartamento());
						rsMun.updateString("municipio", Mun.getMunicipio());
						
						rsMun.insertRow();
						rsMun.moveToCurrentRow();
						guardado = true;
					
				} catch (Exception e) {
					System.err.println("ERROR AL GUARDAR Tbl_municipio "+ e.getMessage());
					e.printStackTrace();
				}
				finally {
					try {
						if(rsMun != null){
							rsMun.close();
						}
						if(c != null){
							poolConexion.closeConnection(c);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		return guardado;
	}
	
	public Tbl_municipio getMunbyID(int idMun) {
		Tbl_municipio tm = new Tbl_municipio();
		
		try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM dbucash.municipio WHERE idMunicipio=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idMun);
				rs = ps.executeQuery();
				
				if (rs.next()) {
					tm.setIdMunicipio(rs.getInt("idMunicipio"));
					tm.setIdDepartamento(rs.getInt("idDepartamento"));
					tm.setMunicipio(rs.getString("municipio"));
				}
		} catch (Exception e) {
			System.out.println("DATOS ERROR getMunbyID(): "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					poolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tm;
	}
	
	public boolean modificarMunicipio(Tbl_municipio tm) {
		boolean modificado = false;
		try {
			c = poolConexion.getConnection();
			this.llenarRsMunicipio(c);
			rsMun.beforeFirst();
			while (rsMun.next()) {
				if (rsMun.getInt(1)==tm.getIdMunicipio()) {
					rsMun.updateInt("idDepartamento", tm.getIdDepartamento());
					rsMun.updateString("municipio", tm.getMunicipio());
					rsMun.updateRow();
					modificado=true;
					break;
				}
				
			}
		} catch (Exception e) {
			System.err.println("ERROR AL modificarMunicipio() "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsMun != null){
					rsMun.close();
				}
				if(c != null){
					poolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return modificado;
	}
	
	public boolean eliminarMunicipio(Tbl_municipio tm)
	{
		boolean eliminado=false;	
		try
		{
			c = poolConexion.getConnection();
			this.llenarRsMunicipio(c);
			rsMun.beforeFirst();
			while (rsMun.next()){
				if(rsMun.getInt(1)==tm.getIdMunicipio()){
					rsMun.deleteRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e){
			System.err.println("ERROR AL eliminarMunicipio() "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsMun != null){
					rsMun.close();
				}
				if(c != null){
					poolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eliminado;
	}

}
