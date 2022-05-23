<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*;"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- Select2 -->
<link href="../vendors/select2/dist/css/select2.min.css"
	rel="stylesheet" />


<link href='https://fonts.googleapis.com/css?family=Roboto:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../vendors/jqlistbox-master/docs/js/jqListbox.plugin-1.3.js"></script>

<style>
body {
	padding-bottom: 100px;
	font-family: 'Roboto', sans-serif;
	font-size: 16px;
}

.jumbotron {
	background-color: transparent;
	text-align: center;
	text-transform: uppercase;
	border-bottom: 1px solid #d1d1d1;
	box-shadow: 0 1px 0 #fff;
	border-radius: 0;
}

p>small {
	color: #aaa;
}

small.smaller {
	font-size: 14px;
	text-transform: none;
	display: block;
}

.customlist {
	background-color: #fff;
	border: 1px solid #999;
	height: 200px;
	overflow: auto;
	box-shadow: 0px 5px 5px #dfdfdf;
}

.customlist .tile {
	padding: 5px;
	margin: 5px 0;
	opacity: 0.75;
	border: 2px solid transparent;
	text-align: center;
	cursor: pointer;
}

.customlist .tile.selected {
	opacity: 1;
	border: 2px solid #E74C3C;
}

.customlist table {
	width: 100%;
}

.customlist table td {
	padding: 5px;
}

.customlist table tr {
	cursor: pointer;
}

.customlist table tr:hover {
	background-color: #eee;
}

.customlist ul {
	list-style-type: none;
	padding-left: 0;
	margin-left: -15px;
	margin-right: -15px;
}

.customlist ul li {
	padding: 5px;
	cursor: pointer;
	border-bottom: 1px solid #ddd;
}

.customlist ul li .glyphicon {
	/*padding-right: 10px;*/
	float: right;
	color: #000;
}

.customlist ul li.selected .glyphicon {
	color: #fff;
}

.customlist ul li .glyphicon-ok {
	color: #fff;
	float: none;
	padding-right: 10px;
}

.customlist ul li small {
	display: block;
}

.customlist ul li:hover {
	background-color: #eee;
}

.customlist ul li.selected {
	background-color: #E74C3C;
	font-weight: bold;
	color: #fff;
}
</style>

<script>
	Date.prototype.timeNow = function() {
		return ((this.getHours() < 10) ? "0" : "") + this.getHours() + ":"
				+ ((this.getMinutes() < 10) ? "0" : "") + this.getMinutes()
				+ ":" + ((this.getSeconds() < 10) ? "0" : "")
				+ this.getSeconds();
	}
</script>

