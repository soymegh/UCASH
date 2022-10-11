package datos;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.HistoricoSaldos;
import entidades.Tbl_usuario;

public class Dt_historicoSaldos {
	
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsHistoricoSaldos = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_historicoSaldos() {

	}

	public void llenaRsHistoricoSaldos(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.historico_movimientos;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsHistoricoSaldos = this.ps.executeQuery();

		} catch (Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<HistoricoSaldos> listarHistorico() {
		ArrayList<HistoricoSaldos> lista = new ArrayList<HistoricoSaldos>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.historico_movimientos;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();

			while (rs.next()) {
				HistoricoSaldos hys = new HistoricoSaldos();
				hys.setIdHistorico(rs.getInt("idhistorico_saldos"));
				hys.setIdEmpresa(rs.getInt("idEmpresa"));
				hys.setIdCatalogo(rs.getInt("idCatalogo"));
				hys.setIdCuenta(rs.getInt("idCuenta"));
				hys.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				hys.setIdSupTipo(rs.getInt("idSup_Tipo"));
				hys.setNombreSubTipo(rs.getString("nombreSupTipo"));
				hys.setNombreEmpresa(rs.getString("nombreEmpresa"));
				hys.setNombreCuenta(rs.getString("nombreCuenta"));
				hys.setSaldoInicial(rs.getDouble("saldoInicial"));
				hys.setSaldoFinal(rs.getDouble("saldoFinal"));
				hys.setDebe(rs.getDouble("debe"));
				hys.setHaber(rs.getDouble("haber"));
				hys.setFecha(rs.getString("fecha"));
				lista.add(hys);
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
	
	public int addHistorico(HistoricoSaldos hys) {
		int guardado = 0;
		try {
			c = poolConexion.getConnection();
			this.llenaRsHistoricoSaldos(c);
			rsHistoricoSaldos.moveToInsertRow();
			rsHistoricoSaldos.updateInt("idEmpresa", hys.getIdEmpresa());
			rsHistoricoSaldos.updateInt("idCatalogo", hys.getIdCatalogo());
			rsHistoricoSaldos.updateInt("idCuenta", hys.getIdCuenta());
			rsHistoricoSaldos.updateInt("idTipoCuenta", hys.getIdTipoCuenta());
			rsHistoricoSaldos.updateInt("idSup_Tipo", hys.getIdSupTipo());
			rsHistoricoSaldos.updateString("nombreSubTipo", hys.getNombreSubTipo());
			rsHistoricoSaldos.updateString("nombreEmpresa", hys.getNombreEmpresa());
			rsHistoricoSaldos.updateString("nombreCuenta", hys.getNombreCuenta());
			rsHistoricoSaldos.updateDouble("saldoInicial", hys.getSaldoInicial());
			rsHistoricoSaldos.updateDouble("saldoFinal", hys.getSaldoFinal());
			rsHistoricoSaldos.updateDouble("debe", hys.getDebe());
			rsHistoricoSaldos.updateDouble("haber", hys.getHaber());
			rsHistoricoSaldos.updateString("fecha", hys.getFecha());
			rsHistoricoSaldos.insertRow();
			rsHistoricoSaldos.moveToCurrentRow();
			this.llenaRsHistoricoSaldos(c);
			rsHistoricoSaldos.last();
			guardado = rsHistoricoSaldos.getInt("idhistorico_movimientos");
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR USUARIOS: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsHistoricoSaldos != null) {
					rsHistoricoSaldos.close();
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
	
	public HistoricoSaldos ObtenerHistorico(int idCuenta, int idEmpresa, String fecha) {
		HistoricoSaldos hys = new HistoricoSaldos();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.historico_movimientos WHERE idCuenta = ? and idEmpresa = ? and fecha = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idCuenta);
			ps.setInt(2, idEmpresa);
			ps.setString(3, fecha);
			rs = ps.executeQuery();

			while (rs.next()) {
				hys.setIdHistorico(rs.getInt("idhistorico_movimientos"));
				hys.setIdEmpresa(rs.getInt("idEmpresa"));
				hys.setIdCatalogo(rs.getInt("idCatalogo"));
				hys.setIdCuenta(rs.getInt("idCuenta"));
				hys.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				hys.setIdSupTipo(rs.getInt("idSub_Tipo"));
				hys.setNombreSubTipo(rs.getString("nombreSubTipo"));
				hys.setNombreEmpresa(rs.getString("nombreEmpresa"));
				hys.setNombreCuenta(rs.getString("nombreCuenta"));
				hys.setSaldoInicial(rs.getDouble("saldoInicial"));
				hys.setSaldoFinal(rs.getDouble("saldoFinal"));
				hys.setDebe(rs.getDouble("debe"));
				hys.setHaber(rs.getDouble("haber"));
				hys.setFecha(rs.getString("fecha"));
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

		return hys;
	}
	
	public ArrayList<HistoricoSaldos> ObtenerHistoricoFechas(int idEmpresa) {
		ArrayList<HistoricoSaldos> fechas = new ArrayList<HistoricoSaldos>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT DISTINCT fecha, idhistorico_movimientos FROM dbucash.historico_movimientos WHERE idEmpresa = ? GROUP BY fecha ORDER BY fecha ASC;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idEmpresa);
			rs = ps.executeQuery();

			while (rs.next()) {
				HistoricoSaldos hys = new HistoricoSaldos();
				hys.setIdHistorico(rs.getInt("idhistorico_movimientos"));
				hys.setFecha(rs.getString("fecha"));
				fechas.add(hys);
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

		return fechas;
	}
	
	public HistoricoSaldos ObtenerFechaExacta(int idHistorico) {
		HistoricoSaldos hys = new HistoricoSaldos();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT distinct fecha FROM dbucash.historico_movimientos WHERE idhistorico_movimientos = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idHistorico);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				hys.setFecha(rs.getString("fecha"));
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

		return hys;
	}
	
	public HistoricoSaldos ObtenerCuentaHistorico(int idCuenta, int idEmpresa, String fecha) {
		HistoricoSaldos hys = new HistoricoSaldos();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_historico WHERE idCuenta = ? and idEmpresa = ? and fecha = ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idCuenta);
			ps.setInt(2, idEmpresa);
			ps.setString(3, fecha);
			rs = ps.executeQuery();

			while (rs.next()) {
				hys.setIdHistorico(rs.getInt("idhistorico_movimientos"));
				hys.setIdCuenta(rs.getInt("idCuenta"));
				//hys.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				hys.setIdSupTipo(rs.getInt("id"));
				hys.setNombreCuenta(rs.getString("nombreCuenta"));
				hys.setSaldoInicial(rs.getDouble("saldoInicial"));
				hys.setSaldoFinal(rs.getDouble("saldoFinal"));
				hys.setDebe(rs.getDouble("debe"));
				hys.setHaber(rs.getDouble("haber"));
				hys.setFecha(rs.getString("fecha"));
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

		return hys;
	}
}
