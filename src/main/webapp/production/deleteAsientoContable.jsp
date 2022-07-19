<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="entidades.Vw_usuariorol,
	entidades.Vw_rolopciones,entidades.Tbl_asientoContable, entidades.Tbl_tipoDocumento, entidades.Vw_tasaCambioDet,
	entidades.Vw_catalogo_tipo_cuentacontable, entidades.Vw_asientoContableDet, entidades.Tbl_empresa,
	datos.Dt_rolOpciones, datos.Dt_asientoContable, datos.Dt_tipoDocumento, datos.Dt_tasaCambio, datos.Dt_cuentaContable,
	datos.Dt_asientoContableDet, java.util.*;"%>

<%
Tbl_asientoContable tpacont = new Tbl_asientoContable();
Dt_asientoContable dtac = new Dt_asientoContable();

int idac = (request.getParameter("ascont") != null) ? Integer.parseInt(request.getParameter("ascont")) : 0;
tpacont = dtac.obtenerAContablePorId(idac);
%>

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
	// response.sendRedirect("../login.jsp?msj=401");
	response.sendRedirect("../login.jsp?msj=403");
	return;
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Eliminar | Asiento Contable</title>

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
							<h3>Eliminar Asiento contable</h3>
						</div>
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Datos a eliminar</h2>

									<div class="clearfix"></div>
								</div>

								<div class="x_content">
									<form class="" action="../Sl_asientoContable" method="post"
										novalidate>
										 <input type="hidden" value="3"
											name="opcion" id="opcion" /> <input type="hidden"
											value="<%=tpacont.getIdAsientoContable()%>"
											name="idAContableEliminar" id="idPContableEliminar" />
										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align">Tipo
												documento: </label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Tbl_tipoDocumento> listaTD = new ArrayList<Tbl_tipoDocumento>();
												Dt_tipoDocumento dttd1 = new Dt_tipoDocumento();
												listaTD = dttd1.listaTipoDocumento();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDTD" id="cbxIDTD" disabled="disabled">
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
											<label class="col-form-label col-md-3 col-sm-3  label-align">Tipo
												de cambio: </label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Vw_tasaCambioDet> listaTCD = new ArrayList<Vw_tasaCambioDet>();
												Dt_tasaCambio dttcd1 = new Dt_tasaCambio();
												listaTCD = dttcd1.listarTasaCambioDet();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDTCD" id="cbxIDTCD" disabled="disabled">
													<option value="">Seleccione...</option>
													<%
													for (Vw_tasaCambioDet tcd : listaTCD) {
													%>
													<option value="<%=tcd.getIdTasaCambioDet()%>"><%=tcd.getTipoCambio()%></option>
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
													value="<%=tpacont.getFecha()%>"
													placeholder="Fecha de inicio" name="fechainicioc"
													readonly="readonly">
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align"
												for="descripcion">Concepto<span class="required">*</span></label>
											<div class="col-md-6 col-sm-6">

												<textarea class="form-control" rows="3"
													placeholder="Concepto" id="descripcion"
													name="descripcion" maxlength="150" disabled="disabled"><%=tpacont.getDescripcion()%></textarea>


												<div id="contador">
													<span id="cantidadCaracteres">0</span>/150
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
																			<%
																			ArrayList<Vw_asientoContableDet> listaAsientoContable = new ArrayList<Vw_asientoContableDet>();
																			Dt_asientoContableDet dtac1 = new Dt_asientoContableDet();
																			listaAsientoContable = dtac1.listarasientocontableDET();
																			double total = 0;
																			double debe = 0;
																			double haber = 0;
																			%>
																			<thead>
																				<tr>
																					<th>ID</th>
																					<th>Cuenta</th>
																					<th>Debe</th>
																					<th>Haber</th>
																				</tr>
																			</thead>
																			<tbody>
																				<%
																				for (Vw_asientoContableDet ac : listaAsientoContable) {
																					if (ac.getIdAsientoContable() == idac) {
																				%>
																				<tr>

																					<td><%=ac.getIdAsientoContableDet()%></td>
																					<td><%=ac.getNumeroCuenta()%>/<%=ac.getSC()%>
																						/<%=ac.getSsC()%>/<%=ac.getSssC()%> -- <%=ac.getNombreCuenta()%></td>
																					<td><%=ac.getDebe()%></td>
																					<td><%=ac.getHaber()%></td>
																				</tr>
																				<%
																				total = total + ac.getDebe() - ac.getHaber();
																				debe += ac.getDebe();
																				haber += ac.getHaber();
																				} //else{

																				//}
																				}
																				%>
																			</tbody>

																		</table>
																	</div>
																</div>
																<div class="alert" role="alert" id="debenhaber"
																	style="width: 100%">
																	
																	<div class="alert" role="alert" id="divTotalhaber"
																		style="background: pink; width: 20%; float :right">
																		<p
																			style="color: black; text-align: left; font-size: 20px">
																			Total haber: <span id="thaber" style="color: black "><%=haber%></span>
																		</p>
																	</div>
																	
																	<div class="alert" role="alert" id="divTotaldebe"
																		style="background: lightblue; width: 20%; float: right; right: 25px">
																		<p style="color: black; text-align: left; font-size: 20px">
																			Total debe: <span id="tdebe" style="color: black"><%=debe%></span>
																		</p>
																	</div>
																	
																</div>
																<div class="alert" role="alert" id="divTotal"
																	style="width: 100%">
																	<p
																		style="color: black; text-align: center; font-size: 25px">
																		Saldo: <span id="total" style="color: black"><%=total%>
																		</span>
																	</p>
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
													<button type='submit' class="btn btn-danger">Eliminar</button>
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
		</div>
	</div>

	<!-- /page content -->

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
        ///SOLO ESTE VALOR NO LO PUEDO PONER DE OTRA MANERA
        function setVals() {
            		$("#cbxIDPC").val("<%=tpacont.getIdPeriodoContable()%>");
					$("#cbxIDTD").val("<%=tpacont.getIdTipoDocumento()%>");
					$("#cbxIDE").val("<%=tpacont.getIdEmpresa()%>");
					$("#cbxIDM").val("<%=tpacont.getIdMoneda()%>");
					$("#cbxIDTCD").val("<%=tpacont.getIdTasaCambioDet()%>");
		}

		$(document).ready(function() {
			////CARGAMOS LOS VALORES EN LOS CONTROLES 
			setVals();
		});
	</script>

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
	<script src="../vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
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

	<script>
		$('document')
				.ready(
						function() {

							document.getElementById('cantidadCaracteres').innerHTML = document
									.getElementById('descripcion').value.length;

							const mensaje = document
									.getElementById('descripcion');
							const contador = document
									.getElementById('cantidadCaracteres');
							const saldo =
	<%=total%>
		;
							if (saldo == 0) {
								$("#divTotal").css({
									"background" : "lightgreen"
								});
							} else if (saldo > 0) {
								$("#divTotal").css({
									"background" : "lightblue"
								});
							} else if (saldo < 0) {
								$("#divTotal").css({
									"background" : "pink"
								});
							}
							mensaje.addEventListener('input', function(e) {
								const target = e.target;
								const longitudMax = target
										.getAttribute('maxlength');
								const longitudAct = target.value.length;
								contador.innerHTML = longitudAct;
							});
						});
	</script>
</body>
</html>