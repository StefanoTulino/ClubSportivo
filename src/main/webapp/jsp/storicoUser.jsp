<!-- VEDERE COME PASSARE DIRETTAMENTE L'ID DEL TORNEO, SENZA DOVER USARE METODO NEL SERVICE E NEL DAO!!!!  -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Storico User</title>
		
		<!-- NUOVO LINK BOOTSTRAP -->
		<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	
		<link rel="stylesheet" href="css/storicoUser.css">
		
		<script type="text/javascript" src="js/storicoUser.js"></script>
		
		<!-- <style>
			
		
		</style> -->
		
	</head>
		<body>
			<h4 id="titlePage1"style="text-align:left; margin-top:2%;margin-left:2%; color:red">Elenco dei Tornei e delle Partite </h4>
			<div class="contaneir-flex">
				
			<form class="formStorico" action="viewStorico"  method="post" > 
				<c:forEach var="torneo" items="${listaTornei}" varStatus="indice">
					 <input type="hidden" name="valoreTorneo" id="valoreTorneo" >  
					<input type="submit" class="btnSquadre" name="selectTorneo" onclick="valueTorneo('${torneo.id}')" 
						 value="${torneo.nome}" >
					<c:if test="${indice.count%4==0}">
						<br>
					</c:if>
				</c:forEach>			
			</form>	
			
			
			
			
				
				
				
		<c:if test="${not empty listaUtenti}">
			<div id="id01-lista" style="border: 10px solid green; border-radius:10px; margin-top:3%" >
					
				<div class="card" id="card1">
			  		<div class="card-body" style="border: 5px double">
			    	 <h5 class="card-title" style="color:red;">Squadre partecipanti al torneo</h5>
					<!-- style="margin-top: 5%; width: 80%; margin-left: 10%; margin-right: 10%" -->
						<form action="viewMatchForUser" method="post" >
							<c:forEach items="${listaUtenti}" var="user" varStatus="indice">
								<!-- <input type="hidden" name="valoreTorneo" value="6"> -->
								<!-- NON SERVE INSERIRE IL VALUE,TANTO VIENE SETTATO AL CLICK -->
								<input type="hidden" name="nomeSquadra" id="nomeSquadra">
								
								<!-- ADD LOGO SULLE SQUADRE: cambiato, rispetto a gestioneCampo, il parametro da passare per eseguire la 
									nuova query nel DAO e nel Service -->
									
									<c:if test="${immagine!=null}">
										<img id="imgCampo"
										src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?email=${user.email}"
											width="140" height="120" />	
									</c:if>
									
									<c:if test="${immagine==null}">
										<img id="imgCampo"
										src="img/immagineUserDefault.png" width="120" height="100" />	
									</c:if>
									
									
								
										
								<input type="submit" class="btnSquadre" id="tastoSquadra${indice.count}" onclick="valueSquadra('${indice.count}')" value="<c:out value="${user.email}"/>">
								<%-- NON SI PUO FARE PERCHE LA LISTA PASSATA QUI CONTIENE EMAIL AL POSTO DEL NOME
								<input type="button" id="btnSquadre" style="border-radius:25px;border: 3px solid red;" value="${user.nome}" disabled> --%>
								<br>
							</c:forEach>
						</form>
						
					</div>
					</div>
					<br>
					<div class="card" id="card2">
			  		<div class="card-body" style="border: 5px double">
			    		<h5 class="card-title" style="color:red;">Match del torneo</h5>
					<!-- style="margin-top: 5%; width: 80%; margin-left: 10%; margin-right: 10%" -->
						<c:forEach items="${listaMatch}" var="match">
							<h5 style="color:#4CAF50">Match:</h5>
							<c:out value="${match.toString()}" />
							<br>
					</c:forEach>
					</div>
					</div>
				</c:if>
			
			
			
			<c:if test="${listaUtenti.size()==0}">
			<div id="id01-lista" style="border: 3px solid green; border-radius:10px; margin-top:3%">
					
				<div class="card" id="card1">
			  		<div class="card-body" style="border: 5px double">
			    	 <h5 class="card-title" style="color:red;">Non ci sono Squadre partecipanti al torneo</h5>	
						</div>
					</div>
					<br>
					</div>
				</c:if>
			
			
			   
			  <c:if test="${empty tipologiaEliminazione && not empty classificaTorneo }">	
				<div class="card" id="card1">
			  		<div class="card-body" style="border: 5px double">
			    	 <h5 class="card-title" style="color:red;">Classifica Campionato</h5>
						<table class="table table-dark table-striped" 
							style="margin-top:3%;width:80%;margin-left:10%;margin-right:10%">
							<tr>
								<th class="head">Nome</th>
								<th class="head">Partite Giocate</th>
								<th class="head">Vittoria</th>
								<th class="head">Pareggio</th>
								<th class="head">Sconfitta</th>
								<th class="head">Punti Totali</th>
							</tr>
							
							<!-- FATTO FOREACH SU UNA VARIABILE LISTA DEL TORNEO:ESSENDO CHE IL RISULTATO
							DI QUESTA CHIAMATA CI Dà GIà IL RIFERIMENTO ALLA CLASSE SQUADRA,CON LA VAR TORNEO
							RICHIAMO DIRETTAMENTE IL TOSTRING() -->
							<c:forEach items="${classificaTorneo.squadra}" var="torneo" varStatus="indice">
								<tr>
									 <th> <c:out value="${torneo.nome }" /> </th>
									 <th> <c:out value="${torneo.partiteGiocate }" /> </th>
									 <th> <c:out value="${torneo.vittoria }" /> </th>
									 <th> <c:out value="${torneo.pareggio }" /> </th>
									 <th> <c:out value="${torneo.sconfitta }" /> </th>
									 <th> <c:out value="${torneo.puntiTotali }" /> </th>
								</tr>		
							</c:forEach>
							</table>
						</div>
					</div>
				</c:if>



		<c:if test="${not empty tipologiaEliminazione && torneoEliminazione.size()>2}">
			<h5 class="card-title" style="color: red; text-align: center">Classifica torneo Eliminazione Diretta</h5>

			<div class="contaneir-flex1">

				<!-- //SCATOLA GRANDE, costruzione di N TABELLE da allineare poi -->
				<c:forEach items="${torneoEliminazione}" var="lista"
					varStatus="indice">
					<table
						style="margin-top: 3%; width: 80%; margin-left: 2%; margin-right: 2%;
							background-color: white;border:none;margin-bottom:2%;">
						<tr class="titleTable">
							<th class="head">Turno ${indice.count}</th>
						</tr>

						<c:forEach items="${lista.listaSquadra}" var="nomeSquadra">
							<c:forEach items="${nomeSquadra}" var="s" varStatus="status">
								
								
								<tr>
									<td class="nameTeam">
									
									<c:choose>
										<%-- TUTTI COLORATI DOPO IL TURNO 1
										<c:when test="${s.value>fn:length(nomeSquadra)}"> --%>
										
										<c:when test="${s.value>indice.count}">
											<h5 class="squadraStyle" style="background-color:#4CAF50;">
												<%-- <img id="imgCampo"
									src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?email=${s.key}"
										width="60" height="40" /> --%>
												<c:out value="${s.key}" />
											</h5>
										</c:when>
										
										<c:when test="${indice.count==torneoEliminazione.size()}">
											<h5 class="squadraStyle" style="background-color:#4CAF50;">
											<%-- 	<img id="imgCampo"
									src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?email=${s.key}"
										width="60" height="40" /> --%>
												<c:out value="${s.key}" />
											</h5>
										</c:when>
										
										<c:otherwise>
											<h5 class="squadraStyle">
										<%-- 		<img id="imgCampo"
									src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?email=${s.key}"
										width="60" height="40" /> --%>
												<c:out value="${s.key}" />
											</h5>
										</c:otherwise>
				
									</c:choose>
										
					
									</td>
								</tr>
							</c:forEach>
						<%--  </c:forEach> --%>
						</c:forEach>

					</table>
				</c:forEach>


			</div>
		</c:if>
	</div>
			   
			
			  
			  
			  	<c:if test="${not empty listaMatchUserForTorneo}">
			  	<div class="id02-lista">
			  	<!-- onload="changeTitle()" > -->
			  	<h6 id="titlePage2"style="text-align:center; margin-top:2%;color:red">Elenco di tutti i match della squadra selezionata</h6>
			  	<c:forEach items="${listaMatchUserForTorneo }" var="partita">
			  		<h6 id="matchSquadra">
			  			Match del Torneo:
			  			<h6>
			  			<c:out value="${partita.toString()}"/>
			  			</h6>	
			  		</h6>
			  		<br>
	
			  	</c:forEach>
			  	
			  	</div>
			  </c:if>
			 
	</body>
</html>