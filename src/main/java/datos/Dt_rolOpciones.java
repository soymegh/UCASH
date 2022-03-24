package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_rolOpciones;
import entidades.Tbl_usuarioRol;
import entidades.Vw_rolopciones;

public class Dt_rolOpciones {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRolOpc = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_rolOpciones() {
		
	}
	
	//Metodo para llenar el ResultSet
	public void llenarRsRolOpciones(Connection c) {
		try {
			ps = c.prepareStatement("Select * from tbl_rolopciones;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRolOpc = ps.executeQuery();
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIO ROL" + e.getMessage());
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
				tblRolOpc.setRol(this.rs.getString("rol"));
				tblRolOpc.setOpciones(this.rs.getString("opciones"));
				listRolOpc.add(tblRolOpc);
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
		return listRolOpc;
	}
	
	public boolean asignarOpcion(Tbl_rolOpciones rolOpc){
		boolean guardado = false;
		try{
			c = poolConexion.getConnection();
			this.llenarRsRolOpciones(c);
			this.rsRolOpc.moveToInsertRow();
			
			rsRolOpc.updateInt("idRol", rolOpc.getIdRol());
			rsRolOpc.updateInt("idOpciones", rolOpc.getIdOpciones());
			
			rsRolOpc.insertRow();
			rsRolOpc.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR ROL OPCIONES "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsRolOpc != null){
					rsRolOpc.close();
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
