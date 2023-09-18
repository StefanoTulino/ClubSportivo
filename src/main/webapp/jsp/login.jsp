<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<link rel="stylesheet" href="./css/login.css">

	</head>
		<body>


			<div>			

			<form class="modal-content animate" action="loginjsp" method="POST">
				<!-- Create an image container -->
				<div class="titleModal" style="background-color: #4CAF50;">
					<span onclick="document.getElementById('id01').style.display='none'"
						class="close" title="Close">&times;</span>
					<h4 style="text-align: center">Login</h4>
				</div>
	
				<!-- Create a form container -->
				<div class="containerFormLogin">
					<br>
					<!-- Username -->
					<label for="uname" id="loginUsername"><b>Username</b></label> <input
						type="text" placeholder="Enter Username" name="email"
						id="emailLogin" required> <br>
					<!-- Password -->
					<label for="psw" id="loginPsw"><b>Password</b></label> <input
						type="password" placeholder="Enter Password" name="password"
						id="passwordLogin" required> <br>
					<!-- Submit Button -->
	
					<button type="submit" id="btnLoginForm" style="color: white;">Accedi</button>
	
				</div>
			</form>
	</div>

</body>
</html>