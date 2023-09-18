 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrazione</title>

<link rel="stylesheet" href="./css/registrazione.css">
		</head>
			<body>
					
			<form class="modal-content animate" action="registrazione"
				method="POST">
				<!-- TITLE -->
				<div class="titleModal" style="background-color: #4CAF50;">
					<span onclick="document.getElementById('id02').style.display='none'"
						class="close" title="Close">&times;</span>
					<h4 style="text-align: center;">Registrazione</h4>
				</div>
				<!-- Create a form container -->
				<div class="containerFormRegistrazione">
					<br> 
						<label for="uname" id="loginUsername"><b>Nome</b></label> 
						<input type="text" placeholder="Inserisci nome" name="nome" id="nome" required> 
					<br> 
						<label for="uname" id="loginUsername"><b>Cognome</b></label>
						<input type="text" placeholder="Inserisci Cognome" name="cognome"
						id="cognome" required> 
					<br> 
						<label for="uname" id="loginUsername"><b>Email</b></label> 
						<!-- NON FA CONTROLLO DI BASE HTML, CAPIRE IL MOTIVO -->
						<input type="email" placeholder="Enter Username" name="email" id="email" required>
					<br> 
						<label for="psw" id="loginUsername"><b>Password</b></label>
						<input type="password" placeholder="Enter Password" name="password"
						id="password" required>
						 <br> <br>
						 <label for="psw" id="loginUsername"><b>Seleziona la casella se sei
							un'associazione sportiva:</b>
							<input type="checkbox" name="tipologia">
							</label> 
							
					<!-- Submit Button -->
					<button type="submit" id="btnRegisterForm" style="color: white;">Iscriviti</button>
				</div>
			</form>
		
</body>
</html> 