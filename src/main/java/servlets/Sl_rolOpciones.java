package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_rolOpciones;
import datos.Dt_usuarioRol;
import entidades.Tbl_rolOpciones;
import entidades.Tbl_usuarioRol;

/**
 * Servlet implementation class Sl_rolOpciones
 */
@WebServlet("/Sl_rolOpciones")
public class Sl_rolOpciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_rolOpciones() {
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
		
		Tbl_rolOpciones tro = new Tbl_rolOpciones();
		Dt_rolOpciones dtro = new Dt_rolOpciones(); 
		
		
		////////////////////////////////////////////////////////////////////
		
		switch(opc) {
		case 1:
			
			tro.setIdRol(Integer.parseInt(request.getParameter("cbxRol")));
			tro.setIdOpciones(Integer.parseInt(request.getParameter("cbxOpciones")));
			
			try {
				if(dtro.asignarOpcion(tro)) {
					response.sendRedirect("production/tbl_rolOpciones.jsp?msj=1");
				}else {
					response.sendRedirect("production/tbl_rolOpciones.jsp?msj=2");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_rolOpciones opc1: "+e.getMessage());
				e.printStackTrace();
			}
			break;
		case 2:
		
			
			tro.setIdOpciones(Integer.parseInt(request.getParameter("cbxOpciones")));
			tro.setIdRol(Integer.parseInt(request.getParameter("cbxRol")));
			tro.setIdRolOpciones(Integer.parseInt(request.getParameter("idRolOpciones")));
			
			System.out.println("Id de la asginación (SERVLET): "+tro.getIdRolOpciones()+"");
			
			try {
				if(dtro.modificarRolOpcion(tro)) {
					response.sendRedirect("production/tbl_rolOpciones.jsp?msj=3");
				}else {
					response.sendRedirect("production/tbl_rolOpciones.jsp?msj=4");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_usuarioRol opc2: "+e.getMessage());
				e.printStackTrace();
			}
			
			break;
	case 3:
			
			tro.setIdRolOpciones(Integer.parseInt(request.getParameter("idRolOpciones")));
			
			try {
				if(dtro.eliminarRolOpcion(tro.getIdRolOpciones())) {
					response.sendRedirect("production/tbl_rolOpciones.jsp?msj=3");
				}else {
					response.sendRedirect("production/tbl_rolOpciones.jsp?msj=4");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_usuarioRol opc2: "+e.getMessage());
				e.printStackTrace();
			}
			break;
		default:
			//codigo
			break;
			
		}
	}

}
