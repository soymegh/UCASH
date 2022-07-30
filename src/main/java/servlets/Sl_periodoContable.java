package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.mail.search.IntegerComparisonTerm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_cuentaContable;
import datos.Dt_cuentaContable_Det;
import datos.Dt_periodoContable;
import entidades.Tbl_cuentaContable_Det;
import entidades.Tbl_periodoContable;
import entidades.Vw_catalogo_tipo_cuentacontable;
import entidades.Vw_usuariorol;

@WebServlet("/Sl_periodoContable")
public class Sl_periodoContable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sl_periodoContable() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		Tbl_periodoContable periodocontable = new Tbl_periodoContable();
		Dt_periodoContable dpc = new Dt_periodoContable();
		Dt_cuentaContable cuentaContable = new Dt_cuentaContable();
		Dt_cuentaContable_Det dtCuentaContableDet = new Dt_cuentaContable_Det();
		Tbl_cuentaContable_Det cuentaContableDet = new Tbl_cuentaContable_Det();
		Tbl_cuentaContable_Det cuentaContableDetSub = new Tbl_cuentaContable_Det();
		
		// Fecha Inicio del Periodo Contable
		try {

			if (request.getParameter("fechainicioc") != null) {
				String fechaIniPCJsp = request.getParameter("fechainicioc").toString();
				java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIniPCJsp);
				periodocontable.setFechaInicio(new java.sql.Date(date1.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Fecha Final del Periodo Contable
		try {
			if (request.getParameter("fechafinalc") != null) {
				String fechaFinPCJsp = request.getParameter("fechafinalc").toString();
				java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinPCJsp);
				periodocontable.setFechaFinal(new java.sql.Date(date2.getTime()));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		switch (opc) {
		case 1:
			int transferenciaSaldos = 0, company = 0; 
			
			if(request.getParameter("transferBalance") != null) {
				transferenciaSaldos = Integer.parseInt(request.getParameter("transferBalance"));
			} else {
				transferenciaSaldos = 0; 
			}
			
			if(request.getParameter("empresa") != null) {
				company = Integer.parseInt(request.getParameter("empresa"));
			}else {
				company = 0; 
			}

			
			try {
				double saldoFinalTotal = 0;
				
				if(transferenciaSaldos != 0) {
					ArrayList<Tbl_cuentaContable_Det> cuentasDeMayor = new ArrayList<Tbl_cuentaContable_Det>();
					ArrayList<Tbl_cuentaContable_Det> subCuentas = new ArrayList<Tbl_cuentaContable_Det>();
					cuentasDeMayor = dtCuentaContableDet.listarCuentasMayorTransSaldos(company);
					
					
					
					for(Tbl_cuentaContable_Det cuenta: cuentasDeMayor) {
						dtCuentaContableDet.editarCuentaContableDet(cuenta);
					}	
					
					subCuentas = dtCuentaContableDet.listarSubcuentasTransSaldos(company);
					
					for(Tbl_cuentaContable_Det cuenta: subCuentas) {
						dtCuentaContableDet.editarCuentaContableDet(cuenta);
					}	
					
				}
				
				periodocontable.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("cbxIDPF")));
				if (dpc.agregarPeriodoContable(periodocontable)) {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=1");
				} else {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=2");
				}
			} catch (Exception e) {
				System.out.println("Error Sl_periodoContable opc1: " + e.getMessage());
			}
			break;
		case 2:
			periodocontable.setIdPeriodoContable(Integer.parseInt(request.getParameter("txtidpcontable")));
			periodocontable.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("cbxIDPF")));
			try {
				if (dpc.modificarPeriodoContable(periodocontable)) {
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

			int idBorrar = Integer.parseInt(request.getParameter("idPContableEliminar"));
			int transferBalance = 0, empresa = 0; 
			double saldoFinalTotal = 0;
			
			if(request.getParameter("transferBalance") != null) {
				transferBalance = Integer.parseInt(request.getParameter("transferBalance"));
			}else {
				transferBalance = 0; 
			}

			if(request.getParameter("empresa") != null) {
				empresa = Integer.parseInt(request.getParameter("empresa"));
			}else {
				empresa = 0; 
			}

			
			try {
				
				if(transferBalance != 0) {
					ArrayList<Vw_catalogo_tipo_cuentacontable> cuentasDeMayor = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
					ArrayList<Tbl_cuentaContable_Det> subCuentas = new ArrayList<Tbl_cuentaContable_Det>();
					cuentasDeMayor = cuentaContable.getCuentaContableMayorByIdEmpresa(empresa);
					
					for(Vw_catalogo_tipo_cuentacontable cuenta: cuentasDeMayor) {
						saldoFinalTotal = dtCuentaContableDet.listaCuentasContablesDetPorNumeroCuentaEmpresaSubCuenta(cuenta.getNumeroCuenta(), empresa); 
						cuentaContableDet.setIdCuenta(cuenta.getIdCuenta());
						cuentaContableDet.setSaldoInicial(dtCuentaContableDet.getCcdbyIDSaldos(cuenta.getIdCuenta()).getSaldoInicial());
						Math.abs(saldoFinalTotal);
						cuentaContableDet.setSaldoFinal(saldoFinalTotal);
						dtCuentaContableDet.editarCuentaContableDetSaldos(cuentaContableDet);
					}
					
					subCuentas = dtCuentaContableDet.listarSubcuentas(empresa);
					
					for(Tbl_cuentaContable_Det subCuenta: subCuentas) {
						dtCuentaContableDet.editarCuentaContableDet(subCuenta);
					}
				}

				if (dpc.EliminarPContablePorId(idBorrar)) {

					response.sendRedirect("production/tbl_periodoContable.jsp?msj=5");

				} else {

					response.sendRedirect("production/tbl_periodoContable.jsp?msj=6");

				}

			} catch (Exception e) {
				System.err.println("ERROR ELIMINAR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}

			break;
			
		case 4: 
			int idPeriodoContable = 0;
			String adminPass = ""; 
			int currentPeriod = 0; 
			Vw_usuariorol vwur = new Vw_usuariorol(); 
			
			if(request.getParameter("combobox_periodoContable") != null && !request.getParameter("combobox_periodoContable").trim().equals("")) {
				idPeriodoContable = Integer.parseInt(request.getParameter("combobox_periodoContable"));
				try {
					if (dpc.obtenerPContablePorIdLogin(idPeriodoContable) > 0) {
						currentPeriod = dpc.obtenerPContablePorIdLogin(idPeriodoContable);
						vwur = (Vw_usuariorol) request.getSession(true).getAttribute("acceso");
						vwur.setIdPeriodoContable(currentPeriod);
						request.setAttribute("acceso", vwur);
						if(request.getParameter("admin_pass") != null && !request.getParameter("admin_pass").trim().equals("")) {
							adminPass = request.getParameter("admin_pass"); 
							response.sendRedirect("production/indexMoneda.jsp?status="+adminPass+"");
						}else {
							response.sendRedirect("production/indexMoneda.jsp");
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				response.sendRedirect("production/indexPeriodoContable.jsp?msj=1");
			}
			;
			break;

		case 5:

			periodocontable.setIdPeriodoContable(Integer.parseInt(request.getParameter("txtidpcontable")));
			periodocontable.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("cbxIDPF")));

			try {
				if (dpc.modificarPeriodoContable(periodocontable)) {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=3");
				} else {
					response.sendRedirect("production/tbl_periodoContable.jsp?msj=4");
				}
			} catch (Exception e) {
				System.err.println("ERROR ABRIR (Servlet) Periodo Fiscal: " + e.getMessage());
				e.printStackTrace();
			}
			break;

		// Agregar un período contable a través dle indexPeriodoContable
		case 6:
			try {
				periodocontable.setIdPeriodoFiscal(Integer.parseInt(request.getParameter("cbxIDPF")));
				if (dpc.agregarPeriodoContable(periodocontable)) {
					response.sendRedirect("production/indexPeriodoContable.jsp?msj=10");
				} else {
					response.sendRedirect("production/indexPeriodoContable.jsp?msj=11");
				}
			} catch (Exception e) {
				System.out.println("Error Sl_periodoContable opc6: " + e.getMessage());
			}
			break;

		default:
			break;
		}

	}

}