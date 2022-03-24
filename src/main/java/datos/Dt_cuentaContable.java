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
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.Vw_catalogo_tipo_cuentacontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsCuentaContable = this.ps.executeQuery();
		}
		catch(Exception var3)
		{
			System.out.println("DATOS: ERROR EN LISTAR CUENTA CONTABLE " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	//M�todo para listar cuentas contables
	public ArrayList<Vw_catalogo_tipo_cuentacontable> listaCuentasContables(){
		ArrayList<Vw_catalogo_tipo_cuentacontable> listCuentaContable = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM sistemacontablebd.Vw_catalogo_tipo_cuentacontable WHERE estado<>3;", 1005, 1007);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_catalogo_tipo_cuentacontable cc = new Vw_catalogo_tipo_cuentacontable();
				cc.setIdCuentaContable(this.rs.getInt("idCuentaContable"));
				cc.setNumeroCuenta(this.rs.getString("numeroCuenta"));
				cc.setsC(this.rs.getString("sC"));
				cc.setSsC(this.rs.getString("ssC"));
				cc.setSssC(this.rs.getString("sssC"));
				cc.setNombreCuenta(this.rs.getString("nombreCuenta"));
				cc.setRubro(this.rs.getString("rubro"));
				cc.setTipoCuenta(this.rs.getString("tipoCuenta"));
				cc.setCatalogoCuenta(this.rs.getString("catalogoCuenta"));
				cc.setEstado(this.rs.getInt("estado"));
				listCuentaContable.add(cc);
			}
			} catch(Exception var11) {
				System.out.println("DATOS: ERROR EN LISTAR CUENTAS CONTABLES "+var11.getMessage());
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
		
		return listCuentaContable;
	}
	
	//M�todo para agregar una cuenta contable
	
	public boolean agregarCuentaContable(Tbl_cuentaContable cc) {
		boolean guardado = false;
		try {
			c = poolConexion.getConnection();
			this.llenarRsCuentaContable(c);
			this.rsCuentaContable.moveToCurrentRow();
			
		}	catch (Exception e) {
			System.err.println("ERROR AL GUARDAR tbl_cuentacontable "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsCuentaContable != null){
					rsCuentaContable.close();
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