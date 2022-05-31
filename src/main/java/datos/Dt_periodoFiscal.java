package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Tbl_periodoFiscal;

public class Dt_periodoFiscal {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsperiodoFiscal = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_periodoFiscal() {
		
	}
	
	public void llenaRsPeriodoFiscal(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.periodofiscal;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsperiodoFiscal = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Periodo Fiscal " + e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<Tbl_periodoFiscal> listarperiodoFiscal(){
		ArrayList<Tbl_periodoFiscal> listperiodoFiscal = new ArrayList<Tbl_periodoFiscal>();
		try {
			c = poolConexion.getConnection();

			ps = c.prepareStatement("SELECT * FROM dbucash.periodofiscal;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_periodoFiscal periodofiscal = new Tbl_periodoFiscal();
				periodofiscal.setIdPeriodoFiscal(rs.getInt("idperiodoFiscal"));
				
				//Fecha inicio\
				//Se realiza este metodo para que no reste un dia
				String fechaIniJsp = rs.getString("fechaInicio");
	            java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniJsp);
	            periodofiscal.setFechaInicio(new java.sql.Date(date1.getTime()));
				
				
				//Fecha final
	            //Se realiza este metodo para que no reste un dia
				String fechaFinJsp = rs.getString("fechaFinal");
	        	java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinJsp); 	 
	        	periodofiscal.setFechaFinal(new java.sql.Date(date2.getTime()));
	        	
				periodofiscal.setEstado(rs.getInt("estado"));
				listperiodoFiscal.add(periodofiscal);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Periodos Fiscales "+e.getMessage());
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
		
		return listperiodoFiscal;
	}
	
	public ArrayList<Tbl_periodoFiscal> listarperiodoFiscalLogin(){
		ArrayList<Tbl_periodoFiscal> listperiodoFiscal = new ArrayList<Tbl_periodoFiscal>();
		try {
			c = poolConexion.getConnection();

			ps = c.prepareStatement("SELECT * FROM dbucash.periodofiscal WHERE estado <> 3;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_periodoFiscal periodofiscal = new Tbl_periodoFiscal();
				periodofiscal.setIdPeriodoFiscal(rs.getInt("idperiodoFiscal"));
				
				//Fecha inicio\
				//Se realiza este metodo para que no reste un dia
				String fechaIniJsp = rs.getString("fechaInicio");
	            java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniJsp);
	            periodofiscal.setFechaInicio(new java.sql.Date(date1.getTime()));
				
				
				//Fecha final
	            //Se realiza este metodo para que no reste un dia
				String fechaFinJsp = rs.getString("fechaFinal");
	        	java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinJsp); 	 
	        	periodofiscal.setFechaFinal(new java.sql.Date(date2.getTime()));
	        	
				periodofiscal.setEstado(rs.getInt("estado"));
				listperiodoFiscal.add(periodofiscal);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Periodos Fiscales "+e.getMessage());
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
		
		return listperiodoFiscal;
	}
	
	public boolean agregarPeriodoFiscal(Tbl_periodoFiscal tpf) {
		boolean guardado = false;
		
		try {
			c = poolConexion.getConnection();
			this.llenaRsPeriodoFiscal(c);
			this.rsperiodoFiscal.moveToInsertRow();
			rsperiodoFiscal.updateInt("idPeriodoFiscal", tpf.getIdPeriodoFiscal());
			rsperiodoFiscal.updateDate("fechaInicio", (Date) tpf.getFechaInicio());
			rsperiodoFiscal.updateDate("fechaFinal", tpf.getFechaFinal());
			rsperiodoFiscal.updateInt("estado", 1);
			rsperiodoFiscal.insertRow();
			rsperiodoFiscal.moveToCurrentRow();
			guardado = true;
		}
		catch(Exception e) {
			System.err.println("ERROR AL GUARDAR tbl_periodoFiscal "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsperiodoFiscal != null) {
					rsperiodoFiscal.close();
				}
				if(c != null) {
					poolConexion.closeConnection(c);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			}
		return guardado;
		}
	
	public Tbl_periodoFiscal obtenerPFiscalPorId(int id)
	{
		Tbl_periodoFiscal pfiscal = new Tbl_periodoFiscal();
		try 
		{
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.periodofiscal WHERE idPeriodoFiscal = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			
			if (rs.next()) 
			{
				pfiscal.setIdPeriodoFiscal(rs.getInt("idPeriodoFiscal"));
				//Fecha inicio\
				//Se realiza este metodo para que no reste un dia
				String fechaIniJsp = rs.getString("fechaInicio");
	            java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniJsp);
	            pfiscal.setFechaInicio(new java.sql.Date(date1.getTime()));
				
				
				//Fecha final
	            //Se realiza este metodo para que no reste un dia
				String fechaFinJsp = rs.getString("fechaFinal");
	        	java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinJsp); 	 
	        	pfiscal.setFechaFinal(new java.sql.Date(date2.getTime()));
				
				
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
				if (rsperiodoFiscal != null) 
				{
					rsperiodoFiscal.close();
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
		
		return pfiscal;
	}
	
	public boolean obtenerPFiscalPorIdLogin(int id)
	{
		boolean flag = false; 
		Tbl_periodoFiscal pfiscal = new Tbl_periodoFiscal();
		try 
		{
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.periodofiscal WHERE estado <> 3 AND idPeriodoFiscal = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			
			if (rs.next()) 
			{
				pfiscal.setIdPeriodoFiscal(rs.getInt("idPeriodoFiscal"));
				//Fecha inicio\
				//Se realiza este metodo para que no reste un dia
				String fechaIniJsp = rs.getString("fechaInicio");
	            java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniJsp);
	            pfiscal.fechaInicioActual = new java.sql.Date(date1.getTime());
				
				
				//Fecha final
	            //Se realiza este metodo para que no reste un dia
				String fechaFinJsp = rs.getString("fechaFinal");
	        	java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinJsp); 	 
	        	pfiscal.fechaFinalActual = new java.sql.Date(date2.getTime());
				
				flag = true; 
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
				if (rsperiodoFiscal != null) 
				{
					rsperiodoFiscal.close();
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
		
		return flag;
	}
	
	public boolean modificarPeriodoFiscal(Tbl_periodoFiscal tpfiscal)
	{
		boolean modificado = false;
		
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("Update dbucash.periodofiscal set  fechaInicio = ?, fechaFinal = ?, estado = 2 WHERE idPeriodoFiscal = ? ;");
			ps.setDate(1,tpfiscal.getFechaInicio());
			ps.setDate(2, tpfiscal.getFechaFinal());
			ps.setInt(3, tpfiscal.getIdPeriodoFiscal());
			
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
				if (rsperiodoFiscal != null)
				{
					rsperiodoFiscal.close();
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
	
	
	public boolean EliminarPFiscalPorId(int idEliminar)
	{
		boolean borrado = false;
		
		try 
		{
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("Update dbucash.periodofiscal Set estado = 3 where idPeriodoFiscal = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, idEliminar);
			int result = this.ps.executeUpdate();

			if (result > 0) {
				borrado = true;
			}
			
			
		} 
		catch (Exception e)
		{
			System.err.println("ERROR AL BORRAR Periodo Fiscal POR ID: " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (rsperiodoFiscal != null) 
				{
					rsperiodoFiscal.close();
				}
				if (c != null) 
				{
					poolConexion.closeConnection(c);
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		return borrado;
	}
	
	
	}