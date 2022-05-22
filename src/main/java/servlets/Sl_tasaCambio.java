package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_tasaCambio;
import entidades.Tbl_tasaCambio;

/**
 * Servlet implementation class Sl_tasaCambio
 */
@WebServlet("/Sl_tasaCambio")
public class Sl_tasaCambio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_tasaCambio() {
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
		
		Tbl_tasaCambio tasacambio = new Tbl_tasaCambio();
		Dt_tasaCambio dtTc = new Dt_tasaCambio();
		
		//funcion para rescatar fecha actual
				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
		
		switch (opc) {
		case 3:
			tasacambio.setId_tasaCambio(Integer.parseInt(request.getParameter("idTasaCambio")));
			try {
				if (dtTc.eliminarTasaCambio(tasacambio)) {
					response.sendRedirect("production/tbl_tasaCambio.jsp?msj=5");
			} else {
					response.sendRedirect("production/tbl_tasaCambio.jsp?msj=6");
			}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR Sl_tasaCambio opc3: "+ e.getMessage());
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

}
