package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_tipoIdentificacion;
import entidades.Tbl_tipoIdentificacion;

/**
 * Servlet implementation class Sl_tipoIdentificacion
 */
@WebServlet("/Sl_tipoIdentificacion")
public class Sl_tipoIdentificacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_tipoIdentificacion() {
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
		// INSTANCIAMOS LOS OBJETOS
		Tbl_tipoIdentificacion TipIden = new Tbl_tipoIdentificacion();
		
		
		Dt_tipoIdentificacion dtTI = new Dt_tipoIdentificacion();
		 
		// CONSTRUIMOS EL OBJETO CON LOS VALORES DE LOS CONTROLES
		
		TipIden.setTipo(request.getParameter("tipoIdentificacion"));
		
		
		
		////////////////////////////////////////////////////////////////////
		
		switch(opc) {
		
		case 1:
			TipIden.setTipo(request.getParameter("tipoIdentificacion"));
		
			try {
				 if(dtTI.addTipoIdentificacion(TipIden)) {
						response.sendRedirect("production/tbl_TipoIdentificacion.jsp?msj=1");
				 }else {
						response.sendRedirect("production/tbl_TipoIdentificacion.jsp?msj=2");
				 }
				}catch(Exception e) {
				System.out.println("Error Sl_tipoIdentificacion opc1: "+e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 2:
			TipIden.setIdTipoIdentifiacion(Integer.parseInt(request.getParameter("idTipoIdentifiacion")));
			TipIden.setTipo(request.getParameter("tipoIdentificacion"));
			
			try {
				if(dtTI.modificarTipoIdentificaion(TipIden)) {
					response.sendRedirect("production/tbl_TipoIdentificacion.jsp?msj=3");
				}else {
					response.sendRedirect("production/tbl_TipoIdentificacion.jsp?msj=4");
				}
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR Sl_tipoIdentificacion opc2: "+ e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 3:
			TipIden.setIdTipoIdentifiacion(Integer.parseInt(request.getParameter("idTipoIdentifiacion")));
			
			try {
				if (dtTI.eliminarTipoIdentificacion(TipIden)) {
						response.sendRedirect("production/tbl_TipoIdentificacion.jsp?msj=5");
				} else {
						response.sendRedirect("production/tbl_TipoIdentificacion.jsp?msj=6");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR Sl_tipoIdentificacion opc3: "+ e.getMessage());
				e.printStackTrace();
			}
		
			break;
			
		default:
			//codigo
			break;
			
		}
	}
}
