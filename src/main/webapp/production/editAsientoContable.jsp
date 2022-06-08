<%@page
	import="com.mysql.cj.protocol.Protocol.GetProfilerEventHandlerInstanceFunction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="entidades.Vw_usuariorol, entidades.Tbl_moneda, entidades.Vw_empresa, entidades.Tbl_periodoContable,
	entidades.Vw_rolopciones,entidades.Tbl_asientoContable, entidades.Tbl_tipoDocumento, entidades.Vw_tasaCambioDet,
	entidades.Vw_catalogo_tipo_cuentacontable, entidades.Vw_asientoContableDet, entidades.Tbl_empresa,
	datos.Dt_rolOpciones, datos.Dt_asientoContable, datos.Dt_tipoDocumento, datos.Dt_tasaCambio, datos.Dt_cuentaContable,
	datos.Dt_asientoContableDet, java.sql.Timestamp, java.util.*;"%>

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

<%
Tbl_asientoContable tpacont = new Tbl_asientoContable();
Dt_asientoContable dtac = new Dt_asientoContable();

int idac = (request.getParameter("ascont") != null) ? Integer.parseInt(request.getParameter("ascont")) : 0;
tpacont = dtac.obtenerAContablePorId(idac);
%>



<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Editar Asiento Contable</title>

