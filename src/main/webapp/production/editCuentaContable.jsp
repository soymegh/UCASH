<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*" %>
<!DOCTYPE html>
<html>
<%

//Maestro
String cc = "";
cc = request.getParameter("idCuenta") == null ? "0" : request.getParameter("idCuenta");

Vw_catalogo_tipo_cuentacontable vwCc = new Vw_catalogo_tipo_cuentacontable();
Dt_cuentaContable dtCc = new Dt_cuentaContable();
vwCc = dtCc.getCuentaContableById(Integer.parseInt(cc));


Tbl_tipocuenta ttc = new Tbl_tipocuenta();
Dt_tipocuenta dtTcc = new Dt_tipocuenta();
ttc = dtTcc.getTableTipocuentaByID(Integer.parseInt(cc));


Vw_catalogocuenta_empresa cata = new Vw_catalogocuenta_empresa();
Dt_catalogocuenta dtCatC = new Dt_catalogocuenta();
cata = dtCatC.getCatalogoByID(Integer.parseInt(cc));

%>

<%
//Detalle
String CCD = "";
Vw_cuentacontable_cuentacontable_det vwCCD = new Vw_cuentacontable_cuentacontable_det();
Dt_cuentaContable_Det dtCCD = new Dt_cuentaContable_Det();

int idCCD = request.getParameter("idCuenta") != null ? Integer.parseInt(request.getParameter("idCuenta")): 0;
vwCCD = dtCCD.getCCDbyID(idCCD);

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Editar | Cuenta Contable</title>

<!-- Bootstrap -->
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
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
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
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
							<h2>Lic. José Ortega.</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>Control General</h3>
							<ul class="nav side-menu">
								<li><a href="index.html"><i class="fa fa-home"></i>Inicio</a>
									<ul class="nav child_menu">
										<li><a href="index.html">Página Principal</a></li>
									</ul></li>

								<li><a><i class="fa fa-shield"></i> Seguridad <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_usuario.jsp">Usuarios</a></li>
										<li><a href="tbl_rol.jsp">Roles</a></li>
										<li><a href="tbl_opciones.jsp">Opciones</a></li>
										<li><a href="tbl_usuarioRol.jsp">Roles de Usuario</a></li>
										<li><a href="tbl_rolOpciones.jsp">Opciones de Rol</a></li>
									</ul></li>
									<li><a><i class="fa fa-building"></i>Gestion Empresa<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_empresa.jsp">Empresas</a></li>
										<li><a href="tbl_municipio.jsp">Municipio</a></li>
										<li><a href="tbl_representanteLegal.jsp">Representante Legal</a></li>
									</ul></li>
									<li><a><i class="fa fa-file"></i>Gestion Cuenta Contable<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_catalogocuenta.jsp">Catalogo Cuenta</a></li>
										<li><a href="tbl_tipocuenta.jsp">Tipo cuenta</a></li>
										<li><a href="tbl_cuentacontable.jsp">Cuenta Contable</a></li>
									</ul></li>
									<li><a><i class="fa fa-dollar"></i>Gestion Moneda<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tbl_moneda.jsp">Moneda</a></li>
										<li><a href="tbl_tasaCambio.jsp">Tasa Cambio</a></li>
									</ul></li>

								<li><a><i class="fa fa-book"></i>Gestion Asiento Contable<span
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
								aria-expanded="true"> <img src="img.jpg" alt="">Lic. José Ortega.
							</a>
								
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
							<h3>Editar Cuenta Contable</h3>
						</div>

						
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										Editar Cuenta Contable
									</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<form class="" action="../Sl_cuentaContable" method="post" novalidate>
										<input type="hidden" value="2" name="opcion" id="opcion"/>
										<input type="hidden" value="<%= vwCc.getIdCuenta()%>" name="idCuenta" id="idCuenta" />
										
										<span class="section">Datos de Cuenta Contable Maestro</span>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Numero de Cuenta:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="number" class="optional" name="numeroCuenta" id="numeroCuenta"
												value="<%= vwCc.getNumeroCuenta() %>"/>
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Sub-Cuenta:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="text" class="optional" name="SC" id="SC" 
												value="<%= vwCc.getsC() %>"/>
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Sub-Sub-Cuenta:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="text" class="optional" name="SsC" id="SsC" 
												value="<%= vwCc.getSsC() %>"/>
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Sub-Sub-Sub-Cuenta:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="text" class="optional" name="SssC" id="SssC" 
												value="<%= vwCc.getSssC() %>"/>
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Nombre Cuenta:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="text" class="optional" name="nombreCuenta" id="nombreCuenta" 
												value="<%= vwCc.getNombreCuenta() %>"/>
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Nivel</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="number" class="optional" name="nivel" id="nivel" 
												value="<%= vwCc.getNivel() %>"/>
											</div>
										</div>
										
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Rubro:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="number" class="optional" name="rubro" id="rubro" 
												value="<%= vwCc.getRubro() %>"/>
											</div>
										</div>
										
										<div class="field item form-group">
                                            <label class="col-form-label col-md-3 col-sm-3  label-align">Tipo de Cuenta:</label>
                                            <div class="col-md-6 col-sm-6">
