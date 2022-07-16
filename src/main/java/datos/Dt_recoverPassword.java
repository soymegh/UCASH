package datos;

import javax.mail.*;
import javax.mail.internet.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import datos.Dt_usuario;
import datos.Dt_usuarioRol;
import entidades.Tbl_usuario;
import entidades.Vw_usuariorol;

public class Dt_recoverPassword {
	Dt_usuario dtUser = new Dt_usuario();
	Dt_usuarioRol dtUserRol = new Dt_usuarioRol(); 
	//ATRIBUTOS

		/*---------------------- Configuración Localhost------------------------------*/
		private static final String SMTP_HOST_NAME = "smtp.gmail.com";
		private static final String SMTP_AUTH_USER = "jaol260549@gmail.com";
		private static final String SMTP_AUTH_PWD = "hrtomvrniwskgtsv";

		//Enlace de verificación de correo
	    String linkHR = "http://localhost:8080/SistemaContable/";

	    //DECLARAMOS UNA CLASE PRIVADA COMO ATRIBUTO QUE HEREDA JAVAX.MAIL.AUTHENTICATOR
	    private class SMTPAuthenticator extends javax.mail.Authenticator 
		{
			public PasswordAuthentication getPasswordAuthentication() 
			{
				String username = SMTP_AUTH_USER;
				String password = SMTP_AUTH_PWD;
				return new PasswordAuthentication(username, password);
			}
		}

	    /*----------------------------------------------------------------------------*/

	    //METODO QUE ENVIA EL EMAIL DE VERIFICACION
		public boolean recoverPassword(String contraseñaDesencriptada, String correo, String usuario) throws MessagingException{
			Tbl_usuario user = new Tbl_usuario();
			Vw_usuariorol userRol = new Vw_usuariorol(); 
			ArrayList<Vw_usuariorol> userRolList = new ArrayList<>();
			
			user = dtUser.ObtenerUsuarioPorUserAndEmail(usuario, correo);
			userRol = dtUserRol.ObtenerUsuarioRolPorIdUsuario(user.getIdUsuario());
			userRolList = dtUserRol.obtenerUsuariosAdministradores();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm:ss");
			
			boolean debug=false;

		   // Correo del solicitante
			String email_solicitante = correo;

		   // Correo del remitente
			String email_remitente = SMTP_HOST_NAME;

		   // Obtener propiedades del sistema
		   Properties properties = new Properties();


		   /*---------------------- Configuración del servidor de correo---------------------------*/ 
		   properties.setProperty("mail.smtp.host", SMTP_HOST_NAME);
		   properties.put("mail.smtp.auth", "true");
		   properties.setProperty("mail.smtp.port", "587");
		   properties.put("mail.smtp.starttls.enable", "true");
		   /*--------------------------------------------------------------------------------------*/

		   Authenticator auth = new SMTPAuthenticator();

		     Session session = Session.getInstance(properties,auth);
			 session.setDebug(debug);

		      // Create a default MimeMessage object.
		      	MimeMessage message = new MimeMessage(session);

		      // Establecer De (remitente)
		      	message.setFrom(new InternetAddress(email_remitente));

		      // Establecer Para (solicitante)
		      	message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_solicitante));

		      // Asunto: encabezado del archivo
		        message.setSubject("PROCESO DE RECUPERACI�N DE CONTRASE�A");


		      //Cuerpo del correo  
		        String myMsg = "<strong>PROCESO DE RECUPERACI�N DE CONTRASE�A </strong><br><br>";
		      	myMsg += "Estimado "+user.getNombre()+" "+user.getApellidos()+", la contrase�a recuperada por el sistema es la siguiente: ";
		      	myMsg += contraseñaDesencriptada+" <br><br>";
		      	myMsg += "<br>----------------------------------------------------------<br>";
		      	myMsg += "Administrador del Sistema<br>";
		      	myMsg += "Usuario: "+user.getUsuario()+"<br>";
		      	myMsg += "Cargo: "+userRol.getRol()+"<br>";
		      	myMsg += "Fecha de recuperaci�n: "+dtf.format(LocalDateTime.now())+"<br>";
		      	myMsg += "Hora de recuperaci�n: "+hour.format(LocalDateTime.now())+"<br>";
		      	myMsg += "Si requiere soporte o algun tipo de acceso en especifico, puede contactar a los siguientes usuarios administradores: <br><br>";
		      	for(Vw_usuariorol ur: userRolList) {
		      		myMsg += "Nombre y apellido: "+ur.getNombre()+" "+ur.getApellido()+" <br> Correo electr�nico: "+ur.getEmail()+" <br><br>";
		      	};
		      	
		      	myMsg += "En caso de necesitar un soporte tecnico especial, haga que un usuario administrador se contacte a este n�mero telefonico o email: <br>";
		      	myMsg += "Movil: +505 7855-2666 <br>";
		      	myMsg += "Email: andUrbina2001@gmail.com";
		      	

		      message.setContent(myMsg, "text/html");

		      // Enviar Correo
		      Transport transport = session.getTransport("smtp");
		      transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
		      Transport.send(message);
		      debug = true;
		      System.out.println("El mensaje fue enviado con exito");
		      return debug;
		}

}