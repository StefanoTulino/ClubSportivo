<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Gestione Utente</title>
		
		<!-- NUOVO LINK BOOTSTRAP -->
		<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		

		<link href="/css/gestioneUtente.css" rel="stylesheet" type="text/css">
		<%-- <jsp:include page="gestioneUtente.css"></jsp:include> --%>
		<script type="text/javascript" src="js/gestioneUtente.js"></script>
		
	</head>
	
	<body>
		<div>
			<%@ include file="header.jsp"%>
		</div>
		<br>

		<h4 class="titleTable" style="text-align: center">Tabella di riepilogo</h4>
		
		
		<div>
			<c:if test="${msgChangePasswordAdmin!=null}">
				<div class="alert alert-success" style="margin-top:4%;" role="alert">
	  				<c:out value="${msgChangePasswordAdmin}" />
					</div>
				</c:if>
		</div>
		
		
		<table class="table table-dark table-striped"
				style="margin-top:5%;width:80%;margin-left:10%;margin-right:10%">
			<tr>
				<th class="head">Email</th>
				<th class="head">Nome</th>
				<th class="head">Cognome</th>
				<th class="head">Tipologia</th>
				<th class="head">Azioni <span class="glyphicon glyphicon-magnet"></span>
				</th>
			</tr>
		
		<%-- onload="checkButtonTipologia('${user.email}')" --%>
		
	
				<c:forEach var="user" varStatus="i" items="${sessionScope.listaUtenti}" >
					<tr>
						<th><c:out value="${user.email}"/> </th>
		
						<th><c:out value="${user.nome}"/> </th>
		
						<th><c:out value="${user.cognome}"/> </th>
		
						<th> 
							<select name="menuTipologia" id="menuTipologia${user.email}" onchange="checkTipologia('${user.email}')" >
								<option value="0" selected="selected"> ${user.tipologiaUtente.nome} </option>
								<%-- <option value="0" selected="selected"> ${user.tipologiaUtente.nome} </option> --%>
								
								<c:forEach var="tipo" items="${listaTipologia}" end="4">
										<c:if test="${user.tipologiaUtente.nome != tipo.nome}">
											<option value="${tipo.id}"> ${tipo.nome} </option>
										</c:if>
										
								</c:forEach>
						</select> </th>
		
						<td>
							<form action="details" method="post">
								 <a href="details">
								 	<input type="hidden" name="email" value="${user.email}" >
								 	<input type="image" src="img/lente1.png" alt="Info" width="25" height="25">
								</a>
							</form>
							
				<!-- NON è NECESSARIO USARE ANCHE IL TAG A,USANDO GIA IL FORM: ID POSIZIONABILE ANCHE VICINO AL FORM -->
							<form action="check" method="post">
								 <a id="linkBtnTipologia${user.email}"  href="check">
								 	<input type="hidden" name="email" value="${user.email}" > 	
								 	<input type="hidden" name="tipologia" id="tipo${user.email}"  >
								 	
								 	<input type="image" src="img/spunta.png" id="btnTipologia${user.email}" onload="checkButtonTipologia('${user.email}')" alt="Info" width="25" height="25">
								</a>
							</form>
						</td>
					</tr>
					
				</c:forEach>
		</table>
		
		
	</body>
</html>



