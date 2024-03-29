package datos;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import datos.Dt_cuentaContable_Det;
import entidades.Historico;
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
	
	public ArrayList<Integer> getIdCuentaByIdACD(int idACD) {
		ArrayList<Integer> idCuentaList = new ArrayList<>();
	    int idCuenta = 0;
	    try {
	        c = poolConexion.getConnection();
	        this.ps = this.c.prepareStatement("SELECT DISTINCT idCuenta FROM dbucash.asientocontabledet WHERE idAsientoContable = ?;", 
	                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ps.setInt(1, idACD);
	        rs = ps.executeQuery();

	        while(this.rs.next()) {
	            idCuenta = rs.getInt("idCuenta");
	            idCuentaList.add(idCuenta);
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

	    return idCuentaList;
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
	
	public double getTotalDebeByIdCuenta(int idCuenta, int idAsientoMaestro) {
	    double totalDebe = 0;
	    try {
	        c = poolConexion.getConnection();
	        this.ps = this.c.prepareStatement("SELECT sum(debe) AS 'totalDebe' FROM dbucash.asientocontabledet WHERE idCuenta = ? AND idAsientoContable = ?;", 
	                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ps.setInt(1, idCuenta);
	        ps.setInt(2, idAsientoMaestro);
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
	
	public double getTotalHaberByIdCuenta(int idCuenta, int idAsientoMaestro) {
	    double totalHaber = 0;
	    try {
	        c = poolConexion.getConnection();
	        this.ps = this.c.prepareStatement("SELECT sum(haber) AS 'totalHaber' FROM dbucash.asientocontabledet WHERE idCuenta = ? AND idAsientoContable = ?;", 
	                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ps.setInt(1, idCuenta);
	        ps.setInt(2, idAsientoMaestro);
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
	
	public ArrayList<Historico> listarasientocontableDetHistorico(int idCuenta, String fechaInicial, String fechaFinal){
		ArrayList<Historico> listasientocontableDet = new ArrayList<Historico>();
		System.out.print("Esta es la cuenta: "+idCuenta+", Este es el inicio: "+fechaInicial+", Este es el final: "+fechaFinal+"");
		try {
			c = poolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM dbucash.vw_historico WHERE idCuenta = ? AND fecha BETWEEN ? AND ?;",  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, idCuenta);
	        ps.setString(2, fechaInicial);
	        ps.setString(3, fechaFinal);
			rs = ps.executeQuery();
			
			while(this.rs.next()) {
				Historico historico = new Historico();
				historico.setIdAsientoContableDet(rs.getInt("idAsientoContableDet"));
				historico.setNombreCuenta(rs.getString("nombreCuenta"));
				historico.setDescripcion(rs.getString("descripcion"));
				historico.setDebe(rs.getDouble("debe"));
				historico.setHaber(rs.getDouble("haber"));
				historico.setFecha(rs.getDate("fecha"));
				
				listasientocontableDet.add(historico);
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
}