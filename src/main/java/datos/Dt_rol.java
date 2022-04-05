package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_rol;

public class Dt_rol {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_rol() {
			
	}
	
	public void llenaRsRol(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.rol;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsRol = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR ROLES " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_rol> listaRolesActivos(){
		ArrayList<Tbl_rol> listRol = new ArrayList<Tbl_rol>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.rol WHERE estado <> 3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_rol rol = new Tbl_rol();
				
				rol.setIdRol(this.rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombreRol"));
				rol.setDescripcion(this.rs.getString("descripcion"));
				rol.setEstado(this.rs.getInt("estado"));
				
				listRol.add(rol);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR ROLES "+e.getMessage());
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
		
		return listRol;
	}
	
	public boolean addRol(Tbl_rol Rol){
		boolean guardado = false;
		try {
			c = poolConexion.getConnection();
			this.llenaRsRol(c);
			this.rsRol.moveToInsertRow();
			rsRol.updateString("descripcion", Rol.getDescripcion());
			rsRol.updateInt("estado", Rol.getEstado());
			
			rsRol.insertRow();
			rsRol.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR ROL: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsRol != null) {
					rsRol.close();
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
