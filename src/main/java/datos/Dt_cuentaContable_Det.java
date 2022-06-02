package datos;

import entidades.Tbl_cuentaContable;
import entidades.Tbl_cuentaContable_Det;
import entidades.Vw_cuentacontable_cuentacontable_det;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dt_cuentaContable_Det {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCuentaContableDet = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_cuentaContable_Det() {
		
	}
	
	public void llenarRsCuentaContableDet(Connection c ) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM cuentacontabledet", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsCuentaContableDet = this.ps.executeQuery();
		}
		catch(Exception var3)
		{
			System.out.println("DATOS: ERROR EN LISTAR DETALLE DE CUENTAS CONTABLES DET" + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Vw_cuentacontable_cuentacontable_det> listaCuentasContablesDet(){
		ArrayList<Vw_cuentacontable_cuentacontable_det> listCuentaContableDet = new ArrayList<Vw_cuentacontable_cuentacontable_det>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM vw_cuentacontable_cuentacontable_det;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_cuentacontable_cuentacontable_det ccD = new Vw_cuentacontable_cuentacontable_det();
				ccD.setIdCuentaContableDet(this.rs.getInt("idCuentaContableDet"));
				ccD.setDebe(this.rs.getDouble("debe"));
				ccD.setHaber(this.rs.getDouble("haber"));
				ccD.setSaldoInicial(this.rs.getDouble("saldoInicial"));
				ccD.setSaldoFinal(this.rs.getDouble("saldoFinal"));
				ccD.setNombreCuenta(this.rs.getString("nombreCuenta"));
				listCuentaContableDet.add(ccD);
			}
			} catch(Exception var11) {
				System.out.println("DATOS: ERROR EN LISTAR DETALLE DE CUENTAS CONTABLES DET "+var11.getMessage());
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
		
		return listCuentaContableDet;
	}
	
	
	public Tbl_cuentaContable_Det getCcdbyID(int idCuentaContableDet) {
		Tbl_cuentaContable_Det ccD = new Tbl_cuentaContable_Det();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.cuentacontabledet WHERE idCuentaContableDet=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idCuentaContableDet);
			rs = ps.executeQuery();
			if(rs.next()) {
				ccD.setIdCuentaContableDet(this.rs.getInt("idCuentaContableDet"));
				ccD.setIdCuenta(this.rs.getInt("idCuenta"));
				ccD.setDebe(this.rs.getDouble("debe"));
				ccD.setHaber(this.rs.getDouble("haber"));
				ccD.setSaldoInicial(this.rs.getDouble("saldoInicial"));
				ccD.setSaldoFinal(this.rs.getDouble("saldoFinal"));
				
			}
		}catch (Exception e)
		{
			System.out.println("DATOS ERROR getCcdbyID(): "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		
		return ccD;
	}
	
	
	public Vw_cuentacontable_cuentacontable_det getCCDbyID(int idCuentaContableDet) {
		Vw_cuentacontable_cuentacontable_det ccD = new Vw_cuentacontable_cuentacontable_det();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cuentacontable_cuentacontable_det WHERE idCuentaContableDet=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idCuentaContableDet);
			rs = ps.executeQuery();
			if(rs.next()) {
				ccD.setIdCuentaContableDet(this.rs.getInt("idCuentaContableDet"));
				ccD.setDebe(this.rs.getDouble("debe"));
				ccD.setHaber(this.rs.getDouble("haber"));
				ccD.setSaldoInicial(this.rs.getDouble("saldoInicial"));
				ccD.setSaldoFinal(this.rs.getDouble("saldoFinal"));
				ccD.setNombreCuenta(this.rs.getString("nombreCuenta"));
			}
		}catch (Exception e)
		{
			System.out.println("DATOS ERROR getCCDbyID(): "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		
		return ccD;
	}
	
	//Metodo para agregar una cuenta contable det
	public boolean addCuentaContableDet(Tbl_cuentaContable_Det ccD) {
		boolean guardado = false;
		try 
		{
			c = poolConexion.getConnection();
			this.llenarRsCuentaContableDet(c);
			this.rsCuentaContableDet.moveToInsertRow();
			
			rsCuentaContableDet.updateInt("idCuentaContableDet", ccD.getIdCuentaContableDet());
			rsCuentaContableDet.updateInt("idCuenta", ccD.getIdCuenta());
			rsCuentaContableDet.updateDouble("debe", ccD.getDebe());
			rsCuentaContableDet.updateDouble("haber", ccD.getHaber());
			rsCuentaContableDet.updateDouble("saldoInicial", ccD.getSaldoInicial());
			rsCuentaContableDet.updateDouble("saldoFinal", ccD.getSaldoFinal());
			
			rsCuentaContableDet.insertRow();
			rsCuentaContableDet.moveToCurrentRow();
			guardado = true;
		}
		catch(Exception e)
		{
			System.err.println("ERROR AL GUARDAR CUENTA CONTABLE DET: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsCuentaContableDet != null) {
					rsCuentaContableDet.close();
				}
				if(c != null) {
					poolConexion.closeConnection(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return guardado;
	}
	
	//Metodo para editar cuentaContableDet
	
	public boolean editarCuentaContableDet(Tbl_cuentaContable_Det ccD) {
		boolean modificado = false;
		try {
			c = poolConexion.getConnection();
			this.llenarRsCuentaContableDet(c);
			rsCuentaContableDet.beforeFirst();
			while (rsCuentaContableDet.next()) {
				if (rsCuentaContableDet.getInt(1)==ccD.getIdCuentaContableDet()) {
					rsCuentaContableDet.updateInt("idCuenta", ccD.getIdCuenta());
					rsCuentaContableDet.updateDouble("debe", ccD.getDebe());
					rsCuentaContableDet.updateDouble("haber", ccD.getHaber());
					rsCuentaContableDet.updateDouble("saldoInicial", ccD.getSaldoInicial());
					rsCuentaContableDet.updateDouble("saldoFinal", ccD.getSaldoFinal());
					rsCuentaContableDet.updateRow();
					modificado=true;
					break;
				}
				
			}
		} catch (Exception e) {
			System.err.println("ERROR AL EDITAR CUENTA CONTABLE DET() "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsCuentaContableDet != null){
					rsCuentaContableDet.close();
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
	//Metodo para eliminar cuentaContableDet
	
	public boolean eliminarCuentaContableDet(Tbl_cuentaContable_Det ccD)
	{
		boolean eliminado=false;	
		try
		{
			c = poolConexion.getConnection();
			this.llenarRsCuentaContableDet(c);
			rsCuentaContableDet.beforeFirst();
			while (rsCuentaContableDet.next()){
				if(rsCuentaContableDet.getInt(1)==ccD.getIdCuentaContableDet()){
					rsCuentaContableDet.deleteRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e){
			System.err.println("ERROR AL eliminarCuentaContableDet "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsCuentaContableDet != null){
					rsCuentaContableDet.close();
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