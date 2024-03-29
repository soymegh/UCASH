package reportes;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_cuentaContable;
import datos.Dt_cuentaContable_Det;
import datos.Dt_empresa;
import datos.Dt_historicoSaldos;
import datos.poolConexion;
import entidades.HistoricoSaldos;
import entidades.Tbl_cuentaContable;
import entidades.Tbl_cuentaContable_Det;
import entidades.Vw_empresa;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;



/**
 * Servlet implementation class Sl_resumenAsientoContable
 */
@WebServlet("/Sl_rptBalanceGeneral")
public class Sl_rptBalanceGeneral extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_rptBalanceGeneral() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		Dt_empresa datosEmpresa = new Dt_empresa();
		Dt_cuentaContable cuentaDatos = new Dt_cuentaContable();
		Dt_cuentaContable_Det cuentaDatosDetalles = new Dt_cuentaContable_Det();
		Dt_historicoSaldos dt_historico = new Dt_historicoSaldos();
		Vw_empresa emp = new Vw_empresa();
		int opcion = 0, idEmpresa = 0, idFecha = 0; 
		String fecha = "";
		
		if(request.getParameter("opcion") != null) {
			opcion = Integer.parseInt(request.getParameter("opcion"));
		} else {
			opcion = 0; 
		}
		
		if(request.getParameter("idEmpresa") != null) {
			idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
		} else {
			idEmpresa = 0; 
		}
		
		if(request.getParameter("fecha_historico") != null) {
			idFecha = Integer.parseInt(request.getParameter("fecha_historico"));
			fecha = dt_historico.ObtenerFechaExacta(idFecha).getFecha();
		}
		
		try {

			switch(opcion) {
			case 1:
				int totalActivosCirculante = 0, totalActivosFijos = 0, totalActivosDiferidos = 0, totalPasivoCirculante = 0,
				totalCapitalNeto = 0; 
				
				ArrayList<Integer> accountsIdentifiers = new ArrayList<Integer>();
				
				String concatKeys = "";
				
				double totalAC = 0, totalAF = 0, totalAD = 0, totalPC = 0, totalCN = 0, totalActivos = 0, totalPasivosXCapital = 0;
				
				double saldoInicial = 0, saldoFinal = 0, debe = 0, haber = 0; 
				
				
				// Activo circular
				ArrayList<Tbl_cuentaContable> listaCuentaAC = new ArrayList<Tbl_cuentaContable>();
				ArrayList<HistoricoSaldos> listaCuentaDetallesAC = new ArrayList<HistoricoSaldos>();
				
				if(request.getParameter("AC_Total") != null) {
					totalActivosCirculante = Integer.parseInt(request.getParameter("AC_Total"));
					
					if(totalActivosCirculante > 0) {
						int idCuenta = 0; 
						for(int x = 0; x < totalActivosCirculante; x++) {
							Tbl_cuentaContable cuenta = new Tbl_cuentaContable(); 
							
							if(request.getParameter("AC"+(x+1)+"") != null) {
								idCuenta = Integer.parseInt(request.getParameter("AC"+(x+1)+""));
								cuenta  = cuentaDatos.getCuentaContableByIdTable(idCuenta);
								listaCuentaAC.add(cuenta);
							}
						}
					}
					
					for(Tbl_cuentaContable cuenta: listaCuentaAC) {
						
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						listaCuentaDetallesAC.add(
								dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha)
						);
						
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 3);
					}
					
					//Obteniedo total
					for(int x = 0; x < listaCuentaDetallesAC.size(); x++) {
						if(listaCuentaDetallesAC.get(x).getSaldoFinal() > 0) {
							totalAC = totalAC + listaCuentaDetallesAC.get(x).getSaldoFinal();
						} else {
							saldoInicial = listaCuentaDetallesAC.get(x).getSaldoInicial();
							debe = listaCuentaDetallesAC.get(x).getDebe();
							haber = listaCuentaDetallesAC.get(x).getHaber();
							
							totalAC = totalAC + (saldoInicial + debe) - haber; 
							
							saldoInicial = debe = haber = 0; 
						}
					}
					
				}
				
				// Activo fijo
				ArrayList<Tbl_cuentaContable> listaCuentaAF = new ArrayList<Tbl_cuentaContable>();
				ArrayList<HistoricoSaldos> listaCuentaDetallesAF = new ArrayList<HistoricoSaldos>();
				
				if(request.getParameter("AF_Total") != null) {
					totalActivosFijos = Integer.parseInt(request.getParameter("AF_Total"));
					
					if(totalActivosFijos > 0) {
						int idCuenta = 0; 
						for(int x = 0; x < totalActivosFijos; x++) {
							Tbl_cuentaContable cuenta = new Tbl_cuentaContable(); 
							
							if(request.getParameter("AF"+(x+1)+"") != null) {
								idCuenta = Integer.parseInt(request.getParameter("AF"+(x+1)+""));
								cuenta  = cuentaDatos.getCuentaContableByIdTable(idCuenta);
								listaCuentaAF.add(cuenta);
							}
						}
					}
					
					for(Tbl_cuentaContable cuenta: listaCuentaAF) {
						
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						listaCuentaDetallesAF.add(
								dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha)
						);
						
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 1);
					}
					
					//Obteniedo total
					for(int x = 0; x < listaCuentaDetallesAF.size(); x++) {
						if(listaCuentaDetallesAF.get(x).getSaldoFinal() > 0) {
							totalAF = totalAF + listaCuentaDetallesAF.get(x).getSaldoFinal();
						} else {
							saldoInicial = listaCuentaDetallesAF.get(x).getSaldoInicial();
							debe = listaCuentaDetallesAF.get(x).getDebe();
							haber = listaCuentaDetallesAF.get(x).getHaber();
							
							totalAF = totalAF + (saldoInicial + debe) - haber; 
							
							saldoInicial = debe = haber = 0; 
						}
					}
				}
				
				// Activo diferido
				ArrayList<Tbl_cuentaContable> listaCuentaAD = new ArrayList<Tbl_cuentaContable>();
				ArrayList<HistoricoSaldos> listaCuentaDetallesAD = new ArrayList<HistoricoSaldos>();
				
				if(request.getParameter("AD_Total") != null) {
					totalActivosDiferidos = Integer.parseInt(request.getParameter("AD_Total"));
					
					if(totalActivosDiferidos > 0) {
						int idCuenta = 0; 
						for(int x = 0; x < totalActivosDiferidos; x++) {
							Tbl_cuentaContable cuenta = new Tbl_cuentaContable(); 
							
							if(request.getParameter("AD"+(x+1)+"") != null) {
								idCuenta = Integer.parseInt(request.getParameter("AD"+(x+1)+""));
								cuenta  = cuentaDatos.getCuentaContableByIdTable(idCuenta);
								listaCuentaAD.add(cuenta);
							}
						}
					}
					
					for(Tbl_cuentaContable cuenta: listaCuentaAD) {
						
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						listaCuentaDetallesAD.add(
								dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha)
						);
						
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 2);
					}
					
					//Obteniedo total
					for(int x = 0; x < listaCuentaDetallesAD.size(); x++) {
						if(listaCuentaDetallesAD.get(x).getSaldoFinal() > 0) {
							totalAD = totalAD + listaCuentaDetallesAD.get(x).getSaldoFinal();
						} else {
							saldoInicial = listaCuentaDetallesAD.get(x).getSaldoInicial();
							debe = listaCuentaDetallesAD.get(x).getDebe();
							haber = listaCuentaDetallesAD.get(x).getHaber();
							
							totalAD = totalAD + (saldoInicial + debe) - haber; 
							
							saldoInicial = debe = haber = 0; 
						}
					}
				}
				
				// Pasivo circular
				ArrayList<Tbl_cuentaContable> listaCuentaPC = new ArrayList<Tbl_cuentaContable>();
				ArrayList<HistoricoSaldos> listaCuentaDetallesPC = new ArrayList<HistoricoSaldos>();
				
				if(request.getParameter("PC_Total") != null) {
					totalPasivoCirculante = Integer.parseInt(request.getParameter("PC_Total"));
					
					if(totalPasivoCirculante > 0) {
						int idCuenta = 0; 
						for(int x = 0; x < totalPasivoCirculante; x++) {
							Tbl_cuentaContable cuenta = new Tbl_cuentaContable(); 
							
							if(request.getParameter("PC"+(x+1)+"") != null) {
								idCuenta = Integer.parseInt(request.getParameter("PC"+(x+1)+""));
								cuenta  = cuentaDatos.getCuentaContableByIdTable(idCuenta);
								listaCuentaPC.add(cuenta);
							}
						}
					}
					
					for(Tbl_cuentaContable cuenta: listaCuentaPC) {
						
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						listaCuentaDetallesPC.add(
								dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha)
						);		
						
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 4);
					}
					
					//Obteniedo total
					for(int x = 0; x < listaCuentaDetallesPC.size(); x++) {
						if(listaCuentaDetallesPC.get(x).getSaldoFinal() > 0) {
							totalPC = totalPC + listaCuentaDetallesPC.get(x).getSaldoFinal();
						} else {
							saldoInicial = listaCuentaDetallesPC.get(x).getSaldoInicial();
							debe = listaCuentaDetallesPC.get(x).getDebe();
							haber = listaCuentaDetallesPC.get(x).getHaber();
							
							totalPC = totalPC + (saldoInicial + debe) - haber; 
							
							saldoInicial = debe = haber = 0; 
						}
					}
				}
				
				// Capital neto
				ArrayList<Tbl_cuentaContable> listaCuentaCN = new ArrayList<Tbl_cuentaContable>();
				ArrayList<HistoricoSaldos> listaCuentaDetallesCN = new ArrayList<HistoricoSaldos>();
				
				if(request.getParameter("CN_Total") != null) {
					totalCapitalNeto = Integer.parseInt(request.getParameter("CN_Total"));
					
					if(totalCapitalNeto > 0) {
						int idCuenta = 0; 
						for(int x = 0; x < totalCapitalNeto; x++) {
							Tbl_cuentaContable cuenta = new Tbl_cuentaContable(); 
							
							if(request.getParameter("CN"+(x+1)+"") != null) {
								idCuenta = Integer.parseInt(request.getParameter("CN"+(x+1)+""));
								cuenta  = cuentaDatos.getCuentaContableByIdTable(idCuenta);
								listaCuentaCN.add(cuenta);
							}
						}
					}
					
					for(Tbl_cuentaContable cuenta: listaCuentaCN) {
						
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						listaCuentaDetallesCN.add(
								dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha)
						);	
						
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 5);
					}
					
					//Obteniedo total
					for(int x = 0; x < listaCuentaDetallesCN.size(); x++) {
						if(listaCuentaDetallesCN.get(x).getSaldoFinal() > 0) {
							totalCN = totalCN + listaCuentaDetallesCN.get(x).getSaldoFinal();
						} else {
							saldoInicial = listaCuentaDetallesCN.get(x).getSaldoInicial();
							debe = listaCuentaDetallesCN.get(x).getDebe();
							haber = listaCuentaDetallesCN.get(x).getHaber();
							
							totalCN = totalCN + (saldoInicial + debe) - haber; 
							
							saldoInicial = debe = haber = 0; 
						}
					}
				}
				
				HashMap<String, Object> hashMap = new HashMap<>();
				
				//Obteniendo rango de fechas
				String fechaInicio = "", fechaFinal = "";
				
				ArrayList<HistoricoSaldos> HistoricoFechas = new ArrayList<HistoricoSaldos>();
				
				HistoricoFechas = dt_historico.ObtenerHistoricoFechas(idEmpresa);
				
				for(int x  = 0; x < HistoricoFechas.size(); x++) {
					try {
						if(idFecha == HistoricoFechas.get(x).getIdHistorico()) {
							fechaInicio = HistoricoFechas.get(x).getFecha();
							fechaFinal = HistoricoFechas.get(x+1).getFecha();
							
							hashMap.put("fechaInicio", fechaInicio);
							hashMap.put("fechaFin", fechaFinal);
						}
					}catch(Exception ex) {
						fechaInicio = HistoricoFechas.get(x).getFecha();
						
						hashMap.put("fechaInicio", fechaInicio);
					}
				}
				
				for(int x = 0; x < accountsIdentifiers.size(); x++) {
					if(x == 0) {
						concatKeys = concatKeys + " idCuenta = "+accountsIdentifiers.get(x)+" and fecha = '"+fecha+"' ";
					} else {
						concatKeys = concatKeys + " OR idCuenta = "+accountsIdentifiers.get(x)+" and fecha = '"+fecha+"' ";
					}
				}
				
				//Totales del reporte (Parametros para jasper)
				totalActivos = totalAC + totalAF + totalAD; 
				totalPasivosXCapital = totalPC + totalCN; 
				
				//La variable concatKeys contiene la condicion y parametros para el where que se usa en Jasper
				System.out.print(concatKeys);
				
				System.out.print("Total Activos: " + totalActivos + " Total Pasivos Y Capital: " + totalPasivosXCapital);
				
				/**
				 * Generación del reporte con JasperSoft
				 */
				poolConexion pc = poolConexion.getInstance();
				Connection c = poolConexion.getConnection();
				
				
				hashMap.put("whereQuery", concatKeys);
				hashMap.put("totalPasivosYCapital", totalPasivosXCapital);
				
				
				
				
				OutputStream outputSt = response.getOutputStream();
				ServletContext slContext = getServletContext();
				
				String path = slContext.getRealPath("/");
				System.out.println("PATH: " + path);
				
				String template = "reportes\\BG.jasper";
				Exporter exporter = new JRPdfExporter();
				JasperPrint jasperPrint = JasperFillManager.fillReport(path + template, hashMap, c);
				
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=\"" + "BalanceGeneral" + "_.pdf");
			
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputSt));
				exporter.exportReport();
				
			}

		} catch (Exception e) {
			System.err.println("ERROR AL GENERAR BALANCE GENERAL: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
