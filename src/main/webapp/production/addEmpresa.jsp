<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="entidades.Tbl_empresa, entidades.Vw_empresa,entidades.Vw_usuariorol, entidades.Vw_rolopciones, entidades.Vw_representanteLegal, 
	entidades.Tbl_periodoFiscal, entidades.Tbl_departamento, entidades.Vw_municipio,entidades.Tbl_tipoIdentificacion, entidades.Tbl_representanteLegal, 
	datos.Dt_empresa, datos.Dt_representanteLegal, datos.Dt_municipio, datos.Dt_periodoFiscal, datos.Dt_departamento, datos.Dt_rolOpciones, datos.Dt_tipoIdentificacion,  
	 
	 java.util.ArrayList;"%>

<%

int idEmpresaHidden = request.getParameter("idEmpresaHidden") != null ? Integer.parseInt(request.getParameter("idEmpresaHidden")) : 0;
 
%>
<%-- <%
//JAlert flag

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
int currentUsuario;
//OBTENEMOS LA SESION
vwur = (Vw_usuariorol) session.getAttribute("acceso");

if (vwur != null) {
	//OBTENEMOS LA LISTA DE OPCIONES ASIGNADAS AL ROL
	currentUsuario = vwur.getId_user();
	listOpc = dtro.ObtenerRolOpcionPorIdLogin(vwur.getIdUsuarioRol());

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
	response.sendRedirect("../login.jsp?msj=403");
	//response.sendRedirect("page_403.jsp");
	return;
}
%> --%>



