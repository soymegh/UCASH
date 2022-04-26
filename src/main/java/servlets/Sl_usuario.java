package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_usuario;
import entidades.Tbl_user2;
import entidades.Tbl_usuario;
import datos.Encrypt;
import datos.Dt_enviarEmail;
import datos.Dt_user2;
import negocio.Ng_usuario;

/**
 * Servlet implementation class Sl_usuario
 */
@WebServlet("/Sl_usuario")
public class Sl_usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sl_usuario() {
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
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));

		// INSTANCIAMOS LOS OBJETOS
		Tbl_usuario user = new Tbl_usuario();
		Tbl_user2 tus2 = new Tbl_user2();
		Dt_usuario dtu = new Dt_usuario();
		Dt_user2 dtus2 = new Dt_user2();
		Dt_enviarEmail dtem = new Dt_enviarEmail();
		Encrypt dtenc = new Encrypt();
		Ng_usuario ngu = new Ng_usuario();
		Date fechaSistema = new Date();

		// GENERAMOS EL CODIGO DE VERIFICACION Y LO ASIGNAMOS AL OBJETO
		user.setCodVerificacion(dtu.randomAlphaNumeric(10));

		/////// VARIABLES PARA ENCRIPTAR LA PWD //////////
		String key = "";
		String pwd = "";
		String pwdEncrypt = "";

		switch (opc) {
		case 1:
			user.setUsuario(request.getParameter("usuario"));
			user.setNombre(request.getParameter("nombres"));
			user.setApellidos(request.getParameter("apellidos"));
			user.setEmail(request.getParameter("email"));
			user.setPwd(request.getParameter("pwd"));
			user.setFechaCreacion(new java.sql.Timestamp(fechaSistema.getTime()));
			user.setUsuarioCreacion(1); // 1 valor temporal mientras se programa la sesion
			user.setEstado(1);

			/////// PARA ENCRIPTAR LA PWD //////////
			key = dtenc.generarLLave();
			tus2.setToken(key);
			pwd = user.getPwd();
			pwdEncrypt = dtenc.getAES(pwd, key);
			user.setPwd(pwdEncrypt);

			try {
				if(ngu.existeUser(user.getUsuario()) || ngu.existeEmail(user.getEmail())) {
					response.sendRedirect("production/tbl_usuarios.jsp?msj=7");
				}else {
					tus2.setId_user(dtu.addUsuario(user));
					if(tus2.getId_user()>0) {
						if(dtus2.guardarUser(tus2)) {
							if(dtem.enviarEmailVerificacion(user.getUsuario(), user.getEmail(), user.getCodVerificacion())){
							response.sendRedirect("production/tbl_usuario.jsp?msj=1");
						}
						}
					}else {
						response.sendRedirect("production/tbl_usuario.jsp?msj=2");
					}
				}

			} catch (Exception e) {
				System.out.println("ERROR Sl_usuario opc1: " + e.getMessage());
				e.printStackTrace();
			}

			break;
		case 2: 
			// CONSTRUIMOS EL OBJETO CON LOS VALORES DE LOS CONTROLES
			user.setIdUsuario(Integer.parseInt(request.getParameter("txtid")));
			user.setUsuario(request.getParameter("txtusuario"));
			user.setNombre(request.getParameter("txtnombres"));
			user.setApellidos(request.getParameter("txtapellidos"));
			user.setEmail(request.getParameter("txtemail"));
			try {
				user.setFechaModificacion(new java.sql.Timestamp(fechaSistema.getTime()));
				user.setUsuarioModificacion(1);//1 valor temporal mientras se programa la sesion
				if(dtu.modificarUsuario(user)) {
					response.sendRedirect("production/tbl_usuario.jsp?msj=3");
				}
				else {
					response.sendRedirect("production/tbl_usuario.jsp?msj=4");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_gestionUser opc2: "+e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case 3: 
			user.setIdUsuario(Integer.parseInt(request.getParameter("txtid")));
			try {
				user.setFechaEliminacion(new java.sql.Timestamp(fechaSistema.getTime()));
				user.setUsuarioEliminacion(1);//1 valor temporal mientras se programa la sesion
				if(dtu.eliminarUsuario(user)) {
					response.sendRedirect("production/tbl_usuario.jsp?msj=5");
				}
				else {
					response.sendRedirect("production/tbl_usuario.jsp?msj=6");
				}
			}catch(Exception e) {
				System.out.println("Error Sl_gestionUser opc3: "+e.getMessage());
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

}
