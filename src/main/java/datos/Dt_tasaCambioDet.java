package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Tbl_tasaCambioDet;
import entidades.Vw_tasaCambioDetalle;

public class Dt_tasaCambioDet {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTCD = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public Dt_tasaCambioDet() {

	}

	// Metodo para llenar el ResultSet

	public void llenar_rsTasaCambioDet(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.tasacambiodetalle;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsTCD = this.ps.executeQuery();

		} catch (Exception e) {
			System.out.println("Datos: Error al en listar Tasa Cambio Detalle " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Metodo de listar asientoContable

	public ArrayList<Vw_tasaCambioDetalle> listarTasaCambioDetActivos() {
		ArrayList<Vw_tasaCambioDetalle> listTasaCambioDet = new ArrayList<Vw_tasaCambioDetalle>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_tasacambiodetalle WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();

			while (rs.next()) {
				Vw_tasaCambioDetalle tbltcd = new Vw_tasaCambioDetalle();
				
				tbltcd.setIdTasaCambioDetalle(this.rs.getInt("idTasaCambioDetalle"));
				tbltcd.setIdTasaCambio(this.rs.getInt("idTasaCambio"));
				tbltcd.setValor(this.rs.getFloat("valor"));

				String fecha = rs.getString("fecha");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
				tbltcd.setFecha(new java.sql.Date(date0.getTime()));

				tbltcd.setTipoCambio(this.rs.getFloat("tipoCambio"));
				tbltcd.setEstado(this.rs.getInt("estado"));
				listTasaCambioDet.add(tbltcd);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Tasa Cambio Detalle " + e.getMessage());
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

		return listTasaCambioDet;
	}

	public Tbl_tasaCambioDet obtenerTasaCambioDetById(int id) {
		Tbl_tasaCambioDet tcd = new Tbl_tasaCambioDet();
		try {
			c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.tasacambiodetalle WHERE idTasaCambioDetalle = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();

			if (rs.next()) {
				tcd.setIdTasaCambioDetalle(rs.getInt("idTasaCambioDetalle"));
				tcd.setIdTasaCambio(rs.getInt("idTasaCambio"));

				String fecha1 = rs.getString("fecha");
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
				tcd.setFecha(new java.sql.Date(date1.getTime()));

				tcd.setTipoCambio(rs.getFloat("tipoCambio"));
				tcd.setEstado(rs.getInt("estado"));
			}
		} catch (Exception e) {
			System.err.println("ERROR AL OBTENER Tasa Cambio Detalle POR ID" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsTCD != null) {
					rsTCD.close();
				}
				if (c != null) {
					poolConexion.closeConnection(c);
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tcd;
	}
}