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
import datos.Dt_empresa;
import datos.Dt_periodoEmpresa;
import entidades.Tbl_empresa;
import entidades.Tbl_periodoEmpresa;

/**
 * Servlet implementation class SL_empresa
 */

@WebServlet("/Sl_empresa")
public class Sl_empresa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_empresa() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
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

		Tbl_empresa empresa = new Tbl_empresa();
		Dt_empresa dtEmpresa = new Dt_empresa();

		
		Tbl_periodoEmpresa periodoEmpresa = new Tbl_periodoEmpresa(); 
		Dt_periodoEmpresa dtPeriodoEmpresa = new Dt_periodoEmpresa(); 
		// Se tienen que usar los ID de los formularios Add

        
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		switch (opc) {
		case 1:
			
			//Agregar una empresa nueva
			empresa.setRuc(request.getParameter("ruc"));
			empresa.setNombreComercial(request.getParameter("nombreComercial"));
			empresa.setRazonSocial(request.getParameter("razonSocial"));
			empresa.setTelefono(request.getParameter("telefono"));
			empresa.setDireccion(request.getParameter("direccion"));
			empresa.setCorreo(request.getParameter("correo"));
			int representanteLegal = Integer.parseInt(request.getParameter("valueIdR"));
			empresa.setIdRepresentanteLegal(representanteLegal);
			int departamento = Integer.parseInt(request.getParameter("departamento"));
			empresa.setIdDepartamento(departamento);
			int municipio = Integer.parseInt(request.getParameter("municipio"));
			empresa.setIdMunicipio(municipio);
			empresa.setUsuarioCreacion(1);
			empresa.setFechaCreacion(date);
			
			
			
			//Agregar una tabla nueva de periodoEmpresa




			try {
				if (dtEmpresa.addEmpresa(empresa)) {
					response.sendRedirect("production/tbl_empresa.jsp?msj=1");
				} else {
					response.sendRedirect("production/tbl_empresa.jsp?msj=2");

				}
			} catch (Exception e) {
				System.out.println("Error al guardar empresa: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		case 2: 
			
			int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa")); 
			int idDepartamento = Integer.parseInt(request.getParameter("departamento")); 
			int idRepresentanteLegal = Integer.parseInt(request.getParameter("representanteLegal")); 
			int idMunicipio  = Integer.parseInt(request.getParameter("municipio")); 
			empresa.setIdEmpresa(idEmpresa); 
			empresa.setIdDepartamento(idDepartamento); 
			empresa.setIdMunicipio(idMunicipio);  
			empresa.setIdRepresentanteLegal(idRepresentanteLegal);

			empresa.setRuc(request.getParameter("ruc")); 
			empresa.setRazonSocial(request.getParameter("razonSocial")); 
			empresa.setNombreComercial(request.getParameter("nombreComercial")); 
			empresa.setTelefono(request.getParameter("telefono")); 
			empresa.setCorreo(request.getParameter("correo")); 
			empresa.setDireccion(request.getParameter("direccion"));
			empresa.setUsuarioModificacion(1); 
			empresa.setFechaModificacion(date); 
			
			//Editar periodo empresa
			
			//Obtener el periodo empresa actual

			try {
				if(dtEmpresa.modificarEmpresa(empresa)) { 

					response.sendRedirect("production/tbl_empresa.jsp?msj=3"); 
				} else {
					response.sendRedirect("production/tbl_empresa.jsp?msj=4"); 

				}
			} catch(Exception e) {
				System.out.println("Error al modificar empresa opc2: "+e.getMessage());
				e.printStackTrace();
				
			}
			break; 
		default:
			break;

		}

	}

}