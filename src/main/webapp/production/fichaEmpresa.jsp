<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div class="col-md-3 widget widget_tally_box col-sm">
		<div class="x_panel">
			<div class="x_content">

				<div class="flex">
					<img src="images/user.png"
						alt="<%=request.getParameter("altImg")%>"
						class="img-circle profile_img">

				</div>

				<h3 class="name"><%=request.getParameter("nombreEmp")%></h3>

				<p><%=request.getParameter("representanteEmp")%></p>
			</div>
		</div>
	</div>
</body>
</html>