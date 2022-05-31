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
		
		switch (opc) {
		case 1:
			try {
				//Asiento Contable
				Date fechaSistema = new Date();
				int cantDetalles = 0; 
				
				if(request.getParameter("detalles") != null) {
					cantDetalles = Integer.parseInt(request.getParameter("detalles"));
				}else {
					System.out.print("No estamos recibiendo la cantidad de detalles del frontend");
				};
				try {
					String fecha = request.getParameter("fecha").toString();
					java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
					ac.setFecha(new java.sql.Date(date2.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				ac.setDescripcion(request.getParameter("descripcion"));

				boolean detalleGuardado = false;

				ac.setIdPeriodoContable(Integer.parseInt(request.getParameter("periodoContable")));
				ac.setIdEmpresa(Integer.parseInt(request.getParameter("empresaActual")));
				ac.setIdMoneda(Integer.parseInt(request.getParameter("moneda")));
				ac.setIdTipoDocumento(Integer.parseInt(request.getParameter("cbxIDTD")));		
				ac.setIdTasaCambioDet(Integer.parseInt(request.getParameter("cbxIDTCD")));
				ac.setUsuarioCreacion(Integer.parseInt(request.getParameter("usuarioCreacion")));
				ac.setFechaCreacion(new java.sql.Timestamp(fechaSistema.getTime()));

				int newAsientoID = dtsc.agregarAsientoContable(ac);
				
				if (newAsientoID > 0 && cantDetalles > 0) {
					
					for(int y = 0; y < cantDetalles; y++) {
						Tbl_asientoContableDet asientoContableDet = new Tbl_asientoContableDet();
						asientoContableDet.setIdAsientoContable(newAsientoID);
						asientoContableDet.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta" + y)));
						asientoContableDet.setDebe(Double.parseDouble(request.getParameter("debe" + y)));
						asientoContableDet.setHaber(Double.parseDouble(request.getParameter("haber" + y)));
						
						dtscd.guardarAsientoContableDet(asientoContableDet);
					}
					// Mensaje de guardado con éxito
					response.sendRedirect("production/addAsientoContable.jsp?msj=1");
				} else {
					// Mensaje de guardado el asiento contable sin los detalles
					response.sendRedirect("production/addAsientoContable.jsp?msj=2");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:
			Date fechaSistema = new Date();
			ac.setIdAsientoContable(Integer.parseInt(request.getParameter("idAcont")));
			ac.setIdPeriodoContable(Integer.parseInt(request.getParameter("periodoContable")));
			ac.setIdEmpresa(Integer.parseInt(request.getParameter("empresaActual")));
			ac.setIdMoneda(Integer.parseInt(request.getParameter("moneda")));
			ac.setIdTipoDocumento(Integer.parseInt(request.getParameter("cbxIDTD")));
			ac.setIdTasaCambioDet(Integer.parseInt(request.getParameter("cbxIDTCD")));
			ac.setDescripcion(request.getParameter("descripcion"));
			ac.setUsuarioModificacion(Integer.parseInt(request.getParameter("usuarioModificacion")));
			ac.setFechaModificacion(new java.sql.Timestamp(fechaSistema.getTime()));

			System.out.print("ESTE ES EL USUARIO MODIFICACION: " + ac.getUsuarioModificacion());
			try {
					String fecha1 = request.getParameter("fechainicioc").toString();
					java.util.Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
					ac.setFecha(new java.sql.Date(date3.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
			boolean modificacion = dtsc.modificarAsientoContable(ac);
			
			try {
				if (modificacion) {
					
					int cantDetallesEliminados = 0;
					int cantDetallesAgregados = 0; 
					boolean detalleEdit = false;
					
					if(request.getParameter("detallesEliminados") != null) {
						cantDetallesEliminados = Integer.parseInt(request.getParameter("detallesEliminados"));
						System.out.print("ESTA ES LA CANTIDAD DE DETALLES ELIMINADOS: " + cantDetallesEliminados);
					}else {
						System.out.print("No estamos recibiendo la cantidad de detalles del frontend");
					}
					
					if(request.getParameter("detallesAgregados") != null) {
						cantDetallesAgregados = Integer.parseInt(request.getParameter("detallesAgregados"));
						System.out.print("ESTA ES LA CANTIDAD DE DETALLES AGREGADOS: " + cantDetallesAgregados);
					}else {
						System.out.print("No estamos recibiendo la cantidad de detalles del frontend");
					}
					
					for(int y = 0; y < cantDetallesEliminados; y++) {
						if(request.getParameter("detalleEliminado" + y) != null) {
							Tbl_asientoContableDet asientoContableDet = new Tbl_asientoContableDet();
							asientoContableDet.setIdAsientoContableDet(Integer.parseInt(request.getParameter("detalleEliminado" + y)));
							detalleEdit = dtscd.EliminarAContableDetPorId(asientoContableDet.getIdAsientoContableDet());
						};
					}
					
					for(int y = 0; y < cantDetallesAgregados; y++) {
						Tbl_asientoContableDet asientoContableDet = new Tbl_asientoContableDet();
						asientoContableDet.setIdAsientoContable(ac.getIdAsientoContable());
						asientoContableDet.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta" + y)));
						asientoContableDet.setDebe(Double.parseDouble(request.getParameter("debe" + y)));
						asientoContableDet.setHaber(Double.parseDouble(request.getParameter("haber" + y)));
						System.out.print("Haber: " + asientoContableDet.getHaber());
						System.out.print("Debe: " + asientoContableDet.getDebe());
						System.out.print("IdCuenta: " + asientoContableDet.getIdCuenta());
						detalleEdit = dtscd.guardarAsientoContableDet(asientoContableDet);
					}
					
					
					if(detalleEdit) {
						// Mensaje de asiento editado correctamente
					response.sendRedirect("production/tbl_asientoContable.jsp?msj=3");
					}else {
						// Maestro editado correctamente excepto detalles
						response.sendRedirect("production/tbl_asientoContable.jsp?msj=4");
					}
				} else {
					// Error al editar asiento contable - maestro
					response.sendRedirect("production/tbl_asientoContable.jsp?msj=5");
				}
			} catch (Exception e) {
				System.err.println("ERROR EDITAR (Servlet) Asiento contable: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		case 3:
			int idBorrar = Integer.parseInt(request.getParameter("idAContableEliminar"));
			try {
				if (dtsc.EliminarAContablePorId(idBorrar)) {
					// Asiento contable borrado exitosamente
					response.sendRedirect("production/tbl_asientoContable.jsp?msj=7");
				} else {
					// Error al borrar asiento contable
					response.sendRedirect("production/tbl_asientoContable.jsp?msj=8");
				}
			} catch (Exception e) {
				System.err.println("ERROR ELIMINAR (Servlet) Asiento Contable: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
	}
}