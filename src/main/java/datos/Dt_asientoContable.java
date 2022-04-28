package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Vw_asientoContable;
import entidades.Vw_asientoContableDet;

public class Dt_asientoContable {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAsientoCon = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_asientoContable() {
		
	}
	
	//Metodo para llenar ResultSet
	
	public void llenar_rsAsientoCon(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.asientocontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsAsientoCon = this.ps.executeQuery();
		}catch(Exception e) {
			System.out.println("Datos: Error al enlista asiento Contable" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//Metodo de listar asientoContable
	
	public ArrayList<Vw_asientoContable> listarasientocontable(){
		ArrayList<Vw_asientoContable> listAsientoContable = new ArrayList<Vw_asientoContable>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_asientocontable;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_asientoContable tblAsientoContable = new Vw_asientoContable();
				tblAsientoContable.setIdAsientoContable(this.rs.getInt("idAsientoContable"));
				tblAsientoContable.setIdPeriodoContable(this.rs.getInt("idPeriodoContable"));
				
				String fechaIniPC = rs.getString("fechaInicio");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPC);
				tblAsientoContable.setFechaInicio(new java.sql.Date(date0.getTime()));
				
				String fechaFinPC = rs.getString("fechaFinal");
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPC);
				tblAsientoContable.setFechaFinal(new java.sql.Date(date1.getTime()));
				
				tblAsientoContable.setIdEmpresa(this.rs.getInt("idEmpresa"));
				tblAsientoContable.setNombreComercial(this.rs.getString("nombreComercial"));
				tblAsientoContable.setIdTipoDocumento(this.rs.getInt("idTipoDocumento"));
				tblAsientoContable.setTipo(this.rs.getString("tipo"));
				tblAsientoContable.setIdMoneda(this.rs.getInt("idMoneda"));
				tblAsientoContable.setNombre(this.rs.getString("nombre"));
				tblAsientoContable.setIdTasaCambioDetalle(this.rs.getInt("idTasaCambioDetalle"));
				tblAsientoContable.setTipoCambio(this.rs.getFloat("tipoCambio"));
				tblAsientoContable.setFecha(this.rs.getDate("fecha"));
				tblAsientoContable.setDescripcion(this.rs.getString("descripcion"));
				tblAsientoContable.setUsuarioCreacion(this.rs.getInt("usuarioCreacion"));
				tblAsientoContable.setFechaCreacion(this.rs.getDate("fechaCreacion"));
				tblAsientoContable.setUsuarioModificacion(this.rs.getInt("usuarioModificacion"));
				tblAsientoContable.setFechaModificacion(this.rs.getDate("fechaModificacion"));
				tblAsientoContable.setUsuarioEliminacion(this.rs.getInt("usuarioEliminacion"));
				tblAsientoContable.setFechaEliminacion(this.rs.getDate("fechaEliminacion"));
				
				listAsientoContable.add(tblAsientoContable);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Asiento Contable"+e.getMessage());
				e.printStackTrace();
			}
		 finally {
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
		
		return listAsientoContable;
	}
}
