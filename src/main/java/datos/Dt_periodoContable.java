package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import entidades.Tbl_periodoContable;
import entidades.Tbl_periodoFiscal;
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
			this.ps = c.prepareStatement("SELECT * FROM dbucash.periodocontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
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
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_periodocontable;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_periodoContable periodocontable = new Vw_periodoContable();
				periodocontable.setIdPeriodoContable(rs.getInt("idPeriodoContable"));
				periodocontable.setIdPeriodoFiscal(rs.getInt("idPeriodoFiscal"));
				
				String fechaIniPF = rs.getString("Fecha_Inicio_del_Periodo_Fiscal");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPF);
				periodocontable.setFechaInicioPF(new java.sql.Date(date0.getTime()));
				
				String fechaFinPF = rs.getString("Fecha_Final_del_Periodo_Fiscal");
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPF);
				periodocontable.setFechaFinalPF(new java.sql.Date(date1.getTime()));
				
				
				//Fecha inicio
				//Se utiliza este metodo para evitar que reste un dia
				String fechaIniPC = rs.getString("fechaInicio");
				java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPC);
				periodocontable.setFechaInicio(new java.sql.Date(date2.getTime()));
				
				//Fecha Final
				//Se utiliza este metodo para evitar que reste un dia
				String fechaFinPC = rs.getString("fechaFinal");
	        	java.util.Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPC);
	        	periodocontable.setFechaFinal(new java.sql.Date(date3.getTime()));
				
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
			rsperiodocontable.updateInt("estado", 1);
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
	
	public Tbl_periodoContable obtenerPContablePorId(int id)
	{
		Tbl_periodoContable pcontable = new Tbl_periodoContable();
		try 
		{
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.periodocontable idPeriodocontable = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			
			if (rs.next()) 
			{
				pcontable.setIdPeriodoContable(rs.getInt("idPeriodoContable"));
				pcontable.setIdPeriodoFiscal(rs.getInt("idPeriodoFiscal"));
				//Fecha inicio
				//Se utiliza este metodo para evitar que reste un dia
				String fechaIniPC = rs.getString("fechaInicio");
				java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPC);
				pcontable.setFechaInicio(new java.sql.Date(date2.getTime()));
				
				//Fecha Final
				//Se utiliza este metodo para evitar que reste un dia
				String fechaFinPC = rs.getString("fechaFinal");
	        	java.util.Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPC);
	        	pcontable.setFechaFinal(new java.sql.Date(date3.getTime()));
				
				
			}
		} 
		catch (Exception e)
		{
			System.err.println("ERROR AL ObTENER Periodo Fiscal POR ID: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (rsperiodocontable != null) 
				{
					rsperiodocontable.close();
				}
				if (c != null) 
				{
					poolConexion.closeConnection(c);
				}
				if (ps != null) 
				{
					ps.close();
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return pcontable;
	}
	
	public boolean modificarPeriodoContable(Tbl_periodoContable tpcontable)
	{
		boolean modificado = false;
		
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("Update dbucash.periodocontable set  idPeriodoFiscal = ?, fechaInicio = ?, fechaFinal = ?, estado = 2 WHERE idPeriodoContable = ? ;");
			ps.setInt(1, tpcontable.getIdPeriodoFiscal());
			ps.setDate(2,tpcontable.getFechaInicio());
			ps.setDate(3, tpcontable.getFechaFinal());
			ps.setInt(4, tpcontable.getIdPeriodoContable());
			
			int result = ps.executeUpdate();
			modificado = (result > 0) ? true : false;
		} 
		catch (Exception e)
		{
			System.err.println("ERROR AL modificarPeriodoFiscal "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (rsperiodocontable != null)
				{
					rsperiodocontable.close();
				}
				if (c != null) 
				{
					poolConexion.closeConnection(c);
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return modificado;
	}
	
	public boolean EliminarPContablePorId(int idEliminar){
		
		boolean borrado = false;
		
		try {
			
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("UPDATE dbucash.periodocontable SET estado = 3 WHERE idPeriodoContable = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, idEliminar);
			int result = this.ps.executeUpdate();

			if (result > 0) {
				borrado = true;
			}
			
		} catch (Exception e) {
			
			System.err.println("ERROR AL BORRAR Periodo Contable POR ID: " + e.getMessage());
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	
		return borrado;
		
	}
}