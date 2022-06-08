package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import datos.Dt_cuentaContable_Det;

import entidades.Tbl_asientoContableDet;
import entidades.Vw_asientoContableDet;

public class Dt_asientoContableDet {
	poolConexion pc = poolConexion.getInstance();
	Connection c = null;
	private ResultSet rsasientocontableDet = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	Dt_cuentaContable_Det ccDet = new Dt_cuentaContable_Det();
	
	public Dt_asientoContableDet() {
		
	}
	
	public void llenaRsAsientoContableDet(Connection c) {
		try {
			this.ps = c.prepareStatement("SELECT * FROM dbucash.asientocontabledet;", ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			this.rsasientocontableDet = this.ps.executeQuery();
			
		} catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Asiento Contable Detalle " + e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<Vw_asientoContableDet> listarasientocontableDET(){
		ArrayList<Vw_asientoContableDet> listasientocontableDet = new ArrayList<Vw_asientoContableDet>();
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_asientocontabledet;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Vw_asientoContableDet asientoContableDET = new Vw_asientoContableDet();
				asientoContableDET.setIdAsientoContableDet(rs.getInt("idAsientoContableDet"));
				asientoContableDET.setIdCuenta(this.rs.getInt("idCuenta"));
				asientoContableDET.setNumeroCuenta(this.rs.getString("numeroCuenta"));
				asientoContableDET.setNombreCuenta(rs.getString("nombreCuenta"));
				asientoContableDET.setSC(this.rs.getString("SC"));
				asientoContableDET.setSsC(this.rs.getString("SsC"));
				asientoContableDET.setSssC(this.rs.getString("SssC"));
				asientoContableDET.setIdAsientoContable(this.rs.getInt("idAsientoContable"));
				
				String fecha = rs.getString("fecha");
				java.util.Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
				asientoContableDET.setFecha(new java.sql.Date(date0.getTime()));
				
				asientoContableDET.setDescripcion(this.rs.getString("descripcion"));
				asientoContableDET.setDebe(rs.getDouble("debe"));
				asientoContableDET.setHaber(rs.getDouble("haber"));
				listasientocontableDet.add(asientoContableDET);
			}
			} catch(Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR Detalle de Asiento Contable"+e.getMessage());
				e.printStackTrace();
			}
		 finally {
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
		
		return listasientocontableDet;
	}
	
	public boolean guardarAsientoContableDet(Tbl_asientoContableDet tacd)
	{
		boolean guardado = false;
		
		try {
			
			c = poolConexion.getConnection();
			this.llenaRsAsientoContableDet(c);
			rsasientocontableDet.moveToInsertRow();
			rsasientocontableDet.updateInt("idAsientoContable", tacd.getIdAsientoContable() );
			rsasientocontableDet.updateInt("idCuenta", tacd.getIdCuenta());
			rsasientocontableDet.updateDouble("debe", tacd.getDebe());
			rsasientocontableDet.updateDouble("haber", tacd.getHaber());
			rsasientocontableDet.insertRow();
			rsasientocontableDet.moveToCurrentRow();
			
			guardado = true;
			
		} catch (Exception e) {
			System.err.println("ERROR AL guardar AsientoContableDet() "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if (rsasientocontableDet != null) {
					rsasientocontableDet.close();
				}
				if (c != null) {
					poolConexion.closeConnection(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return  guardado;
	}
	
	public int getIdCuentaByIdACD(int idACD) {
	    int idCuenta = 0;
	    try {
	        c = poolConexion.getConnection();
	        this.ps = this.c.prepareStatement("SELECT idCuenta FROM dbucash.asientocontabledet WHERE idAsientoContable = ?;", 
	                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ps.setInt(1, idACD);
	        rs = ps.executeQuery();

	        while(this.rs.next()) {
	            idCuenta = rs.getInt("idCuenta");
	        }

	    } catch(Exception e) {
	        System.out.println("DATOS: ERROR EN ENCONTRAR IdCuenta "+ e.getMessage());
	        e.printStackTrace();
	    }
	    finally 
	    {
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

	    return idCuenta ;
	}
	
	public boolean EliminarAContableDetPorIdRestaTotal(int idEliminar)
	{		
			boolean borrado = false;
			
			
			try {
				
				c = poolConexion.getConnection();
				this.ps = this.c.prepareStatement("DELETE FROM dbucash.asientocontabledet WHERE idAsientoContableDet = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				this.ps.setInt(1, idEliminar);
				int result = this.ps.executeUpdate();

				if (result > 0) {
					borrado = true;
				}
				
			} catch (Exception e) {
				
				System.err.println("ERROR AL BORRAR Asiento Contable Detalle POR ID: " + e.getMessage());
				e.printStackTrace();
			}
			finally {
				
				try {
					
					if (rsasientocontableDet != null) {
						
						rsasientocontableDet.close();
						
					}
					if (c != null) {
						
						poolConexion.closeConnection(c);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		
			return borrado;
			
		}
	
	public boolean EliminarAContableDetPorId(int idEliminar)
	{		
			boolean borrado = false;
			
			try {
				
				c = poolConexion.getConnection();
				this.ps = this.c.prepareStatement("DELETE FROM dbucash.asientocontabledet WHERE idAsientoContableDet = ?;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				this.ps.setInt(1, idEliminar);
				int result = this.ps.executeUpdate();

				if (result > 0) {
					borrado = true;
				}
				
			} catch (Exception e) {
				
				System.err.println("ERROR AL BORRAR Asiento Contable Detalle POR ID: " + e.getMessage());
				e.printStackTrace();
			}
			finally {
				
				try {
					
					if (rsasientocontableDet != null) {
						
						rsasientocontableDet.close();
						
					}
					if (c != null) {
						
						poolConexion.closeConnection(c);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		
			return borrado;
			
		}
	
	public double getTotalDebeByIdCuenta(int idCuenta) {
	    double totalDebe = 0;
	    try {
	        c = poolConexion.getConnection();
	        this.ps = this.c.prepareStatement("SELECT sum(debe) AS 'totalDebe' FROM dbucash.asientocontabledet WHERE idCuenta = ?;", 
	                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ps.setInt(1, idCuenta);
	        rs = ps.executeQuery();

	        while(this.rs.next()) {
	            totalDebe = rs.getDouble("totalDebe");
	        }

	    } catch(Exception e) {
	        System.out.println("DATOS: ERROR EN SUMAR DEBE "+ e.getMessage());
	        e.printStackTrace();
	    }
	    finally 
	    {
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

	    return totalDebe ;
	}
	
	public double getTotalHaberByIdCuenta(int idCuenta) {
	    double totalHaber = 0;
	    try {
	        c = poolConexion.getConnection();
	        this.ps = this.c.prepareStatement("SELECT sum(haber) AS 'totalHaber' FROM dbucash.asientocontabledet WHERE idCuenta = ?;", 
	                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ps.setInt(1, idCuenta);
	        rs = ps.executeQuery();

	        while(this.rs.next()) {
	            totalHaber = rs.getDouble("totalHaber");
	        }

	    } catch(Exception e) {
	        System.out.println("DATOS: ERROR EN SUMAR HABER "+ e.getMessage());
	        e.printStackTrace();
	    }
	    finally 
	    {
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

	    return totalHaber ;
	}
}