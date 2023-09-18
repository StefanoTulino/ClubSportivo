<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Match</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/gestioneMatch.css">
		
		<script type="text/javascript" src="js/gestioneMatch.js"></script>
		
</head>
<body>

		<div class="contaneir">
	
		<h4 class="titleTable">Gestione Match</h4>
		<table class="table table-dark table-striped" 
			style="margin-top:5%;width:80%;margin-left:10%;margin-right:10%">
			<tr class="addBtn">
				
				<th>Aggiungi un nuovo match
				<br>	
				<input type="image" class="btnAdd" src="img/add-rosso.jpg" width="25" height="25"
					onclick="document.getElementById('id01').style.display='block' ">
				</th>
					
				<th>Visualizza tutti i match di un torneo
				<br>	
				<input type="image" class="btnAdd" src="img/add-rosso.jpg" width="25" height="25"
					onclick="document.getElementById('id02').style.display='block' ">
				</th>
	
				 <th>Visualizza i match ancora da disputare
				 <br>
				<input type="image" class="btnAdd" src="img/add-rosso.jpg" width="25" height="25"
					onclick="viewMatchFuturi()">
				</th>
			</tr>
			</table>
			
			
			
			
			
			<div id="id01" class="modal">
			<div class="titleModal">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="close" title="Close">&times;</span>
				<h4 style="text-align: center; background-color: #4CAF50;">
				Inserisci i dati per aggiungere un nuovo match</h4>
			</div>

			<form class="modal-content animate" action="insertMatch" method="get">
				<div class="containerFormRegistrazione">
					<br>
					<h6 style="margin-left: 2%;">Inserisci una data:</h6>
					<input type="datetime-local" name="meeting-time-data"
						style="margin-left: 2%;" min="2022-07-11 08:00:00"
						max="2022-31-12 12:00:00" required="required"> 
						<br>
					<h6 style="margin-left: 2%;">Inserisci un risultato</h6>
					<input type="text" name="risultato"
						style="margin-left: 2%;" placeholder="Formato del tipo: Numero1-Numero2"
						value="-">
						 <br>
					<h6 style="margin-left: 2%; margin-top: 2%">Seleziona il torneo di riferimento:</h6>
					<select name="selectTorneo" id="selectTorneo">
						<c:forEach items="${listaTornei}" var="torneo">
							<option value="${torneo.id}">${torneo.nome}</option>
						</c:forEach>
					</select>
						<br>
					<h6 style="margin-left: 2%; margin-top: 2%">Seleziona lo sfidante1:</h6>
					<select name="selectSfidante1">
						<c:forEach items="${listaSfidante}" var="sfidante">
							<option value="${sfidante}">${sfidante.toString()}</option>
						</c:forEach>
					</select>
					
					<br>
					<h6 style="margin-left: 2%; margin-top: 2%">Seleziona lo sfidante2:</h6>
					<select name="selectSfidante2">
						<c:forEach items="${listaSfidante}" var="sfidante">
							<option value="${sfidante}">${sfidante.toString()}</option>
						</c:forEach>
					</select>
					
					
						<br> 
						<input type="submit" style="margin-left: 2%; margin-top: 2%" value="Invia">
				</div>
			</form>
		</div>
			
			
			
			
			
			
			<!-- PROBLEMA QUAAA E NELLA FUNCTION IN .JS -->
			<div id="id02" class="modal" onload="viewMatch()">
			<form class="modal-content animate" action="viewMatchOfTorneo" 
			method="post" >
				<!-- TITLE -->
				<div class="titleModal">
					<span onclick="document.getElementById('id02').style.display='none'"
						class="close" title="Close">&times;</span>
					<h4 style="text-align: center; background-color: #4CAF50;">Visualizza i match di un torneo</h4>
				</div>
		
				<!-- Create a form container -->
				<div class="containerFormRegistrazione">
					<br> 
					<label>Elenco dei tornei:</label>
					<select name="selectTorneo">
						<c:forEach var="torneo" items="${listaTornei}">
							<option>${torneo.id}: ${torneo.nome}</option>
						</c:forEach>			
					</select>
					<br>
					<!-- Submit Button -->
					<button type="submit" id="btnMatchTorneoForm" style="color: white;"
					onclick="viewMatchLista()" >Visualizza</button>
					<!-- onclick="document.getElementById('id02-lista').style.display='block'"  -->
				</div>
			</form>
			</div>
	
			<div id="id02-lista" onload="viewMatchLista()" >
			<c:if test="${not empty listaMatch }">
			<br>
				<h4 style="color: red; text-align: center">Match di un preciso torneo</h4>
				<table class="table table-dark table-striped"
					style="margin-top: 5%; width: 80%; margin-left: 10%; margin-right: 10%">
					<tr>
						<th class="head">Id Match</th>
						<th class="head">Data</th>
						<th class="head">Risultato</th>
						<th class="head">Id Torneo</th>
						<th class="head">Sifdante1</th>
						<th class="head">Sifdante2</th>
					</tr>

					<c:forEach items="${listaMatch}" var="match">
						<tr>
							<th><c:out value="${match.id}" /></th>
							<th><c:out value="${match.data}" /></th>
							<th><c:out value="${match.risultato}" /></th>
							<th><c:out value="${match.idTorneo}" /></th>
							<th><c:out value="${match.sfidante1}" /></th>
							<th><c:out value="${match.sfidante2}" /></th>
						</tr>
					</c:forEach>
				</table>
				</c:if>
			</div>
		
	
	

			<div id="id03" style="display:none" onload="viewMatchFuturi()">
				<h4 style="color: red; text-align: center">Match ancora da disputare</h4>
				<table class="table table-dark table-striped"
					style="margin-top: 3%; width: 80%; margin-left: 10%; margin-right: 10%">
					<tr>
						<th class="head">Id Match</th>
						<th class="head">Data</th>
						<th class="head">Risultato</th>
						<th class="head">Id Torneo</th>
						<th class="head">Sifdante1</th>
						<th class="head">Sifdante2</th>
					</tr>


					<c:forEach items="${listaMatchFuturi}" var="match">
						<tr>
							<th><c:out value="${match.id}" /></th>
							<th><c:out value="${match.data}" /></th>
							<th><c:out value="${match.risultato}" /></th>
							<th><c:out value="${match.idTorneo}" /></th>
							<th><c:out value="${match.sfidante1}" /></th>
							<th><c:out value="${match.sfidante2}" /></th>
						</tr>
					</c:forEach>
				</table>
			
		</div>
		
		
	</div>
</body>
</html>