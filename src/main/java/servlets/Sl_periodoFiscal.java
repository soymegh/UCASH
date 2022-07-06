package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Tbl_periodoFiscal;
import entidades.Vw_periodoContable;
import datos.Dt_periodoContable;
import datos.Dt_periodoFiscal;

@WebServlet("/Sl_periodoFiscal")
public class Sl_periodoFiscal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sl_periodoFiscal() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		Tbl_periodoFiscal periodofiscal = new Tbl_periodoFiscal();
		Dt_periodoFiscal dpf = new Dt_periodoFiscal();

		// fechaInicio

		try {
			if (request.getParameter("fechaInicio") != null) {
				String fechaIniJsp = request.getParameter("fechaInicio").toString();
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniJsp);
				periodofiscal.setFechaInicio(new java.sql.Date(date1.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// fechaFinal

		try {

			if (request.getParameter("fechaFinal") != null) {
				String fechaFinJsp = request.getParameter("fechaFinal");
				java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinJsp);
				periodofiscal.setFechaFinal(new java.sql.Date(date2.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		switch (opc) {
		case 1:
			try {

				if (dpf.agregarPeriodoFiscal(periodofiscal)) {
					int ultimoPeriodoFiscal = dpf.obtenerUltimoPeriodoFiscal();
					int empresa = Integer.parseInt(request.getParameter("empresaActual"));
					if (dpf.agregarPeriodoEmpresa(ultimoPeriodoFiscal, empresa)) {
						response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=1");
					}

				} else {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=2");
				}
			} catch (Exception e) {
				System.out.println("Error Sl_periodoFiscal opc1: " + e.getMessage());
			}
			break;
		case 2:

			periodofiscal.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("idPFUpdate")));

			try {
				if (dpf.modificarPeriodoFiscal(periodofiscal)) {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=3");
				} else {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=4");
				}
			} catch (Exception e) {
				System.err.println("ERROR EDITAR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 3:
			int idBorrar = Integer.parseInt(request.getParameter("idPFiscalEliminar"));
			int empresaActual = Integer.parseInt(request.getParameter("empresaActual"));
			Dt_periodoContable dtpc = new Dt_periodoContable();
			ArrayList<Vw_periodoContable> listaperiodoContable = new ArrayList<Vw_periodoContable>();
			listaperiodoContable = dtpc.listarperiodoContable(empresaActual);

			boolean close = true;
			for (Vw_periodoContable PC : listaperiodoContable) {
				if (idBorrar == PC.getIdPeriodoFiscal()) {
					if (PC.getEstado() < 3) {

						close = false;
					}
				}
			}
			try {
				if (close) {
					if (dpf.EliminarPFiscalPorId(idBorrar)) {
						response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=5");
					}
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=6");
				} else {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=7");
				}
			} catch (Exception e) {
				System.err.println("ERROR Borrar (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		case 4:


			int idPeriodoFiscal = 0;
			String adminPass = ""; 
			
			
			
			if (request.getParameter("combobox_periodoFiscal") != null) {
				idPeriodoFiscal = Integer.parseInt(request.getParameter("combobox_periodoFiscal"));

				try {
					if (dpf.obtenerPFiscalPorIdLogin(idPeriodoFiscal)) {
						if(request.getParameter("admin_pass") != null && !request.getParameter("admin_pass").trim().equals("")) {
							adminPass = request.getParameter("admin_pass"); 
							response.sendRedirect("production/indexPeriodoContable.jsp?status="+adminPass+"");
						}else {
							response.sendRedirect("production/indexPeriodoContable.jsp");
						}
						
					}
					;
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				response.sendRedirect("production/indexPeriodoFiscal.jsp?msj=1");
			}
			;

			break;

		case 5:

			periodofiscal.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("idPFiscalAbrir")));

			try {
				if (dpf.modificarPeriodoFiscal(periodofiscal)) {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=8");
				} else {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=9");
				}
			} catch (Exception e) {
				System.err.println("ERROR ABRIR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}
			break;

		// Caso donde se agrega un nuevo período fiscal desde el index de la
		// multiempresa
		case 6:
			try {
				if (dpf.agregarPeriodoFiscal(periodofiscal)) {
					int ultimoPeriodoFiscal = dpf.obtenerUltimoPeriodoFiscal();
					int empresa = Integer.parseInt(request.getParameter("empresaActual"));
					if (dpf.agregarPeriodoEmpresa(ultimoPeriodoFiscal, empresa)) {
						response.sendRedirect("production/indexPeriodoFiscal.jsp?msj=10");
					}

				} else {
					response.sendRedirect("production/indexPeriodoFiscal.jsp?msj=11");
				}
			} catch (Exception e) {
				System.out.println("Error Sl_periodoFiscal opc6: " + e.getMessage());
			}
			break;

		default:
			break;
		}
	}
}