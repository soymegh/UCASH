package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_tipocuenta;
import entidades.Tbl_tipocuenta;

@WebServlet("/Sl_tipocuenta")
public class Sl_tipocuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_tipocuenta() {
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
		
		Tbl_tipocuenta tc = new Tbl_tipocuenta();
		Dt_tipocuenta dtTC = new Dt_tipocuenta(); 
		
		
		tc.setTipoCuenta(request.getParameter("tipoCuenta"));
		
	
		


		
		////////////////////////////////////////////////////////////////////
		
		switch(opc) {
		case 1:
			try {
				if(dtTC.addTipocuenta(tc)) {
					response.sendRedirect("production/tbl_tipocuenta.jsp?msj=1");
				}else {
					response.sendRedirect("production/tbl_tipocuenta.jsp?msj=2");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_tipocuenta opc1: "+e.getMessage());
				e.printStackTrace();
			}
			break;

		case 2: 
			int idTipocuenta = Integer.parseInt(request.getParameter("idTipoCuenta"));
			
			
			tc.setIdTipoCuenta(idTipocuenta);
			tc.setTipoCuenta(request.getParameter("tipoCuenta"));
			
		
			try {
				if(dtTC.modificarTipocuenta(tc)) { 
					response.sendRedirect("production/tbl_tipocuenta.jsp?msj=3"); 
				} else {
					response.sendRedirect("production/tbl_tipocuenta.jsp?msj=4"); 

				}
			} catch(Exception e) {
				System.out.println("Error al modificar tipocuenta opc2: "+e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 3:
			tc.setIdTipoCuenta(Integer.parseInt(request.getParameter("idTipoCuenta")));
			try {
				if (dtTC.deleteTipocuenta(tc)){
					response.sendRedirect("production/tbl_tipocuenta.jsp?msj=5");
				} else {
					response.sendRedirect("production/tbl_tipocuenta.jsp?msj=6");
				}
			} catch(Exception e) {
				System.out.println("ERROR en Sl_tipocuenta "+ e.getMessage());
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
	}
	
}