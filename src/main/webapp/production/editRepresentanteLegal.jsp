<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*;"%>

<%
//INVALIDA LA CACHE DEL NAVEGADOR //
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
response.setDateHeader("Expires", -1);

//DECLARACIONES
Vw_usuariorol vwur = new Vw_usuariorol();
Dt_rolOpciones dtro = new Dt_rolOpciones();
ArrayList<Vw_rolopciones> listOpc = new ArrayList<Vw_rolopciones>();
boolean permiso = false; //VARIABLE DE CONTROL

//OBTENEMOS LA SESION
vwur = (Vw_usuariorol) session.getAttribute("acceso");
if (vwur != null) {
	//OBTENEMOS LA LISTA DE OPCIONES ASIGNADAS AL ROL

	listOpc = dtro.listarRolOpciones(vwur.getId_rol());

	//RECUPERAMOS LA URL = MI OPCION ACTUAL
	int index = request.getRequestURL().lastIndexOf("/");
	String miPagina = request.getRequestURL().substring(index + 1);

	//VALIDAR SI EL ROL CONTIENE LA OPCION ACTUAL DENTRO DE LA MATRIZ DE OPCIONES
	for (Vw_rolopciones vrop : listOpc) {
		if (vrop.getOpciones().trim().equals(miPagina.trim())) {
	permiso = true; //ACCESO CONCEDIDO
	break;
		}
	}
} else {
	response.sendRedirect("../login.jsp?msj=401");
	return;
}

if (!permiso) {
	// response.sendRedirect("../login.jsp?msj=401");
	response.sendRedirect("page_403.jsp");
	return;
}
%>

<!DOCTYPE html>
<html lang="es">

<%
String Representante = "";
Representante = request.getParameter("idRepresentanteLegal") == null
		? "0"
		: request.getParameter("idRepresentanteLegal");

Vw_representanteLegal vRL = new Vw_representanteLegal();
Tbl_representanteLegal tRL = new Tbl_representanteLegal();

Dt_representanteLegal dtRl = new Dt_representanteLegal();

vRL = dtRl.getViewRepresentanteLegalbyID(Integer.parseInt(Representante));
tRL = dtRl.getRepresentanteLegalbyID(Integer.parseInt(Representante));

Tbl_tipoIdentificacion tipI = new Tbl_tipoIdentificacion();
Dt_tipoIdentificacion dtTId = new Dt_tipoIdentificacion();
tipI = dtTId.getTipoIdentificacionbyID(tRL.getIdTipoIdentifiacion());
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Editar | Representante Legal</title>

<!-- Bootstrap -->
<link href="cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- Datatables -->

