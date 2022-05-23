package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_asientoContable;
import datos.Dt_asientoContableDet;
import entidades.Tbl_asientoContable;
import entidades.Tbl_asientoContableDet;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


@WebServlet("/Sl_asientoContable")
public class Sl_asientoContable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Sl_asientoContable() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=iso-8859-1");
		request.setCharacterEncoding("iso-8859-1");
		
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));

		Dt_asientoContable dtsc = new Dt_asientoContable();
		Dt_asientoContableDet dtscd = new Dt_asientoContableDet();
		Tbl_asientoContable ac = new Tbl_asientoContable();
		Tbl_asientoContableDet acd = new Tbl_asientoContableDet();
		
		//Asiento Contable
		Date fechaSistema = new Date();
		
		try {

			String fecha = request.getParameter("fecha").toString();
			java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
			ac.setFecha(new java.sql.Date(date2.getTime()));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ac.setDescripcion(request.getParameter("descripcion"));
		
		//Asiento Contable detalle
		String jsonAsientoContable = request.getParameter("asientoJSON").toString();
		boolean detalleGuardado = false;
		
		JSONParser parser = new JSONParser();
		
		switch (opc) {
		case 1:
			try {
				
				ac.setIdPeriodoContable(1);
				ac.setIdEmpresa(14);
				ac.setIdMoneda(2);
				ac.setIdTipoDocumento(Integer.parseInt(request.getParameter("cbxIDTD")));		
				ac.setIdTasaCambioDet(3);
				ac.setUsuarioCreacion(1);
				ac.setFechaCreacion(new java.sql.Timestamp(fechaSistema.getTime()));
				
				int newAsientoID = dtsc.agregarAsientoContable(ac);
				
				if (newAsientoID > 0) {
					
					// Registrar el detalle del asiento contable
					
					try {
						
						Object obj = parser.parse(jsonAsientoContable);
						JSONArray arrayDetalles = (JSONArray)obj;
						
						for (int i = 0; i < arrayDetalles.size(); i++) {
							JSONObject detalle = (JSONObject)arrayDetalles.get(i);
							
							acd.setDebe(Integer.parseInt(detalle.get("Debe").toString()));
							acd.setHaber(Integer.parseInt(detalle.get("Haber").toString()));
							acd.setIdCuenta(Integer.parseInt(request.getParameter("cbxdtcc")));
							acd.setIdAsientoContable(newAsientoID);
							
							detalleGuardado = dtscd.guardarAsientoContableDet(acd);
						}
						
					} catch (org.json.simple.parser.ParseException e1) {
						
						e1.printStackTrace();
						
					}
					
					if (detalleGuardado)
						response.sendRedirect("production/addAsientoContable.jsp?msj=1");
				} else {
					response.sendRedirect("production/addAsientoContable.jsp?msj=2");
				}
			} catch (Exception e) {
				System.out.println("Error Sl_asientoContable opc1: " + e.getMessage());
			}
			break;
		case 2:

			try {
				if (dtsc.agregarAsientoContable(ac) < 0) {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=3");
				} else {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=4");
				}
			} catch (Exception e) {
				System.err.println("ERROR EDITAR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 3:

			int idBorrar = Integer.parseInt(request.getParameter("idAContableEliminar"));

			try {

				if (dtsc.EliminarAContablePorId(idBorrar)) {
					ac.setUsuarioEliminacion(1);
					ac.setFechaEliminacion(new java.sql.Timestamp(fechaSistema.getTime()));
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=5");

				} else {

					response.sendRedirect("production/tbl_periodoContable.jsp?msj=6");

				}

			} catch (Exception e) {
				System.err.println("ERROR ELIMINAR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}

			break;

		default:
			break;
		}
		
	}

}