<!--                                                 <input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->

													<% 
													ArrayList<Tbl_tipocuenta> listaTc = new ArrayList<Tbl_tipocuenta>();
													Dt_tipocuenta dtTc = new Dt_tipocuenta();
													listaTc = dtTc.listaTipocuentaActivos();
													%>

								                 <select class="form-control js-example-basic-single" name="cbxTipoCuenta" id="cbxTipoCuenta" required="required" >
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
                                            <label class="col-form-label col-md-3 col-sm-3  label-align">Cuenta Contable:</label>
                                            <div class="col-md-6 col-sm-6">
<!--                                                 <input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->

								                 	<% 
													ArrayList<Vw_catalogocuenta_empresa> listaCat = new ArrayList<Vw_catalogocuenta_empresa>();
													Dt_catalogocuenta dtCat = new Dt_catalogocuenta();
													listaCat = dtCat.listarCatalogocuenta();
													%>

								                 <select class="form-control js-example-basic-single" 
								                 		 name="cbxCatalogoCuenta" id="cbxCatalogoCuenta" required="required">
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
											<label class="col-form-label col-md-3 col-sm-3  label-align">Saldo Inicial:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="number" class="optional" name="saldoInicial" id="saldoInicial" 
												value="<%= vwCCD.getSaldoInicial() %>"/>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Debe:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="number" class="optional" name="debe" id="debe" 
												value="<%= vwCCD.getDebe() %>"/>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Haber:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="number" class="optional" name="haber" id="haber" 
												value="<%= vwCCD.getHaber() %>"/>
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Saldo Final:</label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="number" class="optional" name="saldoFinal" id="saldoFinal" 
												value="<%= vwCCD.getSaldoFinal() %>"/>
											</div>
										</div>
										
										<div class="ln_solid">
											<div class="form-group">
												<div class="col-md-6 offset-md-3">
													<p> </p>
													<button type='submit' class="btn btn-primary">Editar</button>
													<a type="button" href="tbl_cuentacontable.jsp" class="btn btn-primary">Cancelar</a>
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
		</div>
	</div>
	
	<footer>
		<div class="pull-right">Sistema contable</div>
		<div class="clearfix"></div>
	</footer>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="../vendors/validator/multifield.js"></script>
	<script src="../vendors/validator/validator.js"></script>
	
	<!-- JavaScript Functions -->

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

	<!-- Script cbx -->
	
	<script>
	
	function setForm(){
		
		document.getElementById("cbxTipoCuenta").value = "<%=ttc.getIdTipoCuenta()%>";
    	document.getElementById("cbxCatalogoCuenta").value = "<%=cata.getIdCatalogo()%>";
    	
		}
	window.onload = setForm;
	
	</script>

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
	<script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
	<script src="../vendors/jszip/dist/jszip.min.js"></script>
	<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
	<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.min.js"></script>

</body>
</html>