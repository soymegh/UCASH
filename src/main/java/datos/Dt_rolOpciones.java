package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_rolOpciones;
import entidades.Tbl_usuarioRol;
import entidades.Vw_rolopciones;
import entidades.Vw_usuariorol;

public class Dt_rolOpciones {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRolOpcion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_rolOpciones() {
		
	}
	
	//Metodo para llenar el ResultSet
	public void llenarRsRolOpciones(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM rolopciones;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRolOpcion = ps.executeQuery();
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR ROL OPCION" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para listar la empresa
	//Sujeto a cambio el Tbl_empresa por algun View
	public ArrayList<Vw_rolopciones> listarRolOpciones(){
		ArrayList<Vw_rolopciones> listRolOpc = new ArrayList<Vw_rolopciones>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_rolopciones; ",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Vw_rolopciones tblRolOpc = new Vw_rolopciones();
				tblRolOpc.setIdRolOpciones(this.rs.getInt("idRolOpciones"));
				tblRolOpc.setRol(this.rs.getString("nombreRol"));
				tblRolOpc.setOpciones(this.rs.getString("nombreOpcion"));
				listRolOpc.add(tblRolOpc);
			}
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR ROL OPCION "+ e.getMessage());
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
		return listRolOpc;
	}
	
	public boolean asignarOpcion(Tbl_rolOpciones rolOpc){
		boolean guardado = false;
		try{
			c = poolConexion.getConnection();
			this.llenarRsRolOpciones(c);
			this.rsRolOpcion.moveToInsertRow();
			
			rsRolOpcion.updateInt("idRol", rolOpc.getIdRol());
			rsRolOpcion.updateInt("idOpciones", rolOpc.getIdOpciones());
			
			rsRolOpcion.insertRow();
			rsRolOpcion.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR ROL OPCIONES "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsRolOpcion != null){
					rsRolOpcion.close();
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

	
	
	public Vw_rolopciones ObtenerRolOpcionPorId(int id) {
		Vw_rolopciones rolOpcion = new Vw_rolopciones();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_rolopciones WHERE idRolOpciones = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				rolOpcion.setIdRolOpciones(rs.getInt("idRolOpciones"));
				rolOpcion.setRol(rs.getString("nombreRol"));
				rolOpcion.setOpciones(rs.getString("nombreOpcion"));
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR ROL OPCIONES" + e.getMessage());
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

		return rolOpcion;
	}
	
	public boolean modificarRolOpcion(Tbl_rolOpciones rolOpcion)
	{
		System.out.println("Id de la asginación: "+rolOpcion.getIdRolOpciones()+"");
		boolean modificado=false;	
		try
		{
			c = poolConexion.getConnection();
			llenarRsRolOpciones(c);
			rsRolOpcion.beforeFirst();
			while (rsRolOpcion.next())
			{
				if(rsRolOpcion.getInt(1)==rolOpcion.getIdRolOpciones())
				{
					rsRolOpcion.updateInt("idRol", rolOpcion.getIdRol());
					rsRolOpcion.updateInt("idOpciones", rolOpcion.getIdOpciones());
					rsRolOpcion.updateRow();
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
				if(rsRolOpcion != null){
					rsRolOpcion.close();
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
	
	public boolean eliminarRolOpcion(int id){
		 boolean eliminado = false;
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("DELETE FROM dbucash.rolopciones WHERE idRolOpciones = ?; ",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			
			if(ps.executeUpdate() > 0) {
				eliminado = true;
			}
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN ELIMINAR ROL OPCIONES "+ e.getMessage());
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
	
	
}