<link
	href="../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title"><i class="fa fa-money"></i>
							<span>Sistema Contable</span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic">
							<img src="img.jpg" alt="..." class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Bienvenido,</span>
							<h2><%=vwur.getNombre() + " " + vwur.getApellido()%></h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<ul class="nav side-menu">
								<li><a href="index.html"><i class="fa fa-home"></i>Inicio</a></li>
							</ul>
						</div>

						<div class="menu_section">
							<h3>Gestión</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-shield"></i> Seguridad <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_usuario.jsp">Usuarios</a></li>
										<li><a href="tbl_rol.jsp">Roles</a></li>
										<li><a href="tbl_opciones.jsp">Opciones</a></li>
										<li><a href="tbl_usuarioRol.jsp">Roles de Usuario</a></li>
										<li><a href="tbl_rolOpciones.jsp">Opciones de Rol</a></li>
									</ul></li>

								<li><a><i class="fa fa-building"></i> Empresa<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_empresa.jsp">Empresas</a></li>
										<li><a href="tbl_departamento.jsp">Departamento</a></li>
										<li><a href="tbl_municipio.jsp">Municipio</a></li>
										<li><a href="tbl_TipoIdentificacion.jsp">Tipo
												Identificacion</a></li>
										<li><a href="tbl_representanteLegal.jsp">Representante
												Legal</a></li>
									</ul></li>

								<li><a><i class="fa fa-file"></i> Cuenta Contable<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_catalogocuenta.jsp">Catalogo Cuenta</a></li>
										<li><a href="tbl_tipocuenta.jsp">Tipo Cuenta</a></li>
										<li><a href="tbl_cuentacontable.jsp">Cuenta Contable</a></li>
									</ul></li>

								<li><a><i class="fa fa-dollar"></i> Moneda<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_moneda.jsp">Moneda</a></li>
										<li><a href="tbl_tasaCambio.jsp">Tasa Cambio</a></li>
									</ul></li>

								<li><a><i class="fa fa-book"></i> Asiento Contable<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_asientoContable.jsp">Asiento
												Contable</a></li>
										<li><a href="tbl_periodoContable.jsp">Periodo
												Contable</a></li>
										<li><a href="tbl_periodoFiscal.jsp">Periodo Fiscal</a></li>
										<li><a href="tbl_tipoDocumento.jsp">Tipo Documento</a></li>
									</ul></li>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>
					<nav class="nav navbar-nav">
						<ul class=" navbar-right">
							<li class="nav-item dropdown open" style="padding-left: 15px;">
								<a href="javascript:;" class="user-profile dropdown-toggle"
								aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown"
								aria-expanded="false"> <img src="img.jpg" alt=""><%=vwur.getNombre() + " " + vwur.getApellido()%>.
							</a>
								<div class="dropdown-menu dropdown-usermenu pull-right"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="../login.jsp"><i
										class="fa fa-sign-out pull-right"></i>Cerrar Sesión</a>
								</div>
							</li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Editar Representante Legal</h3>
						</div>

						<div class="title_right">
							<div class="col-md-5 col-sm-5 form-group pull-right top_search">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Buscar...">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">Go!</button>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										Formulario <small>Editar Representante Legal</small>
									</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">



									<form class="" action="../Sl_representanteLegal" method="post"
										novalidate>

										<input type="hidden" value="3" name="opcion" id="opcion" /> <span
											class="section">Datos de Representante Legal</span>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">ID
												<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">

												<input value="<%=tRL.getIdRepresentante()%>"
													class="form-control" class='optional' readonly
													name="idRepresentanteLegal" id="idRepresentanteLegal"
													type="text" required="required" />
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Nombre
												<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input value="<%=tRL.getNombre()%>" class="form-control"
													class='optional' name="nombre" type="text"
													required="required" />
											</div>
										</div>


										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Apellido
												<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input value="<%=tRL.getApellido()%>" class="form-control"
													class='optional' name="apellido" type="text"
													required="required" />
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Tipo
												<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Tbl_tipoIdentificacion> listTI = new ArrayList<Tbl_tipoIdentificacion>();
												Dt_tipoIdentificacion dtTI = new Dt_tipoIdentificacion();
												listTI = dtTI.listarTipoIdentificacion();
												%>
												<select class="form-control js-example-basic-single"
													name="idTipoIdentifiacion" id="idTipoIdentifiacion"
													required="required">
													<option value="<%=tipI.getIdTipoIdentifiacion()%>"><%=tipI.getTipo()%></option>
													<%
													for (Tbl_tipoIdentificacion TI : listTI) {
													%>
													<option value="<%=TI.getIdTipoIdentifiacion()%>"><%=TI.getTipo()%></option>
													<%
													}
													%>
												</select>
											</div>
										</div>


										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Correo<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input value="<%=tRL.getCorreo()%>" class="form-control"
													name="correo" class='email' required="required"
													type="email" />
											</div>
										</div>
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Confirmar
												correo<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input value="<%=tRL.getCorreo()%>" class="form-control"
													type="email" class='email' name="confirm_email"
													data-validate-linked='correo' required='required' />
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Teléfono<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input value="<%=tRL.getTelefono()%>" class="form-control"
													type="tel" class='tel' name="telefono" required='required'
													data-validate-length-range="8,20" />
											</div>
										</div>
								</div>
								<div class="ln_solid">
									<div class="form-group">
										<div class="col-md-6 offset-md-3">
											<button type='submit' class="btn btn-primary">Agregar</button>

											<button type='reset' class="btn btn-success">Reiniciar</button>

											<a href="tbl_representanteLegal.jsp" class="btn btn-primary">
												Cancelar</a>
										</div>
									</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /page content -->

		<!-- footer content -->
		<footer>
			<div class="pull-right">Sistema contable by UCASH</div>
			<div class="clearfix"></div>
		</footer>
		<!-- /footer content -->
	</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="../vendors/validator/multifield.js"></script>
	<script src="../vendors/validator/validator.js"></script>

	<!-- Javascript functions	-->
	<script>
		function hideshow() {
			var password = document.getElementById("password1");
			var slash = document.getElementById("slash");
			var eye = document.getElementById("eye");
			if (password.type === 'password') {
				password.type = "text";
				slash.style.display = "block";
				eye.style.display = "none";
			} else {
				password.type = "password";
				slash.style.display = "none";
				eye.style.display = "block";
			}
		}
	</script>

	<script>
		// initialize a validator instance from the "FormValidator" constructor.
		// A "<form>" element is optionally passed as an argument, but is not a must
		var validator = new FormValidator({
			"events" : [ 'blur', 'input', 'change' ]
		}, document.forms[0]);
		// on form "submit" event
		document.forms[0].onsubmit = function(e) {
			var submit = true, validatorResult = validator.checkAll(this);
			console.log(validatorResult);
			return !!validatorResult.valid;
		};
		// on form "reset" event
		document.forms[0].onreset = function(e) {
			validator.reset();
		};
		// stuff related ONLY for this demo page:
		$('.toggleValidationTooltips').change(function() {
			validator.settings.alerts = !this.checked;
			if (this.checked)
				$('form .alert').remove();
		}).prop('checked', false);

		//Codigo para visualizar las opciones del select
	</script>



	<!-- jQuery -->
	<script src="../vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="../vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../vendors/nprogress/nprogress.js"></script>
	<!-- validator -->
	<!-- <script src="../vendors/validator/validator.js"></script> -->

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.min.js"></script>

</body>

</html>