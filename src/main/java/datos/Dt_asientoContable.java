package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Vw_asientoContable;

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
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_asientocontable;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsAsientoCon = this.ps.executeQuery();
		}catch(Exception e) {
			System.out.println("Datos: Error al enlista asiento Contable" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//Metodo de listar asientoContable
	
	public ArrayList<Vw_asientoContable> listarAsientoContable(){
		ArrayList<Vw_asientoContable> listAsientoContable = new ArrayList<Vw_asientoContable>();
	try{
		this.c = poolConexion.getConnection();
		this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.vw_asientocontable WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.rs = ps.executeQuery();
		while(rs.next()) {
			Vw_asientoContable tblAsientoContable = new Vw_asientoContable();
			tblAsientoContable.setIdAsientoContable(this.rs.getInt("idAsientoContable"));
			tblAsientoContable.setPeriodoContable(this.rs.getString("periodo contable"));
			tblAsientoContable.setNombreComercial(this.rs.getString("nombreComercial"));
			tblAsientoContable.setTipoCambio(this.rs.getDouble("tipoCambio"));
			tblAsientoContable.setTipo(this.rs.getString("tipo"));
			tblAsientoContable.setDebe(this.rs.getDouble("debe"));
			tblAsientoContable.setHaber(this.rs.getDouble("haber"));
			tblAsientoContable.setFecha(this.rs.getDate("fecha"));
			tblAsientoContable.setDescripcion(this.rs.getString("descripcion"));
			tblAsientoContable.setSaldo(this.rs.getDouble("saldo"));
			tblAsientoContable.setEstado(this.rs.getInt("estado"));
			listAsientoContable.add(tblAsientoContable);
		}
	}catch(Exception e){
		System.out.println("DATOS: ERROR EN LISTAR Tbl_asientoContable "+ e.getMessage());
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
	return listAsientoContable;
}
	
}
