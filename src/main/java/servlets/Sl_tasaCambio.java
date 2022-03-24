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

import datos.Dt_rolOpciones;
import datos.Dt_tasaCambio;
import entidades.Tbl_rolOpciones;
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
		
		Tbl_tasaCambio tc = new Tbl_tasaCambio();
		Dt_tasaCambio dtTC = new Dt_tasaCambio(); 
		
		tc.setIdMonedaO(Integer.parseInt(request.getParameter("cbxMonedaO")));
		tc.setIdMonedaD(Integer.parseInt(request.getParameter("cbxMonedaD")));
		tc.setTipoCambio(Double.parseDouble(request.getParameter("tipoCambio")));
		tc.setValor(Double.parseDouble(request.getParameter("valor")));
		tc.setEstado(1);
		
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		tc.setFecha(date);
		tc.setFechaCreacion(date);
		tc.setUsuarioCreacion(1);

		
		////////////////////////////////////////////////////////////////////
		
		switch(opc) {
		case 1:
			try {
				if(dtTC.addTasaCambio(tc)) {
					response.sendRedirect("production/tbl_tasaCambio.jsp?msj=1");
				}else {
					response.sendRedirect("production/tbl_tasaCambio.jsp?msj=2");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_rolOpciones opc1: "+e.getMessage());
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
