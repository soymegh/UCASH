package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Tbl_empresa;
import entidades.Tbl_periodoEmpresa;

public class Dt_periodoEmpresa {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPeriodoEmpresa = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_periodoEmpresa() {
		
	}

	
	// Metodo para llenar el ResultSet
	public void llenar_rsPeriodoEmpresa(Connection c) {
		try {
			ps = c.prepareStatement("Select * from dbucash.periodoEmpresa;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPeriodoEmpresa = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR PERIODO EMPRESA " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean addPeriodoEmpresa(Tbl_periodoEmpresa periodoEmpresa) {
		boolean guardado = false;

		try {
			c = poolConexion.getConnection();
			this.llenar_rsPeriodoEmpresa(c);
			this.rsPeriodoEmpresa.moveToInsertRow();

			rsPeriodoEmpresa.updateInt("idPeriodoEmpresa", periodoEmpresa.getIdPeriodoEmpresa());
			rsPeriodoEmpresa.updateInt("idPeriodoFiscal", periodoEmpresa.getIdPeriodoFiscal());
			rsPeriodoEmpresa.updateInt("idEmpresa", periodoEmpresa.getIdEmpresa());

			rsPeriodoEmpresa.insertRow();
			rsPeriodoEmpresa.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR PERIODO EMPRESA: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsPeriodoEmpresa != null) {
					rsPeriodoEmpresa.close();
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
	
	public boolean modificarPeriodoEmpresa(Tbl_periodoEmpresa periodoEmpresa) {
		boolean modificado = false;
		try {
			c = poolConexion.getConnection();
			this.llenar_rsPeriodoEmpresa(c);
			rsPeriodoEmpresa.beforeFirst();
			while (rsPeriodoEmpresa.next()) {
				if (rsPeriodoEmpresa.getInt(1) == periodoEmpresa.getIdPeriodoEmpresa()) {
					rsPeriodoEmpresa.updateInt("idEmpresa", periodoEmpresa.getIdEmpresa());
					rsPeriodoEmpresa.updateInt("idPeriodoFiscal", periodoEmpresa.getIdPeriodoFiscal());

					rsPeriodoEmpresa.updateRow();
					modificado = true;
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR AL  modificar Periodo Empresa() " + e.getMessage());
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
		return modificado;
	}
	
	
	public Tbl_periodoEmpresa getTablePeriodoEmpresaByIdEmpresa(int idEmpresa) {
		Tbl_periodoEmpresa periodoEmpresa = new Tbl_periodoEmpresa();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.periodoEmpresa WHERE idEmpresa =?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idEmpresa);
			rs = ps.executeQuery();

			// Hace peticion a la base de datos, por lo que los nombres en parentesis son
			// los de la base de datos.
			if (rs.next()) {
				periodoEmpresa.setIdPeriodoEmpresa(rs.getInt("idPeriodoEmpresa"));
				periodoEmpresa.setIdEmpresa(rs.getInt("idEmpresa"));
				periodoEmpresa.setIdPeriodoFiscal(rs.getInt("idPeriodoFiscal")); 
	
				
				System.out.println(periodoEmpresa); 
			}

		} catch (Exception e) {
			System.out.println("DATOS ERROR AL OBTENER TABLA PERIODO EMPRESA POR ID: " + e.getMessage());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return periodoEmpresa;

	}
}