package datos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_representanteLegal;
import entidades.Tbl_tipoIdentificacion;
import entidades.Vw_representanteLegal;


public class Dt_representanteLegal {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private ResultSet rsRepresentantel = null;
	private PreparedStatement ps = null;

	public Dt_representanteLegal() {

	}

	public void llenaRsRepresentanteLegal(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.representanteLegal;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsRepresentantel = this.ps.executeQuery();

		} catch (Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR REPRESENTANTELEGAL " + var3.getMessage());
			var3.printStackTrace();
		}
	}

	public int idRepresentanteLegal() {
	    int idR = 0;
	    
	    try {
	    	c = poolConexion.getConnection(); 
	    	ps = c.prepareStatement("SELECT idRepresentante FROM dbucash.representanteLegal ORDER BY idRepresentante DESC LIMIT 1",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    	rs = ps.executeQuery();
	    	if(rs.next()) { 
	    	idR = Integer.parseInt(rs.getObject(1).toString());
	    	}
	    } catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR idRepresentante " + e.getMessage());
			e.printStackTrace();
		} finally {
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
	    
		return idR;
	}

	public ArrayList<Vw_representanteLegal> listarRepresentanteLegal() {
		ArrayList<Vw_representanteLegal> listRL = new ArrayList<Vw_representanteLegal>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.vw_representantelegal WHERE estado<>3;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();

			while (this.rs.next()) {
				Vw_representanteLegal RL = new Vw_representanteLegal();

				RL.setIdRepresentante(rs.getInt("idRepresentante"));
				RL.setNombreCompleto(rs.getString("nombre Completo"));
				RL.setTipo(rs.getString("tipo"));
				RL.setCorreo(rs.getString("correo"));
				RL.setTelefono(rs.getString("telefono"));
				RL.setEstado(rs.getInt("estado"));

				listRL.add(RL);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR REPRESENTANTELEGAL " + e.getMessage());
			e.printStackTrace();
		} finally {
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

		return listRL;
	}
	
	public ArrayList<Vw_representanteLegal> listarRepresentanteLegalNoEnEmpresa() {
		ArrayList<Vw_representanteLegal> listRL = new ArrayList<Vw_representanteLegal>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM vw_representantelegal WHERE idRepresentante NOT IN (SELECT idRepresentante FROM empresa) and vw_representantelegal.estado <> 3;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();

			while (this.rs.next()) {
				Vw_representanteLegal RL = new Vw_representanteLegal();

				RL.setIdRepresentante(rs.getInt("idRepresentante"));
				RL.setNombreCompleto(rs.getString("nombre Completo"));
				RL.setTipo(rs.getString("tipo"));
				RL.setCorreo(rs.getString("correo"));
				RL.setTelefono(rs.getString("telefono"));
				RL.setEstado(rs.getInt("estado"));

				listRL.add(RL);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR REPRESENTANTELEGAL " + e.getMessage());
			e.printStackTrace();
		} finally {
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

		return listRL;
	}
	//Metodo para Agregar Representante Legal
	
	public boolean addRepresentanteLegal(Tbl_representanteLegal representante) {
		boolean guardado = false;

		try {
			c = poolConexion.getConnection();
			this.llenaRsRepresentanteLegal(c);
			this.rsRepresentantel.moveToInsertRow();

			rsRepresentantel.updateInt("idRepresentante", representante.getIdRepresentante());
			rsRepresentantel.updateInt("idTipoIdentifiacion", representante.getIdTipoIdentifiacion());
			rsRepresentantel.updateString("nombre", representante.getNombre());
			rsRepresentantel.updateString("apellido", representante.getApellido());
			rsRepresentantel.updateString("telefono", representante.getTelefono());
			rsRepresentantel.updateString("correo", representante.getCorreo());
			rsRepresentantel.updateInt("estado", representante.getEstado());

			rsRepresentantel.insertRow();
			rsRepresentantel.moveToCurrentRow();
			guardado = true;

		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR REPRESENTANTE LEGAL: " + e.getMessage());
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

		return guardado;
	}
	

	//Metodo para modificar Representante Legal
	public boolean modificarRepresentanteLegal(Tbl_representanteLegal mRepresentanteL) {
		   boolean modificado = false;
		   try{
			   c = poolConexion.getConnection();
			   this.llenaRsRepresentanteLegal(c);
			   rsRepresentantel.beforeFirst();
			   while (rsRepresentantel.next()) {
				   if(rsRepresentantel.getInt(1)==mRepresentanteL.getIdRepresentante()){
					   rsRepresentantel.updateInt("idRepresentante", mRepresentanteL.getIdRepresentante());
					   rsRepresentantel.updateInt("idTipoIdentifiacion", mRepresentanteL.getIdTipoIdentifiacion());
					   rsRepresentantel.updateString("nombre", mRepresentanteL.getNombre());
					   rsRepresentantel.updateString("apellido", mRepresentanteL.getApellido());
					   rsRepresentantel.updateString("telefono", mRepresentanteL.getTelefono());
					   rsRepresentantel.updateString("correo", mRepresentanteL.getCorreo());
					   rsRepresentantel.updateInt("estado", 2 );
					   rsRepresentantel.updateRow();
					   modificado=true;
					   break;
				   }
			   }
	} catch (Exception e) {
		System.err.println("ERROR AL modificar Representante Legal() "+e.getMessage());
		e.printStackTrace();
	}
	 finally
	 {
		 try {
			  if(rsRepresentantel !=null) {
				  rsRepresentantel.close();
			  }
			  if(rsRepresentantel !=null) {
				  poolConexion.closeConnection(c);
			  }
			  
		 } catch (SQLException e) {
			 //Todo Auto-generated catch block
			 e.printStackTrace();
		 }
	 }
		   return modificado;
}
	
	
	
	//Metodo para eliminar un Representante Legal
	public boolean eliminarRepresentanteLegal(Tbl_representanteLegal eliRepresentanteL)
	{
		boolean eliminado=false;	
		try
		{
			c = poolConexion.getConnection();
			this.llenaRsRepresentanteLegal(c);
			rsRepresentantel.beforeFirst();
			while (rsRepresentantel.next()){
				if(rsRepresentantel.getInt(1)==eliRepresentanteL.getIdRepresentante()){
					rsRepresentantel.updateInt("estado", 3);
					rsRepresentantel.updateRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e){
			System.err.println("ERROR AL Representante Legal() "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsRepresentantel != null){
					rsRepresentantel.close();
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
	
	//Metodo GetByID
	public Tbl_representanteLegal getRepresentanteLegalbyID(int idR) {
		Tbl_representanteLegal REL = new Tbl_representanteLegal();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.representantelegal where idRepresentante = " +idR, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//ps.setInt(1, idTC);
			rs = ps.executeQuery();
			if(rs.next()) {
				REL.setIdRepresentante(rs.getInt("idRepresentante"));
				
				REL.setIdTipoIdentifiacion(rs.getInt("idTipoIdentifiacion"));
				
				REL.setNombre(rs.getString("nombre"));
				
				REL.setApellido(rs.getString("apellido"));
				
				REL.setTelefono(rs.getString("telefono"));
				
				REL.setCorreo(rs.getString("correo"));
				
				REL.setEstado(rs.getInt("estado"));
				
			}
		}catch (Exception e)
		{
			System.out.println("DATOS ERROR getRepresentanteLegalbyID(): "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		
		return REL;
	}
	
	public Vw_representanteLegal getViewRepresentanteLegalbyID(int idVw) {
		Vw_representanteLegal viewR = new Vw_representanteLegal();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_representantelegal where idRepresentante = " +idVw, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//ps.setInt(1, idTC);
			rs = ps.executeQuery();
			if(rs.next()) {
				viewR.setIdRepresentante(rs.getInt("idRepresentante"));
				
				viewR.setNombreCompleto(rs.getString("nombre completo"));

				viewR.setTipo(rs.getString("tipo"));
				
				viewR.setCorreo(rs.getString("correo"));
				
				viewR.setTelefono(rs.getString("telefono"));
				
				viewR.setEstado(rs.getInt("estado"));
				
				
			}
		}catch (Exception e)
		{
			System.out.println("DATOS ERROR getViewRepresentanteLegalbyID(): "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		
		return viewR;
	}
	
	public ArrayList<Vw_representanteLegal> listarRepresentanteLegalDeEmpresa(int idEmpresa) {
		ArrayList<Vw_representanteLegal> listRL = new ArrayList<Vw_representanteLegal>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT\r\n"
					+ "  representantelegal.idRepresentante,\r\n"
					+ "  representantelegal.idTipoIdentifiacion,\r\n"
					+ "    CONCAT(`representantelegal`.`nombre`, ' ', `representantelegal`.`apellido`) AS `nombre Completo`,\r\n"
					+ "  representantelegal.telefono,\r\n"
					+ "  representantelegal.correo,\r\n"
					+ "  representantelegal.estado,\r\n"
					+ "  tipoidentificacion.tipo\r\n"
					+ "FROM empresa\r\n"
					+ "  INNER JOIN representantelegal\r\n"
					+ "    ON empresa.idRepresentante = representantelegal.idRepresentante \r\n"
					+ "  INNER JOIN tipoidentificacion\r\n"
					+ "    ON representantelegal.idTipoIdentifiacion = tipoidentificacion.idTipoIdentifiacion where idEmpresa =" +idEmpresa, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//ps.setInt(1, idTC);
			rs = ps.executeQuery();
			while (this.rs.next()) {
				Vw_representanteLegal RL = new Vw_representanteLegal();

				RL.setIdRepresentante(rs.getInt("idRepresentante"));
				RL.setNombreCompleto(rs.getString("nombre Completo"));
				RL.setTipo(rs.getString("tipo"));
				RL.setCorreo(rs.getString("correo"));
				RL.setTelefono(rs.getString("telefono"));
				RL.setEstado(rs.getInt("estado"));

				listRL.add(RL);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR REPRESENTANTELEGAL " + e.getMessage());
			e.printStackTrace();
		} finally {
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

		return listRL;
	}
}
