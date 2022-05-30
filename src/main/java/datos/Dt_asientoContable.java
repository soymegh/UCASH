package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Tbl_asientoContable;
import entidades.Tbl_periodoContable;
import entidades.Vw_asientoContable;

public class Dt_asientoContable {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAsientoCon = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_asientoContable() {
		
	}
	
	//Metodo para llenar ResultSet
	
	public void llenar_rsAsientoCon(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.asientocontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsAsientoCon = this.ps.executeQuery();
		}catch(Exception e) {
			System.out.println("Datos: Error al enlista asiento Contable" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//Metodo de listar asientoContable
	
	public ArrayList<Vw_asientoContable> listarasientocontable(){
		ArrayList<Vw_asientoContable> listAsientoContable = new ArrayList<Vw_asientoContable>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_asientocontable;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_asientoContable tblAsientoContable = new Vw_asientoContable();
				tblAsientoContable.setIdAsientoContable(this.rs.getInt("idAsientoContable"));
				tblAsientoContable.setIdPeriodoContable(this.rs.getInt("idPeriodoContable"));
				
				String fechaIniPC = rs.getString("fechaInicio");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPC);
				tblAsientoContable.setFechaInicio(new java.sql.Date(date0.getTime()));
				
				String fechaFinPC = rs.getString("fechaFinal");
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPC);
				tblAsientoContable.setFechaFinal(new java.sql.Date(date1.getTime()));
				
				tblAsientoContable.setIdEmpresa(this.rs.getInt("idEmpresa"));
				tblAsientoContable.setNombreComercial(this.rs.getString("nombreComercial"));
				tblAsientoContable.setIdTipoDocumento(this.rs.getInt("idTipoDocumento"));
				tblAsientoContable.setTipo(this.rs.getString("tipo"));
				tblAsientoContable.setIdMoneda(this.rs.getInt("idMoneda"));
				tblAsientoContable.setNombre(this.rs.getString("nombre"));
				tblAsientoContable.setIdTasaCambioDetalle(this.rs.getInt("idTasaCambioDetalle"));
				tblAsientoContable.setTipoCambio(this.rs.getFloat("tipoCambio"));
				
				String fecha = rs.getString("fecha");
				java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
				tblAsientoContable.setFecha(new java.sql.Date(date2.getTime()));
				
				
				tblAsientoContable.setDescripcion(this.rs.getString("descripcion"));
				tblAsientoContable.setUsuarioCreacion(this.rs.getInt("usuarioCreacion"));
				tblAsientoContable.setFechaCreacion(this.rs.getDate("fechaCreacion"));
				tblAsientoContable.setUsuarioModificacion(this.rs.getInt("usuarioModificacion"));
				tblAsientoContable.setFechaModificacion(this.rs.getDate("fechaModificacion"));
				tblAsientoContable.setUsuarioEliminacion(this.rs.getInt("usuarioEliminacion"));
				tblAsientoContable.setFechaEliminacion(this.rs.getDate("fechaEliminacion"));
				
				listAsientoContable.add(tblAsientoContable);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Asiento Contable"+e.getMessage());
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
		
		return listAsientoContable;
	}
	
	public ArrayList<Vw_asientoContable> listarasientocontableporid(int idEmpresa){
		ArrayList<Vw_asientoContable> listAsientoContable = new ArrayList<Vw_asientoContable>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_asientocontable WHERE idEmpresa = ?;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, idEmpresa);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_asientoContable tblAsientoContable = new Vw_asientoContable();
				tblAsientoContable.setIdAsientoContable(this.rs.getInt("idAsientoContable"));
				tblAsientoContable.setIdPeriodoContable(this.rs.getInt("idPeriodoContable"));
				
