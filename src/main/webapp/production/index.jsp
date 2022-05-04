<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Sistema Contable |</title>

<!-- Bootstrap -->
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="../vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			
			<jsp:include page="navegacion.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="x_panel">

							<div class="x_title">
								<a><i class="fa fa-building" style="font-size: 30px">Empresa</i></a>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">
								<div class="field item form-group colspan-3">
									<a href="tbl_empresa.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-table" style="padding-right: 10px;"></i>Empresa</a>
									<a style="padding-left: 20px; padding-right: 20px;"> </a> <a
										href="tbl_departamento.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-location-arrow"
										style="padding-right: 10px;"></i>Departamento</a> <a
										style="padding-left: 20px; padding-right: 20px;"> </a> <a
										href="tbl_municipio.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-location-arrow"
										style="padding-right: 10px;"></i>Municipio</a> <a
										style="padding-left: 20px; padding-right: 20px;"> </a> <a
										href="tbl_representanteLegal.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-user" style="padding-right: 10px;"></i>Representante
										Legal</a>
								</div>
							</div>

						</div>

						<div class="x_panel">

							<div class="x_title">
								<a><i class="fa fa-bitcoin" style="font-size: 30px">Moneda</i></a>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">
								<div class="field item form-group colspan-3">
									<a href="tbl_moneda.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-dollar" style="padding-right: 10px;"></i>Moneda</a>
									<a style="padding-left: 20px; padding-right: 20px;"> </a> <a
										href="tbl_tasaCambio.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-percent" style="padding-right: 10px;"></i>Tasa
										Cambio</a>
								</div>
							</div>

						</div>

						<div class="x_panel">

							<div class="x_title">
								<a><i class="fa fa-balance-scale" style="font-size: 30px">Asiento
										Contable</i></a>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">
								<div class="field item form-group colspan-3">
									<a href="tbl_asientoContable.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-dollar" style="padding-right: 10px;"></i>Asiento
										Contable</a> <a style="padding-left: 20px; padding-right: 20px;">
									</a> <a href="tbl_periodoContable.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-calendar" style="padding-right: 10px;"></i>Periodo
										Contable</a> <a style="padding-left: 20px; padding-right: 20px;">
									</a> <a href="tbl_periodoFiscal.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-legal" style="padding-right: 10px;"></i>Periodo
										Fiscal</a> <a style="padding-left: 20px; padding-right: 20px;">
									</a> <a href="tbl_tipoDocumento.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-file-text" style="padding-right: 10px;"></i>Tipo
										Documento</a>
								</div>
							</div>

						</div>

						<div class="x_panel">

							<div class="x_title">
								<a><i class="fa fa-suitcase" style="font-size: 30px">Cuenta
										Contable</i></a>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">
								<div class="field item form-institution colspan-3">
									<a href="tbl_catalogocuenta.jsp?idE=<%=request.getParameter("idE") %>"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-book" style="padding-right: 10px;"></i>Catalogo
										Cuenta</a> <a style="padding-left: 20px; padding-right: 20px;">
									</a> <a href="tbl_tipocuenta.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-tasks" style="padding-right: 10px;"></i>Tipo
										Cuenta</a> <a style="padding-left: 20px; padding-right: 20px;">
									</a> <a href="tbl_cuentacontable.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-archive" style="padding-right: 10px;"></i>Cuenta
										Contable</a>
								</div>
							</div>

						</div>

						<div class="x_panel">

							<div class="x_title">
								<a><i class="fa fa-shield" style="font-size: 30px">Seguridad</i></a>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">
								<div class="field item form-institution colspan-3">
									<a href="tbl_usuario.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-user" style="padding-right: 10px;"></i>Usuario</a>
									<a style="padding-left: 20px; padding-right: 20px;"> </a> <a
										href="tbl_rol.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-unlock" style="padding-right: 10px;"></i>Rol</a>
									<a style="padding-left: 20px; padding-right: 20px;"> </a> <a
										href="tbl_usuarioRol.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-puzzle-piece" style="padding-right: 10px;"></i>Usuario
										Rol</a> <a style="padding-left: 20px; padding-right: 20px;"> </a>
									<a href="tbl_rolOpciones.jsp"
										style="font-size: 20px; padding-left: 15px;"><i
										class="fa fa-lg fa-cogs" style="padding-right: 10px;"></i>Rol
										Opciones</a>
								</div>
							</div>

						</div>

					</div>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">
					<a><i class="green">FROGBMJ Software </i><i>por</i> <i
						class="red"> Eldian's Development</i></a>
				</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>

	<!-- jQuery -->
	<script src="../vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="../vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="../vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- jQuery Sparklines -->
	<script src="../vendors/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- Flot -->
	<script src="../vendors/Flot/jquery.flot.js"></script>
	<script src="../vendors/Flot/jquery.flot.pie.js"></script>
	<script src="../vendors/Flot/jquery.flot.time.js"></script>
	<script src="../vendors/Flot/jquery.flot.stack.js"></script>
	<script src="../vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="../vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="../vendors/DateJS/build/date.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="../vendors/moment/min/moment.min.js"></script>
	<script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.min.js"></script>
</body>
</html>