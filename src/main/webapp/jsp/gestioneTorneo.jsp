<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Torneo</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<link rel="stytlesheet" href="css/gestioneTorneo.css">
		
		<script type="text/javascript" src="js/gestioneTorneo.js"></script>	
		
				
		
</head>
	<body>

	<div class="contaneir">


		<h4 class="titleTable" style="text-align: center; margin-top:2%">Gestione Tornei</h4>
		<table class="table table-dark table-striped"
			style="margin-top: 5%; width: 80%; margin-left: 10%; margin-right: 10%">
			<tr class="addBtn">
				<th>Aggiungi un nuovo Torneo 
				<br> 
				<input type="image" class="btnAdd" src="img/add-verde.png" width="25" height="25"
					onclick="document.getElementById('id01').style.display='block' ">
				</th>

				<th>Visualizza tutti i tornei 
					<br>
						<input type="image" class="btnAdd" src="img/add-verde.png"
							width="25" height="25" onclick="viewAlltornei()">
				</th>
				<th>Modifica tornei 
				<br> 
				<input type="image" class="btnAdd" src="img/add-verde.png" width="25" 
					height="25" onclick="torneiModificabili()">
				</th>

			</tr>
		</table>




		<div id="id01" class="modal">
			<div class="titleModal">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="close" title="Close">&times;</span>
				<h4 style="text-align: center; background-color: #4CAF50;">
				Inserisci i dati per creare un torneo</h4>
			</div>

			<form class="modal-content animate" action="datiTorneo" method="post">
				<div class="containerFormRegistrazione">
					<br>
					<!-- 
					//ST new add tipologia -->
					<h6 style="margin-left: 2%; margin-top: 2%">Scegli la tipologia del torneo</h6>
					<select name="selectTipologiaTorneo" id="selectTipologiaTorneo"
						style="margin-left: 2%;">
						<option selected="selected">---</option>
						<c:forEach var="tipologia" varStatus="i" items="${listaTipologiaTorneo}">
							<%-- <option id="optionId" value="${tipologia.id}"> ${tipologia.descrizione} </option> --%>
							<option id="optionId" value="${tipologia.id}"> ${tipologia.nome}</option>
						</c:forEach>
					</select> 
						<br> 
					<h6 style="margin-left: 2%;">Inserisci una data di inizio:</h6>
					<input type="datetime-local" name="meeting-time-data-inizio"
						style="margin-left: 2%;" min="2022-07-11 08:00:00"
						max="2022-31-12 12:00:00" required="required"> 
						<br>
					<h6 style="margin-left: 2%;">Inserisci una data di fine:</h6>
					<input type="datetime-local" name="meeting-time-data-fine"
						style="margin-left: 2%;" min="2022-07-11 08:00:00"
						max="2022-31-12 12:00:00" required="required">
						 <br>
					<h6 style="margin-left: 2%; margin-top: 2%">Inserisci un numero di squadre:</h6>
					<input type="text" name="numeroSquadre" placeholder="Numero"
						style="margin-left: 2%;"> 
						<br>
					<h6 style="margin-left: 2%; margin-top: 2%">Inserisci il nome del torneo</h6>
					<input type="text" name="nomeTorneo" placeholder="NomeTorneo" style="margin-left: 2%;"> 
						<br> 
						<input type="submit" style="margin-left: 2%; margin-top: 2%" value="Invia">
				</div>
			</form>
		</div>




		
		<div id="home" style="display: block"> 
			<br>
			<div class="corpo">
				<h4 style="color: red; text-align: center">Lista tornei Attivi</h4>
				<table class="table table-dark table-striped"
					style="width: 80%; margin-left: 10%; margin-right: 10%">
					<tr>
						<th class="head">Id</th>
						<th class="head">Nome</th>
						<th class="head">Data Inizio</th>
						<th class="head">Data Fine</th>
						<th class="head">Campo Nr. </th>
						<th class="head">Numero Squadre</th>
						<th class="head">Posti Disponibili</th>
						<!-- <th class="head">Visualizza Calendario</th> -->
					</tr>


					<c:forEach items="${listaTorneiAttivi}" var="torneo">
						<tr>
							<th><c:out value="${torneo.id}" /></th>
							<th><c:out value="${torneo.nome}" /></th>
							<th><c:out value="${torneo.dataInizio}" /></th>
							<th><c:out value="${torneo.dataFine}" /></th>
							<th><c:out value="${torneo.idCampo}" /> </th>
							<th><c:out value="${torneo.numSquadre}" /></th>
							<th><c:out value="${torneo.postiDisponibili}"/></th> 
						
							<%-- <th>
								<form id="linkBtnCalendario${torneo.id}" action="viewCalendarioTorneo" method="post">
									<input type="hidden" name="idTorneo" value="${torneo.id}">
										SERVE PER LA FUNZIONE.JS 	
									<input type="hidden" name="postiDisponibili" id="postiDisponibili${torneo.id}" 
										value="${torneo.postiDisponibili}"> 
									<input type="image" src="img/spunta.png" onload="checkBtnCalendario('${torneo.id}')"
									 alt="Info" width="25" height="25">
								</form>
							</th> --%>
							
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>




		<c:if test="${not empty listaCampi}">
			<!-- //ST NEW 
				ADD STYLE QUI, PRIMA NON SE LO PRENDEVA
			-->
			<h3 style="text-align:center">Lista Campi Prenotabili</h3>
			<div class="corpo">

				<table class="table table-dark table-striped"
					style="margin-top: 5%; width: 80%; margin-left: 10%; margin-right: 10%">
					<tr>
						<th class="head">Id campo</th>
						<th class="head">Tipologia Campo</th>
						<th class="head">Prezzo</th>
						<th class="head">Immagine</th>
						<th class="head">Stato</th>
						<!-- <th class="head">NumeroSquadre</th> -->
						<th class="head">Scegli campo</th>
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

							<!-- <th>
								<input name="numeroSquadre" type="number" placeholder="Inserisci Numero" min=4 max=100>
							</th> -->

							<form action="sendTorneo" method="get">
								<input type="hidden" name="idCampo" value="${campo.id}">
								<input type="hidden" name="dataI" value="${dataInizio}">
								<input type="hidden" name="dataF" value="${dataFine}"> 
								<input type="hidden" name="numS" value="${numeroSquadre}"> 
								<input type="hidden" name="nomeT" value="${nomeTorneo}">
								<!-- //ST new 
								<input type="hidden" name="listaTipologiaTorneo" value="${listaTipologiaTorneo}">								
								-->
								<th>
									<button id="prenota" name="prenota">Prenota</button>
								</th>
							</form>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>




		<div id="div02" style="display: none" onload="viewAlltornei()">
			<br>
			<div class="corpo">
				<h4 style="color: red; text-align: center">Lista di tutti i
					tornei</h4>
				<table class="table table-dark table-striped"
					style="width: 80%; margin-left: 10%; margin-right: 10%">
					<tr>
						<th class="head">Id</th>
						<th class="head">Nome</th>
						<th class="head">Data Inizio</th>
						<th class="head">Data Fine</th>
						<th class="head">Numero Squadre</th>
					</tr>


					<c:forEach items="${listaTornei}" var="torneo">
						<tr>
							<th><c:out value="${torneo.id}" /></th>
							<th><c:out value="${torneo.nome}" /></th>
							<th><c:out value="${torneo.dataInizio}" /></th>
							<th><c:out value="${torneo.dataFine}" /></th>
							<th><c:out value="${torneo.numeroSquadre}" /></th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>




		<div id="div03" style="display: none" onload="torneiModificabili()">
			<br>
			<div class="corpo">
				<h4 style="color: red; text-align: center">Tornei</h4>
				<table class="table table-dark table-striped"
					style="width: 80%; margin-left: 10%; margin-right: 10%">
					<tr>
						<th class="head">Id</th>
						<th class="head">Nome</th>
						<th class="head">Data Inizio</th>
						<th class="head">Data Fine</th>
						<th class="head">Nr. Campo</th>
						<th class="head">Numero Squadre</th>
						<th class="head">Posti Disponibili</th>
						<th class="head">Check</th>
					</tr>


					<c:forEach items="${listaTorneiAttivi}" var="torneo">
						<tr>
							<th><c:out value="${torneo.id}" /></th>
							<th><c:out value="${torneo.nome}" /></th>

							<th><input type="text" value="${torneo.dataInizio}"
								name="editDataInizio" id="editDataInizio${torneo.id}"
								onchange="checkDataInizio('${torneo.id }')" pattern="/^\d*$/" >
							</th>
							<th><input type="text" value="${torneo.dataFine}"
								name="editDataFine" id="editDataFine${torneo.id}"
								onchange="checkDataFine('${torneo.id }')" pattern="/^\d*$/">
							</th>

							<th><c:out value="${torneo.idCampo}" /></th>
							<th><c:out value="${torneo.numSquadre}" /></th>
							<th><c:out value="${torneo.postiDisponibili}"/> </th> 

							<th>
								<form action="modificheTorneo" method="get">
									<input type="hidden" name="idTorneo" value="${torneo.id}">
									<input type="hidden" name="dataInizio1" id="dataInizio1${torneo.id}" value="${torneo.dataInizio}"  >
									<input type="hidden" name="dataFine1" id="dataFine1${torneo.id}" value="${torneo.dataFine}"> 
									<input type="image" src="img/spunta.png" class="btnImagee" 
									id="btnImage${torneo.id}" width="25" height="25" 
									alt="Info"   disabled >
								</form>
							</th>
							
						</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>

	</div>
</body>
</html>