<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Profilo</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		
		<link rel="stylesheet" href="css/profilo1.css">
		
	</head>
	<body>
		<br>		
		<div class="demo-wrap">
  			<img class="demo-bg" src="img/user-profile-bg.jpg" alt="imageContaneir" >
  	
  			<div class="card">
			<c:choose>
				<c:when test="${dettaglioUtente==null}">
				<h1>Profilo</h1>
				<c:if test="${immagine ==null }">
					<img style="margin-left: 40%;margin-bottom: 2%;"
						src="img/immagineUserDefault.png" width="160" height="140" />
				</c:if>
				
				<c:if test="${immagine !=null }">
					<img style="margin-left: 40%;margin-bottom: 2%;"
						src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?email=${sessionScope.user.email}"
						width="160" height="140" />
				</c:if>
				
    			<p>In questa sezione è possibile visualizzare tutte le info principali del 
						proprio profilo personale.Ecco i dati principali:</p>
				<h4>Email: 
					<c:out value="${sessionScope.user.email}" />
					</h4>
					
					<h4>Nome:
					<c:out value="${sessionScope.user.nome}" />
					</h4>
					
					<c:if test="${sessionScope.user.cognome!=null}">
						<h4>Cognome:
							<c:out value="${sessionScope.user.cognome}" />
						</h4>
					</c:if>
					
					
					
					<br>
				<form action="profilo" method="post">
					<input type="hidden" name="email" value="${sessionScope.user.email}" >
					<h4>Modifica Password</h4>
					<br>
					<h5>Inserisci la nuova Password:</h5><input type="text" name="setPassword">
					
					
					<input type="submit" class="btnModificaAdmin" value="Modifica">
					
				</form>	
				</c:when>
				
				<c:otherwise>
					<h1>Sezione Dettagli</h1>
					<h4>Email: 
					<c:out value="${dettaglioUtente.email}" />
					</h4>
					
					<h4>Nome:
					<c:out value="${dettaglioUtente.nome}" />
					</h4>
				
					<h4>Cognome:
					<c:out value="${dettaglioUtente.cognome}" />
					</h4>
					
					<h4>Password:
					<c:out value="${dettaglioUtente.password}" />
					</h4>
					
					<h4>Tipologia di Utente:
					<c:out value="${dettaglioUtente.tipologiaUtente.nome}" />
					</h4>
					
					<br>
				<form action="adminSetDati" method="post">
					<input type="hidden" name="email" value="${dettaglioUtente.email}" >
					
					<h4>Modifica Password</h4>
					<input type="hidden" name="password" value="${dettaglioUtente.password}">
					<h5>Inserisci la nuova Password:</h5><input type="text" name="setPassword">
					
					
					<input type="submit" class="btnModificaAdmin" value="Modifica">
					
				</form>	
				</c:otherwise>
			
			</c:choose>
		
		
		</div>
		</div>
					

	</body>
</html>