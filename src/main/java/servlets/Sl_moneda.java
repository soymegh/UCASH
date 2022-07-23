package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_moneda;
import entidades.PermisoTemporal;
import entidades.Tbl_moneda;
import entidades.Vw_empresa;
import entidades.Vw_usuariorol;

/**
 * Servlet implementation class Sl_moneda
 */
@WebServlet("/Sl_moneda")
public class Sl_moneda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_moneda() {
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

		Tbl_moneda moneda = new Tbl_moneda();
		Dt_moneda dtm = new Dt_moneda();

		// funcion para rescatar fecha actual
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		switch (opc) {
		case 1:
			moneda.setNombre(request.getParameter("txtnombre"));
			moneda.setSimbolo(request.getParameter("txtsimbolo"));
			// moneda.setUsuarioCreacion(40);
			moneda.setUsuarioCreacion(Integer.parseInt(request.getParameter("usuario")));
			moneda.setFechaCreacion(date);
			moneda.setEstado(1);
			try {
				if (dtm.addMoneda(moneda)) {
					response.sendRedirect("production/tbl_moneda.jsp?msj=1");
				} else {
					response.sendRedirect("production/tbl_moneda.jsp?msj=2");
				}

			} catch (Exception e) {
				System.out.println("ERROR Sl_moneda opc1: " + e.getMessage());
				e.printStackTrace();
			}

			break;

		case 2:
			moneda.setIdMoneda(Integer.parseInt(request.getParameter("IdMoneda")));
			moneda.setNombre(request.getParameter("txtnombre"));
			moneda.setSimbolo(request.getParameter("txtsimbolo"));
			// moneda.setUsuarioModificacion(40);
			 moneda.setUsuarioModificacion(Integer.parseInt(request.getParameter("usuario")));
			moneda.setFechaModificacion(date);
			moneda.setEstado(2);
			try {
				if (dtm.modificarMoneda(moneda)) {
					response.sendRedirect("production/tbl_moneda.jsp?msj=3");
				} else {
					response.sendRedirect("production/tbl_moneda.jsp?msj=4");
				}

			} catch (Exception e) {
				System.out.println("ERROR Sl_moneda opc2: " + e.getMessage());
				e.printStackTrace();
			}

			break;

		case 3:
			moneda.setIdMoneda(Integer.parseInt(request.getParameter("IdMoneda")));
			// moneda.setUsuarioEliminacion(40);
			moneda.setUsuarioEliminacion(Integer.parseInt(request.getParameter("usuario")));
			moneda.setFechaEliminacion(date);
			moneda.setEstado(3);
			try {
				if (dtm.eliminarMoneda(moneda)) {
					response.sendRedirect("production/tbl_moneda.jsp?msj=5");
				} else {
					response.sendRedirect("production/tbl_moneda.jsp?msj=6");
				} 
			}catch (Exception e) {
				System.out.println("ERROR Sl_moneda opc3: " + e.getMessage());
				e.printStackTrace();
			}
			
			break;
		case 4:
			int idMoneda = 0;
			int currentCoin = 0; 
			Vw_usuariorol vwur = new Vw_usuariorol();
			
			if(request.getParameter("combobox_moneda") != null && request.getParameter("combobox_moneda").matches("[0-9]")) {
				idMoneda = Integer.parseInt(request.getParameter("combobox_moneda"));
				if(dtm.getMonedaByIDLogin(idMoneda) > 0) {
					currentCoin = dtm.getMonedaByIDLogin(idMoneda);	
					vwur = (Vw_usuariorol) request.getSession(true).getAttribute("acceso");
					vwur.setIdMoneda(currentCoin);
					request.setAttribute("acceso", vwur);
					PermisoTemporal.temporalFlag = false; 
					response.sendRedirect("production/index.jsp");
					System.out.print("Este es el id de la moneda" + Tbl_moneda.idMonedaActual);
				}
			}else {
				response.sendRedirect("production/indexMoneda.jsp?msj=1");
			};	

			break;
		default:
			break;
		}
	
	}
}
