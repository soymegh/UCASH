package datos;

import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_representanteLegal;
import entidades.Vw_representanteLegal;

public class Dt_representanteLegal {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private ResultSet rsRepresentantel = null; 
	private PreparedStatement ps = null;
	
	public Dt_representanteLegal() {
			
	}
	
	public void llenaRsRepresentanteLegal(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.representanteLegal;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsRepresentantel = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR REPRESENTANTELEGAL " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Vw_representanteLegal> listarRepresentanteLegal(){
		ArrayList<Vw_representanteLegal> listRL = new ArrayList<Vw_representanteLegal>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.vw_representantelegal WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_representanteLegal RL = new Vw_representanteLegal();
				
				RL.setIdRepresentante(rs.getInt("idRepresentante"));
				RL.setNombreCompleto(rs.getString("nombre Completo"));
				RL.setTipo(rs.getString("tipo"));
				RL.setCorreo(rs.getString("correo"));
				RL.setTelefono(rs.getString("telefono"));
				RL.setEstado(rs.getInt("estado"));
				
				
				listRL.add(RL);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR REPRESENTANTELEGAL "+e.getMessage());
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
		
		return listRL;
	}
	
	public boolean addRepresentanteLegal(Tbl_representanteLegal representante) { 
		boolean guardado = false;
		
		try {
			c = poolConexion.getConnection(); 
			this.llenaRsRepresentanteLegal(c);
			this.rsRepresentantel.moveToInsertRow();
			
			
			rsRepresentantel.updateInt("idRepresentante", representante.getIdRepresentante());
			rsRepresentantel.updateInt("idTipoIdentifiacion", representante.getIdTipoIdentifiacion());
			rsRepresentantel.updateString("nombre", representante.getNombre());
			rsRepresentantel.updateString("apellido", representante.getApellido());
			rsRepresentantel.updateString("telefono", representante.getTelefono());
			rsRepresentantel.updateString("correo", representante.getCorreo());
			rsRepresentantel.updateInt("estado", representante.getEstado());

			rsRepresentantel.insertRow(); 
			rsRepresentantel.moveToCurrentRow();
			guardado = true; 
			
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR REPRESENTANTE LEGAL: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (c != null) {
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
