<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="entidades.*,datos.*, java.util.*;"%>
	
	
<%
String CCD = "";
Vw_cuentacontable_cuentacontable_det vwCCD = new Vw_cuentacontable_cuentacontable_det();
Dt_cuentaContable_Det dtCCD = new Dt_cuentaContable_Det();

String Ccd = "";
Tbl_cuentaContable_Det ccd = new Tbl_cuentaContable_Det();

int idCCD = request.getParameter("idCD") != null ? Integer.parseInt(request.getParameter("idCD")): 0;
vwCCD = dtCCD.getCCDbyID(idCCD);
%>

<!DOCTYPE html>
<html lang="en">

<head>

<style>
.select[readonly] option, select[readonly] optgroup {
    display: none;
}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Agregar | Maestro Cuenta Contable</title>

<!-- Bootstrap -->
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">

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
							<img src="img.jpg" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Bienvenido,</span>
							<h2>Lic. Josï¿½ Ortega.</h2>
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
							<h3>Gestiï¿½n</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-shield"></i> Seguridad <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_usuario.jsp">Usuarios</a></li>
										<li><a href="tbl_rol.jsp">Roles</a></li>
										<li><a href="tbl_opciones.jsp">Opciones</a></li>
										<li><a href="tbl_usuarioRol.jsp">Roles de Usuario</a></li>
										<li><a href="tbl_rolOpciones.jsp">Opciones de Rol</a></li>
									</ul></li>
									
									<li><a><i class="fa fa-building"></i> Empresa<span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_empresa.jsp">Empresas</a></li>
										<li><a href="tbl_departamento.jsp">Departamento</a></li>
										<li><a href="tbl_municipio.jsp">Municipio</a></li>
										<li><a href="tbl_representanteLegal.jsp">Representante Legal</a></li>
									</ul></li>
									
									<li><a><i class="fa fa-file"></i> Cuenta Contable<span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_catalogocuenta.jsp">Catalogo Cuenta</a></li>
										<li><a href="tbl_tipocuenta.jsp">Tipo Cuenta</a></li>
										<li><a href="tbl_cuentacontable.jsp">Cuenta Contable</a></li>
									</ul></li>
									
									<li><a><i class="fa fa-dollar"></i> Moneda<span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_moneda.jsp">Moneda</a></li>
										<li><a href="tbl_tasaCambio.jsp">Tasa Cambio</a></li>
									</ul></li>

								<li><a><i class="fa fa-book"></i> Asiento Contable<span class="fa fa-chevron-down"></span></a>
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
								aria-expanded="false"> <img src="img.jpg" alt="">Lic. José Ortega.
							</a>
								<div class="dropdown-menu dropdown-usermenu pull-right"	aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="login.html"><i class="fa fa-sign-out pull-right"></i>Cerrar Sesion</a>
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
							<h3>Agregar Cuenta Contable</h3>
						</div>

						
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Agregar CC Maestro</h2>

									<div class="clearfix"></div>
								</div>
								
								<div class="x_content">
									<form class="" action="../Sl_cuentaContable" method="post" novalidate>
									<input type="hidden" value="1" name="opcion" id="opcion" />
										<span class="section">Datos de CC Maestro</span>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Numero Cuenta<span class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" class='optional' id="numeroCuenta" name="numeroCuenta" type="text" required="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Sub-Cuenta<span class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input type="number" class="form-control" id="SC" name="SC" class="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Sub-Sub-Cuenta<span class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input type="number" class="form-control" id="SsC" name="SsC" class="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Sub-Sub-Sub-Cuenta<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input type="number" class="form-control" id="SssC" name="SssC" class="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Nombre Cuenta<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input type="text" class="form-control" id="nombreCuenta" name="nombreCuenta" class="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Nivel<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input type="number" class="form-control" id="nivel" name="nivel" class="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Rubro<span class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input type="number" class="form-control" id="rubro" name="rubro" class="required" />
											</div>
										</div>

                                        <div class="field item form-group">
                                            <label class="col-form-label col-md-3 col-sm-3  label-align">Tipo Cuenta<span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6">
<!--                                                 <input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->
													
													<% 
													ArrayList<Tbl_tipocuenta> listaTc = new ArrayList<Tbl_tipocuenta>();
													Dt_tipocuenta dtTc = new Dt_tipocuenta();
													listaTc = dtTc.listaTipocuentaActivos();
													%>
													
								                 <select class="form-control js-example-basic-single" 
								                 		 name="tipoCuenta" id="tipoCuenta" required="required">
												  <option value="">Seleccione...</option>
												  	<%
												  		for(Tbl_tipocuenta tc : listaTc){
												  	%>
												  <option value="<%=tc.getIdTipoCuenta()%>">
													<%=tc.getTipoCuenta()%>
												</option>
													<%
													}
													%>
												</select>
											</div>
                                        </div>
                                        
                                        <div class="field item form-group">
                                            <label class="col-form-label col-md-3 col-sm-3  label-align">Catalogo Cuenta<span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6">
