<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="entidades.Vw_usuariorol, entidades.Vw_empresa, entidades.Vw_rolopciones,entidades.Tbl_asientoContable,
	entidades.Vw_asientoContable, entidades.Tbl_periodoContable, 
	datos.Dt_rolOpciones, datos.Dt_asientoContable, datos.Dt_periodoContable, java.util.*;"%>

<%

//Placeholder para el mensaje
String codigoMensaje = "";

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

<title>Gestión | Asiento Contable</title>

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
							<h3>Asiento Contable</h3>
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
								<div class="x_title">
									<h2>Asientos Contables Registrados <small><a href="imprimirAsientoContable.jsp"><i class="fa fa-print"></i>Imprimir asientos contables</a></small> </h2>

									<div class="float-right">
										<h2>Periodo contable: <%=Tbl_periodoContable.fechaInicioActual%> - <%=Tbl_periodoContable.fechaFinalActual %> </h2>
									</div>	
									<div class="clearfix">
										
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
																	style="text-align: right;">
																	<a href="addAsientoContable.jsp"> <i
																		class="fa fa-plus-square"></i> Nuevo Asiento Contable
																	</a> <br></br>
																</div>
																<table id="datatable-buttons"
																	class="table table-striped table-bordered"
																	style="width: 100%">
																	<%
																	ArrayList<Vw_asientoContable> listaAsientoContable = new ArrayList<Vw_asientoContable>();
																	Dt_asientoContable dtac = new Dt_asientoContable();
																	listaAsientoContable = dtac.listarasientocontableporid(Vw_empresa.empresaActual);

																	Dt_periodoContable dtpc = new Dt_periodoContable();
																	Tbl_periodoContable tblpc = new Tbl_periodoContable();
																	%>
																	<thead>
																		<tr>
																			<th>ID</th>
																			<th>Periodo Contable</th>
																			<th>Nombre Comercial</th>
																			<th>Tipo de Documento</th>
																			<th>Moneda</th>
																			<th>Tipo de Cambio</th>
																			<th>Fecha</th>
																			<th>Concepto</th>
																			<th>Acciones</th>
																		</tr>
																	</thead>
																	<tbody>
																		<%
																		for (Vw_asientoContable ac : listaAsientoContable) {

																			tblpc = dtpc.obtenerPContablePorId(ac.getIdPeriodoContable());
																			if (tblpc.getIdPeriodoContable() == Tbl_periodoContable.idPeriodoActual && tblpc.getEstado() != 3) {
																		%>
																		<tr>

																			<td><%=ac.getIdAsientoContable()%></td>
																			<td><%=ac.getFechaInicio()%> - <%=ac.getFechaFinal()%></td>
																			<td><%=ac.getNombreComercial()%></td>
																			<td><%=ac.getTipo()%></td>
																			<td><%=ac.getNombre()%></td>
																			<td><%=ac.getTipoCambio()%></td>
																			<th><%=ac.getFecha()%></th>
																			<td><%=ac.getDescripcion()%></td>


																			<td><a
																				href="editAsientoContable.jsp?ascont=<%=ac.getIdAsientoContable()%>">
																					<i class="fa fa-edit" title="Editar"></i>
																			</a> &nbsp;&nbsp; <a
																				href="viewAsientoContable.jsp?ascont=<%=ac.getIdAsientoContable()%>">
																					<i class="fa fa-eye" title="Mostrar"></i>
																			</a> &nbsp;&nbsp; <a
																				href="deleteAsientoContable.jsp?ascont=<%=ac.getIdAsientoContable()%>">
																					<i class="fa fa-trash" title="Eliminar"></i>
																			</a></td>
																		</tr>
																		<%
																			} else if (tblpc.getEstado() == 3 && tblpc.getIdPeriodoContable() == Tbl_periodoContable.idPeriodoActual) {
																				%>
																				<tr>

																					<td><%=ac.getIdAsientoContable()%></td>
																					<td><%=ac.getFechaInicio()%> - <%=ac.getFechaFinal()%></td>
																					<td><%=ac.getNombreComercial()%></td>
																					<td><%=ac.getTipo()%></td>
																					<td><%=ac.getNombre()%></td>
																					<td><%=ac.getTipoCambio()%></td>
																					<th><%=ac.getFecha()%></th>
																					<td><%=ac.getDescripcion()%></td>


																					<td><a
																						href="viewAsientoContable.jsp?ascont=<%=ac.getIdAsientoContable()%>">
																							<i class="fa fa-eye" title="Mostrar"></i>
																					</a></td>
																				</tr>
																				<%
																				}
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
		</div>
	</div>



	<!-- footer content -->

	<!-- /footer content -->

					<!-- /footer content -->

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
					    heading: 'ï¿½xito', // Optional heading to be shown on the toast
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
					    text: "Ocurriï¿½ un error al borrar el asiento contable", // Text that is to be shown in the toast
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