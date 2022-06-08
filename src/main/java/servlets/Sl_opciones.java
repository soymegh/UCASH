package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_opciones;
import entidades.Tbl_opciones;

/**
 * Servlet implementation class Sl_opciones
 */
@WebServlet("/Sl_opciones")
public class Sl_opciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_opciones() {
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
		
		Tbl_opciones opcion = new Tbl_opciones();
		Dt_opciones dtoOpciones = new Dt_opciones();
		
		opcion.setNombreOpcion(request.getParameter("nombreOpcion"));
		opcion.setDescripcion(request.getParameter("descripcion"));
		opcion.setEstado(1);
		
		switch (opc) {
		case 1:
				try {
						if(dtoOpciones.addOpciones(opcion)) {
							response.sendRedirect("production/tbl_opciones.jsp?msj=1");
						} else {
							response.sendRedirect("production/tbl_opciones.jsp?msj=2");
						}
					
				} catch (Exception e) {
					System.out.println("ERROR Sl_opciones opc1: "+e.getMessage());
					e.printStackTrace();
				}
			
			break;
		case 2: 
			
			try {
				opcion.setIdOpciones(Integer.parseInt(request.getParameter("txtId")));
				opcion.setNombreOpcion(request.getParameter("txtOpcion"));
				opcion.setDescripcion(request.getParameter("txtDescripcion"));
				opcion.setEstado(2);
				if(dtoOpciones.modificarOpcion(opcion)) {
					response.sendRedirect("production/tbl_opciones.jsp?msj=3");
				}
				else {
					response.sendRedirect("production/tbl_opciones.jsp?msj=4");
				}
			} catch(Exception e) {
				System.out.println("Error Sl_opciones opc2: "+e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 3:
			opcion.setIdOpciones(Integer.parseInt(request.getParameter("txtId")));
			try {
				if(dtoOpciones.eliminarOpcion(opcion)) {
					response.sendRedirect("production/tbl_opciones.jsp?msj=5");
				}
				else {
					response.sendRedirect("production/tbl_opciones.jsp?msj=6");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_gestionUser opc3: "+e.getMessage());
				e.printStackTrace();
			}
			break;
			
		default:
			break;
		}
	}

}
