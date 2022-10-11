package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.HistoricoSaldos;
import entidades.SubCategoria;

public class Dt_SubTipoCategoria {
	
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsSubCategoria = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_SubTipoCategoria() {

	}

	public void llenaRsHistoricoSaldos(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.subtipo_categoria;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsSubCategoria = this.ps.executeQuery();

		} catch (Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<SubCategoria> listarSubCategoria() {
		ArrayList<SubCategoria> lista = new ArrayList<SubCategoria>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.subtipo_categoria;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();

			while (rs.next()) {
				SubCategoria sub = new SubCategoria();
				sub.setIdSupTipo(rs.getInt("id"));
				sub.setNombreSupTipo(rs.getString("nombre"));
				lista.add(sub);
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

		return lista;
	}
	
	public SubCategoria ObtenerSubCategoria(int idSubTipo) {
		SubCategoria sub = new SubCategoria();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.subtipo_categoria WHERE id = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idSubTipo);
			rs = ps.executeQuery();

			while (rs.next()) {
				sub.setIdSupTipo(rs.getInt("id"));
				sub.setNombreSupTipo(rs.getString("nombre"));
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

		return sub;
	}
}
