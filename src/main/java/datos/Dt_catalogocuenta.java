package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_catalogocuenta;
import entidades.Tbl_empresa;
import entidades.Tbl_municipio;
import entidades.Vw_catalogocuenta_empresa;
import entidades.Vw_empresa;

public class Dt_catalogocuenta {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCatalogocuenta = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Metodo para llenar el ResultSet
	public void llenar_rsCatalogo(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM dbucash.catalogocuenta;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCatalogocuenta = ps.executeQuery();
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR catalogocuenta" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para listar catalogo cuenta
	public ArrayList<Vw_catalogocuenta_empresa> listarCatalogocuenta(){
		
		ArrayList<Vw_catalogocuenta_empresa> listCatalogocuenta = new ArrayList<Vw_catalogocuenta_empresa>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_catalogocuenta_empresa;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Vw_catalogocuenta_empresa tblCatalogocuenta = new Vw_catalogocuenta_empresa();
			tblCatalogocuenta.setIdCatalogo(rs.getInt("idCatalogo"));
			tblCatalogocuenta.setIdEmpresa(rs.getInt("idEmpresa"));
			tblCatalogocuenta.setnombreComercial(rs.getString("nombreComercial"));
			tblCatalogocuenta.setTitulo(rs.getString("titulo"));
			tblCatalogocuenta.setDescripcion(rs.getString("descripcion"));
			tblCatalogocuenta.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
			tblCatalogocuenta.setFechaCreacion(rs.getDate("fechaCreacion"));
			tblCatalogocuenta.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
			tblCatalogocuenta.setFechaModificacion(rs.getDate("fechaModificacion"));
			tblCatalogocuenta.setUsuarioEliminacion(rs.getInt("usuarioEliminacion"));
			tblCatalogocuenta.setFechaEliminacion(rs.getDate("fechaEliminacion"));
				

				listCatalogocuenta.add(tblCatalogocuenta);
			}
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR  "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					poolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listCatalogocuenta;
	}
	
