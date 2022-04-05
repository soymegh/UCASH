package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_rol;
import entidades.Tbl_rol;

/**
 * Servlet implementation class SL_rol
 */
@WebServlet("/Sl_rol")
public class Sl_rol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_rol() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));

		Tbl_rol rol = new Tbl_rol();
		Dt_rol dtr = new Dt_rol();

		rol.setNombre(request.getParameter("txtNombre"));
		rol.setDescripcion(request.getParameter("descripcion"));
		rol.setEstado(1);

		switch (opc) {
		case 1:
			try {
				if (dtr.addRol(rol)) {
					response.sendRedirect("production/tbl_rol.jsp?msj=1");
				} else {
					response.sendRedirect("production/tbl_rol.jsp?msj=2");
				}

			} catch (Exception e) {
				System.err.println("ERROR AGREGAR (Servlet) ROL: " + e.getMessage());
				e.printStackTrace();
			}

			break;

		default:
			break;
		}
	}

}
