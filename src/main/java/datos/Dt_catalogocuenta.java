package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_catalogocuenta;
import entidades.Vw_catalogocuenta_empresa;

public class Dt_catalogocuenta {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCatalogocuenta = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Metodo para llenar el ResultSet
	public void llenar_rsCatalogo(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_catalogocuenta;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCatalogocuenta = ps.executeQuery();
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR tbl_catalogocuenta " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para listar la empresa
	//Sugeto a cambio el Tbl_empresa por algun View
	public ArrayList<Vw_catalogocuenta_empresa> listarCatalogocuenta(){
		ArrayList<Vw_catalogocuenta_empresa> listCatalogocuenta = new ArrayList<Vw_catalogocuenta_empresa>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM sistemacontablebd.Vw_catalogocuenta_empresa;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Vw_catalogocuenta_empresa tblCatalogocuenta = new Vw_catalogocuenta_empresa();
			tblCatalogocuenta.setIdCatalogoCuenta(rs.getInt("idCatalogoCuenta"));
			tblCatalogocuenta.setIdEmpresa(rs.getInt("idEmpresa"));
			tblCatalogocuenta.setnombreComercial(rs.getString("nombreComercial"));
			tblCatalogocuenta.setTitulo(rs.getString("titulo"));
			tblCatalogocuenta.setDescripcion(rs.getString("descripcion"));
			tblCatalogocuenta.setFechaCreacion(rs.getDate("fechaCreacion"));
			tblCatalogocuenta.setFechaModificacion(rs.getDate("fechaModificacion"));
			tblCatalogocuenta.setFechaEliminacion(rs.getDate("fechaEliminacion"));
			tblCatalogocuenta.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
			tblCatalogocuenta.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
			tblCatalogocuenta.setUsuarioEliminacion(rs.getInt("usuarioEliminacion"));
		
			

				listCatalogocuenta.add(tblCatalogocuenta);
			}
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR  "+ e.getMessage());
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
		return listCatalogocuenta;
	}
	
}
