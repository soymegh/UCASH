package servlets;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_catalogocuenta;
import entidades.Tbl_catalogocuenta;

/**
 * Servlet implementation class SL_empresa
 */
@WebServlet("/Sl_catalogoCuenta")
public class Sl_catalogoCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_catalogoCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));

		Tbl_catalogocuenta catalogoC = new Tbl_catalogocuenta();
		Dt_catalogocuenta dtcc = new Dt_catalogocuenta();

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		long Millis = System.currentTimeMillis();
		java.sql.Date Date = new java.sql.Date(Millis);
		// Se tienen que usar los ID de los formularios Add
		
		switch(opc) {
		case 1:
			int IdEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
			catalogoC.setIdEmpresa(IdEmpresa);
			catalogoC.setTitulo(request.getParameter("titulo"));
			catalogoC.setDescripcion(request.getParameter("descripcion"));
			catalogoC.setFecha(date);
			catalogoC.setFechaCreacion(Date);
			catalogoC.setUsuarioCreacion(1);
		
				try {
					if (dtcc.addCatalogocuenta(catalogoC)) {
						response.sendRedirect("production/tbl_catalogocuenta.jsp?msj=1");
					} else {
						response.sendRedirect("production/tbl_catalogocuenta.jsp?msj=2");

					}
				} catch (Exception e) {
					System.out.println("Error al guardar un catalogo de cuenta: " + e.getMessage());
					e.printStackTrace();
				}
				break;
		case 2: 
			int idCatalogocuenta = Integer.parseInt(request.getParameter("IdCatalogo"));
			int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));  
			
			catalogoC.setIdCatalogo(idCatalogocuenta);
			catalogoC.setIdEmpresa(idEmpresa); 
			
			catalogoC.setTitulo(request.getParameter("titulo")); 
			catalogoC.setDescripcion(request.getParameter("descripcion")); 
			catalogoC.setUsuarioModificacion(2); 
			catalogoC.setFechaModificacion(date); 
			
			try {
				if(dtcc.modificarCatalogo(catalogoC)) { 
					response.sendRedirect("production/tbl_catalogocuenta.jsp?msj=3"); 
				} else {
					response.sendRedirect("production/tbl_catalogocuenta.jsp?msj=4"); 

				}
			} catch(Exception e) {
				System.out.println("Error al modificar catalogo de cuenta opc2: "+e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 3:
			catalogoC.setIdCatalogo(Integer.parseInt(request.getParameter("IdCatalogo")));
			try {
				if (dtcc.deleteCatalogo(catalogoC)){
					response.sendRedirect("production/tbl_catalogocuenta.jsp?msj=5");
				} else {
					response.sendRedirect("production/tbl_catalogocuenta.jsp?msj=6");
				}
			} catch(Exception e) {
				System.out.println("ERROR en Sl_catalogoCuenta "+ e.getMessage());
				e.printStackTrace();
			}
			break;		
		default:
			break;
		}
	}
}