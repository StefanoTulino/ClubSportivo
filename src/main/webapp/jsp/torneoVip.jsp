<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Torneo Vip</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/torneoVip.css">
	
		
	</head>		
		<body>

	<div class="contaneir">
		<br>
		<c:if test="${empty listaTornei}">
			<h3 class="titleTable">Non ci sono tornei prenotabili</h3>
		</c:if>
		
		<div class="corpo">
			<c:if test="${not empty  listaTornei}">
				<h3 class="titleTable">Lista Tornei Prenotabili</h3>
				<table class="table table-dark table-striped"
					style="margin-top:5%;width:80%;margin-left:10%;margin-right:10%">
				<tr>
					<th class="head">Id torneo</th>
					<th class="head">Nome Torneo </th>
					<!-- <th class="head">Immagine</th> -->
					<th class="head">Data inizio</th>
					<th class="head">Data fine</th>
					<th class="head">Campo Nr. </th>
					<th class="head">Numero Tot. di Squadre</th>
					<th class="head">Posti Disponibili</th> 
					<th class="head">Seleziona torneo</th>	
				</tr>


				<c:forEach var="torneo" varStatus="i" items="${listaTornei}">
					<tr>
						<th><c:out value="${torneo.id}" /></th>
						<th><c:out value="${torneo.nome}" /></th>
						 <%-- <th><img id="imgCampo"
							src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?id=${campo.tipologia}"
							width="180" height="150" /></th> --%>
						<th><c:out value="${torneo.dataInizio}" /></th>
						<th><c:out value="${torneo.dataFine}" /></th>
						<th><c:out value="${torneo.idCampo}" /> </th>
						
						<th><c:out value="${torneo.numSquadre}" /></th>
						
						 <th><c:out value="${torneo.postiDisponibili}"/> </th> 
						

							<form action="sendTorneoVip" method="post">
								<input type="hidden" name="idTorneo" value="${torneo.id}">
								<input type="hidden" name="idCampo" value="${torneo.idCampo}">
								<th>
									<button id="prenota" name="prenota">Prenota</button>
								</th>
							</form>

					</tr>
				</c:forEach>
			</table>
			</c:if>
			
		</div>

	</div>
</body>
</html>