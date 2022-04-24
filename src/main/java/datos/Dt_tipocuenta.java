package datos;



import entidades.Tbl_tipocuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dt_tipocuenta {
	
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTipocuenta = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Dt_tipocuenta() {
			
	}
	
	public void llenaRsTipocuenta(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.tipocuenta;", 1005, 1008, 1);
			this.rsTipocuenta = this.ps.executeQuery();
			
		} catch(Exception var3) {
			System.out.println("DATOS: ERROR EN LISTAR TIPO DE CUENTA " + var3.getMessage());
			var3.printStackTrace();
		}
	}
	
	public ArrayList<Tbl_tipocuenta> listaTipocuentaActivos(){
		ArrayList listTipocuenta = new ArrayList();
		try {
			this.c = poolConexion.getConnection();
			this.ps = this.c.prepareStatement("SELECT * FROM dbucash.tipocuenta;", 1005, 1007);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				Tbl_tipocuenta tipocuenta = new Tbl_tipocuenta();
				tipocuenta.setIdTipoCuenta(this.rs.getInt("idTipoCuenta"));
				tipocuenta.setTipoCuenta(this.rs.getString("tipoCuenta"));
				listTipocuenta.add(tipocuenta);
			}
			} catch(Exception var11) {
				System.out.println("DATOS: ERROR EN LISTAR TIPO DE CUENTA "+var11.getMessage());
				var11.printStackTrace();
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
	            } catch (SQLException var10) {
	                var10.printStackTrace();
	            }

		}
		
		return listTipocuenta;
	}
	
	
	
	public boolean addTipocuenta(Tbl_tipocuenta Tipocuenta){
		boolean guardado = false;
		try {
			c = poolConexion.getConnection();
			this.llenaRsTipocuenta(c);
			this.rsTipocuenta.moveToInsertRow();
			rsTipocuenta.updateInt("idTipoCuenta", Tipocuenta.getIdTipoCuenta());
			rsTipocuenta.updateString("tipoCuenta", Tipocuenta.getTipoCuenta());
			
			rsTipocuenta.insertRow();
			rsTipocuenta.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR TIPO DE CUENTA: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsTipocuenta != null) {
					rsTipocuenta.close();
				}
				if(c != null) {
					poolConexion.closeConnection(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
		
		return guardado;
		
		
	}
	

	
	//obtener tabla de tipo de cuenta por ID
			public Tbl_tipocuenta getTableTipocuentaByID(int idTipoCuenta) {
				Tbl_tipocuenta tipocuenta = new Tbl_tipocuenta();
				try {
					c = poolConexion.getConnection();
					ps = c.prepareStatement("SELECT * FROM dbucash.tipocuenta WHERE idTipoCuenta =?",
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					ps.setInt(1, idTipoCuenta);
					rs = ps.executeQuery();

					// Hace peticion a la base de datos, por lo que los nombres en parentesis son los de la base de datos
					if (rs.next()) {
						tipocuenta.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
						tipocuenta.setTipoCuenta(rs.getString("tipoCuenta"));
						
						
						System.out.println(tipocuenta); 
					}

				} catch (Exception e) {
					System.out.println("DATOS ERROR AL OBTENER TABLA TIPO DE CUENTA POR ID: " + e.getMessage());
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
				return tipocuenta;
			}
		//metodo para editar un tipo de cuenta
				public boolean modificarTipocuenta(Tbl_tipocuenta Tipocuenta) {
					boolean modificado = false;
					try {
						c = poolConexion.getConnection();
						this.llenaRsTipocuenta(c);
						rsTipocuenta.beforeFirst();
						while (rsTipocuenta.next()) {
							if (rsTipocuenta.getInt(1) == Tipocuenta.getIdTipoCuenta ()) {
								rsTipocuenta.updateString("tipoCuenta", Tipocuenta.getTipoCuenta());
								 
								rsTipocuenta.updateRow();
								modificado = true;
								break;
							}
						}
					} catch (Exception e) {
						System.err.println("ERROR AL  modificar Tipocuenta " + e.getMessage());
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
				
				//eliminar un tipo de cuenta
				public boolean deleteTipocuenta(Tbl_tipocuenta Tipocuenta)
				{
					boolean eliminado=false;	
					try
					{
						c = poolConexion.getConnection();
						this.llenaRsTipocuenta(c);
						rsTipocuenta.beforeFirst();
						while (rsTipocuenta.next()){
							if(rsTipocuenta.getInt(1)==Tipocuenta.getIdTipoCuenta()){
								rsTipocuenta.deleteRow();
								eliminado=true;
								break;
							}
						}
					}
					catch (Exception e){
						System.err.println("ERROR AL Tipocuenta() "+e.getMessage());
						e.printStackTrace();
					}
					finally{
						try {
							if(rsTipocuenta != null){
								rsTipocuenta.close();
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
		}