<!--                                                 <input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->

												 	<% 
													ArrayList<Vw_catalogocuenta_empresa> listaCat = new ArrayList<Vw_catalogocuenta_empresa>();
													Dt_catalogocuenta dtCat = new Dt_catalogocuenta();
													listaCat = dtCat.listarCatalogocuenta();
													%>

								                 <select class="form-control js-example-basic-single" 
								                 		 name="catalogoCuenta" id="catalogoCuenta" required="required">
												  <option value="">Seleccione...</option>
												  	<%
												  		for(Vw_catalogocuenta_empresa cat : listaCat){
												  	%>
												  <option value="<%=cat.getIdCatalogo()%>">
													<%=cat.getTitulo()%>
												</option>
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
												<input class="form-control" class='optional' name="debe" id="debe"
													 type="text"
													required="required" />
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Haber<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" class='optional' name="haber" id="haber"
													 type="text"
													required="required" />
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Saldo Inicial<span
												class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" class='optional'
													name="saldoInicial" id="saldoInicial"
													type="text" required="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Saldo
												Final<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="tel" class='optional'
													name="saldoFinal" id="saldoFinal" required='required'
													/>
											</div>
										</div>
										
										
									<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Cuenta Contable<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="tel" class='optional'
													name="idCuenta" id="idCuenta" 
													/>
											</div>
										</div>
                                        
										<div class="ln_solid">
											<div class="form-group">
												<div class="col-md-6 offset-md-3">
													<button id="AgregarCC" type='submit' class="btn btn-primary">Agregar</button>
													<button id="ResetCC" type='reset' class="btn btn-success">Reiniciar</button>
													<a  href="tbl_cuentacontable.jsp"><button id="CancelarCC" type="button" class="btn btn-primary">Cancelar</button></a>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					
					
					<%-- <div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Agregar Detalle CC</h2>

									<div class="clearfix"></div>
								</div>

								<div class="x_content">
									<form class="" action="../Sl_cuentaContableDet" method="post" novalidate>
									<input type="hidden" value="1" name="opcion" id="opcion" /> 
										<span class="section">Datos de Detalle CC</span>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Debe<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" class='optional' name="debe" id="debe"
													 type="text"
													required="required" />
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Haber<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" class='optional' name="haber" id="haber"
													 type="text"
													required="required" />
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Saldo Inicial<span
												class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" class='optional'
													name="saldoInicial" id="saldoInicial"
													type="text" required="required" />
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Saldo
												Final<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="tel" class='optional'
													name="saldoFinal" id="saldoFinal" required='required'
													/>
											</div>
										</div>
										
										
									<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Nombre de cuenta<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Vw_catalogo_tipo_cuentacontable> listaNc = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
												Dt_cuentaContable dtidCuenta = new Dt_cuentaContable();
												listaNc = dtidCuenta.listaCuentasContables();
												%>
												<select class="form-control js-example-basic-single"
													name="idCuenta" id="idCuenta" required="required" style="pointer-events: none;">
													<option id="idCuentaC" value="0" >Seleccione...</option>
													<%
													for (Vw_catalogo_tipo_cuentacontable ccDS : listaNc){
													%>
													<option value="<%=ccDS.getIdCuenta()%>">
													<%=ccDS.getNombreCuenta()%>
												</option>
													<%
													}
													%>
												</select>
											</div>
										</div>
										<div class="ln_solid">
											<div class="form-group">
												<div class="col-md-6 offset-md-3">
													<button id="AgregarCCD" type='submit' class="btn btn-primary">Agregar</button>
													<button id="ResetCCD"
													 type='reset' onClick=(location.reload()) class="btn btn-success">Reiniciar</button>
													<a  href="tbl_cuentacontable.jsp"><button id="CancelarCCD" type="button" class="btn btn-primary">Cancelar</button></a>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div> --%>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">Sistema contable</div>
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

<script type="text/javascript"></script>
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
	</script>
	
	<script>
			function getURL() {
				const url = window.location.href;
				var idCC = url.substring(url.indexOf('=') + 1);
				document.getElementById("idCuenta").value;
				
				}
			}
			window.onload = getURL();
		</script>
		<script>
			function returnidCC() {
				const url = window.location.href;
				const idCC = url.substring(url.indexOf('=') + 1);
				if (url == "http://localhost:8080/SistemaContable/production/addCuentaContable.jsp") {
					document.getElementById('valueidCC').value = "Se ingresara automaticamente cuando guarde Cuenta Contable";
				} else {
					
					document.getElementById('valueidCC').value = idCC;
				}
				console.log(document.getElementById('valueidCC').value);
			}
			window.onload = returnidCC();
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