package reportes;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_empresa;
import datos.poolConexion;
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
@WebServlet("/Sl_resumenAsientoContable")
public class Sl_resumenAsientoContable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_resumenAsientoContable() {
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
		Vw_empresa emp = new Vw_empresa();
		
		try {

			String idEmpresa = request.getParameter("empresaActual") == null ? "0" : (request.getParameter("empresaActual"));
			emp = datosEmpresa.getEmpresaByID(Integer.parseInt(idEmpresa));
			String nombreEmpresa = emp.getNombreComercial();
			
			String fechaInicialString = request.getParameter("fecha_inicio").toString();
			java.util.Date dateInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicialString);
			Date fechaInicio = new java.sql.Date(dateInicio.getTime());
			
			String fechaFinalString = request.getParameter("fecha_final").toString();
			java.util.Date dateFinal = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinalString);
			Date fechaFinal = new java.sql.Date(dateFinal.getTime());
			

			poolConexion pc = poolConexion.getInstance();
			Connection c = poolConexion.getConnection();

			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("EmpresaID", Integer.parseInt(idEmpresa));
			hashMap.put("FechaInicio", fechaInicio);
			hashMap.put("FechaFinal", fechaFinal);

			OutputStream outputSt = response.getOutputStream();
			ServletContext slContext = getServletContext();

			String path = slContext.getRealPath("/");
			System.out.println("PATH: " + path);

			String template = "reportes\\AsientosContablesPorFecha.jasper";
			Exporter exporter = new JRPdfExporter();
			JasperPrint jasperPrint = JasperFillManager.fillReport(path + template, hashMap, c);

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"inline; filename=\"" + nombreEmpresa + "_ReporteAsientoContable_del_" + fechaInicialString + "_al_" + fechaFinalString + "_.pdf");

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputSt));
			exporter.exportReport();

		} catch (Exception e) {
			System.err.println("ERROR AL GENERAR RESUMEN DE ASIENTOS CONTABLES: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
