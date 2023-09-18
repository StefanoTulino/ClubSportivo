<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Prenotazione User</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/prenotazioneAssAdmin.css">
		
	</head>		
		<body>

	<div class="contaneir">
		<h3 class="titleTable">Lista Campi</h3>
		
		<form class="formDati" action="datiPrenotazioneAssAdmin" method="post">
			<h6 style="margin-left:2%;">Inserisci una data di inizio:</h6>

			<input type="datetime-local" name="meeting-time-data-inizio" style="margin-left:2%;"
				min="2022-07-11 08:00:00" max="2022-31-12 12:00:00"
				required="required">
			<br>
			
			<h6 style="margin-left:2%;">Inserisci una data di fine:</h6>

			<input type="datetime-local" name="meeting-time-data-fine" style="margin-left:2%;"
				min="2022-07-11 08:00:00" max="2022-31-12 12:00:00"
				required="required">
				<br>
				
			<h6 style="margin-left:2%;margin-top:2%">Scegli una tipologia:</h6>

			<select name="selectTipologia" id="selectTipologia" style="margin-left:2%;">
				<option selected="selected">--- </option>
					<c:forEach var="tipologia" varStatus="i" items="${listaCampiTipologia}">
						<%-- <option id="optionId" value="${tipologia.id}"> ${tipologia.descrizione} </option> --%>
						<option id="optionId" value="${tipologia.descrizione}"> ${tipologia.descrizione} </option>
					</c:forEach>
			</select> 
			<br> 
			<input type="submit" style="margin-left:2%;margin-top:2%" value="Invia">
		</form>

		<br>
		
		<c:if test="${not empty listaCampi}">
			<div class="corpo">
			<table class="table table-dark table-striped"
					style="margin-top:5%;width:80%;margin-left:10%;margin-right:10%">
				<tr>
					<th class="head">Id campo</th>
					<th class="head">Tipologia Campo</th>
					<th class="head">Prezzo Intero</th>
					<th class="head">Immagine <span
						class="glyphicon glyphicon-magnet"></span>
					<th class="head">Stato</th>
					<th class="head">Prenotazione</th>
					</th>
				</tr>


				<c:forEach var="campo" varStatus="i" items="${listaCampi}">
					<tr>
						<th><c:out value="${campo.id}" /></th>
						<th><c:out value="${campo.dettaglio}" /></th>
						<th><c:out value="${campo.costo}" /></th>
				
						<th><img id="imgCampo"
							src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?id=${campo.tipologia}"
							width="180" height="150" /></th>

						<th><c:choose>
								<c:when test="${campo.disponibilità==true}">
									<img src="img/pallino_verde.jpg" width="35" height="30" />
								</c:when>

								<c:otherwise>
									<img src="img/pallino_rosso.jpg" width="35" height="30" />
								</c:otherwise>
							</c:choose></th>

						<th>

							<form action="sendPrenotazioneAltriUtenti" method="get">
								<input type="hidden" name="idCampo" value="${campo.id}">
								<input type="hidden" name="dataInizio" value="${dataInizio}">
								<input type="hidden" name="dataFine" value="${dataFine}">
								<input type="hidden" name="idTipologia" value="${idTipologia}">
	
									<button id="prenota" name="prenota">Prenota</button>
							</form>

						</th>

					</tr>
				</c:forEach>
			</table>

		</div>
		</c:if>
		

	</div>

</body>
</html>