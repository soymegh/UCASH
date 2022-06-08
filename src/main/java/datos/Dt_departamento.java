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
	private ResultSet rsDep = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_departamento() {
			
	}
	
	public void llenaRsDepartamento(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.departamento;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsDep = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR DEPARTAMENTO " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_departamento> listarDepartamento(){
		ArrayList<Tbl_departamento> listDp = new ArrayList<Tbl_departamento>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.departamento", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	
	public boolean guardarDepartamento(Tbl_departamento Dept){
		boolean guardado = false;
		
				try {
						c = poolConexion.getConnection();
						this.llenaRsDepartamento(c);
						this.rsDep.moveToInsertRow();
						
						rsDep.updateString("departamento", Dept.getDepartamento());
						
						rsDep.insertRow();
						rsDep.moveToCurrentRow();
						guardado = true;
					
				} catch (Exception e) {
					System.err.println("ERROR AL GUARDAR Tbl_departamento "+ e.getMessage());
					e.printStackTrace();
				}
				finally {
					try {
						if(rsDep != null){
							rsDep.close();
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
	
	public Tbl_departamento getDeptbyID(int idDept) {
		Tbl_departamento td = new Tbl_departamento();
		
		try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM dbucash.departamento WHERE idDepartamento=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idDept);
				rs = ps.executeQuery();
				
				if (rs.next()) {
					td.setIdDepartamento(rs.getInt("idDepartamento"));
					td.setDepartamento(rs.getString("departamento"));
				}
		} catch (Exception e) {
			System.out.println("DATOS ERROR getDeptbyID(): "+ e.getMessage());
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
		return td;
	}
	
	public boolean modificarDepartamento(Tbl_departamento td) {
		boolean modificado = false;
		try {
			c = poolConexion.getConnection();
			this.llenaRsDepartamento(c);
			rsDep.beforeFirst();
			while (rsDep.next()) {
				if (rsDep.getInt(1)==td.getIdDepartamento()) {
					rsDep.updateString("departamento", td.getDepartamento());
					rsDep.updateRow();
					modificado=true;
					break;
				}
				
			}
		} catch (Exception e) {
			System.err.println("ERROR AL modificarDepartamento() "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsDep != null){
					rsDep.close();
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
	
	public boolean eliminarDepartamento(Tbl_departamento td)
	{
		boolean eliminado=false;	
		try
		{
			c = poolConexion.getConnection();
			this.llenaRsDepartamento(c);
			rsDep.beforeFirst();
			while (rsDep.next()){
				if(rsDep.getInt(1)==td.getIdDepartamento()){
					rsDep.deleteRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e){
			System.err.println("ERROR AL eliminarDepartamento() "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsDep != null){
					rsDep.close();
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

