<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/home.css">
<title>Welcome</title>
</head>
<body onload="loadResource()">
	<script type="text/javascript">
		function loadResource() {
			var status = '${status}';
			if (status != "") {
				alert(status);
			}
			var error = '${error}';
			if (error != "") {
				alert(error);
			}
		}
	</script>
	<script>
		if (window.history.replaceState) {
			window.history.replaceState(null, null, window.location.href);
		}
	</script>
	<div class="container-fluid" style="background-color: cadetblue;">
		<div class="row home">
			<div class="col-md-4">
				<h4 style="text-align: left; color: honeydew;">SyncByte</h4>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<a href="logOut.do"
					style="text-align: end; color: honeydew; display: grid;">logout</a>
			</div>
		</div>
	</div>
	<div class="container" style="height: 600px">
		<div class="row">

			<div class="col-md"
				style="text-align: -webkit-right; display: inline-grid;">

				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav">
							<li class="nav-item active"><a class="nav-link"
								href="home.do">Home <span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item active"><a class="nav-link"
								href="getAllEmployee.do"
								style="display: ${dto.userTypeRole=='ADMIN'?'block':'none'}">Employees
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item active"><a class="nav-link"
								href="markAttendance.do"
								style="display: ${dto.userTypeRole=='ADMIN'?'none':'block'}">Mark
									Attendance <span class="sr-only">(current)</span>
							</a></li>
						</ul>
					</div>
				</nav>

			</div>
		</div>

		<div class="row">

			<div class="col-md-9" style="color: red;"></div>
			<div class="col-md-3" style="color:orangered;display: ${map.hidden};">
				${map.message} ${map.hidden==""?"PENDING":""}</div>
			<div class="col-md-3"
				style="display: ${map.hidden=='none'?'':'none'}">
				<h5 style="color: orangered;">Todays Session Completed</h5>
			</div>
		</div>
		<br>

		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<tr>
						<th scope="col">Attendance Date</th>
						<th scope="col">Status</th>
						<th scope="col">CheckedIn Time</th>
						<th scope="col">CheckedIn Type</th>
						<th scope="col">Checkout Time</th>
						<th scope="col">Checkout Type</th>
					</tr>

					<c:forEach var="attendance" items="${list}">
						<tr>
							<td><c:out value="${attendance.attendanceDate}" /></td>
							<td><c:out value="${attendance.status}" /></td>
							<td><c:out value="${attendance.checkInTime}" /></td>
							<td><c:out value="${attendance.checkInType}" /></td>
							<td><c:out
									value="${attendance.checkoOutTime == null ?'PENDING':attendance.checkoOutTime}" /></td>
							<td><c:out
									value="${attendance.checkOutType=='' || attendance.checkOutType==null ?'PENDING':attendance.checkOutType}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="container-fluid" style="background-color: cadetblue;">
		<div class="row">
			<div class="col-md-12">
				<h4 style="text-align: center; color: honeydew;">Developed By
					Md Sonu Nadaf.</h4>
			</div>
		</div>
	</div>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jq.js"></script>
</body>
</html>