package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.Tbl_cuentaContable_Det;
import datos.Dt_cuentaContable_Det;
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
		
		Tbl_cuentaContable_Det ccd = new Tbl_cuentaContable_Det();
		Dt_cuentaContable_Det dtccd = new Dt_cuentaContable_Det();
		
		
		
		switch (opc) {
		
		case 1:
			
			cc.setNumeroCuenta(request.getParameter("numeroCuenta"));
			cc.setsC(request.getParameter("SC"));
			cc.setSsC(request.getParameter("SsC"));
			cc.setSssC(request.getParameter("SssC"));
			cc.setNombreCuenta(request.getParameter("nombreCuenta").toUpperCase());
			int nivel = Integer.parseInt(request.getParameter("nivel"));
			cc.setNivel(nivel);
			int rubro = Integer.parseInt(request.getParameter("rubro"));
			cc.setRubro(rubro);
			int tipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
			cc.setIdTipoCuenta(tipoCuenta);
			int catalogoCuenta = Integer.parseInt(request.getParameter("catalogoCuenta"));
			cc.setIdCatalogo(catalogoCuenta);
			
			Double debe = Double.parseDouble(request.getParameter("debe"));
			ccd.setDebe(debe);
			Double haber = Double.parseDouble(request.getParameter("haber"));
			ccd.setHaber(haber);
			Double saldoInicial = Double.parseDouble(request.getParameter("saldoInicial"));
			ccd.setSaldoInicial(saldoInicial);
			Double saldoFinal = Double.parseDouble(request.getParameter("saldoFinal"));
			ccd.setSaldoFinal(saldoFinal);
			ccd.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta")));
			
			try
			{
				if(dtCc.addCuentaContable(cc) && dtccd.addCuentaContableDet(ccd)) {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=1");
				}
				else
				{
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=2");
				}
			}
			catch(Exception e)
			{
				System.out.println("ERROR AL CREAR CUENTA CONTABLE: "+ e.getMessage());
				e.printStackTrace();
			}
			
			break;
			
		case 2:
			
			cc.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta")));
			cc.setNumeroCuenta(request.getParameter("numeroCuenta"));
			cc.setsC(request.getParameter("SC"));
			cc.setSsC(request.getParameter("SsC"));
			cc.setSssC(request.getParameter("SssC"));
			cc.setNombreCuenta(request.getParameter("nombreCuenta"));
			cc.setNivel(Integer.parseInt(request.getParameter("nivel")));
			cc.setRubro(Integer.parseInt(request.getParameter("rubro")));
			cc.setIdTipoCuenta(Integer.parseInt(request.getParameter("cbxTipoCuenta")));
			cc.setIdCatalogo(Integer.parseInt(request.getParameter("catalogoCuenta")));
			
			ccd.setIdCuentaContableDet(Integer.parseInt(request.getParameter("idCuentaContableDet")));
			Double debeU = Double.parseDouble(request.getParameter("debe"));
			ccd.setDebe(debeU);
			Double haberU = Double.parseDouble(request.getParameter("haber"));
			ccd.setHaber(haberU);
			Double saldoInicialU = Double.parseDouble(request.getParameter("saldoInicial"));
			ccd.setSaldoInicial(saldoInicialU);
			Double saldoFinalU = Double.parseDouble(request.getParameter("saldoFinal"));
			ccd.setSaldoFinal(saldoFinalU);
			ccd.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta")));
			
			try {
				if(dtCc.editCuentaContable(cc) && dtccd.editarCuentaContableDet(ccd)) { 
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=3"); 
				} else {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=4"); 

				}
			} catch(Exception e) {
				System.out.println("ERROR AL MODIFICAR CUENTA CONTABLE "+e.getMessage());
				e.printStackTrace();
			}
			
			break;
			
		case 3:
			ccd.setIdCuentaContableDet(Integer.parseInt(request.getParameter("idCuentaContableDet")));
			cc.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta")));
			try {
				if (dtCc.deleteCuentaContable(cc) && dtccd.eliminarCuentaContableDet(ccd)) {
						response.sendRedirect("production/tbl_cuentacontable.jsp?msj=5");
				} else {
						response.sendRedirect("production/tbl_cuentacontable.jsp?msj=6");
				}
				
			} catch (Exception e) {
				System.out.println("ERROR AL ELIMINAR CUENTA CONTABLE: "+ e.getMessage());
				e.printStackTrace();
			}
			
			break;
		}
	}

}
