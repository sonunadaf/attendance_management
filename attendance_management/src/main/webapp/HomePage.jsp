<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/home.css">
<title>Home</title>
</head>
<body onload="loadResource()">
	<script type="text/javascript">
		function verifyInputs() {
			//	alert("Invoked");
			var name = document.getElementById('name').value;
			var password = document.getElementById('password').value;
			var empcode = document.getElementById('empcode').value;
			var file = document.getElementById('file').value;
			var dateOfBirth = document.getElementById('dateOfBirth').value;
			if (name == null || name == "" || name == " ") {
				alert("Enter name.");
				return false;
			}
			if (empcode == "") {
				alert("Enter Employee code.");
				return false;
			}
			if (password == "") {
				alert("Enter password.");
				return false;
			}
			if (dateOfBirth == "") {
				alert("Enter Date of birth.");
				return false;
			}
			if (file == null) {
				alert("Select finger print file.");
				return false;
			}
		}
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
	<div class="container-fluid" style="background-color: cadetblue;">
		<div class="row home">
			<div class="col-md-4">
				<h4 style="text-align: left; color: honeydew;">SyncByte</h4>
			</div>
		</div>
	</div>
	<div class="container" style="height: 600px">
		<form action="register.do" method="post" enctype='multipart/form-data'
			onsubmit="return verifyInputs()">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<br>
					<h4>User Registration</h4>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<br> <label for="name">Name</label> <input type="text"
						name="name" class="form-control" id="name"
						value="${requestObj.name}" placeholder="Enter Full Name"
						required="required" />
				</div>

			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<label for="empCode">Employee Code</label> <input type="text"
						name="empCode" class="form-control" id="empCode"
						value="${requestObj.empCode}" placeholder="Enter Employee code"
						required="required" />
				</div>

			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<label for="password">Password</label> <input type="password"
						name="password" class="form-control" id="password"
						value="${requestObj.password}" placeholder="Enter Password"
						required="required" />
				</div>

			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<label for="dateOfBirth">D.O.B</label> <input type="date"
						value="${requestObj.dateOfBirth}" name="dateOfBirth"
						class="form-control" id="dateOfBirth" required="required" />
				</div>

			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<label for="fingerPrint">Finger Print</label> <input type="file"
						name="file" class="form-control" id="file" required="required"
						accept="image/*" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-2">
					<br> <input type="submit" class="form-control">
				</div>
				<div class="col-md-2">
					<br> <a href="login.do" class="form-control"
						style="text-align: center;">LogIn</a>
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