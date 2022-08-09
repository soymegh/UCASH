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
import datos.poolConexion;

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
				int totalActivosCirculante = 0, totalActivosFijos = 0, totalActivosDiferidos = 0, totalPasivoCirculante = 0,
				totalCapitalNeto = 0; 
				
				
				double totalAC = 0, totalAF = 0, totalAD = 0, totalPC = 0, totalCN = 0;
				
				double saldoInicial = 0, saldoFinal = 0, debe = 0, haber = 0; 
				
				ArrayList<Tbl_cuentaContable> listaCuentaAC = new ArrayList<Tbl_cuentaContable>();
				ArrayList<Tbl_cuentaContable_Det> listaCuentaDetallesAC = new ArrayList<Tbl_cuentaContable_Det>();
				
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
						listaCuentaDetallesAC.add(
								cuentaDatosDetalles.getDetalleByIdCuenta(cuenta.getIdCuenta())
						);				
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
				
				ArrayList<Tbl_cuentaContable> listaCuentaAF = new ArrayList<Tbl_cuentaContable>();
				ArrayList<Tbl_cuentaContable_Det> listaCuentaDetallesAF = new ArrayList<Tbl_cuentaContable_Det>();
				
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
						listaCuentaDetallesAF.add(
								cuentaDatosDetalles.getDetalleByIdCuenta(cuenta.getIdCuenta())
						);				
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
				
				
				ArrayList<Tbl_cuentaContable> listaCuentaAD = new ArrayList<Tbl_cuentaContable>();
				ArrayList<Tbl_cuentaContable_Det> listaCuentaDetallesAD = new ArrayList<Tbl_cuentaContable_Det>();
				
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
						listaCuentaDetallesAD.add(
								cuentaDatosDetalles.getDetalleByIdCuenta(cuenta.getIdCuenta())
						);				
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
				
				ArrayList<Tbl_cuentaContable> listaCuentaPC = new ArrayList<Tbl_cuentaContable>();
				ArrayList<Tbl_cuentaContable_Det> listaCuentaDetallesPC = new ArrayList<Tbl_cuentaContable_Det>();
				
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
						listaCuentaDetallesPC.add(
								cuentaDatosDetalles.getDetalleByIdCuenta(cuenta.getIdCuenta())
						);				
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
				
				
				ArrayList<Tbl_cuentaContable> listaCuentaCN = new ArrayList<Tbl_cuentaContable>();
				ArrayList<Tbl_cuentaContable_Det> listaCuentaDetallesCN = new ArrayList<Tbl_cuentaContable_Det>();
				
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
						listaCuentaDetallesCN.add(
								cuentaDatosDetalles.getDetalleByIdCuenta(cuenta.getIdCuenta())
						);				
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
				
				
				
				for(int x = 0; x < listaCuentaAC.size(); x++) {
					System.out.print("Activo Circulante - Id cuenta:" + listaCuentaAC.get(x).getIdCuenta() + " Nombre de la cuenta: " + listaCuentaAC.get(x).getNombreCuenta() + " Saldo Inicial: " + listaCuentaDetallesAC.get(x).getSaldoInicial());
				}
				
				System.out.print("Total Activo Circulante: " + totalAC);
				
				for(int x = 0; x < listaCuentaAF.size(); x++) {
					System.out.print("Activo Fijo - Id cuenta:" + listaCuentaAF.get(x).getIdCuenta() + " Nombre de la cuenta: " + listaCuentaAF.get(x).getNombreCuenta() + " Saldo Inicial: " + listaCuentaDetallesAF.get(x).getSaldoInicial());
				}
				
				System.out.print("Total Activo Fijo: " + totalAF);
				
				for(int x = 0; x < listaCuentaAD.size(); x++) {
					System.out.print("Activo Diferido - Id cuenta:" + listaCuentaAD.get(x).getIdCuenta() + " Nombre de la cuenta: " + listaCuentaAD.get(x).getNombreCuenta() + " Saldo Inicial: " + listaCuentaDetallesAD.get(x).getSaldoInicial());
				}
				
				System.out.print("Total Activo Diferido: " + totalAD);
				
				for(int x = 0; x < listaCuentaPC.size(); x++) {
					System.out.print("Pasivo Circulante - Id cuenta:" + listaCuentaPC.get(x).getIdCuenta() + " Nombre de la cuenta: " + listaCuentaPC.get(x).getNombreCuenta() + " Saldo Inicial: " + listaCuentaDetallesPC.get(x).getSaldoInicial());
				}
				
				System.out.print("Total Pasivo Circulante: " + totalPC);
				
				for(int x = 0; x < listaCuentaCN.size(); x++) {
					System.out.print("Capital Neto - Id cuenta:" + listaCuentaCN.get(x).getIdCuenta() + " Nombre de la cuenta: " + listaCuentaCN.get(x).getNombreCuenta() + " Saldo Inicial: " + listaCuentaDetallesCN.get(x).getSaldoInicial());
				}
				
				System.out.print("Total Capital Neto: " + totalCN);
				
				
				System.out.print("Total Activos: " + (totalAC + totalAF + totalAD) + "Total Pasivos y Capital: " + (totalPC + totalCN));
				break; 
			}

		} catch (Exception e) {
			System.err.println("ERROR AL GENERAR RESUMEN DE ASIENTOS CONTABLES: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
