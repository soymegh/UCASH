package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_cuentaContable;
import entidades.Tbl_cuentaContable;

/**
 * Servlet implementation class Sl_cuentaContable
 */
@WebServlet("/Sl_cuentaContable")
public class Sl_cuentaContable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_cuentaContable() {
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
		
		Tbl_cuentaContable cc = new Tbl_cuentaContable();
		Dt_cuentaContable dtCc = new Dt_cuentaContable();
		
		cc.setNumeroCuenta(request.getParameter("numeroCuenta"));
		cc.setsC(request.getParameter("SC"));
		cc.setSsC(request.getParameter("SsC"));
		cc.setSssC(request.getParameter("SssC"));
		cc.setNombreCuenta(request.getParameter("nombreCuenta"));
		int nivel = Integer.parseInt(request.getParameter("nivel"));
		cc.setNivel(nivel);
		int rubro = Integer.parseInt(request.getParameter("rubro"));
		cc.setRubro(rubro);
		int tipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
		cc.setIdTipoCuenta(tipoCuenta);
		int catalogoCuenta = Integer.parseInt(request.getParameter("catalogoCuenta"));
		cc.setIdCatalogo(catalogoCuenta);
		
		switch (opc) {
		
		case 1:
			try
			{
				if(dtCc.addCuentaContable(cc)) {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=1");
				}
				else
				{
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=2");
				}
			}
			catch(Exception e)
			{
				
			}
			
			break;
			
		case 2:
			
			break;
			
		case 3:
			break;
		}
	}

}
