package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			ps = c.prepareStatement("Select * from tbl_usuariorol;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
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
				tblUserRol.setIdUsuarioRol(this.rs.getInt("idUsuarioRol"));
				tblUserRol.setDescripcion(this.rs.getString("descripcion"));
				tblUserRol.setNombreCompleto(this.rs.getString("Nombre Completo"));
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
			this.llenarRsUserRol(c);
			this.rsUserRol.moveToInsertRow();
			
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
}
