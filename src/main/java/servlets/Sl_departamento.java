package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_departamento;
import entidades.Tbl_departamento;

/**
 * Servlet implementation class Sl_departamento
 */
@WebServlet("/Sl_departamento")
public class Sl_departamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_departamento() {
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
		
		Tbl_departamento dept = new Tbl_departamento();
		Dt_departamento dtDept = new Dt_departamento();
		
		switch (opc) {
		case 1:
				dept.setDepartamento(request.getParameter("txtdepartamento"));
				try {
					if (dtDept.guardarDepartamento(dept)) {
							response.sendRedirect("production/tbl_departamento.jsp?msj=1");
					} else {
							response.sendRedirect("production/tbl_departamento.jsp?msj=2");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERROR Sl_departamento opc1: "+ e.getMessage());
					e.printStackTrace();
				}
			
			break;
		case 2:
			dept.setIdDepartamento(Integer.parseInt(request.getParameter("idDepartamento")));
			dept.setDepartamento(request.getParameter("txtdepartamento"));
			try {
				if (dtDept.modificarDepartamento(dept)) {
						response.sendRedirect("production/tbl_departamento.jsp?msj=3");
				} else {
						response.sendRedirect("production/tbl_departamento.jsp?msj=4");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR Sl_departamento opc2: "+ e.getMessage());
				e.printStackTrace();
			}
		
			break;
			
		case 3:
			dept.setIdDepartamento(Integer.parseInt(request.getParameter("idDepartamento")));
			try {
				if (dtDept.eliminarDepartamento(dept)) {
						response.sendRedirect("production/tbl_departamento.jsp?msj=5");
				} else {
						response.sendRedirect("production/tbl_departamento.jsp?msj=6");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR Sl_departamento opc3: "+ e.getMessage());
				e.printStackTrace();
			}
		
			break;

		default:
			break;
		}
		
	}
	
}
