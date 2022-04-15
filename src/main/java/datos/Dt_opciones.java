package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_opciones;
import entidades.Tbl_usuario;

public class Dt_opciones {

	poolConexion pc = poolConexion.getInstance();
	Connection connection = null;
	private ResultSet rsOpc = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_opciones() {
			
	}
	
	public void llenaRsOpciones(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.opciones;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsOpc = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR OPCIONES " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_opciones> listaOpcionesActivas(){
		ArrayList<Tbl_opciones> listOpc = new ArrayList<Tbl_opciones>();
		try {
			connection = poolConexion.getConnection();
			ps = connection.prepareStatement("SELECT * FROM dbucash.opciones WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Tbl_opciones opc = new Tbl_opciones();
				opc.setIdOpciones(rs.getInt("idOpciones"));
				opc.setNombreOpcion(rs.getString("nombreOpcion"));
				opc.setDescripcion(rs.getString("descripcion"));
				opc.setEstado(rs.getInt("estado"));
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

	                if (this.connection != null) {
	                    poolConexion.closeConnection(this.connection);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

		}
		
		return listOpc;
	}
	
	public Tbl_opciones obtenerOpcionPorId(int id) {
		Tbl_opciones user = new Tbl_opciones();
		try {
			connection = poolConexion.getConnection();
			ps = connection.prepareStatement("SELECT * FROM dbucash.opciones WHERE estado<>3 and idOpciones= ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setIdOpciones(rs.getInt("idOpciones"));
				user.setNombreOpcion(rs.getString("nombreOpcion"));
				user.setDescripcion(rs.getString("descripcion"));
				System.out.println(user.toString());
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR OPCIONES " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}

				if (connection != null) {
					poolConexion.closeConnection(connection);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return user;
	}
	
	
	public boolean addOpciones(Tbl_opciones opciones) {
		boolean guardado = false;
		try {
			connection = poolConexion.getConnection();
			this.llenaRsOpciones(connection);
			this.rsOpc.moveToInsertRow();
			
			rsOpc.updateString("nombreOpcion", opciones.getNombreOpcion());
			rsOpc.updateString("descripcion", opciones.getDescripcion());
			rsOpc.updateInt("estado", opciones.getEstado());
			
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
				if(connection != null) {
					poolConexion.closeConnection(connection);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return guardado;
	}

}