</head>
<body>
	<form class="form-inline" align="center">
		<div style="margin-bottom: 40px;">
			<div class="form-group">
				<label class="col-form-label col-md-3 col-sm-3  label-align">
					Cuenta: </label>
				<div class="col-md-6 col-sm-6">
					<%
					ArrayList<Vw_catalogo_tipo_cuentacontable> listacc = new ArrayList<Vw_catalogo_tipo_cuentacontable>();
					Dt_cuentaContable dtcc = new Dt_cuentaContable();
					listacc = dtcc.listaCuentasContables();
					%>
					<select class="js-example-basic-single" name="cbxIDTD" id="cbxdtcc"
						required="required">
						<option value="0" disabled selected>Seleccione...</option>
						<%
						for (Vw_catalogo_tipo_cuentacontable td : listacc) {
						%>
						<option value="<%=td.getIdCuenta()%>"><%=td.getNumeroCuenta() + "/" + td.getsC() + "/" + td.getSsC() + "/" + td.getSssC()%></option>
						<%
						}
						%>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-form-label col-md-3 col-sm-3  label-align">Debe</label>
				<div class="col-md-6 col-sm-6">
					<input class="form-control" class='optional' name="debe" id="debe"
						style="background: blue; color: white"
						data-validate-length-range="5,15" type="number" min='0' step="any"
						required="required" value="0" />
				</div>
			</div>

			<div class="form-group">

				<label class="col-form-label col-md-3 col-sm-3  label-align">Haber</label>
				<div class="col-md-6 col-sm-6">
					<input class="form-control" class='optional' name="haber"
						style="background: red; color: white" id="haber"
						data-validate-length-range="5,15" type="number" min="0" step="any"
						required="required" value="0" />
				</div>
			</div>
		</div>

		<div class="container">
			<div class="col">

				<div class="col">
					<a class="btn btn-success" id="example2-insert">Agregar</a> <a
						class="btn btn-danger" id="example2-remove">Borrar</a> <a
						class="btn btn-warning" id="example2-clear">Vaciar</a> <br> <br>
				</div>

				<div class="col customlist">

					<table id="example2">

					</table>

				</div>

			</div>
			<div class="row">
				<div class="col-md-12 text-center">
					<br>

					<div class="alert alert-success espacio-saldo" role="alert">
						Saldo: <span class="saldoTotal"></span>
					</div>

					<p>
						<span class="label label-success"><strong
							id="example2-count">0</strong> items</span> <span
							class="label label-primary"><strong id="example2-selected">0</strong>
							items selected</span>
					</p>
				</div>
			</div>
			
			<textarea type="text" style="font-family: Courier;" name="asientoJSON" id="asientoJSON"
                                  class="form-control"></textarea>
		</div>

	</form>


	<script>
		//Inicio select2
		$(document).ready(function() {
			$('.js-example-basic-single').select2();
		});
		//Cierre Select2
		var debeTotal = 0;
		var haberTotal = 0;
		var saldo = 0;
		var estadoSaldo = 0;

		$('#example2')
				.jqListbox(
						{
							targetInput : '#asientoJSON',
							itemSelector : 'tr',
							selectedClass : false,
							initialValues : [
								{
								}
							],
							itemRenderer : function(item, pos, listbox) {
								var row = $('<tr><td><span class="glyphicon glyphicon-unchecked"></span></td><td>Cuenta: '
										+ item.Cuenta
										+ '</td><td>Debe: '
										+ item.Debe
										+ '</td><td>Haber: '
										+ item.Haber + '</td></tr>');
								if (listbox.isSelected(pos)) {
									row
											.find('td:first')
											.html(
													'<span class="glyphicon glyphicon-check"></span>');
									row.addClass('selected');
								}
								return row;
							},
							onBeforeRender : function(listbox) {
								if (listbox.countSelected() == 0) {
									$(
											'#example2-update,#example2-remove,#example2-moveup,#example2-movedown')
											.addClass('disabled');
								} else {
									$(
											'#example2-update,#example2-remove,#example2-moveup,#example2-movedown')
											.removeClass('disabled');
								}
							},
							onAfterRender : function(listbox) {
								var count = listbox.count();
								var selCount = listbox.countSelected();

								$('#example2-count').text(count);
								$('#example2-selected').text(selCount);

								if (count > 0 && count === selCount) {
									$('#example2-selected').text('All');
								}
							}
						});
		$('#example2-insert').click(
				function(e) {
					if ($("#cbxdtcc option:selected").val() != 0) {
						var cuenta = $("#cbxdtcc option:selected").text();
						//!!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : 0"
						if(!(isNaN($("#debe").val()) || $("#debe").val() == '')){
							if(!(isNaN($("#haber").val()) || $("#haber").val() == '')){
								var debe = parseFloat($("#debe").val());
								var haber = parseFloat($("#haber").val());
								var $example2 = $('#example2');
								$example2.jqListbox('insert', {
									'Cuenta' : cuenta,
									'Debe' : debe,
									'Haber' : haber
								});

								$example2.parent('div').scrollTop($example2.height());

								debeTotal += debe;
								haberTotal += haber;
								saldo = debeTotal - haberTotal;

								$('.saldoTotal').text(saldo);
							} else
								alert("Proporcione un haber");
						} else 
							alert("Proporcione un debe");
						

						if (saldo < 0) {
							switch (estadoSaldo) {
							case 0:
								$('.espacio-saldo')
										.removeClass('alert-success').addClass(
												'alert-danger');
								break;
							case 1:
								$('.espacio-saldo').removeClass('alert-info')
										.addClass('alert-danger');
								break;
							}
							estadoSaldo = -1;

						} else if (saldo > 0) {
							switch (estadoSaldo) {
							case 0:
								$('.espacio-saldo')
										.removeClass('alert-success').addClass(
												'alert-info');
								break;
							case -1:
								$('.espacio-saldo').removeClass('alert-danger')
										.addClass('alert-info');
								break;
							}
							estadoSaldo = 1;
						} else {
							switch (estadoSaldo) {
							case -1:
								$('.espacio-saldo').toggleClass(
										'alert-danger alert-success');
								break;
							case 1:
								$('.sespacio-saldo').toggleClass(
										'alert-primary alert-success');
								break;
							}
							estadoSaldo = 0;
						}

					} else {
						alert("Seleccione una cuenta");
					}

				});

		$('#example2-remove').click(
				function(e) {
					if (confirm("Are you sure?")) {

						var $example2 = $('#example2');
						var items = $example2.jqListbox('getSelectedItems');

						for (var i = 0; i < items.length; i++) {
							saldo -= parseFloat(items[i].Debe);
							debeTotal -= parseFloat(items[i].Debe);
							saldo += parseFloat(items[i].Haber);
							haberTotal -= parseFloat(items[i].Haber);
						}

						$('.saldoTotal').text(saldo);

						$('#example2').jqListbox('remove');

						if (saldo < 0) {
							switch (estadoSaldo) {
							case 0:
								$('.espacio-saldo')
										.removeClass('alert-success').addClass(
												'alert-danger');
								break;
							case 1:
								$('.espacio-saldo').removeClass('alert-info')
										.addClass('alert-danger');
								break;
							}
							estadoSaldo = -1;

						} else if (saldo > 0) {
							switch (estadoSaldo) {
							case 0:
								$('.espacio-saldo')
										.removeClass('alert-success').addClass(
												'alert-info');
								break;
							case -1:
								$('.espacio-saldo').removeClass('alert-danger')
										.addClass('alert-info');
								break;
							}
							estadoSaldo = 1;
						} else {
							switch (estadoSaldo) {
							case -1:
								$('.espacio-saldo').toggleClass(
										'alert-danger alert-success');
								break;
							case 1:
								$('.sespacio-saldo').toggleClass(
										'alert-primary alert-success');
								break;
							}
							estadoSaldo = 0;
						}

					} else {
						alert("Seleccione una cuenta");
					}

				});
		$('#example2-clear').click(function(e) {
			if (confirm("¿Desea vaciar la lista?")) {
				$('#example2').jqListbox('clear');
				
				saldo = 0;
				debeTotal = 0;
				haberTotal = 0;
				
				$('.saldoTotal').text(saldo);
				
			}
		});
	</script>
	<script>
		$(document).ready(function() {
			$.jAlert({
				'title' : 'It works!',
				'content' : 'YAY!',
				'theme' : 'green',
				'btns' : {
					'text' : 'close'
				}
			});
			alert("working");
		});
	</script>

	<script src="../vendors/select2/dist/js/select2.min.js"></script>
</body>
</html>