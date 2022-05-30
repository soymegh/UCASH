package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.search.IntegerComparisonTerm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_periodoContable;
import entidades.Tbl_periodoContable;

@WebServlet("/Sl_periodoContable")
public class Sl_periodoContable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sl_periodoContable() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		Tbl_periodoContable periodocontable = new Tbl_periodoContable();
		Dt_periodoContable dpc = new Dt_periodoContable();

		// Fecha Inicio del Periodo Contable
		try {
			
			if(request.getParameter("fechainicioc") != null) {
				String fechaIniPCJsp = request.getParameter("fechainicioc").toString();
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPCJsp);
				periodocontable.setFechaInicio(new java.sql.Date(date1.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Fecha Final del Periodo Contable
		try {
			if(request.getParameter("fechafinalc") != null) {
				String fechaFinPCJsp = request.getParameter("fechafinalc").toString();
				java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPCJsp);
				periodocontable.setFechaFinal(new java.sql.Date(date2.getTime()));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		switch (opc) {
		case 1:
			try {
				periodocontable.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("cbxIDPF")));
				if (dpc.agregarPeriodoContable(periodocontable)) {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=1");
				} else {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=2");
				}
			} catch (Exception e) {
				System.out.println("Error Sl_periodoContable opc1: " + e.getMessage());
			}
			break;
		case 2:
			periodocontable.setIdPeriodoContable(Integer.parseInt(request.getParameter("txtidpcontable")));
			periodocontable.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("cbxIDPF")));
			try {
				if (dpc.modificarPeriodoContable(periodocontable)) {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=3");
				} else {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=4");
				}
			} catch (Exception e) {
				System.err.println("ERROR EDITAR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 3:

			int idBorrar = Integer.parseInt(request.getParameter("idPContableEliminar"));

			try {

				if (dpc.EliminarPContablePorId(idBorrar)) {

					response.sendRedirect("production/tbl_periodoContable.jsp?msj=5");

				} else {

					response.sendRedirect("production/tbl_periodoContable.jsp?msj=6");

				}

			} catch (Exception e) {
				System.err.println("ERROR ELIMINAR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}

			break;
			
		case 4: 
			int idPeriodoContable = Integer.parseInt(request.getParameter("combobox_periodoContable"));
			try {
				if(dpc.obtenerPContablePorIdLogin(idPeriodoContable)) {
					response.sendRedirect("production/indexMoneda.jsp");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			break; 

		default:
			break;
		}

	}

}