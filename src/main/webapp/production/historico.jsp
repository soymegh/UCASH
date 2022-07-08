<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="entidades.Historico, entidades.Vw_catalogo_tipo_cuentacontable, entidades.Vw_usuariorol, entidades.Vw_empresa, entidades.Vw_rolopciones,entidades.Tbl_asientoContable,
	entidades.Vw_asientoContable, entidades.Tbl_periodoContable, datos.Dt_cuentaContable,
	datos.Dt_rolOpciones, datos.Dt_asientoContableDet, datos.Dt_asientoContable, datos.Dt_periodoContable, java.util.*, java.text.*;"%>

<%

//Placeholder para el mensaje
String codigoMensaje = "";

int idCuenta = 0; 
String fechaInicio = "";
String fechaFinal =  "";

if(request.getParameter("idCuenta") != null){
	idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
}

if(request.getParameter("fechaI") != null){
	fechaInicio = request.getParameter("fechaI");
}

if(request.getParameter("fechaF") != null){
	fechaFinal = request.getParameter("fechaF");
}

if (request.getParameter("msj") != null)
	codigoMensaje = request.getParameter("msj");

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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Historico de movimientos contables</title>

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
	
<!--Jquery Toast Plugin -->
<link href="../vendors/jquery.toast.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
<input type="hidden" id="idMensaje" value="<%=codigoMensaje%>" />
	<div class="container body">
		<div class="main_container">
			<jsp:include page="navegacion.jsp"></jsp:include>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Historico</h3>
						</div>

						<div class="title_right">
							<div
								class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
							</div>
						</div>
					</div>

					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-md-12">
							<div class="x_panel">
								<div class="x_content">
									<div class="row">
										<div class="col-md-12 col-md-12">
											<div class="x_panel">
												<div class="x_content">
													<div class="row">
														<div class="col-md-12">
															<div class="card-box table-responsive">
																<form class="" action="../Sl_asientoContable" method="post" data-parsley-validate>
																<input type="hidden" value="4" name="opcion" id="opcion" />
																<div class="field item form-group">
																	<label class="col-form-label col-md-3 col-sm-3  label-align">Cuenta
																		<span class="required">*</span>
																	</label>
																	<div class="col-md-6 col-sm-6">
																		<%
																		ArrayList<Vw_catalogo_tipo_cuentacontable> listaTC = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
																		Dt_cuentaContable dttc = new Dt_cuentaContable();
																		listaTC = dttc.getCuentaContableByIdEmpresa(Vw_empresa.empresaActual);
																		%>
																		<select class="form-control js-example-basic-single"
																		name="cuentaContable" id="cuentaContable" required="required">
																		<option value="" disabled selected>Seleccione...</option>
																		<%
																		for (Vw_catalogo_tipo_cuentacontable tc : listaTC) {
																		%>
																		<option value="<%=tc.getIdCuenta()%>"><%=tc.getNombreCuenta()%></option>
																		<%
																		}
																		%>
																		</select>
																	</div>
																</div>
															
																<div class="field item form-group">
																	<label class="col-form-label col-md-3 col-sm-3  label-align">Fecha Inicial:</label>
																	<div class="col-md-6 col-sm-6">
																		<input type="date" class="form-control" data-parsley-excluded=true name="fechaInicio" id="fechaInicio" required/>
																	</div>
																</div>
																
																<div class="field item form-group">
																	<label class="col-form-label col-md-3 col-sm-3  label-align">Fecha Final:</label>
																	<div class="col-md-6 col-sm-6">
																		<input type="date" class="form-control" data-parsley-excluded=true name="fechaFinal" id="fechaFinal" required/>
																	</div>
																</div>
																<div class="ln_solid">
																	<div class="form-group">
																		<div class="col-md-6 offset-md-3">
																			<button id="btnMovimientos" type='submit' class=" btn btn-primary">Traer movimientos</button>
																			<a href="tbl_asientoContable.jsp" type="button" class="btn btn-danger">Cancelar</a>
																		</div>
																	</div>
																</div>
																</form>
															</div>
															<div class="card-box table-responsive">
															<%
																ArrayList<Historico> listaHistoricos = new ArrayList<Historico>();
																Dt_asientoContableDet acd = new Dt_asientoContableDet();
																listaHistoricos = acd.listarasientocontableDetHistorico(idCuenta, fechaInicio, fechaFinal);
															%>
															
															<%
																for(Historico his: listaHistoricos){
															%>
																<div class="field item form-group">
																<h3 style="display:block;"><%=his.getNombreCuenta()%></h3>
																</div>
																<div class="field item form-group">
																	<table 
																	class=" table table-striped table-bordered"
																	style="width: 100%; diplay:block;">
																		<thead>
																			<tr>
																				<th>Fecha del movimiento</th>
																				<th>Descripción</th>
																				<th>Debe</th>
																				<th>Haber</th>
																			</tr>
																		</thead>
																		<tbody>
																			<tr>
																				<td><%=his.getFecha() %></td>
																				<td><%=his.getDescripcion()%></td>
																				<td><%=his.getDebe()%></td>
																				<td><%=his.getHaber()%></td>
																			</tr>
																		</tbody>
																	</table>
																</div>
															<%
																}
															%>
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
		</div>
	</div>



	<!-- footer content -->

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
		// Toasts y alertas
		$("document").ready(function(){
			var codigoMensaje = $("#idMensaje").val();
			
			switch (codigoMensaje) {
				case "3":
					$.toast({
					    text: "Asiento contable editado con éxito",
					    heading: 'Éxito', 
					    icon: 'success',
					    showHideTransition: 'slide',
					    allowToastClose: false, 
					    hideAfter: 5000, 
					    stack: 5,
					    position: 'top-center', 
					    textAlign: 'left',  
					    loader: true,  
					    loaderBg: '#9EC600',
					});
					break;
					
				case "4":
					$.toast({
					    text: "No se actualizaron los detalles del asiento contable", // Text that is to be shown in the toast
					    heading: 'Advertencia', // Optional heading to be shown on the toast
					    icon: 'warning', // Type of toast icon
					    showHideTransition: 'slide', // fade, slide or plain
					    allowToastClose: false, // Boolean value true or false
					    hideAfter: 5000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
					    stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
					    position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
					    
					    textAlign: 'left',  // Text alignment i.e. left, right or center
					    loader: true,  // Whether to show loader or not. True by default
					    loaderBg: '#9EC600',  // Background color of the toast loader
					    
					});
					break;
					
				case "5":
					$.toast({
					    text: "No se pudo actualizar el asiento contable", // Text that is to be shown in the toast
					    heading: 'Error', // Optional heading to be shown on the toast
					    icon: 'error', // Type of toast icon
					    showHideTransition: 'slide', // fade, slide or plain
					    allowToastClose: false, // Boolean value true or false
					    hideAfter: 5000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
					    stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
					    position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
					    
					    textAlign: 'left',  // Text alignment i.e. left, right or center
					    loader: true,  // Whether to show loader or not. True by default
					    loaderBg: '#9EC600',  // Background color of the toast loader
					});
					break;
					
				case "7":
					$.toast({
					    text: "Asiento contable borrado exitosamente", // Text that is to be shown in the toast
					    heading: 'Éxito', // Optional heading to be shown on the toast
					    icon: 'success', // Type of toast icon
					    showHideTransition: 'slide', // fade, slide or plain
					    allowToastClose: false, // Boolean value true or false
					    hideAfter: 5000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
					    stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
					    position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
					    
					    textAlign: 'left',  // Text alignment i.e. left, right or center
					    loader: true,  // Whether to show loader or not. True by default
					    loaderBg: '#9EC600',  // Background color of the toast loader
					});
					break;
					
				case "8":
					$.toast({
					    text: "Ocurrió un error al borrar el asiento contable", // Text that is to be shown in the toast
					    heading: 'Error', // Optional heading to be shown on the toast
					    icon: 'error', // Type of toast icon
					    showHideTransition: 'slide', // fade, slide or plain
					    allowToastClose: false, // Boolean value true or false
					    hideAfter: 5000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
					    stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
					    position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
					    
					    textAlign: 'left',  // Text alignment i.e. left, right or center
					    loader: true,  // Whether to show loader or not. True by default
					    loaderBg: '#9EC600',  // Background color of the toast loader
					});
					break;
					
				default:
					break;
			}
		});
	</script>
	
</body>
</html>