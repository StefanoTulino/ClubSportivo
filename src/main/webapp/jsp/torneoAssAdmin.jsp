<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp" %>	

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TorneoAss</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/torneoAssAdmin.css">
	
	</head>
		<body>

	
	<div class="contaneir">
		<c:choose>
		<c:when test="${!result}">
			<form class="formDati" action="datiTorneoAssAdmin" method="post">
				<h6 style="margin-left:2%;margin-top:2%">Scegli una tipologia:</h6>

				<select name="selectTipologia" id="selectTipologia" style="margin-left:2%;">
					<option selected="selected">--- </option>
						<c:forEach var="tipologia" varStatus="i" items="${listaCampiTipologia}">
							<option id="optionId" value="${tipologia.descrizione}"> 
								${tipologia.descrizione} 
							</option>
						</c:forEach>
				</select> 
				<input type="submit" style="margin-left:2%;margin-top:2%" value="Invia">
			</form>
		
		</c:when>
		
		
			<c:when test="${not empty listaTornei}">
			<h3 class="titleTable">Lista Tornei Prenotabili</h3>
		<div class="corpo">

			<table class="table table-dark table-striped"
					style="margin-top:5%;width:80%;margin-left:10%;margin-right:10%">
				<tr>
					<th class="head">Id torneo</th>
					<th class="head">Nome Torneo </th>
					<th class="head">Data inizio</th>
					<th class="head">Data fine</th>
					<th class="head">Campo Nr. </th>
					<th class="head">Numero Tot. di Squadre</th>
					 <th class="head">Posti Disponibili</th>
					<th class="head">Seleziona torneo</th>	
				</tr>


				<c:forEach var="torneo" varStatus="i" items="${listaTornei}">
					<tr>
						<th> <c:out value="${torneo.id}" /> </th>
						<th> <c:out value="${torneo.nome}" /> </th>
						<th> <c:out value="${torneo.dataInizio}" /> </th>
						<th> <c:out value="${torneo.dataFine}" /> </th>
						<th> <c:out value="${torneo.idCampo}" /> </th>
						<th> <c:out value="${torneo.numSquadre}" /> </th>
						<th><c:out value="${torneo.postiDisponibili}"/> </th> 
						
							<form action="prenotaTorneoAss" method="post">
								<input type="hidden" name="idTorneo" value="${torneo.id}">
								<input type="hidden" name="idCampo" value="${torneo.idCampo}">
								<th>
									<button id="prenota" name="prenota">Prenota</button>
								</th>
							</form>

						</tr>
					</c:forEach>
				</table>

			</div>
		</c:when>
		
		<c:when test="${empty listaTorneo}">
			<h3 style="text-align:center;color:red">${messaggeDefault}</h3>
		</c:when>
		
		</c:choose>
	</div>
	</body>
</html>