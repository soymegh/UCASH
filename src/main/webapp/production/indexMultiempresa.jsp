<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="datos.*, entidades.*, java.util.*"%>

<%
ArrayList<Vw_empresa> listadoEmpresas = new ArrayList<>();
Dt_empresa datosEmpresas = new Dt_empresa();
listadoEmpresas = datosEmpresas.listarEmpresa();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
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
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="x_panel">

							<div class="x_title">
								<p style="font-size: 30px">¡Buenos días, Lic. José Ortega!</p>
								<p>¿Qué empresa vamos a trabajar hoy?</p>
								<div class="clearfix"></div>
							</div>

							<div class="x_content row">
								<%
								for (Vw_empresa empresa : listadoEmpresas) {
									request.setAttribute("idE", empresa.getIdEmpresa());
								%>
								<div class="field item form-group col">
									<a href="index.jsp?idE=<%=empresa.getIdEmpresa()%>"> <jsp:include
											page="fichaEmpresa.jsp">
											<jsp:param name="nombreEmp"
												value="<%=empresa.getNombreComercial()%>" />
											<jsp:param name="altImg"
												value="<%=empresa.getNombreComercial()%>" />
											<jsp:param name="representanteEmp"
												value="<%=empresa.getRepresentante()%>" />
										</jsp:include>
									</a>
								</div>
								<%
								}
								%>
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