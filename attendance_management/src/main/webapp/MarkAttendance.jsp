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
	<script>
		function hideCheckBox() {
			var checkBox = document.getElementById("checkBoxColumn");
			var fingerPrint = document.getElementById("fingetPrintFile");
			fingerPrint.style.display = "block";
			checkBox.style.display = "none";
		}
		function hideFingerPrint() {
			var checkBox = document.getElementById("checkBoxColumn");
			var fingerPrint = document.getElementById("fingetPrintFile");
			fingerPrint.style.display = "none";
			checkBox.style.display = "block";
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
								href="attendance.do"
								style="display: ${dto.userTypeRole=='ADMIN'?'none':'block'}">My
									Attendance <span class="sr-only">(current)</span>
							</a></li>
						</ul>
					</div>
				</nav>

			</div>

		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4" style="text-align: center;">
				<h5>Select Attendance Type</h5>
			</div>
		</div>
		<!-- <div class="contaner"> -->
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4"
				style="background-color: powderblue; height: 400px; text-align: center;">
				<br>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="exampleRadios"
						id="exampleRadios1" value="option1" checked
						onclick="hideCheckBox()"> <label class="form-check-label"
						for="exampleRadios1"> Finger Print </label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-check-input"
						type="radio" name="exampleRadios" id="exampleRadios2"
						value="option2" onclick="hideFingerPrint()"> <label
						class="form-check-label" for="exampleRadios2"> Check Box</label>
				</div>
				<br>
				<div id="fingetPrintFile" style="display: ${map.hidden}">
					<h5 style="display: ${map.hidden}">${map.message}${map.hidden==""?"PENDING":""}</h5>
					<form action="${map.fileActions}" method="post"
						enctype='multipart/form-data'>
						<input type="file" class="form-control"
							style="display: ${map.hidden}" required="required" name=file
							accept="image/*" /><input type="hidden" name="id"
							value="${map.id}" /> <input type="submit" value="Upload"
							class="form-control" style="display: ${map.hidden}" />
					</form>
				</div>
				<div id="checkBoxColumn" style="display: none;">
					<div style="display: ${map.hidden}">
						<h5>${map.message}${map.hidden==""?"PENDING":""}</h5>
						<form action="${map.actions}" method="post">
							<label class="form-check-label" for="empCode">${map.message}
							</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								class="form-check-input" type="radio" name="empCode"
								id="empCode" value="${empCode}" required="required"
								style="display: ${map.hidden}" /> <input type="hidden"
								name="id" value="${map.id}" /><input type="submit"
								value="Confirm">
						</form>
					</div>

				</div>
				<div style="display: ${map.hidden=='none'?'':'none'}">
					<h5 style="color: orangered;">Todays Session Completed</h5>
				</div>
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