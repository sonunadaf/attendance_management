<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/home.css">
<title>Login</title>
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
		</div>
	</div>
	<div class="container" style="height: 600px">
		<form action="login.do" method="post" enctype='multipart/form-data'
			onsubmit="return verifyInputs()">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<br>
					<h4>LoIn</h4>
				</div>
			</div>

			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<br> <label for="empCode">Employee Code</label> <input
						type="text" name="empCode" class="form-control" id="empCode"
						value="${empCode}" placeholder="Enter Employee code"
						required="required" />
				</div>

			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<label for="password">Password</label> <input type="password"
						name="password" class="form-control" id="password"
						value="${password}" placeholder="Enter Password"
						required="required" />
				</div>

			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-2">
					<br> <input type="submit" class="form-control" value="LogIn">
				</div>
				<div class="col-md-2">
					<br> <a class="form-control" href="register.do"
						style="text-align: center;">Register</a>
				</div>
			</div>
		</form>
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