<!DOCTYPE html>
<html lang="es">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Agregar | Empresa</title>
<link rel="stylesheet" href="../vendors/jAlert/dist/jAlert.css" />

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
			<jsp:include page="navegacion.jsp"></jsp:include>

			<!-- /top navigation -->
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Agregar empresa</h3>
						</div>

						<div class="title_right">
							<div class="col-md-5 col-sm-5 form-group pull-right top_search">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Search for..."> <span
										class="input-group-btn">
										<button class="btn btn-default" type="button">Go!</button>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 col-sm-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>
									Formulario <small>Agregar empresa</small>
								</h2>

								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<form class="" action="../Sl_empresa" method="post" novalidate>
									<input type="hidden" value="1" name="opcion" id="opcion" />
									<input type="hidden" value=<%=idEmpresaHidden%> name="idEmpresaHidden" id="idEmpresaHidden" />
									<%-- 											<input type="hidden" value=<%=currentUsuario%> name="currentUsuario" id="currentUsuario" />
 --%>
									<span class="section">Datos de empresa</span>
									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">RUC<span
											class="required">*</span></label>
										<div class="col-md-6 col-sm-6">
											<input required="required" name="ruc" class="form-control"
												placeholder="ex. 2347827431" required="required" id="ruc" />
										</div>
									</div>
									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Raz�n
											social<span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6">
											<input name="razonSocial" class="form-control"
												class='optional' type="text" required="required" />
										</div>
									</div>
									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Nombre
											comercial<span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6">
											<input name="nombreComercial" class="form-control"
												class='optional' name="occupation" type="text"
												required="required" />
										</div>
									</div>
									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Correo<span
											class="required">*</span></label>
										<div class="col-md-6 col-sm-6">
											<input name="correo" class="form-control" class='email'
												required="required" type="email" />
										</div>
									</div>

									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Tel�fono<span
											class="required">*</span></label>
										<div class="col-md-6 col-sm-6">
											<input name="telefono" class="form-control" type="tel"
												id="telefonoEm" class='tel' required='required'
												data-validate-length-range="8,8" />
										</div>
									</div>
									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Direcci�n<span
											class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6">
											<input name="direccion" class="form-control" class='optional'
												name="occupation" data-validate-length-range="5,100"
												type="text" required="required" />
										</div>
									</div>



									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Representante legal:
										 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6">
											<!--                                                 
												<input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->
											<%
											ArrayList<Vw_representanteLegal> listaRep = new ArrayList<Vw_representanteLegal>();
											Dt_representanteLegal dtRep = new Dt_representanteLegal();
											listaRep = dtRep.listarRepresentanteLegal();
											%>

											<select class="form-control js-example-basic-single"
												name="valueIdR" id="valueIdR" required="required">
												<option value="0">Seleccione...</option>
												<%
												for (Vw_representanteLegal rep : listaRep) {
												%>
												<option value="<%=rep.getIdRepresentante()%>">
													<%=rep.getNombreCompleto()%>
												</option>
												<%
												}
												%>

											</select>
										</div>
										
										<p>No encuentra el representante legal? <br />
										<a href="/SistemaContable/production/addRepresentanteLegal.jsp">Agregar representante legal aqui</a>
										</p>
									</div>



									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Periodo
											fiscal: <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6">
											<!--                                                 
												<input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->
											<%
											ArrayList<Tbl_periodoFiscal> listaPeriodo = new ArrayList<Tbl_periodoFiscal>();
											Dt_periodoFiscal dtPeriodo = new Dt_periodoFiscal();
											listaPeriodo = dtPeriodo.listarperiodoFiscal();
											%>

											<select class="form-control js-example-basic-single"
												name="periodoFiscal" id="periodoFiscal" required="required">
												<option value="0">Seleccione...</option>
												<%
												for (Tbl_periodoFiscal periodo : listaPeriodo) {
												%>
												<option value="<%=periodo.getIdPeriodoFiscal()%>">
													<%=periodo.getFechaInicio()%> ->
													<%=periodo.getFechaFinal()%>
												</option>
												<%
												}
												%>

											</select>
										</div>
									</div>



									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Departamento:
											<span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6">
											<!--                                                 <input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->
											<%
											ArrayList<Tbl_departamento> listaDep = new ArrayList<Tbl_departamento>();
											Dt_departamento dtDep = new Dt_departamento();
											listaDep = dtDep.listarDepartamento();
											ArrayList<Vw_municipio> listaMuni = new ArrayList<Vw_municipio>();
											Dt_municipio dtMunicipio = new Dt_municipio();
											int currentDepId = 0; 
											%>
											<select onchange="checkDep()" class="form-control js-example-basic-single"
												name="departamento" id="departamento" required="required">
												<option value="0">Seleccione...</option>
												<%
												for (Tbl_departamento depa : listaDep) {
													
												%>
												<option value="<%=depa.getIdDepartamento()%>">
													<%=depa.getDepartamento()%>
												</option>
												<%
												currentDepId = depa.getIdDepartamento();
												
												}
												listaMuni = dtMunicipio.listarMunicipioByDepId(currentDepId);

												%>

											</select>
										</div>
									</div>

									<div class="field item form-group">
										<label class="col-form-label col-md-3 col-sm-3  label-align">Municipio:
											<span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6">
											<!--                                                 <input class="form-control" class='optional' name="occupation" data-validate-length-range="5,15" type="text" /></div> -->
											<%
											
										
										
									
											
											%>
											<select class="form-control js-example-basic-single"
												name="municipio" id="municipio" required="required">
												<option value="0">Seleccione...</option>
												<%
												System.out.println(listaMuni); 

												for (Vw_municipio muni : listaMuni) {
												%>
												<option value="<%=muni.getIdMunicipio()%>">
													<%=muni.getMunicipio()%>
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
												<button id="agregarE" type='submit' onClick=(validarDatos())
													class="btn btn-primary">Agregar</button>
												<button id="reiniciarE" type='reset'
													onClick=(location.reload()) class="btn btn-success">Reiniciar</button>
												<button id="cancelarE" type="button" class="btn btn-primary">Cancelar</button>
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
			<div class="pull-right">Sistema contable by Eldian's Software</div>
			<div class="clearfix"></div>
		</footer>
		<!-- /footer content -->
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
		</script>

	

	<script>
		function validarDatos() {
			if (document.getElementbyId("ruc").value = "") {
				alert("Ingrese un RUC");
			}

			if (document.getElementbyId("telefonoEm").length > 8) {
				alert("El telefono no puede ser mayor a 8 numeros");
			}
		}
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
	
	<!-- jAlert -->
	<script src="../vendors/jAlert/dist/jAlert.min.js"></script>
	<script src="../vendors/jAlert/dist/jAlert-functions.min.js"></script>


	<script>
	var dep = document.getElementById("departamento"); 
	document.getElementById("municipio").disabled = false; 

	function checkDep(){
	if (document.getElementById("departamento").value != "0"){
			document.getElementById("municipio").disabled = false; 
		}
	
	console.log(dep.value)
	}
	
		
		
		
	</script>


</body>

</html>
