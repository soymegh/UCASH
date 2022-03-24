package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_moneda;
import entidades.Tbl_moneda;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		Tbl_moneda moneda = new Tbl_moneda();
		Dt_moneda dtm = new Dt_moneda();
		
		moneda.setNombre(request.getParameter("nombre"));
		moneda.setSimbolo(request.getParameter("simbolo"));
		moneda.setEstado(1);
		
		switch (opc) {
		case 1:
				try {
						if(dtm.addMoneda(moneda)) {
							response.sendRedirect("production/tbl_moneda.jsp?msj=1");
						} else {
							response.sendRedirect("production/tbl_moneda.jsp?msj=2");
						}
					
				} catch (Exception e) {
					System.out.println("ERROR Sl_moneda opc1: "+e.getMessage());
					e.printStackTrace();
				}
			
			break;

		default:
			break;
		}
	}

}
