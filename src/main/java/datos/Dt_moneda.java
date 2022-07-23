package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_departamento;
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
			this.ps = c.prepareStatement("SELECT * FROM dbucash.moneda;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
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
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.moneda WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
			rsMon.updateInt("usuarioCreacion", Moneda.getUsuarioCreacion());
			rsMon.updateDate("fechaCreacion", Moneda.getFechaCreacion());
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
	
	public Tbl_moneda getMonedaByID(int idMon) {
		Tbl_moneda tmon = new Tbl_moneda();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.moneda WHERE idMoneda=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idMon);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				tmon.setIdMoneda(rs.getInt("idMoneda"));
				tmon.setNombre(rs.getString("nombre"));
				tmon.setSimbolo(rs.getString("simbolo"));
				tmon.setEstado(rs.getInt("estado"));
			}
		} catch (Exception e) {
			System.out.println("DATOS ERROR getMonedaByID(): "+ e.getMessage());
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
		return tmon;
	}
	
	public int getMonedaByIDLogin(int idMon) {
		int flag = 0; 
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.moneda WHERE idMoneda=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idMon);
			rs = ps.executeQuery();

			if (rs.next()) {
				flag = rs.getInt("idMoneda");
			}
		} catch (Exception e) {
			System.out.println("DATOS ERROR getMonedaByID(): "+ e.getMessage());
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
		return flag;
	}

	public boolean modificarMoneda(Tbl_moneda tm) {
		boolean modificado = false;
		try {
		c = poolConexion.getConnection();
		this.llenaRsMoneda(c);
		rsMon.beforeFirst();
		while (rsMon.next()) {
			if (rsMon.getInt(1) == tm.getIdMoneda()) {
				
				rsMon.updateString("nombre", tm.getNombre());
				rsMon.updateString("simbolo", tm.getSimbolo());
				rsMon.updateInt("usuarioModificacion", tm.getUsuarioModificacion());
				rsMon.updateDate("fechaModificacion", tm.getFechaModificacion());
				rsMon.updateInt("estado", tm.getEstado());
				
				rsMon.updateRow();
				modificado=true;
				break;
			}
			
		}
	} catch (Exception e) {
		System.err.println("ERROR AL modificarMoneda() "+e.getMessage());
		e.printStackTrace();
	}
	finally
	{
		try {
			if(rsMon != null){
				rsMon.close();
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
	
	public boolean eliminarMoneda(Tbl_moneda tm)
	{
		boolean eliminado=false;	
		try
		{
			c = poolConexion.getConnection();
			this.llenaRsMoneda(c);
			rsMon.beforeFirst();
			while (rsMon.next()){
				if(rsMon.getInt(1)==tm.getIdMoneda()){
					rsMon.updateInt("usuarioEliminacion", tm.getUsuarioEliminacion());
					rsMon.updateDate("fechaEliminacion", tm.getFechaEliminacion());
					rsMon.updateInt("estado", tm.getEstado());
					rsMon.updateRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e){
			System.err.println("ERROR AL eliminarMoneda() "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsMon != null){
					rsMon.close();
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