				String fechaIniPC = rs.getString("fechaInicio");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPC);
				tblAsientoContable.setFechaInicio(new java.sql.Date(date0.getTime()));
				
				String fechaFinPC = rs.getString("fechaFinal");
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPC);
				tblAsientoContable.setFechaFinal(new java.sql.Date(date1.getTime()));
				
				tblAsientoContable.setIdEmpresa(this.rs.getInt("idEmpresa"));
				tblAsientoContable.setNombreComercial(this.rs.getString("nombreComercial"));
				tblAsientoContable.setIdTipoDocumento(this.rs.getInt("idTipoDocumento"));
				tblAsientoContable.setTipo(this.rs.getString("tipo"));
				tblAsientoContable.setIdMoneda(this.rs.getInt("idMoneda"));
				tblAsientoContable.setNombre(this.rs.getString("nombre"));
				tblAsientoContable.setIdTasaCambioDetalle(this.rs.getInt("idTasaCambioDetalle"));
				tblAsientoContable.setTipoCambio(this.rs.getFloat("tipoCambio"));
				
				String fecha = rs.getString("fecha");
				java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
				tblAsientoContable.setFecha(new java.sql.Date(date2.getTime()));
				
				
				tblAsientoContable.setDescripcion(this.rs.getString("descripcion"));
				tblAsientoContable.setUsuarioCreacion(this.rs.getInt("usuarioCreacion"));
				tblAsientoContable.setFechaCreacion(this.rs.getDate("fechaCreacion"));
				tblAsientoContable.setUsuarioModificacion(this.rs.getInt("usuarioModificacion"));
				tblAsientoContable.setFechaModificacion(this.rs.getDate("fechaModificacion"));
				tblAsientoContable.setUsuarioEliminacion(this.rs.getInt("usuarioEliminacion"));
				tblAsientoContable.setFechaEliminacion(this.rs.getDate("fechaEliminacion"));
				
				listAsientoContable.add(tblAsientoContable);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Asiento Contable"+e.getMessage());
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
		
		return listAsientoContable;
	}
	
	public Tbl_asientoContable obtenerAContablePorId(int id)
	{
		Tbl_asientoContable pacontable = new Tbl_asientoContable();
		try 
		{
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.asientocontable WHERE idAsientoContable = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			
			if (rs.next()) 
			{
				pacontable.setIdAsientoContable(rs.getInt("idAsientoContable"));
				pacontable.setIdPeriodoContable(rs.getInt("idPeriodoContable"));
				pacontable.setIdEmpresa(rs.getInt("idEmpresa"));
				pacontable.setIdTipoDocumento(rs.getInt("idTipoDocumento"));
				pacontable.setIdMoneda(rs.getInt("idMoneda"));
				pacontable.setIdTasaCambioDet(rs.getInt("idTasaCambioDetalle"));
				
				//Fecha 
				//Se utiliza este metodo para evitar que reste un dia
				String fecha = rs.getString("fecha");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
				pacontable.setFecha(new java.sql.Date(date0.getTime()));
				
				pacontable.setDescripcion(rs.getString("descripcion"));
				pacontable.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
				pacontable.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				pacontable.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
				pacontable.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
				pacontable.setUsuarioEliminacion(rs.getInt("usuarioEliminacion"));
				pacontable.setFechaEliminacion(rs.getTimestamp("fechaEliminacion"));
				
				
			}
		} 
		catch (Exception e)
		{
			System.err.println("ERROR AL ObTENER Asiento Contable POR ID: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (rsAsientoCon != null) 
				{
					rsAsientoCon.close();
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
		
		return pacontable;
	}
	
	
	
	public int agregarAsientoContable(Tbl_asientoContable tac) {
		int guardado = 0;
		
		try {
			c = poolConexion.getConnection();
			this.llenar_rsAsientoCon(c);
			this.rsAsientoCon.moveToInsertRow();
			rsAsientoCon.updateInt("idPeriodoContable",tac.getIdPeriodoContable());
			rsAsientoCon.updateInt("idEmpresa",tac.getIdEmpresa());
			rsAsientoCon.updateInt("idTipoDocumento",tac.getIdTipoDocumento());
			rsAsientoCon.updateInt("idMoneda", tac.getIdMoneda());
			rsAsientoCon.updateInt("idTasaCambioDetalle", tac.getIdTasaCambioDet());
			rsAsientoCon.updateDate("fecha", tac.getFecha());
			rsAsientoCon.updateString("descripcion", tac.getDescripcion());
			rsAsientoCon.updateTimestamp("fechaCreacion", tac.getFechaCreacion());
			rsAsientoCon.updateInt("usuarioCreacion", tac.getUsuarioCreacion());
			rsAsientoCon.insertRow();
			this.rsAsientoCon.moveToCurrentRow();
			this.llenar_rsAsientoCon(c);
			rsAsientoCon.last();
			guardado = rsAsientoCon.getInt("idAsientoContable");
			
			
		} catch (Exception e) {
			
			System.err.println("ERROR AL GUARDAR tbl_asientoContable "+e.getMessage());
			e.printStackTrace();
			
		}finally {
			try {
				if (rsAsientoCon != null) {
					rsAsientoCon.close();
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
	
	public boolean modificarAsientoContable(Tbl_asientoContable tpascont)
	{
		boolean modificado = false;
		
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("Update dbucash.asientocontable set  idPeriodoContable = ?, idEmpresa = ?, idTipoDocumento = ?, idMoneda = ?, idTasaCambioDetalle = ?, fecha = ?, descripcion = ?, usuarioModificacion = ?, fechaModificacion = ? WHERE idAsientoContable = ? ;");
			ps.setInt(1, tpascont.getIdPeriodoContable());
			ps.setInt(2,tpascont.getIdEmpresa());
			ps.setInt(3, tpascont.getIdTipoDocumento());
			ps.setInt(4, tpascont.getIdMoneda());
			ps.setInt(5, tpascont.getIdTasaCambioDet());
			ps.setDate(6, tpascont.getFecha());
			ps.setString(7, tpascont.getDescripcion());
			ps.setInt(8, tpascont.getUsuarioModificacion());
			ps.setTimestamp(9, tpascont.getFechaModificacion());
			ps.setInt(10, tpascont.getIdAsientoContable());

			int result = ps.executeUpdate();
			modificado = (result > 0) ? true : false;
		} 
		catch (Exception e)
		{
			System.err.println("ERROR AL modificarAsientoContable "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (rsAsientoCon != null)
				{
					rsAsientoCon.close();
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
	
	
public boolean EliminarAContablePorId(int idEliminar){
		
		boolean borrado = false;
		
		try {
			
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("DELETE FROM dbucash.asientocontable WHERE idAsientoContable = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, idEliminar);
			int result = this.ps.executeUpdate();

			if (result > 0) {
				borrado = true;
			}
			
		} catch (Exception e) {
			
			System.err.println("ERROR AL BORRAR Asiento Contable POR ID: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			
			try {
				
				if (rsAsientoCon != null) {
					
					rsAsientoCon.close();
					
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