<!-- Bootstrap -->
<link href="cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Select2 -->
<link href="../vendors/select2/dist/css/select2.min.css"
	rel="stylesheet" />
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
<!--Jquery Toast Plugin -->
<link href="../vendors/jquery.toast.min.css"
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
							<h3>Editar asiento contable</h3>
						</div>
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Datos de Asiento Contable</h2><br><br>
									<h2 id="fecha_hora_sistem"></h2>
									<div class="float-right periodoContable">
										<h2>Periodo contable:</h2><br><br>
										<h2>Inicio: <%=Tbl_periodoContable.fechaInicioActual %> - Final: <%=Tbl_periodoContable.fechaFinalActual %></h2>
									</div>
									<div class="clearfix"></div>
								</div>

								<div class="x_content">
									<form class="" action="../Sl_asientoContable" method="post"
										novalidate>
										<input type="hidden" value="2" name="opcion" id="opcion" /> <span
											class="section"></span> <input type="hidden" value="0"
											name="detallesEliminados" id="detallesEliminados" /> <input
											type="hidden" value="0" name="detallesAgregados"
											id="detallesAgregados" /> <input type="hidden"
											value="<%=tpacont.getIdAsientoContable()%>" name="idAcont"
											id="idAcont" /> <span class="section"></span>
											<input type="hidden" value="<%=Tbl_periodoContable.idPeriodoActual %>" name="periodoContable" id="periodoContable" />
											<input type="hidden" value="<%=Vw_empresa.empresaActual %>" name="empresaActual" id="empresaActual" />
											<input type="hidden" value="<%=vwur.getId_user()%>" name="usuarioModificacion" id="usuarioModificacion" />
											<input type="hidden" value="<%=Tbl_moneda.idMonedaActual%>" name="moneda" id="moneda" />

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
											<label class="col-form-label col-md-3 col-sm-3  label-align">Tipo
												de cambio: <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6">
												<%
												ArrayList<Vw_tasaCambioDet> listaTC = new ArrayList<Vw_tasaCambioDet>();
												Dt_tasaCambio dttc = new Dt_tasaCambio();
												listaTC = dttc.listarTasaCambioDet();
												%>
												<select class="form-control js-example-basic-single"
													name="cbxIDTCD" id="cbxIDTCD" required="required">
													<option value="">Seleccione...</option>
													<%
													for (Vw_tasaCambioDet tc : listaTC) {
													%>
													<option value="<%=tc.getIdTasaCambioDet()%>"><%=tc.getTipoCambio()%></option>

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
													placeholder="Fecha de inicio" name="fechainicioc"
													value="<%=tpacont.getFecha()%>">
											</div>
										</div>

										<div class="field item form-group">
											<label class="col-form-label col-md-3 col-sm-3  label-align"
												for="descripcion">Concepto<span class="required">*</span></label>
											<div class="col-md-6 col-sm-6">

												<textarea class="form-control" rows="3"
													placeholder="Concepto" id="descripcion"
													name="descripcion" maxlength="150"><%=tpacont.getDescripcion()%></textarea>


												<div id="contador">
													<span id="cantidadCaracteres">0</span>/150
												</div>

											</div>
										</div>

										<div class="x_panel">
											<div class="x_title">
												<h2>Detalles</h2>

												<div class="clearfix"></div>
											</div>

											<div class="x_content">
												<div class="row">
													<div class="col-md-12 col-md-12">
														<div class="x_panel">
															<div class="x_content">
																<div class="row">
																	<div class="field item form-group">
																		<label class="col-form-label  label-align">Cuenta:
																		</label>
																		<div class="col-md-3 col-sm-3">
																			<%
																			ArrayList<Vw_catalogo_tipo_cuentacontable> listaCC = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
																			Dt_cuentaContable dtcc = new Dt_cuentaContable();
																			listaCC = dtcc.listaCuentasContables();
																			%>
																			<select class="js-example-basic-single" name="cbxCC"
																				id="cbxCC" required="required">
																				<option value="" disabled selected>Seleccione...</option>
																				<%
																				for (Vw_catalogo_tipo_cuentacontable cc : listaCC) {
																				%>
																				<option value="<%=cc.getIdCuenta()%>"><%=cc.getNumeroCuenta()%>-<%=cc.getsC()%>-<%=cc.getSsC()%>-<%=cc.getSssC()%>--<%=cc.getNombreCuenta()%></option>
																				<%
																				}
																				%>
																			</select>
																		</div>
																	</div>
																	<div class="row float-center">
																		<div>
																			<label
																				class="col-form-label col-md-3 col-sm-12  label-align">Debe:
																			</label>
																			<div class="col-md-6 col-sm-12">
																				<input type="number" class="form-control"
																					style="background: blue; color: white" name="debe"
																					value="0" min="0" id="debe">
																			</div>
																		</div>
																	</div>

																	<div class="row float-right">
																		<div>
																			<label
																				class="col-form-label col-md-3 col-sm-3  label-align">Haber:
																			</label>
																			<div class="col-md-6 col-sm-6">
																				<input type="number" class="form-control"
																					style="background: red; color: white" name="haber"
																					value="0" min="0" id="haber">
																			</div>
																		</div>
																	</div>

																</div>
																
																<div class="row">
																	<a tabindex="0" id="agregardet" class="btn btn-success col"
																		style="color: black"> Agregar </a>
																</div>
																
																<div class="row">
																	<div class="col-md-12">
																		<div class="card-box table-responsive">
																			<div class="text-muted font-13 col-md-12"
																				style="text-align: right;"></div>
																			<table
																				class="table table-striped jambo_table bulk_action"
																				style="width: 100%" id="tbldet">
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
																						<th>Opción</th>
																						<th>ID Cuenta</th>
																						<th>Cuenta</th>
																						<th>Debe</th>
																						<th>Haber</th>
																						<th></th>
																						<th></th>
																					</tr>
																				</thead>
																				<tbody>

																					<%
																					int detallesSalientes = 0; 
																					for (Vw_asientoContableDet ac : listaAsientoContable) {
																						if (ac.getIdAsientoContable() == idac) {
																					%>
																					<tr id='detail'>
																						<td><input type="button"
																							id="btnBorrarDetalle" value="Borrar" /></td>
																						<td><input type="text"
																							class="form-control col-sm-3" name = "<%="idCuentaSaliente"+detallesSalientes%>"
																							value="<%=ac.getIdCuenta()%>"
																							readonly="readonly"></td>
																						<td><input type="text"
																							class="form-control col-sm-12"
																							value="<%=ac.getNumeroCuenta()%>-<%=ac.getSC()%>-<%=ac.getSsC()%>-<%=ac.getSssC()%>--<%=ac.getNombreCuenta()%>"
																							readonly="readonly"></td>
																						<td id='tddebe'><input type="text"
																							class="form-control col-sm-6"
																							value="<%=ac.getDebe()%>" name = "<%="debeSaliente"+detallesSalientes%>" readonly="readonly"></td>
																						<td id="tdhaber"><input type="text"
																							class="form-control col-sm-6"
																							value="<%=ac.getHaber()%>" name = "<%="haberSaliente"+detallesSalientes%>" readonly="readonly"></td>
																						<td id='agreg'><input type="text"
																							hidden="hidden"
																							value="<%=ac.getIdAsientoContableDet()%>"></td>
																					</tr>
																					<%
																					total = total + ac.getDebe() - ac.getHaber();
																					debe += ac.getDebe();
																					haber += ac.getHaber();
																					detallesSalientes++;  
																					}
																					}
																					%>
																				</tbody>

																			</table>
																		</div>
																	</div>
																	<div class="alert" role="alert" id="divTotal"
																		style="background: lightgreen; width: 100%">
																		<p
																			style="color: black; text-align: center; font-size: 25px">
																			Saldo: <span id="total" style="color: black"><%=total%></span>
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
														<button id="btnGuardar" type='submit' class="btn btn-danger">Guardar
															ediciones</button>
														<a href="tbl_asientoContable.jsp" type="button"
															class="btn btn-primary">Cancelar</a>
													</div>
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
					$("#cbxIDTD").val("<%=tpacont.getIdTipoDocumento()%>");
					$("#cbxIDTCD").val("<%=tpacont.getIdTasaCambioDet()%>");
		}

		$(document).ready(function() {
			////CARGAMOS LOS VALORES EN LOS CONTROLES 
			setVals();
			
			var botonGuardar = document.getElementById("btnGuardar");

	        botonGuardar.addEventListener('click', (e) => {
	            if(saldo !== 0){
	            	$.toast({
	            	    text: "El saldo debe ser 0 para poder guardar",
	            	    heading: 'Advertencia - saldo',
	            	    icon: 'warning',
	            	    showHideTransition: 'slide',
	            	    allowToastClose: false, 
	            	    hideAfter: 5000,
	            	    stack: 5,
	            	    position: 'top-center',  
	            	    
	            	    textAlign: 'left',
	            	    loader: true,
	            	    loaderBg: '#9EC600',
	            	    
	            	});
	                e.preventDefault();
	            };
	        });
			
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

	<script src="../vendors/jquery.toast.min.js"></script>

<script>
	var counter = 100000;  
	var dateContainer = document.getElementById("fecha_hora_sistem");
	var today = new Date();
	dateContainer.innerHTML = "Fecha del sistema: " + today.getDate() + "/" + (today.getMonth() + 1) + "/" + today.getFullYear() + "<br><br>";
	var temp = dateContainer.innerHTML;
	
	function refreshHour(){
		var todayTime = new Date();
		var time = "";
		var newFormat = (todayTime.getHours()) >= 12 ? 'PM' : 'AM';
		
		dateContainer.innerHTML = "";
		if(counter > 0){
			counter--; 
			time = "Hora del sistema: " + (todayTime.getHours() < 12 ? "0" + todayTime.getHours() : todayTime.getHours() - 12) + ":" + (todayTime.getMinutes() < 10 ? "0" + todayTime.getMinutes() : todayTime.getMinutes()) + ":" + (todayTime.getSeconds() < 10 ? "0" + todayTime.getSeconds() : todayTime.getSeconds()) +" "+  newFormat;
			dateContainer.innerHTML = temp + time; 
			setTimeout(refreshHour, 1000);
		}else {
			counter = 100000; 
			setTimeout(refreshHour, 1000);
		};
	};
	
	refreshHour();
</script>

	<script>
		//Inicio select2
		$(document).ready(function() {
			$('.js-example-basic-single').select2();
		});
		//Cierre Select2

		var saldo = 0;
		var debe = 0;
		var haber = 0;
		var tableBody = document.getElementById("tbldet");
		var cantDetalles = document.getElementById("detalles");
		var rows = getCantRows();
		var deletedRows = 0;
		var addedRows = 0;
		var addedRegistersCounters = [ 0, 0, 0 ];
		var deletedRegServletKeyWords = [];

		$("#agregardet")
				.click(function() {
							if (!$.isNumeric($("#debe").val()) || !$.isNumeric($("#haber").val()) || $("#cbxCC option:checked").val() == 0) {
								$.toast({
									text : "Datos inválidos", // Text that is to be shown in the toast

									icon : 'warning', // Type of toast icon
									showHideTransition : 'plain', // fade, slide or plain
									allowToastClose : false, // Boolean value true or false
									hideAfter : 5000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
									stack : 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
									position : 'mid-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values

									textAlign : 'left', // Text alignment i.e. left, right or center
									loader : true, // Whether to show loader or not. True by default
									loaderBg : '#9EC600', // Background color of the toast loader
								});
							} else {
								$("#tbldet tbody")
										.append("<tr id='detail' class='added'>"
												+ "<td>" + '<input type="button" id="btnBorrarDetalle" value="Borrar" />' + "</td>"
												+ "<td id='idCuenta'>" + '<input type="text" class="form-control col-sm-3" value=' + $("#cbxCC").val() + ' readOnly>' + "</td>"
												+ "<td>" + '<input type="text" class="form-control col-sm-6" value=' + $('#cbxCC option:selected').text() + ' readOnly>'
												+ "</td>" + "<td id='tddebe'>" + '<input type="text" class="form-control col-sm-6" value=' + $("#debe").val() + ' readOnly>'
												+ "</td>" + "<td id='tdhaber'>" + '<input type="text" class="form-control col-sm-6" value=' + $("#haber").val() + ' readOnly>'
												+ "</td>" + "<td id='agreg'>'" + '<input type="text" value="0" hidden="hidden">' + "</td>"
												+ "</tr>");
								debe = parseFloat($("#debe").val());
								haber = parseFloat($("#haber").val());
								saldo = saldo + (debe - haber);

								$("#debe").val(0);
								$("#haber").val(0);
								if (saldo == 0) {
									$("#divTotal").css({"background" : "lightgreen"});
								} else if (saldo > 0) {
									$("#divTotal").css({"background" : "lightblue"});
								} else if (saldo < 0) {
									$("#divTotal").css({"background" : "pink"});
								}

								$("#total").text(saldo);

								rows = getCantRows();
								addedRowsNameMapper();
							}
						});

		$("#tbldet").on('click', '#btnBorrarDetalle', function() {
			var currentRow = $(this).closest("tr");
			debe = parseFloat(currentRow.find("#tddebe").children().val());
			haber = parseFloat(currentRow.find("#tdhaber").children().val());
			$(this).parent().parent().hide();
			rows = getCantRows();
			deletedRowsNameMapper();
			saldo = saldo - debe + haber;
			$("#total").text(saldo);

			$("#debe").val(0);
			$("#haber").val(0);
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
		});

		function deletedRowsNameMapper() {
			var columns = 0;
			var idCeldaAgregado = "agreg";
			var idRegistro = "";
			var cantDetallesEliminados = document.getElementById("detallesEliminados");
			var cantDetallesAgregados = document.getElementById("detallesAgregados");
			var detallesAbortados = 0;

			for (var x = 0; x < tableBody.childNodes[3].childNodes.length; x++) {
				if (tableBody.childNodes[3].childNodes[x].tagName == "TR" && !tableBody.childNodes[3].childNodes[x].classList.contains("addedAndConfigured")) {
					if (tableBody.childNodes[3].childNodes[x].style.display == "none" && !tableBody.childNodes[3].childNodes[x].classList.contains("abortedReg")) {
						columns = tableBody.childNodes[3].childNodes[x].childNodes.length;
						for (var y = 0; y < columns; y++) {
							if (tableBody.childNodes[3].childNodes[x].childNodes[y].tagName == "TD" && tableBody.childNodes[3].childNodes[x].childNodes[y].id == idCeldaAgregado) {
								if (!tableBody.childNodes[3].childNodes[x].childNodes[y].classList.contains("deleted")) {
									tableBody.childNodes[3].childNodes[x].childNodes[y].classList.add("deleted");
									tableBody.childNodes[3].childNodes[x].childNodes[y].firstElementChild.setAttribute('name', "detalleEliminado" + deletedRows);
									deletedRows++;
									cantDetallesEliminados.setAttribute('value', deletedRows);
								};
							};
						};
					};
				} else if (tableBody.childNodes[3].childNodes[x].tagName == "TR" && tableBody.childNodes[3].childNodes[x].classList.contains("addedAndConfigured")) {
					if (tableBody.childNodes[3].childNodes[x].style.display == "none") {
						tableBody.childNodes[3].childNodes[x].classList.remove("addedAndConfigured")
						tableBody.childNodes[3].childNodes[x].classList.add("abortedReg")
						detallesAbortados++;
						addedRows--;
						cantDetallesAgregados.setAttribute('value', parseInt(cantDetallesAgregados.value) - detallesAbortados);
						columns = tableBody.childNodes[3].childNodes[x].childNodes.length;
						for (var y = 0; y < columns; y++) {
							if (tableBody.childNodes[3].childNodes[x].childNodes[y].tagName == "TD") {
								switch (tableBody.childNodes[3].childNodes[x].childNodes[y].id) {
								case "idCuenta":
									tableBody.childNodes[3].childNodes[x].childNodes[y].firstElementChild.removeAttribute('name');
									addedRegistersCounters[0]--;
									break;
								case "tddebe":
									tableBody.childNodes[3].childNodes[x].childNodes[y].firstElementChild.removeAttribute('name');
									addedRegistersCounters[1]--;
									break;
								case "tdhaber":
									tableBody.childNodes[3].childNodes[x].childNodes[y].firstElementChild.removeAttribute('name');
									addedRegistersCounters[2]--;
									break;
								};
							};
						};
					};
				};
			};
		};

		function addedRowsNameMapper() {
			var columns = 0;
			var idCeldaAgregado = "agreg";
			var idRegistro = "";
			var cantDetallesAgregados = document.getElementById("detallesAgregados");

			for (var x = 0; x < tableBody.childNodes[3].childNodes.length; x++) {
				if (tableBody.childNodes[3].childNodes[x].tagName == "TR") {
					if (tableBody.childNodes[3].childNodes[x].classList.contains("added")) {
						tableBody.childNodes[3].childNodes[x].classList.remove("added");
						tableBody.childNodes[3].childNodes[x].classList.add("addedAndConfigured");
						addedRows++;
						cantDetallesAgregados.setAttribute('value', addedRows);
						columns = tableBody.childNodes[3].childNodes[x].childNodes.length;
						for (var y = 0; y < columns; y++) {
							if (tableBody.childNodes[3].childNodes[x].childNodes[y].tagName == "TD") {
								switch (tableBody.childNodes[3].childNodes[x].childNodes[y].id) {
								case "idCuenta":
									tableBody.childNodes[3].childNodes[x].childNodes[y].firstElementChild.setAttribute('name',"idCuenta" + (addedRegistersCounters[0]));
									addedRegistersCounters[0]++;
									break;
								case "tddebe":
									tableBody.childNodes[3].childNodes[x].childNodes[y].firstElementChild.setAttribute('name',"debe" + (addedRegistersCounters[1]));
									addedRegistersCounters[1]++;
									break;
								case "tdhaber":
									tableBody.childNodes[3].childNodes[x].childNodes[y].firstElementChild.setAttribute('name',"haber" + (addedRegistersCounters[2]));
									addedRegistersCounters[2]++;
									break;
								};
							};
						};
					};
				};
			};
		};

		function getCantRows() {
			var rowsCant = 0;
			for (var x = 0; x < tableBody.childNodes[3].childNodes.length; x++) {
				if (tableBody.childNodes[3].childNodes[x].tagName == "TR" && tableBody.childNodes[3].childNodes[x].style.display != "none") {
					rowsCant++;
				};
			}
			return rowsCant;
		};
	</script>

	<script>
		$('document').ready(function() {

							document.getElementById('cantidadCaracteres').innerHTML = document.getElementById('descripcion').value.length;

							const mensaje = document.getElementById('descripcion');
							const contador = document.getElementById('cantidadCaracteres');
							saldo = <%=total%>;
							debe = <%=debe%>;
							haber = <%=haber%>;
		if (saldo == 0) {
								$("#divTotal").css({"background" : "lightgreen"});
							} else if (saldo > 0) {
								$("#divTotal").css({"background" : "lightblue"});
							} else if (saldo < 0) {
								$("#divTotal").css({"background" : "pink"});
							}

							mensaje.addEventListener('input', function(e) {
								const target = e.target;
								const longitudMax = target.getAttribute('maxlength');
								const longitudAct = target.value.length;
								contador.innerHTML = longitudAct;
							});
						});
	</script>
	<script src="../vendors/select2/dist/js/select2.min.js"></script>
</body>
</html>