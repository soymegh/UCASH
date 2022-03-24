package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_usuarioRol;
import entidades.Tbl_usuarioRol;

/**
 * Servlet implementation class Sl_usuarioRol
 */
@WebServlet("/Sl_usuarioRol")
public class Sl_usuarioRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_usuarioRol() {
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
		
		Tbl_usuarioRol tur = new Tbl_usuarioRol();
		Dt_usuarioRol dtur = new Dt_usuarioRol(); 
		
		tur.setIdUsuario(Integer.parseInt(request.getParameter("cbxUser")));
		tur.setIdRol(Integer.parseInt(request.getParameter("cbxRol")));
		
		////////////////////////////////////////////////////////////////////
		
		switch(opc) {
		case 1:
			try {
				if(dtur.asignarRol(tur)) {
					response.sendRedirect("production/tbl_usuarioRol.jsp?msj=1");
				}else {
					response.sendRedirect("production/tbl_usuarioRol.jsp?msj=2");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_usuarioRol opc1: "+e.getMessage());
				e.printStackTrace();
			}
			break;
		case 2:
			//codigo
			break;
		default:
			//codigo
			break;
			
		}
	}

}
