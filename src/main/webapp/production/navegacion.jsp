<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="datos.Dt_empresa;"%>

<%
String nombreEmpresa = "";
if (request.getParameter("idE") != null) {
	Dt_empresa datosEmpresa = new Dt_empresa();
	int idEmpresa = Integer.parseInt(request.getParameter("idE"));
	nombreEmpresa = "Trabajando, " + datosEmpresa.getNombreEmpresaPorId(idEmpresa);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
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
					<h2>Lic. José Ortega.</h2>
				</div>
			</div>
			<!-- /menu profile quick info -->

			<br />

			<!-- sidebar menu -->
			<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

				<div class="menu_section">
					<ul class="nav side-menu">
						<li><a href="#"><i></i><%=nombreEmpresa%></a></li>
					</ul>
				</div>
				
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
								<li><a href="tbl_asientoContable.jsp">Asiento Contable</a></li>
								<li><a href="tbl_periodoContable.jsp">Periodo Contable</a></li>
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
							José Ortega.
					</a>
						<div class="dropdown-menu dropdown-usermenu pull-right"
							aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="login.html"><i
								class="fa fa-sign-out pull-right"></i>Cerrar Sesión</a>
						</div>
					</li>
				</ul>
			</nav>
		</div>
	</div>
	<!-- /top navigation -->

</body>
</html>