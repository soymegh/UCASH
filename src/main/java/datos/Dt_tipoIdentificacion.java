package datos;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Tbl_tipoIdentificacion;


public class Dt_tipoIdentificacion {

	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTI = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_tipoIdentificacion() {
			
	}
	
	public void llenaRsTipoIdentificacion(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.tipoidentificacion;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsTI = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR TIPOIDENTIFICACION " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_tipoIdentificacion> listarTipoIdentificacion(){
		ArrayList<Tbl_tipoIdentificacion> listTI = new ArrayList<Tbl_tipoIdentificacion>();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.tipoidentificacion WHERE estado<>3;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_tipoIdentificacion TI = new Tbl_tipoIdentificacion();
				
				TI.setIdTipoIdentifiacion(rs.getInt("idTipoIdentifiacion"));
				TI.setTipo(rs.getString("tipo"));
				TI.setEstado(rs.getInt("Estado"));
				
				listTI.add(TI);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR TIPOIDENTIFICACION "+e.getMessage());
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
		
		return listTI;
		
		
	}
	
	//Metodo para almacenar nuevo TipoIdentificacion
		public boolean addTipoIdentificacion(Tbl_tipoIdentificacion TIS){
			boolean guardado = false;
			
			try{
				c = poolConexion.getConnection();
				this.llenaRsTipoIdentificacion(c);
				this.rsTI.moveToInsertRow();
				rsTI.updateString("tipo", TIS.getTipo());
				rsTI.updateInt("estado", 1); //0 PORQUE EL USUARIO ES REGISTRADO PERO SU EMAIL AUN NO HA SIDO VERIFICADO
				rsTI.insertRow();
				rsTI.moveToCurrentRow();
				//this.llenaRsTipoIdentificacion(c);
				//rsTI.last();
				
				guardado = true;
			}
			catch (Exception e) {
				System.err.println("ERROR AL GUARDAR Tbl_TipoIdentificacion "+e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsTI != null){
						rsTI.close();
					}
					if(c != null){
						poolConexion.closeConnection(c);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return guardado;
		}
		
		public Tbl_tipoIdentificacion getTipoIdentificacionbyID(int id) {
			Tbl_tipoIdentificacion TIS = new Tbl_tipoIdentificacion();
			try {
				c = poolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM dbucash.tipoIdentificacion where idTipoIdentifiacion = " +id, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				//ps.setInt(1, idTC);
				rs = ps.executeQuery();
				if(rs.next()) {
					TIS.setIdTipoIdentifiacion(rs.getInt("idTipoIdentifiacion"));
					
					TIS.setTipo(rs.getNString("tipo"));			
					
					
					TIS.setEstado(rs.getInt("estado"));
				}
			}catch (Exception e)
			{
				System.out.println("DATOS ERROR getTipoIdentificacionbyID(): "+ e.getMessage());
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
			
			return TIS;
		}
	
		
 //Metodo para eliminar tipo identificacion
		public boolean eliminarTipoIdentificacion(Tbl_tipoIdentificacion ti)
		{
			boolean eliminado=false;	
			try
			{
				c = poolConexion.getConnection();
				this.llenaRsTipoIdentificacion(c);
				rsTI.beforeFirst();
				while (rsTI.next()){
					if(rsTI.getInt(1)==ti.getIdTipoIdentifiacion()){
						rsTI.updateInt("estado", 3);
						rsTI.updateRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e){
				System.err.println("ERROR AL eliminarTipoIdentificacion() "+e.getMessage());
				e.printStackTrace();
			}
			finally{
				try {
					if(rsTI != null){
						rsTI.close();
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

		
	//Metodo para modificar Tipo Identificacion
		
		public boolean modificarTipoIdentificaion(Tbl_tipoIdentificacion mTI) {
			   boolean modificado = false;
			   try{
				   c = poolConexion.getConnection();
				   this.llenaRsTipoIdentificacion(c);
				   rsTI.beforeFirst();
				   while (rsTI.next()) {
					   if(rsTI.getInt(1)==mTI.getIdTipoIdentifiacion()){
						    rsTI.updateInt("idTipoIdentifiacion", mTI.getIdTipoIdentifiacion());
						    rsTI.updateString("tipo", mTI.getTipo());
						    rsTI.updateInt("estado", 2 );
						    rsTI.updateRow();
						    modificado=true;
						    break;
					   }
				   }
		} catch (Exception e) {
			System.err.println("ERROR AL modificarTipoIdentificacion() "+e.getMessage());
			e.printStackTrace();
		}
		 finally
		 {
			 try {
				  if(rsTI !=null) {
					  rsTI.close();
				  }
				  if(rsTI !=null) {
					  poolConexion.closeConnection(c);
				  }
				  
			 } catch (SQLException e) {
				 //Todo Auto-generated catch block
				 e.printStackTrace();
			 }
		 }
			   return modificado;
	}
		
	

}
