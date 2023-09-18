<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica Prenotazione User</title>

<link rel="stylesheet" href="css/modificaPrenotazioneUser.css">
<script type="text/javascript" src="utility.js"></script>


</head>
<body>
		

				<form class="formDati" action="sendNewPrenotazione" method="post">
				<!-- TITLE -->
				<div class="titleModal">
					<h4 style="text-align: center; background-color: #4CAF50;">Modifica prenotazione</h4>
				</div>
				<!-- Create a form container -->
				<div class="containerFormRegistrazione">
						<br> 
						<br> 
						<label for="uname" id="loginUsername" style="margin-top:2%"><b>Data di inizio</b></label>
						<input type="datetime-local" name="meeting-time-inizio" style="margin-left:2%;"
							 max="2022-31-12 12:00:00"
							required="required">
							<br>
							<label for="uname" id="loginUsername" style="margin-top:2%"><b>Data di fine</b></label>
						<input type="datetime-local" name="meeting-time-fine" style="margin-left:2%;"
							 max="2022-31-12 12:00:00"
							required="required">
							<br>
						<label for="uname" style="margin-left:4%;margin-top:2%"><b>Scegli una tipologia</b></label>
						<select name="selectTipologia" id="selectTipologia" style="margin-left:2%;"
				onchange="checkTipologia()">
				<option selected="selected">--- </option>
					<c:forEach var="tipologia" varStatus="i" items="${listaCampiTipologia}" end="9">
						<option id="optionId" value="${tipologia.descrizione}"> ${tipologia.descrizione} </option>
					</c:forEach>
			</select> 
						
					
					<!-- Submit Button -->
					
						<input type="hidden" name="idPrenotazione" value="${prenotazioneId}">
						<input type="submit" id="btnRegisterForm" style="color: white; "></button>
				</div>
			</form>
			
			
			<h3 class="titleTable" style="text-align:center;margin-top:3%;">Lista Campi</h3>
		<div class="corpo">


			<table class="table table-dark table-striped"
					style="margin-top:3%;width:80%;margin-left:10%;margin-right:10%">
				<tr>
					<th class="head">Id campo</th>
					<th class="head">Tipologia Campo</th>
					<th class="head">Prezzo</th>
					<th class="head">Prenotazione</th>
				</tr>



				<c:forEach var="campo" varStatus="i" items="${listaCampi}">
					<tr>
						<th><c:out value="${campo.id}" /></th>

						<th><c:out value="${campo.dettaglio}" /></th>

						<th><c:out value="${campo.costo}" /></th>

						
							<form action="CambioPrenotazione" method="get">
								<input type="hidden" name="idCampo" value="${campo.id}">
								<input type="hidden" name="idPrenotazione" value="${prenotazioneChangeId}">
								<input type="hidden" name="data" value="${data}">
								<input type="hidden" name="dataFine" value="${dataFine}">
								<input type="hidden" name="dataInizio" value="${dataInizio}">
								<th>
									<button id="prenota" name="prenota">Cambia</button>
							</form>

						</th>

					</tr>
				</c:forEach>
			</table>
		

</body>
</html>