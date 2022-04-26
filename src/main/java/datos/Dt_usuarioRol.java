package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_usuario;
import entidades.Tbl_usuarioRol;
import entidades.Vw_usuariorol;

public class Dt_usuarioRol {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsUserRol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_usuarioRol() {
		
	}
	
	//Metodo para llenar el ResultSet
	public void llenarRsUserRol(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM usuariorol;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUserRol = ps.executeQuery();
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIO ROL" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para listar la empresa
	//Sujeto a cambio el Tbl_empresa por algun View
	public ArrayList<Vw_usuariorol> listarUserRol(){
		ArrayList<Vw_usuariorol> listUserRol = new ArrayList<Vw_usuariorol>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_usuariorol; ",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Vw_usuariorol tblUserRol = new Vw_usuariorol();
				tblUserRol.setIdUsuarioRol(rs.getInt("idUsuarioRol"));
				tblUserRol.setDescripcion(rs.getString("nombreRol"));
				tblUserRol.setNombre(rs.getString("nombre"));
				tblUserRol.setApellido(rs.getString("apellido"));
				tblUserRol.setUsuario(rs.getString("usuario"));
				listUserRol.add(tblUserRol);
			}
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR USUARIO ROL "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
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
		return listUserRol;
	}
	
	public boolean asignarRol(Tbl_usuarioRol UserRol){
		boolean guardado = false;
		try{
			c = poolConexion.getConnection();
			llenarRsUserRol(c);
			rsUserRol.moveToInsertRow();
			
			rsUserRol.updateInt("idRol", UserRol.getIdRol());
			rsUserRol.updateInt("idUsuario", UserRol.getIdUsuario());
			
			rsUserRol.insertRow();
			rsUserRol.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR USUARIO ROL "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsUserRol != null){
					rsUserRol.close();
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
	
	public Vw_usuariorol ObtenerUsuarioRolPorId(int id) {
		Vw_usuariorol userRol = new Vw_usuariorol();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_usuariorol WHERE  idUsuarioRol = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				userRol.setIdUsuarioRol(rs.getInt("idUsuarioRol"));
				userRol.setDescripcion(rs.getString("nombreRol"));
				userRol.setNombre(rs.getString("nombre"));
				userRol.setApellido(rs.getString("apellido"));
				userRol.setUsuario(rs.getString("usuario"));
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS " + e.getMessage());
			e.printStackTrace();
		} finally {
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

		return userRol;
	}
	
	public boolean modificarUsuarioRol(Tbl_usuarioRol userRol)
	{
		boolean modificado=false;	
		try
		{
			c = poolConexion.getConnection();
			llenarRsUserRol(c);
			rsUserRol.beforeFirst();
			while (rsUserRol.next())
			{
				if(rsUserRol.getInt(1)==userRol.getIdUsuarioRol())
				{
					rsUserRol.updateInt("idRol", userRol.getIdRol());
					rsUserRol.updateInt("idUsuario", userRol.getIdUsuario());
					rsUserRol.updateRow();
					modificado=true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("ERROR AL modificarUser() "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsUserRol != null){
					rsUserRol.close();
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
	
	public boolean eliminarUsuarioRol(int id){
		 boolean eliminado = false;
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("DELETE FROM dbucash.usuariorol WHERE idUsuarioRol = ?; ",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			
			if(ps.executeUpdate() > 0) {
				eliminado = true;
			}
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN ELIMINAR USUARIO ROL "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
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
		return eliminado;
	}
	
	public static void main(String[] args) throws SQLException {
		Dt_usuarioRol dt = new Dt_usuarioRol();
		dt.eliminarUsuarioRol(3);
    }
}
