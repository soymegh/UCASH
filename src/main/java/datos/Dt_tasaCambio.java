package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Tbl_tasaCambio;
import entidades.Vw_tasacambio;

public class Dt_tasaCambio {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTC = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_tasaCambio() {
		
	}
	
	//Metodo para llenar el ResultSet
	public void llenarRsTasaCambio(Connection c) {
		try {
			ps = c.prepareStatement("Select * from tbl_tasacambio;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsTC = ps.executeQuery();
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR TASA CAMBIO " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para listar la empresa
	//Sujeto a cambio el Tbl_empresa por algun View
	public ArrayList<Vw_tasacambio> listarTasaCambioActivas(){
		ArrayList<Vw_tasacambio> listTC = new ArrayList<Vw_tasacambio>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_tasacambio WHERE estado<>3;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Vw_tasacambio tblTC = new Vw_tasacambio();
				tblTC.setIdTasaCambio(this.rs.getInt("idTasaCambio"));
				tblTC.setIdMonedaO(this.rs.getInt("idMonedaO"));
				tblTC.setNombreO(this.rs.getString("nombreO"));
				tblTC.setIdMonedaD(this.rs.getInt("idMonedaD"));
				tblTC.setNombreD(this.rs.getString("nombreD"));
				
				String fecha = rs.getString("fecha");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
				tblTC.setFecha(new java.sql.Date(date0.getTime()));
				tblTC.setFecha(this.rs.getDate("fecha"));
				
				tblTC.setValor(this.rs.getFloat("valor"));
				tblTC.setEstado(this.rs.getInt("estado"));
				tblTC.setUsuarioCreacion(this.rs.getInt("usuarioCreacion"));
				tblTC.setFechaCreacion(this.rs.getDate("fechaCreacion"));
				tblTC.setUsuarioModificacion(this.rs.getInt("usuarioModificacion"));
				tblTC.setFechaModificacion(this.rs.getDate("fechaModificacion"));
				tblTC.setUsuarioEliminacion(this.rs.getInt("usuarioEliminacion"));
				tblTC.setFecha(this.rs.getDate("fechaEliminacion"));
				listTC.add(tblTC);
			}
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR TASA CAMBIO "+ e.getMessage());
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
		return listTC;
	}
	
	public boolean addTasaCambio(Tbl_tasaCambio TC) {
		boolean guardado = false;
		try{
		c = poolConexion.getConnection();
		this.llenarRsTasaCambio(c);
		this.rsTC.moveToInsertRow();
		
		rsTC.updateInt("idMonedaO", TC.getIdMonedaO());
		rsTC.updateInt("idMonedaD", TC.getIdMonedaD());
		rsTC.updateDate("fecha", (Date) TC.getFecha());
		rsTC.updateDouble("tipoCambio", TC.getTipoCambio());
		rsTC.updateDouble("valor", TC.getValor());
		rsTC.updateInt("estado", TC.getEstado());
		rsTC.updateDate("fechaCreacion", (Date) TC.getFechaCreacion());
		rsTC.updateInt("usuarioCreacion", TC.getUsuarioCreacion());
		
		rsTC.insertRow();
		rsTC.moveToCurrentRow();
		guardado = true;
	}
	catch (Exception e) {
		System.err.println("ERROR AL GUARDAR TASA CAMBIO "+e.getMessage());
		e.printStackTrace();
	}
	finally{
		try {
			if(rsTC != null){
				rsTC.close();
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
