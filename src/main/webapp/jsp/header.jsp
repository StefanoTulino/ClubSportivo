<!-- NON TOCCARE IL CSS,SENNò SI SCOMBINA TUTTO -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
				integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		
		<script type="text/javascript" src="js/infoUserPrenotazione.js"></script>
		<script type="text/javascript" src="js/header.js"></script>
		
		
		<style>
		
		/* /*Prenotazione */
		.dropdown {
		  position: relative;
		  display: flex;
		  right: 5%; 
		}
		
		.dropdown-content {
    		display: none;
    		position: absolute;
    		overflow: auto;
    		background-color: #f1f1f1;
    		width: 500px;
    		right:20%;
    		margin-top: 30%;
		}
		
	
		 .closePrenotazione {
    	font-size: 20px;
    	right: 0;
    	text-align: end;
    	color: red;
		}
	
		.containerPrenotazione {
			width:100%;
			padding: 16px;
		}
		
		
		.image1{
			width: 14%;
			height: 16%;
			position: relative;
			right:15%;
		}
		
		.image2{
			width: 3%;
			height: 3%;
			position: relative;
			right:12%;
		}
		
		/*Prenotazioni da cancellare */
		.dropdown-content1 {
    		display: none;
    		position: absolute;
    		overflow: auto;
    		background-color: #f1f1f1;
    		width: 500px;
    		right:20%;
    		margin-top: 20%;
		}
		
		.titleModalPrenotazione1 {
			margin-top:25%;
		}
		
		.closePrenotazione1 {
    		font-size: 20px;
    		right: 0;
    		text-align: end;
    		margin-top: -8%;
	    	color: red;
		}
	
		
		.containerPrenotazione1 {
			width:100%;
			padding: 0% 0% 3% 3%;
		}
		
		.titlePrenotazioneAlert{
			color:red;
			margin-top:0%;
		}
		
	
		body {
			font-family: Arial, Helvetica, sans-serif;
		}
		
		
		#btnLogout {
			position: relative;
			padding: 5px 7px;
			border-radius: 10px;
			right: 10%;
			display:flex;
			background-color: #4CAF50;
		}
		
		
		#btnLogout1 {
			position: absolute;
    		padding: 5px 7px;
    		border-radius: 10px;
    		right: 3%;
    		margin-top:-1%;
    		display: flex;
   	 		background-color: #4CAF50;
		}
		
		
		/*CAMPO*/
		.dropGestionebtn {
  			background-color: #3498DB;
  			color: white;
  			padding: 16px;
  			font-size: 16px;
  			border: none;
  			cursor: pointer;
		}

		.dropGestionebtn:hover, .dropbtn:focus {
  			background-color: #2980B9;
  			cursor: pointer;
		}



		.Gestione-dropdown-content {
		  display: none;
		  position: absolute;
		  cursor: pointer;
		  background-color: #f1f1f1;
		  min-width: 160px;
		 
		  /*Inserito questo per adattarlo anche a Prenotazione Personale */
		  margin-top:2%;
		  
		  overflow: auto;
		  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		  z-index: 1;
		}

		.Gestione-dropdown-content a {
		  color: black;
		  padding: 12px 16px;
		  text-decoration: none;
		  cursor:pointer;
		  display: block;
		}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;} */
		
		
		
		</style>
		
		</head>
			<body>

				<c:choose>
					
					<c:when test="${sessionScope.user.tipologia==3}">
					<!-- ADMIN -->
					<nav class="navbar navbar-expand navbar-dark bg-dark">
						<a class="navbar-brand" href="index.jsp">Home</a>
						<div class="collapse navbar-collapse" id="navbarCollapse">
							<ul class="navbar-nav mr-auto">
								
								<a class="nav-link" onclick="myFunctionPrenotazione()" class="dropGestionebtn" style="cursor: pointer">Prenotazione Personale</a>
								<div id="GestionemyDropdown2" class="Gestione-dropdown-content" >
								    <a href="PrenotazioneAssAdminServlet" style="text-decoration: none">Campo</a>
								    <a href="TorneoAssAdmin" style="text-decoration: none">Torneo</a>
							   </div>
							
								<li class="nav-item"><a class="nav-link" href="profilo">Profilo</a> </li>
								<li class="nav-item" style="cursor: pointer">
								
								<a class="nav-link" onclick="myFunctionGestione()" class="dropGestionebtn" style="cursor: pointer">Gestione</a>
								<div id="GestionemyDropdown1" class="Gestione-dropdown-content" >
								    <a href="GestioneUtente" style="text-decoration: none">Utente</a>
								    <a href="GestioneCampo" style="text-decoration: none">Campo</a>
								    <a href="GestioneTorneo" style="text-decoration: none">Torneo</a>
								    <a href="GestioneMatch" style="text-decoration: none">Match</a>
							   </div>
							   
							</li>
						</ul>
						<a href=logout style="text-decoration: none">
							<button id="btnLogout" style="cursor:pointer;color:white;">Logout</button>
						</a>
						
				</div>
			</nav>
		</c:when>


		<c:when test="${sessionScope.user.tipologia==1}">
			
			<nav class="navbar navbar-expand navbar-dark bg-dark">
			<!-- UTENTE CLASSICO -->
				<a class="navbar-brand" href="index.jsp">HomePage</a>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link"
							href="PrenotazioneUserClassico">Prenotazione</a></li>
						<li class="nav-item"><a class="nav-link" href="profilo">Profilo</a> </li>
						<li class="nav-item"><a class="nav-link" href="StoricoUser">Storico Torneo</a> </li>
					</ul>
				
						<a href=logout style="text-decoration: none">
							<button id="btnLogout1" style="cursor:pointer;color:white;">Logout</button>
						</a>
					<br>
				</div>


				<div class="dropdown">
				<c:if test="${sessionScope.user.listaPrenotazione.size() >0 }">
					<input class="image1" type="image" src="img/infoPrenotazione.jpg" onclick="openInfo()">
				</c:if>

				<div id="id03" class="dropdown-content">
					<div class="titleModalPrenotazione">
						<h5 style="cursor:pointer;" class="closePrenotazione" onclick="closeInfo()">X</h5>
					</div>

					<div class="containerPrenotazione">
						<c:forEach var="prenotazione"
							items="${sessionScope.user.listaPrenotazione}">

							<h4 style="color: red;">
								Prenotazione nr. <c:out value="${prenotazione.id}" />		
							</h4>

							<h6>
								Per il Campo numero <c:out value="${prenotazione.idCampo}" />  ,
							</h6>

							<h6>
								<c:out value="${prenotazione.toString()}" />
							</h6>
							<hr>
						</c:forEach>
					</div>
				</div>
				</div>
				
				
				
				
				<c:if test="${sessionScope.prenotazioniNonValide.size()>0 }">
					 <input type="image" class="image2" src="img/triangolo-giallo.jpg" onclick="openInfoAlert()">
				</c:if>
				
				
				<div id="id04" class="dropdown-content1">
					<div class="titleModalPrenotazione1">
						<h5 style="cursor:pointer;" class="closePrenotazione1" onclick="closeInfoAlert()">X</h5>
					</div>
				
				<div class="containerPrenotazione1">
						<h4 style="color:red;">Le seguenti prenotazioni non possono essere
							soddisfatte: scegliere se modificare  una prenotazione con un
							altro campo oppure eliminare la prenotazione:</h4>
						<c:forEach var="prenotazione"
							items="${sessionScope.prenotazioniNonValide}">

							<h4>
								Prenotazione nr. <c:out value="${prenotazione.id}" />		
							</h4>
							
							<form action="changePrenotazione" method="get">
								<input type="hidden" name="idPrenotazione" value="${prenotazione.id}">
								<%-- <input type="hidden" name="dataInizio" value="${prenotazione.data_inizio}"> --%>
								<input type="submit" value="Modifica">
							</form>
							
							<form action="deletePrenotazione"  method="get">
								<input type="hidden" name="idPrenotazione" value="${prenotazione.id}">
								<input type="submit" value="Cancella"> 
							</form>
							
							<h6>
								Per il Campo numero <c:out value="${prenotazione.idCampo}" />  ,
							</h6>

							<h6>
								<c:out value="${prenotazione.toString()}" />
							</h6>
							<hr>
						</c:forEach>
					</div>
				</div>
				
			</nav>
		</c:when>

		
		
		
		<c:when test="${sessionScope.user.tipologia==2 || sessionScope.user.tipologia==4 }">
			
			<nav class="navbar navbar-expand navbar-dark bg-dark">
			<!-- VIP O ASSOCAZIONE -->
				<a class="navbar-brand" href="index.jsp">HomePage</a>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav mr-auto">
						
						<li class="nav-item"><a class="nav-link" href="profilo">Profilo</a> </li>
						
						
						<c:if test="${sessionScope.user.tipologia==2 }">
							<li class="nav-item"><a class="nav-link"
							href="PrenotazioneVipServlet">Prenotazione</a></li>
							<li class="nav-item"><a class="nav-link" href="TorneoVip">Tornei</a> </li>
						</c:if>
						
						<c:if test="${sessionScope.user.tipologia==4 }">
							<li class="nav-item"><a class="nav-link"
							href="PrenotazioneAssAdminServlet">Prenotazione</a></li>
							<li class="nav-item"><a class="nav-link" href="TorneoAssAdmin">Tornei</a> </li>
						</c:if>
						
						<li class="nav-item"><a class="nav-link" href="StoricoUser">Storico Torneo</a> </li>
					</ul>
				
						<a href=logout style="text-decoration: none">
							<button id="btnLogout1" style="cursor:pointer;color:white;">Logout</button>
						</a>
					<br>
				</div>


				<div class="dropdown">
				<c:if test="${sessionScope.user.listaPrenotazione.size() >0 }">
					<input class="image1" type="image" src="img/infoPrenotazione.jpg" onclick="openInfo()">
				</c:if>

				<div id="id03" class="dropdown-content">
					<div class="titleModalPrenotazione">
						<h5 style="cursor:pointer;" class="closePrenotazione" onclick="closeInfo()">X</h5>
					</div>

					<div class="containerPrenotazione">
						<c:forEach var="prenotazione"
							items="${sessionScope.user.listaPrenotazione}">

							<h4 style="color: red;">
								Prenotazione nr. <c:out value="${prenotazione.id}" />		
							</h4>

							<h6>
								Per il Campo numero <c:out value="${prenotazione.idCampo}" />  ,
							</h6>

							<h6>
								<c:out value="${prenotazione.toString()}" />
							</h6>
							<hr>
						</c:forEach>
					</div>
				</div>
				</div>
				
				
				
				<c:if test="${sessionScope.prenotazioniNonValide.size()>0 }">
					 <input type="image" class="image2" src="img/triangolo-giallo.jpg" onclick="openInfoAlert()">
				</c:if>
				
				
				<div id="id04" class="dropdown-content1">
					<div class="titleModalPrenotazione1">
						<h5 style="cursor:pointer;" class="closePrenotazione1" onclick="closeInfoAlert()">X</h5>
					</div>
				
				<div class="containerPrenotazione1">
						<h4 class="titlePrenotazioneAlert">Le seguenti prenotazioni non possono essere
							soddisfatte: scegliere se modificare  una prenotazione con un
							altro campo oppure eliminare la prenotazione:</h4>
						<c:forEach var="prenotazione"
							items="${sessionScope.prenotazioniNonValide}">

							<h4>
								Prenotazione nr. <c:out value="${prenotazione.id}" />		
							</h4>
							
							<form action="changePrenotazione" method="get">
								<input type="hidden" name="idPrenotazione" value="${prenotazione.id}">
								<%-- <input type="hidden" name="dataInizio" value="${prenotazione.data_inizio}"> --%>
								<input type="submit" value="Modifica">
							</form>
							
							<form action="deletePrenotazione"  method="get">
								<input type="hidden" name="idPrenotazione" value="${prenotazione.id}">
								<input type="submit" value="Cancella"> 
							</form>
							
							
							
							
							<h6>
								Per il Campo numero <c:out value="${prenotazione.idCampo}" />  ,
							</h6>

							<h6>
								<c:out value="${prenotazione.toString()}" />
							</h6>
							<hr>
						</c:forEach>
					</div>
				</div>
				
			</nav>
		</c:when>
		


		<c:otherwise>
			<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
				<a class="navbar-brand" href="index.jsp">HomePage</a> 
				 <button id="btnLogin" style="cursor:pointer;color:white;" onclick="document.getElementById('id01').style.display='block' ">
						Accedi</button> 
					
				
				<button  id="btnRegistrazione"
				style="cursor:pointer;color:white;" onclick="document.getElementById('id02').style.display='block' ">
					Registrati</button>	
			</nav>
					
					<div id="id01" class="modal">
						<%@ include file="login.jsp" %>
					</div>
					
					
					<div id="id02" class="modal">
						<%@ include file="registrazione.jsp" %>
					</div>	
		</c:otherwise>
	</c:choose>

</body>
</html>