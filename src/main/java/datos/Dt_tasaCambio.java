package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Tbl_moneda;
import entidades.Tbl_tasaCambio;
import entidades.Vw_tasaCambioDet;
import entidades.Vw_tasacambio;

public class Dt_tasaCambio {

		//Atributos
		poolConexion pc = poolConexion.getInstance(); 
		Connection c = null;
		private ResultSet rsTsc = null;
		private PreparedStatement ps = null;
		
		//Metodo para llenar el RusultSet //para insert, update and delete
		public void llenarsTsc(Connection c){
			try{
				ps = c.prepareStatement("SELECT * FROM dbucash.tasacambio;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsTsc = ps.executeQuery();
			}
			catch (Exception e){
				System.out.println("DATOS: ERROR EN llenar tasa cambio "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		//Metodo para ver maestro de tasa cambio
		public ArrayList<Vw_tasacambio> listarTasaCambioActivas(){
			ArrayList<Vw_tasacambio> listTC = new ArrayList<Vw_tasacambio>();
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM  dbucash.vw_tasacambio WHERE estado<>3;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rsTsc = ps.executeQuery();
				while(rsTsc.next()) { 
					Vw_tasacambio tblTC = new Vw_tasacambio();
					tblTC.setIdTasaCambio(rsTsc.getInt("id_tasaCambio"));
					tblTC.setIdMonedaO(rsTsc.getInt("id_MonedaO"));
					tblTC.setNombreO(rsTsc.getString("nombreO"));
					tblTC.setIdMonedaC(rsTsc.getInt("id_MonedaC"));
					tblTC.setNombreC(rsTsc.getString("nombreC"));
					tblTC.setMes(rsTsc.getInt("mes"));
					tblTC.setAnio(rsTsc.getInt("anio"));
					tblTC.setEstado(rsTsc.getInt("estado"));
					listTC.add(tblTC);
				}
			} 
			catch (Exception e){
				System.out.println("DATOS: ERROR EN LISTAR TASA CAMBIO "+ e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsTsc != null){
						rsTsc.close();
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
		
		//Metodo para ver detalle de tasa cambio
		public ArrayList<Vw_tasaCambioDet> listarTasaCambioDet(){
			ArrayList<Vw_tasaCambioDet> listTCD = new ArrayList<Vw_tasaCambioDet>();
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM  dbucash.vw_tasacambiodetalle;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rsTsc = ps.executeQuery();
				while(rsTsc.next()) { 
					Vw_tasaCambioDet tblTCD = new Vw_tasaCambioDet();
					tblTCD.setIdTasaCambioDet(rsTsc.getInt("id_tasaCambio_det"));
					tblTCD.setIdTasaCambio(rsTsc.getInt("id_tasaCambio"));
					tblTCD.setIdMonedaO(rsTsc.getInt("id_monedaO"));
					tblTCD.setNombreO(rsTsc.getString("nombreO"));
					tblTCD.setIdMonedaC(rsTsc.getInt("id_monedaC"));
					tblTCD.setNombreC(rsTsc.getString("nombreC"));
					tblTCD.setFecha(rsTsc.getDate("fecha"));
					tblTCD.setTipoCambio(rsTsc.getDouble("tipoCambio"));
					listTCD.add(tblTCD);
				}
			} 
			catch (Exception e){
				System.out.println("DATOS: ERROR EN LISTAR TASA CAMBIO DETALLE "+ e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsTsc != null){
						rsTsc.close();
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
		
		//Metodo para almacenar nueva tbl_tasacambio
		
		public int guardarTasac(Tbl_tasaCambio tsc){
			int guardado = 0;
			try{
				c = poolConexion.getConnection();
				this.llenarsTsc(c);
				rsTsc.moveToInsertRow();
				rsTsc.updateInt("mes", tsc.getMes());
				rsTsc.updateInt("anio", tsc.getAnio());
				rsTsc.updateInt("id_monedaO", tsc.getId_monedaO());
				rsTsc.updateInt("id_monedaC", tsc.getId_monedaC());
				rsTsc.updateInt("estado", 1);
				rsTsc.updateInt("usuarioCreacion", 26);
				rsTsc.updateString("fechaCreacion", "2022-04-14");
				rsTsc.insertRow();
				rsTsc.moveToCurrentRow();
				this.llenarsTsc(c);
				rsTsc.last();
				guardado = rsTsc.getInt("id_tasaCambio");
			}
			catch (Exception e) {
				System.err.println("ERROR AL GUARDAR guardarTasaC() "+e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsTsc != null){
						rsTsc.close();
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
		
		//Metodo para filtrar una tasa cambio por su ID
		
		public Tbl_tasaCambio getTasaCambiobyID(int idTc) {
			Tbl_tasaCambio tc = new Tbl_tasaCambio();
			
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM dbucash.tasacambio WHERE id_tasaCambio=?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idTc);
				rsTsc = ps.executeQuery();
				
				if (rsTsc.next()) {
					tc.setId_tasaCambio(rsTsc.getInt("id_tasaCambio"));
					tc.setId_monedaO(rsTsc.getInt("id_monedaO"));
					tc.setId_monedaC(rsTsc.getInt("id_monedaC"));
					tc.setMes(rsTsc.getInt("mes"));
					tc.setAnio(rsTsc.getInt("anio"));
					tc.setEstado(rsTsc.getInt("estado"));
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("DATOS ERROR getTasaCambiobyID(): "+ e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if(rsTsc != null){
						rsTsc.close();
					}
					if(ps != null){
						ps.close();
					}
					if(c != null){
						poolConexion.closeConnection(c);
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				}
			}
			return tc;
		}
		
		//Metodo para eliminar maestro de tasa cambio
		public boolean eliminarTasaCambio(Tbl_tasaCambio tc)
		{
			boolean eliminado=false;	
			try
			{
				c = poolConexion.getConnection();
				this.llenarsTsc(c);
				rsTsc.beforeFirst();
				while (rsTsc.next()){
					if(rsTsc.getInt(1)==tc.getId_tasaCambio()){
						rsTsc.deleteRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e){
				System.err.println("ERROR AL eliminarTasaCambio() "+e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsTsc != null){
						rsTsc.close();
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