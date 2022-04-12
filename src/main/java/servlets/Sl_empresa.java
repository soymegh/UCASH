package servlets;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.Dt_empresa;
import entidades.Tbl_empresa;

/**
 * Servlet implementation class SL_empresa
 */

@WebServlet("/Sl_empresa")
public class Sl_empresa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));

		Tbl_empresa empresa = new Tbl_empresa();
		Dt_empresa dtEmpresa = new Dt_empresa();

		// Se tienen que usar los ID de los formularios Add
		empresa.setRuc(request.getParameter("ruc"));
		empresa.setNombreComercial(request.getParameter("nombreComercial"));
		empresa.setRazonSocial(request.getParameter("razonSocial"));
		empresa.setTelefono(request.getParameter("telefono"));
		empresa.setDireccion(request.getParameter("direccion"));
		empresa.setCorreo(request.getParameter("correo"));
		int representanteLegal = Integer.parseInt(request.getParameter("valueIdR"));
		empresa.setIdRepresentanteLegal(representanteLegal);
		int departamento = Integer.parseInt(request.getParameter("departamento"));
		empresa.setIdDepartamento(departamento);
		int municipio = Integer.parseInt(request.getParameter("municipio"));
		empresa.setIdMunicipio(municipio);

		
		int periodoFiscal = Integer.parseInt(request.getParameter("periodoFiscal"));
		empresa.setIdPeriodoFiscal(periodoFiscal);
		empresa.setUsuarioCreacion(1);

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		empresa.setFechaCreacion(date);

		switch (opc) {
		case 1:
			try {
				if (dtEmpresa.addEmpresa(empresa)) {
					response.sendRedirect("production/tbl_empresa.jsp?msj=1");
				} else {
					response.sendRedirect("production/tbl_empresa.jsp?msj=2");

				}
			} catch (Exception e) {
				System.out.println("Error al guardar empresa: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		default:
			break;

		}

	}

}