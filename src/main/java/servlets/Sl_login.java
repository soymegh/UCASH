package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.Dt_usuario;
import entidades.PermisoTemporal;
import entidades.Vw_usuariorol;
import datos.Dt_recoverPassword;

@WebServlet("/Sl_login")
public class Sl_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_login() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		Dt_usuario dtu = new Dt_usuario();
		Vw_usuariorol vwur = new Vw_usuariorol();
		String usuario = "";
		String clave = "";
		String codigoV = "";
		int rolId = 0;
		int opc = 0;
		
		if(request.getParameter("opcion") != null) {
			opc = Integer.parseInt(request.getParameter("opcion"));
		}

		if(request.getParameter("usuario") != null) {
			usuario = request.getParameter("usuario");
		}

		if(request.getParameter("password") != null) {
			clave = request.getParameter("password");
		}

		if(request.getParameter("rol") != null) {
			rolId = Integer.parseInt(request.getParameter("rol"));
		}

		if(request.getParameter("codVerificacion") != null) {
			codigoV= request.getParameter("codVerificacion");
		}
		
		
		
		
		switch(opc) {
		case 1:
			try{
				if(dtu.dtverificarLogin(usuario, clave, rolId)){
					vwur = dtu.dtGetVwUR(usuario);
					HttpSession hts = request.getSession(true);
					hts.setAttribute("acceso", vwur);
					PermisoTemporal.temporalFlag = true; 
					response.sendRedirect("production/indexMultiempresa.jsp");
				}
				else{
				response.sendRedirect("login.jsp?msj=403");
				}	
			}
			catch(Exception e){
				System.out.println("Servlet: El error es: "+e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 2:
			try{
				if(dtu.dtverificarLogin2(usuario, clave, rolId, codigoV)){
					vwur = dtu.dtGetVwUR(usuario);
					HttpSession hts = request.getSession(true);
					hts.setAttribute("acceso", vwur);
					response.sendRedirect("production/indexMultiempresa.jsp");
				}
				else{
					response.sendRedirect("login.jsp?msj=403");
				}	
			}
			catch(Exception e){
				System.out.println("Servlet: El error es: "+e.getMessage());
				e.printStackTrace();
			}
			break;
		case 3:
			String user = "";
			String email = "";
			Dt_recoverPassword recoverPwd = new Dt_recoverPassword();

			if(request.getParameter("usuario") != null) {
					user = request.getParameter("usuario");
			}

			if(request.getParameter("email") != null) {
				email = request.getParameter("email");
			}

			try{
				if(dtu.recoverPassword(user, email) != null) {
					vwur = dtu.recoverPassword(user, email);
					if(dtu.desencriptarPassword(vwur.getUsuario(), vwur.getKey(), vwur.getPassword()) != null) {
						recoverPwd.recoverPassword(dtu.desencriptarPassword(vwur.getUsuario(), vwur.getKey(), vwur.getPassword()), vwur.getEmail(), vwur.getUsuario());
						response.sendRedirect("login.jsp");
					}else {
						response.sendRedirect("login.jsp?msj=404");
					}
				}else {
					response.sendRedirect("login.jsp?msj=404");
				}
			}
			catch(Exception e){
				System.out.println("Servlet: El error es: "+e.getMessage());
				e.printStackTrace();
			}
			break; 
			
		default:
			break;
				
		}
		
		
		
		
		
		
		
	}

}