	//Metodo para listar catalogocuenta conforme a empresa
	public ArrayList<Vw_catalogocuenta_empresa> listarCatalogocuentaDeEmpresa(int idEmpresa) {

		ArrayList<Vw_catalogocuenta_empresa> listCatalogocuenta = new ArrayList<Vw_catalogocuenta_empresa>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_catalogocuenta_empresa WHERE idEmpresa = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idEmpresa);
			rs = ps.executeQuery();
			while (rs.next()) {
				Vw_catalogocuenta_empresa tblCatalogocuenta = new Vw_catalogocuenta_empresa();
				tblCatalogocuenta.setIdCatalogo(rs.getInt("idCatalogo"));
				tblCatalogocuenta.setIdEmpresa(rs.getInt("idEmpresa"));
				tblCatalogocuenta.setnombreComercial(rs.getString("nombreComercial"));
				tblCatalogocuenta.setTitulo(rs.getString("titulo"));
				tblCatalogocuenta.setDescripcion(rs.getString("descripcion"));
				tblCatalogocuenta.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
				tblCatalogocuenta.setFechaCreacion(rs.getDate("fechaCreacion"));
				tblCatalogocuenta.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
				tblCatalogocuenta.setFechaModificacion(rs.getDate("fechaModificacion"));
				tblCatalogocuenta.setUsuarioEliminacion(rs.getInt("usuarioEliminacion"));
				tblCatalogocuenta.setFechaEliminacion(rs.getDate("fechaEliminacion"));
				listCatalogocuenta.add(tblCatalogocuenta);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR  " + e.getMessage());
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
		return listCatalogocuenta;
	}
	
	
	// Metodo para agregar un catalogo de cuenta
	public boolean addCatalogocuenta(Tbl_catalogocuenta catalogoC) {
		boolean guardado = false;			
		
		try {
            c = poolConexion.getConnection();
            this.llenar_rsCatalogo(c);
            this.rsCatalogocuenta.moveToInsertRow();            

            rsCatalogocuenta.updateInt("IdCatalogo", catalogoC.getIdCatalogo());
            rsCatalogocuenta.updateInt("idEmpresa", catalogoC.getIdEmpresa());
            rsCatalogocuenta.updateString("titulo", catalogoC.getTitulo());
            rsCatalogocuenta.updateString("descripcion", catalogoC.getDescripcion());
            rsCatalogocuenta.updateDate("fecha", catalogoC.getFecha());
            rsCatalogocuenta.updateInt("usuarioCreacion", catalogoC.getUsuarioCreacion());
            rsCatalogocuenta.updateDate("fechaCreacion", catalogoC.getFechaCreacion());

            rsCatalogocuenta.insertRow();
            rsCatalogocuenta.moveToCurrentRow();
            guardado = true;
            
            } catch (Exception e) {
			System.err.println("ERROR AL GUARDAR CATALOGO DE CUENTA: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsCatalogocuenta != null) {
					rsCatalogocuenta.close();
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

		//Obtener el ID del Catalogo
		//sujeto a cambios por nombres de entidades de la view registrada en la base de datos
		public Vw_catalogocuenta_empresa getCatalogoByID(int idCatalogo) {
			Vw_catalogocuenta_empresa catalogo = new Vw_catalogocuenta_empresa();
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM dbucash.vw_catalogocuenta_empresa WHERE idCatalogo =?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idCatalogo);
				rs = ps.executeQuery();

				// Hace peticion a la base de datos, por lo que los nombres en parentesis son los de la base de datos
				if (rs.next()) {
					catalogo.setIdCatalogo(rs.getInt("idCatalogo"));
					catalogo.setIdEmpresa(rs.getInt("idEmpresa"));
					catalogo.setnombreComercial(rs.getString("nombreComercial"));
					catalogo.setTitulo(rs.getString("titulo"));
					catalogo.setDescripcion(rs.getString("descripcion"));
					catalogo.setFechaCreacion(rs.getDate("fechaCreacion"));
					catalogo.setFechaModificacion(rs.getDate("fechaModificacion"));
					catalogo.setFechaEliminacion(rs.getDate("fechaEliminacion"));
					catalogo.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
					catalogo.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
					catalogo.setUsuarioEliminacion(rs.getInt("usuarioEliminacion"));

				}

			} catch (Exception e) {
				System.out.println("DATOS ERROR AL OBTENER CATALOGO DE CUENTA POR ID: " + e.getMessage());
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
			return catalogo;
		}
		
		//obtener tabla de catalogo de cuenta por ID
		public Tbl_catalogocuenta getTableCatalogocuentaByID(int idCatalogo) {
			Tbl_catalogocuenta catalogoc = new Tbl_catalogocuenta();
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM dbucash.catalogocuenta WHERE IdCatalogo =?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idCatalogo);
				rs = ps.executeQuery();

				// Hace peticion a la base de datos, por lo que los nombres en parentesis son los de la base de datos
				if (rs.next()) {
					catalogoc.setIdCatalogo(rs.getInt("IdCatalogo"));
					catalogoc.setIdEmpresa(rs.getInt("idEmpresa"));
					catalogoc.setTitulo(rs.getString("titulo"));
					catalogoc.setDescripcion(rs.getString("descripcion"));
					catalogoc.setFecha(rs.getDate("fecha"));
					catalogoc.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
					catalogoc.setFechaCreacion(rs.getDate("fechaCreacion"));
					catalogoc.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
					catalogoc.setFechaModificacion(rs.getDate("fechaModificacion"));
					catalogoc.setUsuarioEliminacion(rs.getInt("usuarioEliminacion"));
					catalogoc.setFechaEliminacion(rs.getDate("fechaEliminacion"));
					
					System.out.println(catalogoc); 
				}

			} catch (Exception e) {
				System.out.println("DATOS ERROR AL OBTENER TABLA CATALOGO DE CUENTA POR ID: " + e.getMessage());
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
			return catalogoc;
		}
		
		//metodo para editar un catalogo de cuenta
		public boolean modificarCatalogo(Tbl_catalogocuenta catalogo) {
			boolean modificado = false;
			try {
				c = poolConexion.getConnection();
				this.llenar_rsCatalogo(c);
				rsCatalogocuenta.beforeFirst();
				while (rsCatalogocuenta.next()) {
					if (rsCatalogocuenta.getInt(1) == catalogo.getIdCatalogo()) {
						rsCatalogocuenta.updateInt("idEmpresa", catalogo.getIdEmpresa());
						rsCatalogocuenta.updateString("titulo", catalogo.getTitulo());
						rsCatalogocuenta.updateString("descripcion", catalogo.getDescripcion());
						rsCatalogocuenta.updateInt("usuarioModificacion", catalogo.getUsuarioModificacion());
						rsCatalogocuenta.updateDate("fechaModificacion", catalogo.getFechaModificacion());

						rsCatalogocuenta.updateRow();
						modificado = true;
						break;
					}
				}
			} catch (Exception e) {
				System.err.println("ERROR AL  modificar Catalogocuenta " + e.getMessage());
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
		
		//eliminar un catalogo de cuenta
		public boolean deleteCatalogo(Tbl_catalogocuenta tcatalogoC)
		{
			boolean eliminado=false;	
			try
			{
				c = poolConexion.getConnection();
				this.llenar_rsCatalogo(c);
				rsCatalogocuenta.beforeFirst();
				while (rsCatalogocuenta.next()){
					if(rsCatalogocuenta.getInt(1)==tcatalogoC.getIdCatalogo()){
						rsCatalogocuenta.deleteRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e){
				System.err.println("ERROR AL ELIMINAR CATALOGO "+e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsCatalogocuenta != null){
						rsCatalogocuenta.close();
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
		
		public Vw_catalogocuenta_empresa getCatalogoByIdEmpresa(int idEmpresa) {
			Vw_catalogocuenta_empresa catalogo = new Vw_catalogocuenta_empresa();
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM dbucash.vw_catalogocuenta_empresa WHERE idEmpresa =?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idEmpresa);
				rs = ps.executeQuery();

				// Hace peticion a la base de datos, por lo que los nombres en parentesis son los de la base de datos
				if (rs.next()) {
					catalogo.setIdCatalogo(rs.getInt("idCatalogo"));
					catalogo.setIdEmpresa(rs.getInt("idEmpresa"));
					catalogo.setTitulo(rs.getString("titulo"));
					catalogo.setnombreComercial(rs.getString("nombreComercial"));
					catalogo.setDescripcion(rs.getString("descripcion"));
					catalogo.setFechaCreacion(rs.getDate("fechaCreacion"));
					catalogo.setFechaModificacion(rs.getDate("fechaModificacion"));
					catalogo.setFechaEliminacion(rs.getDate("fechaEliminacion"));
					catalogo.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
					catalogo.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
					catalogo.setUsuarioEliminacion(rs.getInt("usuarioEliminacion"));

				}

			} catch (Exception e) {
				System.out.println("DATOS ERROR AL OBTENER CATALOGO DE CUENTA POR ID EMPRESA: " + e.getMessage());
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
			return catalogo;
		}		
				
		
		
		//filtrar catalogos unicos por empresa
		public ArrayList<Vw_empresa> getTableEmpresaNoCatalogoCuenta() {

			ArrayList<Vw_empresa> listEmpresa = new ArrayList<Vw_empresa>();
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM vw_empresa WHERE idEmpresa NOT IN (SELECT idEmpresa FROM catalogocuenta);", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery();
				while (rs.next()) {
					Vw_empresa tblEmpresa = new Vw_empresa();
					tblEmpresa.setIdEmpresa(rs.getInt("idEmpresa"));
					tblEmpresa.setRuc(rs.getString("ruc"));
					tblEmpresa.setRazonSocial(rs.getString("razonSocial"));
					tblEmpresa.setNombreComercial(rs.getString("nombreComercial"));
					tblEmpresa.setTelefono(rs.getString("telefono"));
					tblEmpresa.setCorreo(rs.getString("correo"));
					tblEmpresa.setDireccion(rs.getString("direccion"));
					tblEmpresa.setRepresentante(rs.getString("Representante"));
					tblEmpresa.setDepartamentoNombre(rs.getString("departamento"));
					tblEmpresa.setMunicipioNombre(rs.getString("municipio"));
					tblEmpresa.setPeriodoFiscal(rs.getString("periodoFiscal"));
					listEmpresa.add(tblEmpresa);
				}
			} catch (Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR tbl_empresa " + e.getMessage());
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
			return listEmpresa;

		}
		
}