package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_empresa;
import entidades.Vw_empresa;

public class Dt_empresa {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsEmpresa = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public Dt_empresa() {

	}

	// Metodo para llenar el ResultSet
	public void llenar_rsEmpresa(Connection c) {
		try {
			ps = c.prepareStatement("Select * from dbucash.empresa;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsEmpresa = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR EMPRESA " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Metodo para listar la empresa
	// Sujeto a cambio el Tbl_empresa por algun View
	public ArrayList<Vw_empresa> listarEmpresa() {

		ArrayList<Vw_empresa> listEmpresa = new ArrayList<Vw_empresa>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_empresa; ", ResultSet.TYPE_SCROLL_SENSITIVE,
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
	
	public String getNombreEmpresaPorId(int idEmpresa) {
		String nombre = "";

		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT nombreComercial FROM dbucash.empresa WHERE idEmpresa = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idEmpresa);
			rs = ps.executeQuery();

			if (rs.next())
				nombre = rs.getString("nombreComercial");
		} catch (Exception e) {
			System.out.println("DATOS ERROR AL OBTENER NOMBRE DE EMPRESA POR ID: " + e.getMessage());
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

		return nombre;
	}

	// Metodo para agregar una empresa
	public boolean addEmpresa(Tbl_empresa empresa) {
		boolean guardado = false;

		try {
			c = poolConexion.getConnection();
			this.llenar_rsEmpresa(c);
			this.rsEmpresa.moveToInsertRow();

			rsEmpresa.updateInt("idEmpresa", empresa.getIdEmpresa());
			rsEmpresa.updateString("ruc", empresa.getRuc());
			rsEmpresa.updateString("razonSocial", empresa.getRazonSocial());
			rsEmpresa.updateString("nombreComercial", empresa.getNombreComercial());
			rsEmpresa.updateString("telefono", empresa.getTelefono());
			rsEmpresa.updateString("correo", empresa.getCorreo());
			rsEmpresa.updateString("direccion", empresa.getDireccion());
			rsEmpresa.updateInt("idRepresentante", empresa.getIdRepresentanteLegal());
			rsEmpresa.updateInt("idDepartamento", empresa.getIdDepartamento());
			rsEmpresa.updateInt("idMunicipio", empresa.getIdMunicipio());
			rsEmpresa.updateInt("idPeriodoFiscal", empresa.getIdPeriodoFiscal());
			rsEmpresa.updateDate("fechaCreacion", empresa.getFechaCreacion());
			rsEmpresa.updateInt("usuarioCreacion", empresa.getUsuarioCreacion());

			rsEmpresa.insertRow();
			rsEmpresa.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR EMPRESA: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsEmpresa != null) {
					rsEmpresa.close();
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

	public int getRepresentantelegalID(String correo) {

		return 0;
	}

	public Vw_empresa getEmpresaByID(int idEmpresa) {
		Vw_empresa empresa = new Vw_empresa();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_empresa WHERE idEmpresa =?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idEmpresa);
			rs = ps.executeQuery();

			// Hace peticion a la base de datos, por lo que los nombres en parentesis son
			// los de la base de datos.
			if (rs.next()) {
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setRuc(rs.getString("ruc"));
				empresa.setRazonSocial(rs.getString("razonSocial"));
				empresa.setNombreComercial(rs.getString("nombreComercial"));
				empresa.setTelefono(rs.getString("telefono"));
				empresa.setCorreo(rs.getString("correo"));
				empresa.setDireccion(rs.getString("direccion"));
				empresa.setPeriodoFiscal(rs.getString("periodoFiscal"));
				empresa.setRepresentante(rs.getString("Representante"));
				empresa.setMunicipioNombre(rs.getString("municipio"));
				empresa.setDepartamentoNombre(rs.getString("departamento"));

			}

		} catch (Exception e) {
			System.out.println("DATOS ERROR AL OBTENER EMPRESA POR ID: " + e.getMessage());
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
		return empresa;

	}

	public Tbl_empresa getTableEmpresaByID(int idEmpresa) {
		Tbl_empresa empresa = new Tbl_empresa();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.empresa WHERE idEmpresa =?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idEmpresa);
			rs = ps.executeQuery();

			// Hace peticion a la base de datos, por lo que los nombres en parentesis son
			// los de la base de datos.
			if (rs.next()) {
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setIdDepartamento(rs.getInt("idDepartamento"));
				empresa.setIdMunicipio(rs.getInt("idMunicipio"));
				empresa.setIdRepresentanteLegal(rs.getInt("idRepresentante"));
				empresa.setIdPeriodoFiscal(rs.getInt("idPeriodoFiscal"));
				empresa.setRuc(rs.getString("ruc"));
				empresa.setRazonSocial(rs.getString("razonSocial"));
				empresa.setNombreComercial(rs.getString("nombreComercial"));
				empresa.setTelefono(rs.getString("telefono"));
				empresa.setCorreo(rs.getString("correo"));
				empresa.setDireccion(rs.getString("direccion"));
				
				System.out.println(empresa); 
			}

		} catch (Exception e) {
			System.out.println("DATOS ERROR AL OBTENER TABLA EMPRESA POR ID: " + e.getMessage());
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
		return empresa;

	}

	public boolean modificarEmpresa(Tbl_empresa empresa) {
		boolean modificado = false;
		try {
			c = poolConexion.getConnection();
			this.llenar_rsEmpresa(c);
			rsEmpresa.beforeFirst();
			while (rsEmpresa.next()) {
				if (rsEmpresa.getInt(1) == empresa.getIdEmpresa()) {
					rsEmpresa.updateInt("idDepartamento", empresa.getIdDepartamento());
					rsEmpresa.updateInt("idMunicipio", empresa.getIdMunicipio());
					rsEmpresa.updateInt("idRepresentante", empresa.getIdRepresentanteLegal());
					rsEmpresa.updateInt("idPeriodoFiscal", empresa.getIdPeriodoFiscal());
					rsEmpresa.updateString("ruc", empresa.getRuc());
					rsEmpresa.updateString("razonSocial", empresa.getRazonSocial());
					rsEmpresa.updateString("nombreComercial", empresa.getNombreComercial());
					rsEmpresa.updateString("telefono", empresa.getTelefono());
					rsEmpresa.updateString("correo", empresa.getCorreo());
					rsEmpresa.updateString("direccion", empresa.getDireccion());
					rsEmpresa.updateInt("usuarioModificacion", empresa.getUsuarioModificacion());
					rsEmpresa.updateDate("fechaModificacion", empresa.getFechaModificacion());

					rsEmpresa.updateRow();
					modificado = true;
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR AL  modificar Empresa() " + e.getMessage());
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
	public boolean getTableEmpresaByIdLogin(int idEmpresa) {
        boolean flag = false; 
        try {
            c = poolConexion.getConnection();
            ps = c.prepareStatement("SELECT * FROM dbucash.empresa WHERE idEmpresa =?",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            ps.setInt(1, idEmpresa);
            rs = ps.executeQuery();

            // Hace peticion a la base de datos, por lo que los nombres en parentesis son
            // los de la base de datos.
            if (rs.next()) {
                Vw_empresa.empresaActual = rs.getInt("idEmpresa");

                flag = true; 
            }

        } catch (Exception e) {
            System.out.println("DATOS ERROR AL OBTENER TABLA EMPRESA POR ID: " + e.getMessage());
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
        return flag;

    }
}