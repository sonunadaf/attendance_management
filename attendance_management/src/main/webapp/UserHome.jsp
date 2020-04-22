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
								href="getAllEmployee.do"
								style="display: ${dto.userTypeRole=='ADMIN'?'block':'none'}">Employees
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item active"><a class="nav-link"
								href="markAttendance.do"
								style="display: ${dto.userTypeRole=='ADMIN'?'none':'block'}">Mark
									Attendance <span class="sr-only">(current)</span>
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
			<div class="col-md-1">
				<br> <img
					src="file:///home/itl-959/finger_print/4069ed4d-14b9-49b5-9666-147a1c36ddb0_Screenshot from 2020-01-23 18-16-51.png"
					alt="Profile pict" style="width: 150px; height: 150px;">
			</div>
		</div>
		<div class="row">

			<div class="col-md-4"></div>
			<div class="col-md-1">Name :</div>
			<div class="col-md-6">${dto.name}</div>

		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-1">Code :</div>
			<div class="col-md-6">${dto.empCode}</div>

		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-1">D.O.B :</div>
			<div class="col-md-6">${dto.dateOfBirth}</div>

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