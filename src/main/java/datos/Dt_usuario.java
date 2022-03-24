package datos;

import entidades.Tbl_usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dt_usuario {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_usuario() {
			
	}
	
	public void llenaRsUsuario(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_usuario;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsUsuario = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_usuario> listaUserActivos(){
		ArrayList<Tbl_usuario> listUser = new ArrayList<Tbl_usuario>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM sistemacontablebd.tbl_usuario WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_usuario user = new Tbl_usuario();
				user.setIdUsuario(this.rs.getInt("idUsuario"));
				user.setUsuario(this.rs.getString("usuario"));
				user.setPwd(this.rs.getString("pwd"));
				user.setNombre(this.rs.getString("nombres"));
				user.setApellidos(this.rs.getString("apellidos"));
				user.setEmail(this.rs.getString("email"));
				user.setEstado(this.rs.getInt("estado"));
				listUser.add(user);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR USUARIOS "+ e.getMessage());
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
		
		return listUser;
	}
	
	public boolean addUsuario(Tbl_usuario User){
		boolean guardado = false;
		try {
			c = poolConexion.getConnection();
			this.llenaRsUsuario(c);
			this.rsUsuario.moveToInsertRow();
			rsUsuario.updateString("usuario", User.getUsuario());
			rsUsuario.updateString("pwd", User.getPwd());
			rsUsuario.updateString("nombres", User.getNombre());
			rsUsuario.updateString("apellidos", User.getApellidos());
			rsUsuario.updateString("email", User.getEmail());
			rsUsuario.updateInt("estado", User.getEstado());
			
			rsUsuario.insertRow();
			rsUsuario.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR USUARIOS: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsUsuario != null) {
					rsUsuario.close();
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
