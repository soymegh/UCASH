<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*;"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Gestión | Cuentas Contables</title>

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
							<h2>Lic. José Ortega.</h2>
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

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Cuenta Contable</h3>
						</div>

						<!-- <div class="title_right">
							<div
								class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Buscar por..."> <span
										class="input-group-btn">
										<button class="btn btn-secondary" type="button">Ir</button>
									</span>
								</div>
							</div>
						</div> -->
					</div>

					<div class="clearfix"></div>

					<div class="row">

								<div class="x_content">
									<div class="row">
						<div class="col-md-12 col-md-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Cuentas Contables Registradas</h2>
									
									<div class="clearfix"></div>
								</div>

								<div class="x_content">
									<div class="row">
										<div class="col-md-12">
											<div class="card-box table-responsive">
												<div class="text-muted font-13 col-md-12"
													style="text-align: right;">
													<a href="addCuentaContable.jsp"> <i
														class="fa fa-plus-square"></i> Nueva Cuenta Contable
													</a> <br></br>
												</div>
												<table id="datatable-buttons"
													class="table table-striped table-bordered"
													style="width: 100%">
													<%
													ArrayList<Vw_catalogo_tipo_cuentacontable> listaCuentasContables = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
													Dt_cuentaContable dtCc = new Dt_cuentaContable();
													listaCuentasContables = dtCc.listaCuentasContables();
													%>
													<thead>
														<tr>
															<th>ID</th>
															<th>Numero de Cuenta</th>
															<th>Sub Cuenta</th>
															<th>Sub-Sub Cuenta</th>
															<th>Sub-Sub-Sub Cuenta</th>
															<th>Nombre Cuenta</th>
															<th>Nivel</th>
															<th>Rubro</th>
															<th>Tipo de Cuenta</th>
															<th>Catalogo de Cuenta</th>
															<th>Estado</th>
															<th>Acciones</th>
														</tr>
													</thead>
													<tbody>
														<%
														for (Vw_catalogo_tipo_cuentacontable cc : listaCuentasContables) {
															String estado = "";
															if (cc.getEstado() == 1){
																estado = "ACTIVO";
															}else{
																if(cc.getEstado() == 2)
																{
																estado = "MODIFICADO";
																}
																else{
																	estado = "INACTIVO";
																}
															}
														%>
														<tr>

															<td><%=cc.getIdCuenta()%></td>
															<td><%=cc.getNumeroCuenta()%></td>
															<td><%=cc.getsC()%></td>
															<td><%=cc.getSsC()%></td>
															<td><%=cc.getSssC()%></td>
															<td><%=cc.getNombreCuenta()%></td>
															<th><%=cc.getNivel()%></th>
															<td><%=cc.getRubro()%></td>
															<td><%=cc.getTipoCuenta()%></td>
															<td><%=cc.getCatalogoCuenta()%></td>
															<td><%=estado%></td>
															
															<td>
															<a href="editCuentaContable.jsp?idCuenta=<%=cc.getIdCuenta() %>">
																	<i class="fa fa-edit" title="Editar"></i>
															</a> &nbsp;&nbsp; <a href="viewCuentaContable.jsp?idCuenta=<%=cc.getIdCuenta() %>"> 
																	<i class="fa fa-eye" title="Mostrar" ></i>
															</a> &nbsp;&nbsp; <a href="deleteCuentaContable.jsp?idCuenta=<%=cc.getIdCuenta() %>"> 
																	<i class="fa fa-trash" title="Eliminar"></i>
															</a>
															</td>
														</tr>
														<%
														}
														%>
													</tbody>

												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>
					<div class="row">
						<div class="col-md-12 col-md-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Detalles de Cuenta Contable Registrados</h2>
									
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<div class="row">
										<div class="col-md-12">
											<div class="card-box table-responsive">
												
												<table id="datatable-buttons"
													class="table table-striped table-bordered"
													style="width: 100%">
													<%
													ArrayList<Vw_cuentacontable_cuentacontable_det> listaCuentasContablesDet = new ArrayList<Vw_cuentacontable_cuentacontable_det>();
													Dt_cuentaContable_Det dtCcD = new Dt_cuentaContable_Det();
													listaCuentasContablesDet = dtCcD.listaCuentasContablesDet();
													%>
													<thead>
														<tr>
															<th>ID</th>
															<th>Debe</th>
															<th>Haber</th>
															<th>Saldo Final</th>
															<th>Saldo</th>
															<th>Cuenta contable</th>
															<th>Acciones</th>
														</tr>
													</thead>
													<tbody>
														<%
														for (Vw_cuentacontable_cuentacontable_det ccD : listaCuentasContablesDet) {
														%>
														<tr>
															<td><%=ccD.getIdCuentaContableDet()%></td>
															<td><%=ccD.getDebe()%></td>
															<td><%=ccD.getHaber()%></td>
															<td><%=ccD.getSaldoInicial()%></td>
															<td><%=ccD.getSaldoFinal()%></td>
															<td><%=ccD.getNombreCuenta()%></td>
															<td><a href="editCuentaContableDet.jsp?idCD=<%=ccD.getIdCuentaContableDet()%>">
																	<i class="fa fa-edit" title="Editar"></i>
															</a> &nbsp;&nbsp; <a href="viewCuentaContableDet.jsp?idCD=<%=ccD.getIdCuentaContableDet()%>" > 
																	<i class="fa fa-eye" title="Mostrar"></i>
															</a> &nbsp;&nbsp; <a href="deleteCuentaContableDet.jsp?idCD=<%=ccD.getIdCuentaContableDet()%>"> 
																	<i class="fa fa-trash" title="Eliminar"></i>
															</a></td>
														</tr>
														<%
														}
														%>
													</tbody>

												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<!-- /page content -->

	<!-- footer content -->
	<footer>
		<div class="pull-right">Sistema contable by Eldian's Software</div>
		<div class="clearfix"></div>
	</footer>
	<!-- /footer content -->

	<!-- jQuery -->
	<script src="../vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="../vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../vendors/nprogress/nprogress.js"></script>
	<!-- iCheck -->
	<script src="../vendors/iCheck/icheck.min.js"></script>
	<!-- Datatables -->
	<script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script
		src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script
		src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
	<script src="../vendors/jszip/dist/jszip.min.js"></script>
	<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
	<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.min.js"></script>

</body>
</html>