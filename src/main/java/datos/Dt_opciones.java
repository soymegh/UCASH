package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_opciones;

public class Dt_opciones {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsOpc = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_opciones() {
			
	}
	
	public void llenaRsOpciones(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_opciones;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsOpc = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR OPCIONES " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_opciones> listaOpcionesActivas(){
		ArrayList<Tbl_opciones> listOpc = new ArrayList<Tbl_opciones>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_opciones WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_opciones opc = new Tbl_opciones();
				opc.setIdOpciones(this.rs.getInt("idOpciones"));
				opc.setDescripcion(this.rs.getString("descripcion"));
				opc.setEstado(this.rs.getInt("estado"));
				listOpc.add(opc);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR OPCIONES "+e.getMessage());
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
		
		return listOpc;
	}
	
	public boolean addOpciones(Tbl_opciones Opciones) {
		boolean guardado = false;
		try {
			c = poolConexion.getConnection();
			this.llenaRsOpciones(c);
			this.rsOpc.moveToInsertRow();
			rsOpc.updateString("descripcion", Opciones.getDescripcion());
			rsOpc.updateInt("estado", Opciones.getEstado());
			
			rsOpc.insertRow();
			rsOpc.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR OPCIONES: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsOpc != null) {
					rsOpc.close();
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
