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

import entidades.Tbl_periodoFiscal;
import datos.Dt_periodoFiscal;

@WebServlet("/Sl_periodoFiscal")
public class Sl_periodoFiscal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Sl_periodoFiscal() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		Tbl_periodoFiscal periodofiscal = new Tbl_periodoFiscal();
		Dt_periodoFiscal dpf = new Dt_periodoFiscal();
		
		//fechaInicio
		try {
            String fechaInicio = request.getParameter("fechaInicio").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Date fInicio = formatter.parse(fechaInicio);

            java.sql.Date sqlDate = new java.sql.Date(fInicio.getTime());

            periodofiscal.setFechaInicio(sqlDate);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //fechaFinal
		try {
            String fechaFinal = request.getParameter("fechaFinal").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

            Date fFinal = formatter.parse(fechaFinal);

            java.sql.Date sqlDate = new java.sql.Date(fFinal.getTime());

            periodofiscal.setFechaInicio(sqlDate);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		switch(opc) {
		case 1:
			try {
				if(dpf.agregarPeriodoFiscal(periodofiscal)) {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=1");
				}else {
					response.sendRedirect("production/tbl_periodoFiscal.jsp?msj=2");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_periodoFiscal opc1: "+e.getMessage());
			}
			break;
		case 2:
			break;
		default:
			break;
		}		
	}
}
