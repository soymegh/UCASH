package datos;

import entidades.Tbl_cuentaContable;
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
	
	//Método para listar cuentas contables
	public ArrayList<Vw_catalogo_tipo_cuentacontable> listaCuentasContables(){
		ArrayList<Vw_catalogo_tipo_cuentacontable> listCuentaContable = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.vw_catalogo_tipo_cuentacontable WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_catalogo_tipo_cuentacontable cc = new Vw_catalogo_tipo_cuentacontable();
				cc.setIdCuentaContable(this.rs.getInt("idCuenta"));
				cc.setNumeroCuenta(this.rs.getString("numeroCuenta"));
				cc.setsC(this.rs.getString("SC"));
				cc.setSsC(this.rs.getString("SsC"));
				cc.setSssC(this.rs.getString("SssC"));
				cc.setNombreCuenta(this.rs.getString("nombreCuenta"));
				cc.setNivel(this.rs.getInt("nivel"));
				cc.setRubro(this.rs.getString("rubro"));
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
	            } catch (SQLException var10) {
	                var10.printStackTrace();
	            }

		}
		
		return listCuentaContable;
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
}
