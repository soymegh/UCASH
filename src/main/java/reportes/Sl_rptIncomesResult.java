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
import datos.Dt_periodoContable;
import datos.poolConexion;
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
		
		Dt_empresa datosEmpresa = new Dt_empresa();
		Dt_cuentaContable cuentaDatos = new Dt_cuentaContable();
		Dt_cuentaContable_Det cuentaDatosDetalles = new Dt_cuentaContable_Det();
		Dt_asientoContable datosAsientoContable = new Dt_asientoContable();
		Dt_periodoContable datosPeriodo = new Dt_periodoContable();
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
				int cuentasIngresosMayor = 0, cuentasIngresosMenor = 0, idEmpresa = 0, idPeriodoContable = 0; 
				
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
				
				//CUENTAS INGRESOS
				if(cuentasIngresosMayor > 0 && cuentasIngresosMenor > 0) {
					int idCuentaMayor = 0, idCuentaMenor = 0;
					
					double subTotalMinuendoAcumulado = 0, subTotalSustraendoAcumulado = 0, totalIngresosAcumulado = 0; 
					
					ArrayList<Tbl_cuentaContable> cuentaContableMinuendo = new ArrayList<Tbl_cuentaContable>();
					ArrayList<Tbl_cuentaContable_Det> cuentaDetalleMinuendo = new ArrayList<Tbl_cuentaContable_Det>();
					
					ArrayList<Tbl_cuentaContable> cuentaContableSustraendo = new ArrayList<Tbl_cuentaContable>();
					ArrayList<Tbl_cuentaContable_Det> cuentaDetalleSustraendo = new ArrayList<Tbl_cuentaContable_Det>();
					
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
						cuentaDetalleMinuendo.add(cuentaDatosDetalles.getDetalleByIdCuenta(cuenta.getIdCuenta()));
					}
					
					for(Tbl_cuentaContable cuenta: cuentaContableSustraendo) {
						cuentaDetalleSustraendo.add(cuentaDatosDetalles.getDetalleByIdCuenta(cuenta.getIdCuenta()));
					}
					
					//Obteniendo subtotales minuendo
					for(int x = 0; x < cuentaContableMinuendo.size(); x++) {
						if(cuentaDetalleMinuendo.get(x).getSaldoFinal() == 0) {
							double debe = 0, haber = 0, saldoInicial = 0; 
							
							debe = cuentaDetalleMinuendo.get(x).getDebe();
							haber = cuentaDetalleMinuendo.get(x).getHaber();
							saldoInicial = cuentaDetalleMinuendo.get(x).getSaldoInicial();
							
							subTotalMinuendoAcumulado += (saldoInicial + debe) - haber; 
							
							debe = haber = saldoInicial = 0; 
							
						} else {
							subTotalMinuendoAcumulado += cuentaDetalleMinuendo.get(x).getSaldoFinal();
						}
					}
					
					//Obteniendo subtotales sustraendo
					for(int x = 0; x < cuentaContableSustraendo.size(); x++) {
						if(cuentaDetalleSustraendo.get(x).getSaldoFinal() == 0) {
							double debe = 0, haber = 0, saldoInicial = 0; 
							
							debe = cuentaDetalleSustraendo.get(x).getDebe();
							haber = cuentaDetalleSustraendo.get(x).getHaber();
							saldoInicial = cuentaDetalleSustraendo.get(x).getSaldoInicial();
							
							subTotalSustraendoAcumulado += (saldoInicial + debe) - haber; 
							
							debe = haber = saldoInicial = 0; 
							
						} else {
							subTotalSustraendoAcumulado += cuentaDetalleSustraendo.get(x).getSaldoFinal();
						}
					}
					
					totalIngresosAcumulado = subTotalMinuendoAcumulado + subTotalSustraendoAcumulado; 
					
					
					/*
					for(int x = 0; x < cuentaContableMinuendo.size(); x++) {
						System.out.print("Nombre de la cuenta: " + cuentaContableMinuendo.get(x).getNombreCuenta() + " Saldo final: " + cuentaDetalleMinuendo.get(x).getSaldoInicial());
					}
					
					System.out.print("Sub Total Minuendo: " + subTotalMinuendoAcumulado);
					
					for(int x = 0; x < cuentaContableSustraendo.size(); x++) {
						System.out.print("Nombre de la cuenta: " + cuentaContableSustraendo.get(x).getNombreCuenta() + " Saldo final: " + cuentaDetalleSustraendo.get(x).getSaldoInicial());
					}
					
					System.out.print("Sub Total Minuendo: " + subTotalSustraendoAcumulado);
					
					System.out.print("Total ingresos: " + totalIngresosAcumulado);
					*/
					
				}
				break; 
			}

		} catch (Exception e) {
			System.err.println("ERROR AL ESTADO DE RESULTADOS: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
