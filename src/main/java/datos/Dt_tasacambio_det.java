package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Tbl_tasaCambioDet;
import entidades.Vw_tasaCambioDet;
import entidades.Vw_tasacambio;


public class Dt_tasacambio_det {
	//Atributos
	poolConexion pc = poolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsTscd = null;
	private PreparedStatement ps = null;
	
	//Metodo para llenar el RusultSet //para insert, update and delete
	public void llenarsTscd(Connection c){
		try{
			ps = c.prepareStatement("SELECT * FROM dbucash.tasacambiodetalle;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsTscd = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR TASA CAMBIO "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para ver detalle de tasa cambio
			public ArrayList<Vw_tasaCambioDet> listarTasaCambioDet(){
				ArrayList<Vw_tasaCambioDet> listTCD = new ArrayList<Vw_tasaCambioDet>();
				try {
					c = poolConexion.getConnection();
					ps = c.prepareStatement("SELECT * FROM  dbucash.vw_tasacambiodetalle;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rsTscd = ps.executeQuery();
					while(rsTscd.next()) { 
						Vw_tasaCambioDet tblTCD = new Vw_tasaCambioDet();
						tblTCD.setIdTasaCambioDet(rsTscd.getInt("id_tasaCambio_det"));
						tblTCD.setIdTasaCambio(rsTscd.getInt("id_tasaCambio"));
						tblTCD.setIdMonedaO(rsTscd.getInt("id_monedaO"));
						tblTCD.setNombreO(rsTscd.getString("nombreO"));
						tblTCD.setIdMonedaC(rsTscd.getInt("id_monedaC"));
						tblTCD.setNombreC(rsTscd.getString("nombreC"));
						tblTCD.setFecha(rsTscd.getDate("fecha"));
						tblTCD.setTipoCambio(rsTscd.getDouble("tipoCambio"));
						listTCD.add(tblTCD);
					}
				} 
				catch (Exception e){
					System.out.println("DATOS: ERROR EN LISTAR TASA CAMBIO DETALLE "+ e.getMessage());
					e.printStackTrace();
				}
				finally{
					try {
						if(rsTscd != null){
							rsTscd.close();
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
				return listTCD;
			}
			
			public Vw_tasaCambioDet ObtenerTasaCambioPorFecha(String fechaActual){
				Vw_tasaCambioDet tblTCD = new Vw_tasaCambioDet();
				try {
					c = poolConexion.getConnection();
					ps = c.prepareStatement("SELECT * FROM  dbucash.vw_tasacambiodetalle WHERE fecha = ?;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, fechaActual);
					rsTscd = ps.executeQuery();
					
					int filasSeleccionadas = 0;
					
					while(rsTscd.next()) { 
						filasSeleccionadas++;
					}
					
					// Regresamos el cursor del ResultSet antes de la primera fila
					rsTscd.beforeFirst();
				
					if (filasSeleccionadas > 0) {
						// En caso de que exista la tasa de cambio de la fecha del parámetro
						while(rsTscd.next()) { 
							tblTCD.setTipoCambio(rsTscd.getDouble("tipoCambio"));
							tblTCD.setIdTasaCambioDet(rsTscd.getInt("id_tasaCambio_det"));
							
							String fechaCambio = rsTscd.getString("fecha");
							java.util.Date fechaCambioFormateado = new SimpleDateFormat("yyyy-MM-dd").parse(fechaCambio);
							tblTCD.setFecha(new java.sql.Date(fechaCambioFormateado.getTime()));
						}
					} else {
						// Si no hay tasa de cambio, se devuelve el último que existe
						ps = c.prepareStatement("SELECT * FROM dbucash.vw_tasacambiodetalle ORDER BY fecha DESC LIMIT 1;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						rsTscd = ps.executeQuery();
						
						while(rsTscd.next()) { 
							tblTCD.setTipoCambio(rsTscd.getDouble("tipoCambio"));
							tblTCD.setIdTasaCambioDet(rsTscd.getInt("id_tasaCambio_det"));

							String fechaCambio = rsTscd.getString("fecha");
							java.util.Date fechaCambioFormateado = new SimpleDateFormat("yyyy-MM-dd").parse(fechaCambio);
							tblTCD.setFecha(new java.sql.Date(fechaCambioFormateado.getTime()));
							
						}
					}
					
				}
				
				catch (Exception e){
					System.out.println("DATOS: ERROR EN LISTAR TASA CAMBIO DETALLE "+ e.getMessage());
					e.printStackTrace();
				}
				finally{
					try {
						if(rsTscd != null){
							rsTscd.close();
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
				System.out.print("FECHA DEL MÉTODO: " + tblTCD.getFecha());
				System.out.print("TIPO DE CAMBIO DEL MÉTODO: " + tblTCD.getTipoCambio());
				return tblTCD;
			}
			
	
	//Metodo para almacenar nueva tbl_tasacambio_det
	public boolean guardarTasacd(Tbl_tasaCambioDet tscd){
	boolean guardado = false;
	try{
		c = poolConexion.getConnection();
		this.llenarsTscd(c);
		rsTscd.moveToInsertRow();
		rsTscd.updateInt("id_tasaCambio", tscd.getId_tasacambio());
		rsTscd.updateDate("fecha",  new java.sql.Date(tscd.getFecha().getTime()));
		rsTscd.updateDouble("tipoCambio", tscd.getTipoCambio());
//		rsTscd.updateInt("estado", 1);
		rsTscd.insertRow();
		rsTscd.moveToCurrentRow();
		guardado = true;
	}
	catch (Exception e) {
		System.err.println("ERROR AL guardarTasacd() "+e.getMessage());
		e.printStackTrace();
	}
	finally{
		try {
			if(rsTscd != null){
				rsTscd.close();
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
