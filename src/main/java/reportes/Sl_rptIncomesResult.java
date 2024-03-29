package reportes;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_asientoContable;
import datos.Dt_cuentaContable;
import datos.Dt_cuentaContable_Det;
import datos.Dt_empresa;
import datos.Dt_historicoSaldos;
import datos.Dt_periodoContable;
import datos.poolConexion;
import entidades.HistoricoSaldos;
import entidades.Tbl_asientoContable;
import entidades.Tbl_cuentaContable;
import entidades.Tbl_cuentaContable_Det;
import entidades.Tbl_periodoContable;
import entidades.Vw_asientoContable;
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
@WebServlet("/Sl_rptIncomesResult")
public class Sl_rptIncomesResult extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_rptIncomesResult() {
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
		
		HashMap<String, Object> hm = new HashMap<>();
		
		Dt_empresa datosEmpresa = new Dt_empresa();
		Dt_cuentaContable cuentaDatos = new Dt_cuentaContable();
		Dt_cuentaContable_Det cuentaDatosDetalles = new Dt_cuentaContable_Det();
		Dt_asientoContable datosAsientoContable = new Dt_asientoContable();
		Dt_periodoContable datosPeriodo = new Dt_periodoContable();
		Dt_historicoSaldos dt_historico = new Dt_historicoSaldos();
		Tbl_periodoContable periodoContable = new Tbl_periodoContable();
		Vw_empresa emp = new Vw_empresa();
		int opcion = 0; 
		
		if(request.getParameter("opcion") != null) {
			opcion = Integer.parseInt(request.getParameter("opcion"));
		} else {
			opcion = 0; 
		}
		
