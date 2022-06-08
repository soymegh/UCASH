package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.Dt_cuentaContable_Det;
import entidades.Tbl_cuentaContable_Det;


/**
 * Servlet implementation class Sl_cuentaContableDet
 */
@WebServlet("/Sl_cuentaContableDet")
public class Sl_cuentaContableDet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_cuentaContableDet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));

		Tbl_cuentaContable_Det ccD = new Tbl_cuentaContable_Det();
		Dt_cuentaContable_Det dtccD = new Dt_cuentaContable_Det();


		switch (opc) {
		case 1:
			Double debe = Double.parseDouble(request.getParameter("debe"));
			ccD.setDebe(debe);
			Double haber = Double.parseDouble(request.getParameter("haber"));
			ccD.setHaber(haber);
			Double saldoInicial = Double.parseDouble(request.getParameter("saldoInicial"));
			ccD.setSaldoInicial(saldoInicial);
			Double saldoFinal = Double.parseDouble(request.getParameter("saldoFinal"));
			ccD.setSaldoFinal(saldoFinal);
			ccD.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta")));
			try {
				if (dtccD.addCuentaContableDet(ccD)) {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=1");
				} else {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=2");

				}
			} catch (Exception e) {
				System.out.println("Error al guardar cuenta contable det: " + e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 2:
			ccD.setIdCuentaContableDet(Integer.parseInt(request.getParameter("idCuentaContableDet")));
			Double debeU = Double.parseDouble(request.getParameter("debe"));
			ccD.setDebe(debeU);
			Double haberU = Double.parseDouble(request.getParameter("haber"));
			ccD.setHaber(haberU);
			Double saldoInicialU = Double.parseDouble(request.getParameter("saldoInicial"));
			ccD.setSaldoInicial(saldoInicialU);
			Double saldoFinalU = Double.parseDouble(request.getParameter("saldoFinal"));
			ccD.setSaldoFinal(saldoFinalU);
			ccD.setIdCuenta(Integer.parseInt(request.getParameter("idCuenta")));
			try {
				if (dtccD.editarCuentaContableDet(ccD)) {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=3");
				} else {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=4");

				}
			} catch (Exception e) {
				System.out.println("Error al editar cuenta contable det: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		
		case 3:
			ccD.setIdCuentaContableDet(Integer.parseInt(request.getParameter("idCuentaContableDet")));
			try {
				if (dtccD.eliminarCuentaContableDet(ccD)) {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=5");
				} else {
					response.sendRedirect("production/tbl_cuentacontable.jsp?msj=6");

				}
			} catch (Exception e) {
				System.out.println("Error al eliminar cuenta contable det: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		default:
			break;

		}

	}

}
