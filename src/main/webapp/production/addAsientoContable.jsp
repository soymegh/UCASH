<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*;"%>
	
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Nuevo Asiento Contable</title>

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
										<li><a href="tbl_departamento.jsp"></a></li>
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

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout"
							href="login.html"> <span class="glyphicon glyphicon-off"
							aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
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
									<a class="dropdown-item" href="javascript:;"> Perfil</a> <a
										class="dropdown-item" href="javascript:;"> <span
										class="badge bg-red pull-right">50%</span> <span>Ajustes</span>
									</a> <a class="dropdown-item" href="javascript:;">Ayuda</a> <a
										class="dropdown-item" href="login.html"><i
										class="fa fa-sign-out pull-right"></i> Salir</a>
								</div>
							</li>

							<li role="presentation" class="nav-item dropdown open"><a
								href="javascript:;" class="dropdown-toggle info-number"
								id="navbarDropdown1" data-toggle="dropdown"
								aria-expanded="false"> <i class="fa fa-envelope-o"></i> <span
									class="badge bg-green">6</span>
							</a>
								<ul class="dropdown-menu list-unstyled msg_list" role="menu"
									aria-labelledby="navbarDropdown1">
									<li class="nav-item"><a class="dropdown-item"> <span
											class="image"><img src="images/img.jpg"
												alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li class="nav-item"><a class="dropdown-item"> <span
											class="image"><img src="images/img.jpg"
												alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li class="nav-item"><a class="dropdown-item"> <span
											class="image"><img src="images/img.jpg"
												alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li class="nav-item"><a class="dropdown-item"> <span
											class="image"><img src="images/img.jpg"
												alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li class="nav-item">
										<div class="text-center">
											<a class="dropdown-item"> <strong>See All Alerts</strong>
												<i class="fa fa-angle-right"></i>
											</a>
										</div>
									</li>
								</ul></li>
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
							<h3>Agregar nuevo de asiento contable</h3>
						</div>
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Datos de Asiento Contable</h2>

									<div class="clearfix"></div>
								</div>

								<div class="x_content">
									<form class="" action="" method="post" novalidate>
										<span class="section"></span>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Fecha
												Inicio del Periodo Contable: <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Vw_periodoContable> listaPC = new ArrayList<Vw_periodoContable>();
												Dt_periodoContable dtpc = new Dt_periodoContable();
												listaPC = dtpc.listarperiodoContable();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDPC" id="cbxIDPC" required="required">
													<option value="">Seleccione...</option>
													<%
													for (Vw_periodoContable pc : listaPC) {
													%>
													<option value="<%=pc.getIdPeriodoContable()%>"><%=pc.getFechaInicio()%>
														-
														<%=pc.getFechaFinal()%></option>
													<%
													}
													%>
												</select>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Nombre
												Comercial: <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Vw_empresa> listaE = new ArrayList<Vw_empresa>();
												Dt_empresa dte = new Dt_empresa();
												listaE = dte.listarEmpresa();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDE" id="cbxIDE" required="required"
													onchange="ShowSelected();">
													<option value="">Seleccione...</option>
													<%
													for (Vw_empresa e : listaE) {
													%>
													<option value="<%=e.getIdEmpresa()%>"><%=e.getNombreComercial()%></option>
													<%
													}
													%>
												</select>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Tipo
												documento: <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Tbl_tipoDocumento> listaTD = new ArrayList<Tbl_tipoDocumento>();
												Dt_tipoDocumento dttd = new Dt_tipoDocumento();
												listaTD = dttd.listaTipoDocumento();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDTD" id="cbxIDTD" required="required">
													<option value="">Seleccione...</option>
													<%
													for (Tbl_tipoDocumento td : listaTD) {
													%>
													<option value="<%=td.getIdTipoDocumento()%>"><%=td.getTipo()%></option>
													<%
													}
													%>
												</select>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Moneda:
												<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Tbl_moneda> listaM = new ArrayList<Tbl_moneda>();
												Dt_moneda dtm = new Dt_moneda();
												listaM = dtm.listaMonedasActivas();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDPF" id="cbxIDPF" required="required">
													<option value="">Seleccione...</option>
													<%
													for (Tbl_moneda m : listaM) {
													%>
													<option value="<%=m.getIdMoneda()%>"><%=m.getNombre()%></option>
													<%
													}
													%>
												</select>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Tipo
												de cambio: <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Vw_tasaCambioDetalle> listaTC = new ArrayList<Vw_tasaCambioDetalle>();
												Dt_tasaCambioDet dttc = new Dt_tasaCambioDet();
												listaTC = dttc.listarTasaCambioDetActivos();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDTCD" id="cbxIDTCD" required="required">
													<option value="">Seleccione...</option>
													<%
													for (Vw_tasaCambioDetalle tc : listaTC) {
													%>
													<option value="<%=tc.getIdTasaCambioDetalle()%>"><%=tc.getValor()%></option>
													<!--Marvin no cambies nada de tasa cambio aun, en caso de que el merge no aniada algo de la vista -->
													<%
													}
													%>
												</select>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Fecha:
											</label>
											<div class="col-md-6 col-sm-6">
												<input type="date" class="form-control"
													placeholder="Fecha de inicio" name="fechainicioc">
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Descripción<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" class='optional'
													name="descripcion" data-validate-length-range="5,15"
													type="text" required="required" />
											</div>
										</div>

										<div class="x_panel">

											<div class="x_title">
												<h2>Detalles</h2>

												<div class="clearfix"></div>
											</div>

											<div class="field item form-group">
												<label class="col-form-label col-md-3 col-sm-3  label-align">
													Cuenta: <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6">
													<%
														ArrayList<Vw_catalogo_tipo_cuentacontable> listacc = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
														Dt_cuentaContable dtcc = new Dt_cuentaContable();
														listacc = dtcc.listaCuentasContables();
														%>
													<select class="form-control js-example-basic-single"
														name="cbxIDTD" id="cbxdtcc" required="required">
														<option value="">Seleccione...</option>
														<%
															for (Vw_catalogo_tipo_cuentacontable td : listacc) {
															%>
														<option value="<%=td.getIdCuenta()%>"><%=td.getNumeroCuenta() %> / <%=td.getsC() %>/
														 / <%=td.getSsC() %> / <%=td.getSssC() %></option>
														<%
															}
															%>
													</select>
												</div>
											</div>

											<div class="field item form-group">
												<label class="col-form-label col-md-3 col-sm-3  label-align">Debe<span
													class="required">*</span></label>
												<div class="col-md-6 col-sm-6">
													<input class="form-control" class='optional' name="debe"
														data-validate-length-range="5,15" type="text"
														required="required" />
												</div>
											</div>

											<div class="field item form-group">

												<label class="col-form-label col-md-3 col-sm-3  label-align">Haber<span
													class="required">*</span></label>
												<div class="col-md-6 col-sm-6">
													<input class="form-control" class='optional' name="haber"
														data-validate-length-range="5,15" type="text"
														required="required" />
												</div>
											</div>

											<div class="form-group">
												<div class="col-md-6 offset-md-3">
													<button  class="btn btn-success">Agregar</button>
													<button type="reset" class="btn btn-primary">Reiniciar</button>

												</div>
											</div>
										</div>

										<div class="x_content">
											<div class="row">
												<div class="col-md-12 col-md-12">
													<div class="x_panel">
														<div class="x_content">
															<div class="row">
																<div class="col-md-12">
																	<div class="card-box table-responsive">
																		<div class="text-muted font-13 col-md-12"
																			style="text-align: right;"></div>
																		<table id="datatable-buttons"
																			class="table table-striped table-bordered"
																			style="width: 100%">
																			<thead>
																				<tr>
																					<th>Cuenta</th>
																					<th>Debe</th>
																					<th>Haber</th>
																					<th>Acciones</th>
																				</tr>
																			</thead>
																			<tbody>

																				<tr>

																					<td>a</td>
																					<td>a</td>
																					<td>a</td>
																					<td><a href="#"> <i
																							class="fa fa-trash" title="Eliminar"></i>
																					</a></td>
																				</tr>
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

										<div class="ln_solid">
											<div class="form-group">
												<div class="col-md-6 offset-md-3">
													<button type='submit' class="btn btn-danger">Guardar todo</button>
													<a href="tbl_asientoContable.jsp" type="button"
														class="btn btn-primary">Cancelar</a>
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

			<!-- jQuery -->
			<script src="../vendors/jquery/dist/jquery.min.js"></script>
			<!-- Bootstrap -->
			<script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
			<!-- FastClick -->
			<script src="../vendors/fastclick/lib/fastclick.js"></script>
			<!-- NProgress -->
			<script src="../vendors/nprogress/nprogress.js"></script>
			<!-- bootstrap-progressbar -->
			<script
				src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
			<!-- iCheck -->
			<script src="../vendors/iCheck/icheck.min.js"></script>
			<!-- bootstrap-daterangepicker -->
			<script src="../vendors/moment/min/moment.min.js"></script>
			<script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
			<!-- bootstrap-wysiwyg -->
			<script
				src="../vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
			<script src="../vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
			<script src="../vendors/google-code-prettify/src/prettify.js"></script>
			<!-- jQuery Tags Input -->
			<script src="../vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
			<!-- Switchery -->
			<script src="../vendors/switchery/dist/switchery.min.js"></script>
			<!-- Select2 -->
			<script src="../vendors/select2/dist/js/select2.full.min.js"></script>
			<!-- Parsley -->
			<script src="../vendors/parsleyjs/dist/parsley.min.js"></script>
			<!-- Autosize -->
			<script src="../vendors/autosize/dist/autosize.min.js"></script>
			<!-- jQuery autocomplete -->
			<script
				src="../vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
			<!-- starrr -->
			<script src="../vendors/starrr/dist/starrr.js"></script>
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
			<script
				src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
			<script
				src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
			<script
				src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
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