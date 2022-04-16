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
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public Dt_usuario() {

	}

	public void llenaRsUsuario(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.usuario;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsUsuario = this.ps.executeQuery();

		} catch (Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS " + var3.getMessage());
			var3.printStackTrace();
		}
	}

	public ArrayList<Tbl_usuario> listaUserActivos() {
		ArrayList<Tbl_usuario> listUser = new ArrayList<Tbl_usuario>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.usuario WHERE estado<>3;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();

			while (rs.next()) {
				Tbl_usuario user = new Tbl_usuario();
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setUsuario(rs.getString("usuario"));
				user.setPwd(rs.getString("password"));
				user.setNombre(rs.getString("nombre"));
				user.setApellidos(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setEstado(rs.getInt("estado"));
				listUser.add(user);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS " + e.getMessage());
			e.printStackTrace();
		} finally {
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

		return listUser;
	}

	public int addUsuario(Tbl_usuario User) {
		int guardado = 0;
		try {
			c = poolConexion.getConnection();
			this.llenaRsUsuario(c);
			rsUsuario.moveToInsertRow();
			rsUsuario.updateString("usuario", User.getUsuario());
			rsUsuario.updateString("nombre", User.getNombre());
			rsUsuario.updateString("apellido", User.getApellidos());
			rsUsuario.updateString("email", User.getEmail());
			rsUsuario.updateString("password", User.getPwd());
			rsUsuario.updateString("pwd_temp", User.getPwdTemp());
			rsUsuario.updateString("urlFoto", User.getUrlFoto());
			rsUsuario.updateString("codVerificacion", User.getCodVerificacion());
			rsUsuario.updateInt("usuarioCreacion", User.getUsuarioCreacion());
			rsUsuario.updateTimestamp("fechaCreacion", User.getFechaCreacion());
			rsUsuario.updateInt("estado", User.getEstado());
			rsUsuario.insertRow();
			rsUsuario.moveToCurrentRow();
			this.llenaRsUsuario(c);
			rsUsuario.last();
			guardado = rsUsuario.getInt("idUsuario");
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR USUARIOS: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsUsuario != null) {
					rsUsuario.close();
				}
				if (c != null) {
					poolConexion.closeConnection(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return guardado;
	}
	
	public Tbl_usuario ObtenerUsuarioPorId(int id) {
		Tbl_usuario user = new Tbl_usuario();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.usuario WHERE estado<>3 and idUsuario = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setUsuario(rs.getString("usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellidos(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setEstado(rs.getInt("estado"));
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS " + e.getMessage());
			e.printStackTrace();
		} finally {
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

		return user;
	}
	
	// Metodo para modificar usuario
		public boolean modificarUsuario(Tbl_usuario tus)
		{
			boolean modificado=false;	
			try
			{
				c = poolConexion.getConnection();
				llenaRsUsuario(c);
				rsUsuario.beforeFirst();
				while (rsUsuario.next())
				{
					if(rsUsuario.getInt(1)==tus.getIdUsuario())
					{
						rsUsuario.updateString("usuario", tus.getUsuario());
						rsUsuario.updateString("nombre", tus.getNombre());
						rsUsuario.updateString("apellido", tus.getApellidos());
						rsUsuario.updateString("email",tus.getEmail());
						rsUsuario.updateTimestamp("fechaModificacion", tus.getFechaModificacion());
						rsUsuario.updateInt("usuarioModificacion", tus.getUsuarioCreacion());
						rsUsuario.updateInt("estado", 2);
						rsUsuario.updateRow();
						modificado=true;
						break;
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("ERROR AL modificarUser() "+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				try {
					if(rsUsuario != null){
						rsUsuario.close();
					}
					if(c != null){
						poolConexion.closeConnection(c);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return modificado;
		}
		
		// Metodo para eliminar usuario
		public boolean eliminarUsuario(Tbl_usuario tus)
		{
			boolean eliminado=false;	
			try
			{
				c = poolConexion.getConnection();
				this.llenaRsUsuario(c);
				rsUsuario.beforeFirst();
				while (rsUsuario.next()){
					if(rsUsuario.getInt(1)==tus.getIdUsuario()){
						rsUsuario.updateTimestamp("fechaEliminacion", tus.getFechaEliminacion());
						rsUsuario.updateInt("usuarioEliminacion", tus.getUsuarioEliminacion());
						rsUsuario.updateInt("estado", 3);
						rsUsuario.updateRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e){
				System.err.println("ERROR AL eliminarUser() "+e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsUsuario != null){
						rsUsuario.close();
					}
					if(c != null){
						poolConexion.closeConnection(c);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return eliminado;
		}

	// METODO PARA GENERAR UN CODIGO DE VERIFICACION //
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
