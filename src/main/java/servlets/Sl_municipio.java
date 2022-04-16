package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_municipio;
import entidades.Tbl_municipio;

/**
 * Servlet implementation class Sl_municipio
 */
@WebServlet("/Sl_municipio")
public class Sl_municipio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_municipio() {
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
		// TODO Auto-generated method stub
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		Tbl_municipio mun = new Tbl_municipio();
		Dt_municipio dtMun = new Dt_municipio();
		
		switch (opc) {
		case 1:
				mun.setIdDepartamento(Integer.parseInt(request.getParameter("cbxDept")));
				mun.setMunicipio(request.getParameter("txtmunicipio"));
				try {
					if (dtMun.guardarMunicipio(mun)) {
							response.sendRedirect("production/tbl_municipio.jsp?msj=1");
					} else {
							response.sendRedirect("production/tbl_municipio.jsp?msj=2");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERROR Sl_municipio opc1: "+ e.getMessage());
					e.printStackTrace();
				}
			
			break;
		case 2:
			/*mun.setIdMunicipio(Integer.parseInt(request.getParameter("idMunicipio")));
			mun.setIdDepartamento(Integer.parseInt(request.getParameter("idDepartamento")));
			mun.setMunicipio(request.getParameter("municipio"));
			try {
				if (dtMun.modificarMunicipio(mun)) {
						response.sendRedirect("production/tbl_municipio.jsp?msj=3");
				} else {
						response.sendRedirect("production/tbl_municipio.jsp?msj=4");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR Sl_municipio opc2: "+ e.getMessage());
				e.printStackTrace();
			}*/
		
			break;
			
		case 3:
			/*mun.setIdMunicipio(Integer.parseInt(request.getParameter("idMunicipio")));
			try {
				if (dtMun.eliminarMunicipio(mun)) {
						response.sendRedirect("production/tbl_municipio.jsp?msj=5");
				} else {
						response.sendRedirect("production/tbl_municipio.jsp?msj=6");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR Sl_municipio opc3: "+ e.getMessage());
				e.printStackTrace();
			}*/
		
			break;

		default:
			break;
		}
	}

}
