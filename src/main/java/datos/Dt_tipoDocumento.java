package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_tipoDocumento;


public class Dt_tipoDocumento {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTipoDocumento = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_tipoDocumento() {
			
	}
	
	public void llenaRsTipoDocumento(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.tipodocumento;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsTipoDocumento = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR TIPO DE DOCUMENTO " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_tipoDocumento> listaTipoDocumento(){
		ArrayList<Tbl_tipoDocumento> listTipoDocumento = new ArrayList<Tbl_tipoDocumento>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.tipodocumento;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_tipoDocumento tDoc = new Tbl_tipoDocumento();
				tDoc.setIdTipoDocumento(this.rs.getInt("idTipoDocumento"));
				tDoc.setTipo(this.rs.getString("tipo"));
				tDoc.setAcronimo(this.rs.getString("acronimo"));
				listTipoDocumento.add(tDoc);
			}
			} catch(Exception var11) {
				System.out.println("DATOS: ERROR EN LISTAR TIPO DE DOCUMENTO "+var11.getMessage());
				var11.printStackTrace();
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
	            } catch (SQLException var10) {
	                var10.printStackTrace();
	            }

		}
		
		return listTipoDocumento;
	}
	
	public boolean guardarTipoDocumento(Tbl_tipoDocumento ttd){
		boolean guardado = false;
		
		try {
			c = poolConexion.getConnection();
			this.llenaRsTipoDocumento(c);
			this.rsTipoDocumento.moveToInsertRow();
			rsTipoDocumento.updateInt("idTipoDocumento", ttd.getIdTipoDocumento());
			rsTipoDocumento.updateString("tipo", ttd.getTipo());
			rsTipoDocumento.updateString("acronimo", ttd.getAcronimo());
			rsTipoDocumento.insertRow();
			rsTipoDocumento.moveToCurrentRow();
			guardado = true;
			
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR tbl_tipoDocumento: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsTipoDocumento != null) {
					rsTipoDocumento.close();
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
	
	
	public Tbl_tipoDocumento obtenerTipoDocPorId(int id){
		
		Tbl_tipoDocumento TR = new Tbl_tipoDocumento();
		
		try {
			
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("Select * from dbucash.tipodocumento where idTipoDocumento = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			
			if (rs.next()) 
			{
				
				TR.setIdTipoDocumento(rs.getInt("idTipoDocumento"));
				TR.setTipo(rs.getString("tipo"));
				TR.setAcronimo(rs.getString("acronimo"));
				
			}
			
		}
		catch (Exception e)
		{
			System.err.println("ERROR AL ObTENER TIPO DE DOCUMENTO POR ID: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				
				if (rsTipoDocumento != null)
				{
					rsTipoDocumento.close();
				}
				if(ps != null)
				{
					ps.close();
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
		
		
		
		return TR;
	}
	
	public boolean modificarTipoDoc(Tbl_tipoDocumento td){
		boolean modificado = false;
		
		try 
		{
			c = poolConexion.getConnection();
			ps = c.prepareStatement("update dbucash.tipodocumento set tipo = ? , acronimo = ? where idTipoDocumento = ?;");
			
			ps.setString(1, td.getTipo());
			ps.setString(2,td.getAcronimo());
			ps.setInt(3, td.getIdTipoDocumento());
			
			int result = ps.executeUpdate();
			modificado = (result > 0) ? true : false;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR AL modificarTipoDoc() "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (rsTipoDocumento != null)
				{
					rsTipoDocumento.close();
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
	
}