		try {

			switch(opcion) {
			case 1:
				int cuentasIngresosMayor = 0, cuentasIngresosMenor = 0, idEmpresa = 0, idPeriodoContable = 0, cuentasGastosGenerales = 0, cuentasOtroIngreso = 0, cuentasOtroEgreso = 0
				, cuentaTotalizdora = 0; 
				
				double totalMes = 0, totalFecha = 0; 
				
				String concatKeys = "";
				String nombreEmpresa;
				int idFecha = 0;
				String fecha = "";
				
				ArrayList<Integer> accountsIdentifiers = new ArrayList<Integer>();
				
				if(request.getParameter("periodoContable") != null) {
					idPeriodoContable = Integer.parseInt(request.getParameter("periodoContable"));
				}
				
				if(request.getParameter("empresaActual") != null) {
					idEmpresa = Integer.parseInt(request.getParameter("empresaActual"));
				}
				
				if(request.getParameter("IngresosMayor_Total") != null) {
					cuentasIngresosMayor = Integer.parseInt(request.getParameter("IngresosMayor_Total"));
				} else {
					cuentasIngresosMayor = 0; 
				}
				
				if(request.getParameter("IngresosMenor_Total") != null) {
					cuentasIngresosMenor = Integer.parseInt(request.getParameter("IngresosMenor_Total"));
				} else {
					cuentasIngresosMenor = 0; 
				}
				
				if(request.getParameter("cuenta_contable_T") != null) {
					cuentaTotalizdora = Integer.parseInt(request.getParameter("cuenta_contable_T"));
				}
				
				if(request.getParameter("fecha_historico") != null) {
					idFecha = Integer.parseInt(request.getParameter("fecha_historico"));
					fecha = dt_historico.ObtenerFechaExacta(idFecha).getFecha();
				}

				// Nombre de empresa para el reporte
				nombreEmpresa = datosEmpresa.getNombreEmpresaPorId(idEmpresa);
				hm.put("nombreEmpresa", nombreEmpresa);

				
				//CUENTAS INGRESOS
				if(cuentasIngresosMayor > 0 && cuentasIngresosMenor > 0) {
					int idCuentaMayor = 0, idCuentaMenor = 0;
					
					double subTotalMinuendoAcumulado = 0, subTotalSustraendoAcumulado = 0, totalIngresosAcumulado = 0, 
					subTotalMinuendoAlMes = 0, subTotalSustraendoAlMes = 0, totalAlMes = 0; 
					
					ArrayList<Tbl_cuentaContable> cuentaContableMinuendo = new ArrayList<Tbl_cuentaContable>();
					ArrayList<HistoricoSaldos> cuentaDetalleMinuendo = new ArrayList<HistoricoSaldos>();
					
					ArrayList<Tbl_cuentaContable> cuentaContableSustraendo = new ArrayList<Tbl_cuentaContable>();
					ArrayList<HistoricoSaldos> cuentaDetalleSustraendo = new ArrayList<HistoricoSaldos>();
					
					for(int x = 0; x < cuentasIngresosMayor; x++){
						Tbl_cuentaContable cuenta = new Tbl_cuentaContable();
						
						if(request.getParameter("IMayor"+(x+1)+"") != null) {
							idCuentaMayor = Integer.parseInt(request.getParameter("IMayor"+(x+1)+""));
							cuenta = cuentaDatos.getCuentaContableByIdTable(idCuentaMayor);
							cuentaContableMinuendo.add(cuenta);
						}
					}
					
					for(int x = 0;  x < cuentasIngresosMenor; x++) {
						Tbl_cuentaContable cuenta = new Tbl_cuentaContable();
						
						if(request.getParameter("IMenor"+(x+1)+"") != null) {
							idCuentaMenor = Integer.parseInt(request.getParameter("IMenor"+(x+1)+""));
							cuenta = cuentaDatos.getCuentaContableByIdTable(idCuentaMenor);
							cuentaContableSustraendo.add(cuenta);
						}
					}
					
					//Obteniendo detalles
					for(Tbl_cuentaContable cuenta: cuentaContableMinuendo) {
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						cuentaDetalleMinuendo.add(dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha));					
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 9);
					}
					
					for(Tbl_cuentaContable cuenta: cuentaContableSustraendo) {
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						cuentaDetalleSustraendo.add(dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha));			
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 9);
					}
					
					//Obteniendo subtotales minuendo
					for(int x = 0; x < cuentaContableMinuendo.size(); x++) {
						if(cuentaDetalleMinuendo.get(x).getSaldoFinal() == 0) {
							double debe = 0, haber = 0, saldoInicial = 0; 
							
							//OBTENIENDO ACUMULADO A LA FECHA
							debe = cuentaDetalleMinuendo.get(x).getDebe();
							haber = cuentaDetalleMinuendo.get(x).getHaber();
							saldoInicial = cuentaDetalleMinuendo.get(x).getSaldoInicial();
							
							subTotalMinuendoAcumulado += (saldoInicial + debe) - haber; 
							
							debe = haber = saldoInicial = 0; 
							
							//OBTENIENDO ACUMULADO AL MES
							debe = cuentaDetalleMinuendo.get(x).getDebe();
							haber = cuentaDetalleMinuendo.get(x).getHaber();
							
							subTotalMinuendoAlMes += debe - haber; 
							
							debe = haber = saldoInicial = 0;
						} else {
							//OBTENIENDO ACUMULADO A LA FECHA
							subTotalMinuendoAcumulado += cuentaDetalleMinuendo.get(x).getSaldoFinal();
							
							//OBTENIENDO ACUMULADO AL MES
							subTotalMinuendoAlMes += cuentaDetalleMinuendo.get(x).getDebe() - cuentaDetalleMinuendo.get(x).getHaber();
						}
					}
					
					//Obteniendo subtotales sustraendo
					for(int x = 0; x < cuentaContableSustraendo.size(); x++) {
						if(cuentaDetalleSustraendo.get(x).getSaldoFinal() == 0) {
							double debe = 0, haber = 0, saldoInicial = 0; 
							
							//OBTENIENDO ACUMULADO A LA FECHA
							debe = cuentaDetalleSustraendo.get(x).getDebe();
							haber = cuentaDetalleSustraendo.get(x).getHaber();
							saldoInicial = cuentaDetalleSustraendo.get(x).getSaldoInicial();
							
							subTotalSustraendoAcumulado += (saldoInicial + debe) - haber; 
							
							debe = haber = saldoInicial = 0; 
							
							//OBTENIENDO ACUMULADO AL MES
							debe = cuentaDetalleSustraendo.get(x).getDebe();
							haber = cuentaDetalleSustraendo.get(x).getHaber();
							
							subTotalSustraendoAlMes += debe - haber; 
							
							debe = haber = saldoInicial = 0; 
						} else {
							//OBTENIENDO ACUMULADO A LA FECHA
							subTotalSustraendoAcumulado += cuentaDetalleSustraendo.get(x).getSaldoFinal();
							
							//OBTENIENDO ACUMULADO AL MES
							subTotalSustraendoAlMes += cuentaDetalleSustraendo.get(x).getDebe() - cuentaDetalleSustraendo.get(x).getHaber();
						}
					}
					
					totalIngresosAcumulado = subTotalMinuendoAcumulado - subTotalSustraendoAcumulado; 
					totalAlMes = subTotalMinuendoAlMes - subTotalSustraendoAlMes; 
					
					// Agregar los datos al HashMap para Jasper
					hm.put("margenBruto", totalAlMes);
					hm.put("ibAcumulado", totalIngresosAcumulado);
					
					
					totalMes += totalAlMes; 
					totalFecha += totalIngresosAcumulado; 
					
					for(int x = 0; x < cuentaContableMinuendo.size(); x++) {
						System.out.print("Nombre de la cuenta: " + cuentaContableMinuendo.get(x).getNombreCuenta() + " Saldo final: " + cuentaDetalleMinuendo.get(x).getSaldoInicial());
					}
					
					System.out.print("Sub Total Minuendo al mes: "+ subTotalMinuendoAlMes +"Sub Total Minuendo: " + subTotalMinuendoAcumulado);
					
					for(int x = 0; x < cuentaContableSustraendo.size(); x++) {
						System.out.print("Nombre de la cuenta: " + cuentaContableSustraendo.get(x).getNombreCuenta() + " Saldo final: " + cuentaDetalleSustraendo.get(x).getSaldoInicial());
					}
					
					System.out.print("Sub Total Sustraendo Al mes"+ subTotalSustraendoAlMes +"Sub Total Minuendo: " + subTotalSustraendoAcumulado);
					
					System.out.print("INGRESOS - Total Al Mes: "+ totalAlMes + "Total ingresos: " + totalIngresosAcumulado);
					
					
				}
				
				//CUENTAS GASTOS DE OPERACI�N
				if(request.getParameter("GastosGenerales_Total") != null) {
					cuentasGastosGenerales = Integer.parseInt(request.getParameter("GastosGenerales_Total"));
				} else {
					cuentasGastosGenerales = 0; 
				}
				
				if(cuentasGastosGenerales > 0) {
					int idCuenta = 0;
					
					double totalAcumulado = 0, totalAlMes = 0; 
					
					ArrayList<Tbl_cuentaContable> cuentaContable = new ArrayList<Tbl_cuentaContable>();
					ArrayList<HistoricoSaldos> cuentaDetalle = new ArrayList<HistoricoSaldos>();
					
					for(int x  = 0; x < cuentasGastosGenerales; x++) {
						Tbl_cuentaContable cuenta = new Tbl_cuentaContable();
						
						if(request.getParameter("GG"+(x+1)+"") != null){
							idCuenta = Integer.parseInt(request.getParameter("GG"+(x+1)+""));
							cuenta = cuentaDatos.getCuentaContableByIdTable(idCuenta);
							cuentaContable.add(cuenta);
						}
					}
					
					//Obteniendo detalles
					for(Tbl_cuentaContable cuenta: cuentaContable) {
						accountsIdentifiers.add(cuenta.getIdCuenta());
						
						cuentaDetalle.add(dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha));	
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 10);
					}
					
					//Obteniendo totales
					for(int x = 0; x < cuentaContable.size(); x++) {
						if(cuentaDetalle.get(x).getSaldoFinal() == 0) {
							//Obteniendo acumulado a la fecha
							double debe = 0, haber = 0, saldoInicial = 0; 
							
							debe = cuentaDetalle.get(x).getDebe(); 
							haber = cuentaDetalle.get(x).getHaber();
							saldoInicial = cuentaDetalle.get(x).getSaldoInicial();
							
							totalAcumulado += (saldoInicial + debe) - haber; 

							debe = haber = saldoInicial = 0; 
							
							//Obteniendo aumulado al mes
							
							debe = cuentaDetalle.get(x).getDebe(); 
							haber = cuentaDetalle.get(x).getHaber();
							
							totalAlMes += debe - haber; 
							
							debe = haber = saldoInicial = 0; 
						} else {
							//Obteniendo acumulado a la fecha
							totalAcumulado += cuentaDetalle.get(x).getSaldoFinal();
							
							//Obteniendo acumulado al mes
							totalAlMes += cuentaDetalle.get(x).getDebe() - cuentaDetalle.get(x).getHaber();
						}
					}
					
					
					if(totalMes < 0 && totalAlMes < 0) {
						totalMes = totalMes + totalAlMes; 
					}else {
						totalMes -= totalAlMes; 
					}
						
					totalFecha -= totalAcumulado; 
					for(int x = 0; x < cuentaDetalle.size(); x++) {
						System.out.print("Nombre de la cuenta: " + cuentaContable.get(x).getNombreCuenta() + " Saldo final: " + cuentaDetalle.get(x).getSaldoInicial());
					}
					
					System.out.print("GASTOS - Total Al Mes: "+ totalAlMes + "Total ingresos: " + totalAcumulado);
					
					// Agregar los datos de gastos de operación al HashMap
					hm.put("totalGastosOperacion", totalAcumulado);
					hm.put("goAcumulado", totalAlMes);
				}
				
				//CUENTAS DE OTROS INGRESOS Y EGRESOS
				if(request.getParameter("Ingresos_Total") != null) {
					cuentasOtroIngreso = Integer.parseInt(request.getParameter("Ingresos_Total")); 
				} else {
					cuentasOtroIngreso = 0; 
				}
				
				if(cuentasOtroIngreso > 0) {
					int idCuenta = 0;
					
					double totalAcumuladoFecha = 0, totalAcumuladoAlMes = 0; 
					
					ArrayList<Tbl_cuentaContable> cuentaContableIngresosYEgresos = new ArrayList<Tbl_cuentaContable>();
					ArrayList<HistoricoSaldos> cuentaDetalleIngresos = new ArrayList<HistoricoSaldos>();
					
					for(int x = 0; x < cuentasOtroIngreso; x++) {
						Tbl_cuentaContable cuenta = new Tbl_cuentaContable();
						
						if(request.getParameter("Ingresos"+(x+1)+"") != null) {
							idCuenta = Integer.parseInt(request.getParameter("Ingresos"+(x+1)+""));
							cuenta = cuentaDatos.getCuentaContableByIdTable(idCuenta);
							cuentaContableIngresosYEgresos.add(cuenta);
						}
					}
					//OBTENIENDO DETALLES
					for(Tbl_cuentaContable cuenta: cuentaContableIngresosYEgresos) {
						accountsIdentifiers.add(cuenta.getIdCuenta());
						cuentaDetalleIngresos.add(dt_historico.ObtenerCuentaHistorico(cuenta.getIdCuenta(), idEmpresa, fecha));
						cuentaDatos.modificarSubTipoCuenta(cuenta.getIdCuenta(), 11);
					}
					
					//OBTENIENDO TOTALES
					for(int x = 0; x < cuentaContableIngresosYEgresos.size(); x++) {
						if(cuentaDetalleIngresos.get(x).getSaldoFinal() == 0) {
							double debe = 0, haber = 0, saldoInicial = 0;
							
							//Obteniendo acumulado a la fecha
							debe = cuentaDetalleIngresos.get(x).getDebe(); 
							haber = cuentaDetalleIngresos.get(x).getHaber(); 
							saldoInicial = cuentaDetalleIngresos.get(x).getSaldoInicial(); 
							
							totalAcumuladoFecha += (saldoInicial + debe) - haber; 
							
							debe = haber = saldoInicial = 0; 
							
							//Obteniendo acumulado a al mes
							
							debe = cuentaDetalleIngresos.get(x).getDebe(); 
							haber = cuentaDetalleIngresos.get(x).getHaber(); 
							
							totalAcumuladoAlMes += debe - haber; 
							
							debe = haber = saldoInicial = 0; 
						} else {
							totalAcumuladoFecha += cuentaDetalleIngresos.get(x).getSaldoFinal();
							
							//Obteniendo acumulado al mes; 
							totalAcumuladoAlMes += cuentaDetalleIngresos.get(x).getDebe() - cuentaDetalleIngresos.get(x).getHaber(); 
							
						}
					}
		
					 
					 totalMes -= totalAcumuladoAlMes; 
					 totalFecha -= totalAcumuladoFecha;
					 
					 for(int x = 0; x < cuentaContableIngresosYEgresos.size(); x++) {
							System.out.print("Nombre de la cuenta: " + cuentaContableIngresosYEgresos.get(x).getNombreCuenta() + " Saldo final: " + cuentaDetalleIngresos.get(x).getSaldoInicial());
						}
						
						System.out.print("Sub Total ingresos al mes: "+ totalAcumuladoAlMes +"Sub Total ingresos: " + totalAcumuladoFecha);
						
						// Agregar los datos de otros ingresos y egresos al HashMap
						hm.put("totalIngresosEgresos", totalAcumuladoFecha);
						hm.put("ieAcumulado", totalAcumuladoAlMes);
						
						//Obteniendo rango de fechas
						String fechaInicio = "", fechaFinal = "";
						
						ArrayList<HistoricoSaldos> HistoricoFechas = new ArrayList<HistoricoSaldos>();
						
						HistoricoFechas = dt_historico.ObtenerHistoricoFechas(idEmpresa);
						
						for(int x  = 0; x < HistoricoFechas.size(); x++) {
							try {
								if(idFecha == HistoricoFechas.get(x).getIdHistorico()) {
									fechaInicio = HistoricoFechas.get(x).getFecha();
									fechaFinal = HistoricoFechas.get(x+1).getFecha();
									
									hm.put("fechaInicio", fechaInicio);
									hm.put("fechaFin", fechaFinal);
								}
							}catch(Exception ex) {
								fechaInicio = HistoricoFechas.get(x).getFecha();
								
								hm.put("fechaInicio", fechaInicio);
							}
						}
						
						
						for(int x = 0; x < accountsIdentifiers.size(); x++) {
							if(x == 0) {
								concatKeys = concatKeys + " idCuenta = "+accountsIdentifiers.get(x)+" and fecha = '"+fecha+"' ";
							} else {
								concatKeys = concatKeys + " OR idCuenta = "+accountsIdentifiers.get(x)+" and fecha = '"+fecha+"' ";
							}
						}
						
						hm.put("whereQuery",concatKeys + "ORDER BY id ASC");
						
				}
				
				System.out.print("TOTAL DEL MES: " + totalMes + " TOTAL ACUMULADO: " + totalFecha);
				
				System.out.print("ID'S: " + concatKeys);
				
				//Totalizando en cuenta 
				
				Tbl_cuentaContable cuentaTotalizadora = new Tbl_cuentaContable();
				
				cuentaTotalizadora = cuentaDatos.getCuentaContableByIdTable(cuentaTotalizdora);
				
				Tbl_cuentaContable_Det detalleTotal = new Tbl_cuentaContable_Det();
				
				detalleTotal = cuentaDatosDetalles.getDetalleByIdCuenta(cuentaTotalizadora.getIdCuenta());
				
				detalleTotal.setSaldoFinal(totalFecha);
				
				cuentaDatosDetalles.editarCuentaContableDet(detalleTotal);
				break; 
			}
			
			/**
			 * Generación del reporte - Estado de resultados
			 */
			
			poolConexion pc = poolConexion.getInstance();
			Connection c = poolConexion.getConnection();
			
			OutputStream outputSt = response.getOutputStream();
			ServletContext slContext = getServletContext();
			
			String path = slContext.getRealPath("/");
			System.out.println("PATH: " + path);
			
			String template = "reportes\\ER.jasper";
			Exporter exporter = new JRPdfExporter();
			JasperPrint jasperPrint = JasperFillManager.fillReport(path + template, hm, c);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=\"" + "EstadoDeResultados" + "_.pdf");
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputSt));
			exporter.exportReport();
			

		} catch (Exception e) {
			System.err.println("ERROR AL ESTADO DE RESULTADOS: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
