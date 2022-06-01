package datos;

import entidades.Tbl_cuentaContable;
import entidades.Tbl_cuentaContable_Det;
import entidades.Vw_catalogo_tipo_cuentacontable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dt_cuentaContable {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCuentaContable = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	private ResultSet rsCuentaContableDet = null;
	
	public Dt_cuentaContable() {
		
	}
	
	public void llenarRsCuentaContable(Connection c ) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.cuentacontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsCuentaContable = this.ps.executeQuery();
		}
		catch(Exception var3)
		{
			System.out.println("DATOS: ERROR EN LISTAR CUENTA CONTABLE " + var3.getMessage());
			var3.printStackTrace();
		}
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
	
	//Método para listar cuentas contables
	public ArrayList<Vw_catalogo_tipo_cuentacontable> listaCuentasContables(){
		ArrayList<Vw_catalogo_tipo_cuentacontable> listCuentaContable = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.vw_catalogo_tipo_cuentacontable WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_catalogo_tipo_cuentacontable cc = new Vw_catalogo_tipo_cuentacontable();
				cc.setIdCuenta(this.rs.getInt("idCuenta"));
				cc.setNumeroCuenta(this.rs.getString("numeroCuenta"));
				cc.setsC(this.rs.getString("SC"));
				cc.setSsC(this.rs.getString("SsC"));
				cc.setSssC(this.rs.getString("SssC"));
				cc.setNombreCuenta(this.rs.getString("nombreCuenta"));
				cc.setNivel(this.rs.getInt("nivel"));
				cc.setRubro(this.rs.getInt("rubro"));
				cc.setTipoCuenta(this.rs.getString("tipoCuenta"));
				cc.setCatalogoCuenta(this.rs.getString("titulo"));
				cc.setEstado(this.rs.getInt("estado"));
				listCuentaContable.add(cc);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR CUENTAS CONTABLES "+ e.getMessage());
				e.printStackTrace();
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
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

		}
		
		return listCuentaContable;
	}
	
	//Método para ver cuenta contable por id
	
	public Vw_catalogo_tipo_cuentacontable getCuentaContableById(int idCuenta) {
		Vw_catalogo_tipo_cuentacontable cc = new Vw_catalogo_tipo_cuentacontable();
		try {
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.vw_catalogo_tipo_cuentacontable WHERE idCuenta =?", 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idCuenta);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				cc.setIdCuenta(this.rs.getInt("idCuenta"));
				
				cc.setNumeroCuenta(this.rs.getString("numeroCuenta"));
				cc.setsC(this.rs.getString("SC"));
				cc.setSsC(this.rs.getString("SsC"));
				cc.setSssC(this.rs.getString("SssC"));
				cc.setNombreCuenta(this.rs.getString("nombreCuenta"));
				cc.setNivel(this.rs.getInt("nivel"));
				cc.setRubro(this.rs.getInt("rubro"));
				cc.setTipoCuenta(this.rs.getString("tipoCuenta"));
				cc.setCatalogoCuenta(this.rs.getString("titulo"));
				
				System.out.println(cc);
			}
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN VER CUENTAS CONTABLES "+ e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
		return cc;
	}
	
	public int idCuentaContable() {
	    int idCC = 0;
	    
	    try {
	    	c = poolConexion.getConnection(); 
	    	ps = c.prepareStatement("SELECT idCuenta FROM dbucash.cuentacontable ORDER BY idCuenta DESC LIMIT 1",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    	rs = ps.executeQuery();
	    	if(rs.next()) { 
	    		idCC = Integer.parseInt(rs.getObject(1).toString());
	    	}
	    } catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR idCuenta " + e.getMessage());
			e.printStackTrace();
		} finally {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	    
		return idCC;
	}
	
	//Método para agregar una cuenta contable
	
	public boolean addCuentaContable(Tbl_cuentaContable cc) {
		boolean guardado = false;
		try 
		{
			c = poolConexion.getConnection();
			this.llenarRsCuentaContable(c);
			this.rsCuentaContable.moveToInsertRow();
			
			rsCuentaContable.updateString("numeroCuenta", cc.getNumeroCuenta());
			rsCuentaContable.updateString("SC", cc.getsC());
			rsCuentaContable.updateString("SsC", cc.getSsC());
			rsCuentaContable.updateString("SssC", cc.getSssC());
			rsCuentaContable.updateString("nombreCuenta", cc.getNombreCuenta());
			rsCuentaContable.updateInt("nivel", cc.getNivel());
			rsCuentaContable.updateInt("rubro", cc.getRubro());
			rsCuentaContable.updateInt("idTipoCuenta", cc.getIdTipoCuenta());
			rsCuentaContable.updateInt("IdCatalogo", cc.getIdCatalogo());
			rsCuentaContable.updateInt("estado", 1);

			rsCuentaContable.insertRow();
			rsCuentaContable.moveToCurrentRow();
			guardado = true;
		}
		catch(Exception e)
		{
			System.err.println("ERROR AL GUARDAR CUENTA CONTABLE: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsCuentaContable != null) {
					rsCuentaContable.close();
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
	
	//Método para editar cuenta contable
	
	public boolean editCuentaContable(Tbl_cuentaContable cc) {
		boolean modificado = false;
		try {
			c = poolConexion.getConnection();
			this.llenarRsCuentaContable(c);
			rsCuentaContable.beforeFirst();
			while (rsCuentaContable.next()) {
				if (rsCuentaContable.getInt(1) == cc.getIdCuenta()) {
					rsCuentaContable.updateString("numeroCuenta", cc.getNumeroCuenta());
					rsCuentaContable.updateString("SC", cc.getsC());
					rsCuentaContable.updateString("SsC", cc.getSsC());
					rsCuentaContable.updateString("SssC", cc.getSssC());
					rsCuentaContable.updateString("nombreCuenta", cc.getNombreCuenta());
					rsCuentaContable.updateInt("nivel", cc.getNivel());
					rsCuentaContable.updateInt("rubro", cc.getRubro());
					rsCuentaContable.updateInt("idTipoCuenta", cc.getIdTipoCuenta());
					rsCuentaContable.updateInt("IdCatalogo", cc.getIdCatalogo());
					rsCuentaContable.updateInt("estado", 2);
					
					rsCuentaContable.updateRow();
					modificado = true;
					break;
					
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("ERROR AL MODIFICAR CUENTA CONTABLE"+ e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if (rs != null) {
					rs.close();
				}
				if (c != null) {
					poolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return modificado;
	}
	
	//Método para elminar cuenta contable
	
	public boolean deleteCuentaContable(Tbl_cuentaContable cc) {
		boolean eliminado = false;
		
		try {
			c = poolConexion.getConnection();
			this.llenarRsCuentaContable(c);
			rsCuentaContable.beforeFirst();
			while(rsCuentaContable.next()) {
				if(rsCuentaContable.getInt(1) == cc.getIdCuenta()) {
					rsCuentaContable.updateInt("estado",3);
					rsCuentaContable.updateRow();
					eliminado = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("ERROR AL ELIMINAR CUENTA CONTABLE"+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsCuentaContable != null){
					rsCuentaContable.close();
				}
				if(c != null){
					poolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminado;
	}
}