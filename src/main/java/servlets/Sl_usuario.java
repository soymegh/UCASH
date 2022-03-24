package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_usuario;
import entidades.Tbl_usuario;

/**
 * Servlet implementation class Sl_usuario
 */
@WebServlet("/Sl_usuario")
public class Sl_usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_usuario() {
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
		
		Tbl_usuario User = new Tbl_usuario();
		Dt_usuario dtu = new Dt_usuario();
		
		User.setUsuario(request.getParameter("usuario"));
		User.setPwd(request.getParameter("pwd"));
		User.setNombre(request.getParameter("nombres"));
		User.setApellidos(request.getParameter("apellidos"));
		User.setEmail(request.getParameter("email"));
		User.setEstado(1);
		
		switch (opc) {
		case 1:
				try {
						if(dtu.addUsuario(User)) {
							response.sendRedirect("production/tbl_usuario.jsp?msj=1");
						} else {
							response.sendRedirect("production/tbl_usuario.jsp?msj=2");
						}
					
				} catch (Exception e) {
					System.out.println("ERROR Sl_usuario opc1: "+e.getMessage());
					e.printStackTrace();
				}
			
			break;

		default:
			break;
		}
	}
	
}
