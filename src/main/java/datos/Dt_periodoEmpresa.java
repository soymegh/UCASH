package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Tbl_periodoEmpresa;
import entidades.Vw_fiscalEmpresa;

public class Dt_periodoEmpresa {
	
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsFiscalEmpresa = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	Tbl_periodoEmpresa tablaFisclaEmpresa = new Tbl_periodoEmpresa();
	
	public Dt_periodoEmpresa() {}
	
	/**
	 * Llena el ResultSet de la tabla de Fiscal-Empresa
	 * @param c (La conexión)
	 */
	public void llenarRsFiscalEmpresa(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.periodoempresa;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsFiscalEmpresa = this.ps.executeQuery();
		}catch(Exception e) {
			System.out.println("Datos: Error al enlista asiento Contable" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista la tabla de empresas y su período fiscal
	 * @return ArrayList con los datos
	 */
	public ArrayList<Vw_fiscalEmpresa> listarFiscalEmpresa() {
		ArrayList<Vw_fiscalEmpresa> arFE = new ArrayList<>();
		
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_fiscalempresa;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while (this.rs.next()) {
				Vw_fiscalEmpresa fE = new Vw_fiscalEmpresa();
				
				fE.setIdEmpresa(rs.getInt("idEmpresa"));
			}
			
		} catch (Exception e) {
			System.err.println("Error al listar FiscalEmpresa: " + e.getMessage());
			e.printStackTrace();
		} finally {
			
		}
		
		return arFE;
	}

}
