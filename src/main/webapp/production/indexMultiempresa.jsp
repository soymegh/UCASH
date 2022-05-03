<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="datos.Dt_empresa,
			entidades.Vw_empresa,
			java.util.ArrayList"%>

<%
ArrayList<Vw_empresa> listadoEmpresas = new ArrayList<>();
Dt_empresa datosEmpresas = new Dt_empresa();
listadoEmpresas = datosEmpresas.listarEmpresa();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Sistema Contable |</title>

<!-- Bootstrap -->
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="../vendors/bootstrap-daterangepicker/daterangepicker.css"
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
							<h2>Lic. JosÃ© Ortega.</h2>
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
							<h3>GestiÃ³n</h3>
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
								aria-expanded="false"> <img src="img.jpg" alt="">Lic.
									JosÃ© Ortega.
							</a>
								<div class="dropdown-menu dropdown-usermenu pull-right"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="login.html"><i
										class="fa fa-sign-out pull-right"></i>Cerrar SesiÃ³n</a>
								</div>
							</li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="x_panel">

							<div class="x_title">
								<p style="font-size: 30px">¡Buenos días, Lic. José Ortega!</p>
								<p>¿Qué empresa vamos a trabajar hoy?</p>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">
								<div class="field item form-group colspan-3">
									<%
									for (Vw_empresa empresa : listadoEmpresas) {
									%>
									<jsp:include page="fichaEmpresa.jsp">
										<jsp:param name="nombreEmp"
											value="<%=empresa.getNombreComercial()%>" />
										<jsp:param name="altImg"
											value="<%=empresa.getNombreComercial()%>" />
										<jsp:param name="representanteEmp"
											value="<%=empresa.getRepresentante()%>" />
									</jsp:include>
									<%
									}
									%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">
					<a><i class="green">FROGBMJ Software </i><i>por</i> <i
						class="red"> Eldian's Development</i></a>
				</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>

	<!-- jQuery -->
	<script src="../vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="../vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="../vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- jQuery Sparklines -->
	<script src="../vendors/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- Flot -->
	<script src="../vendors/Flot/jquery.flot.js"></script>
	<script src="../vendors/Flot/jquery.flot.pie.js"></script>
	<script src="../vendors/Flot/jquery.flot.time.js"></script>
	<script src="../vendors/Flot/jquery.flot.stack.js"></script>
	<script src="../vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="../vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="../vendors/DateJS/build/date.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="../vendors/moment/min/moment.min.js"></script>
	<script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.min.js"></script>
</body>
</html>