<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Gestione Campo</title>
		
		<!-- NUOVO LINK BOOTSTRAP -->
		<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		
		<link rel="stylesheet" href="css/gestioneCampo.css">
		<script type="text/javascript" src="js/gestioneCampo.js"></script>
		
	</head>
	
	<body>
		<div>
			<%@ include file="header.jsp"%>
		</div>
		<br>


		<c:if test="${errorAddTipologia!=null}">
			<div class="alert alert-danger"  role="alert">
	  			<c:out value="${errorAddTipologia}" />
			</div>
		</c:if>
		
		<c:if test="${errorAddCampo!=null}">
			<div class="alert alert-danger"  role="alert">
	  			<c:out value="${errorAddCampo}" />
			</div>
		</c:if>
		
		<c:if test="${addTipologia!=null}">
			<div class="alert alert-success"  role="alert">
	  			<c:out value="${addTipologia}" />
			</div>
		</c:if>
		
		<c:if test="${addCampo!=null}">
			<div class="alert alert-success"  role="alert">
	  			<c:out value="${addCampo}" />
			</div>
		</c:if>
		
		<c:if test="${msgCampo!=null}">
			<div class="alert alert-success" style="margin-top:2%;" role="alert">
	  			<c:out value="${msgCampo}" />
			</div>
		</c:if>
		
		<c:if test="${msgStatus!=null}">
			<div class="alert alert-success" style="margin-top:2%;" role="alert">
	  			<c:out value="${msgStatus}" />
			</div>
		</c:if>


	<div>
	
	
		<div id="id01" class="modal">
			<form class="modal-content animate" action="insertCampoAdmin" method="get">
				<!-- TITLE -->
				<div class="titleModal">
					<span onclick="document.getElementById('id01').style.display='none'"
						class="close" title="Close">&times;</span>
					<h4 style="text-align: center; background-color: #4CAF50;">Inserisci un campo</h4>
				</div>
				<!-- Create a form container -->
				<div class="containerFormRegistrazione">
					<br> <label for="uname" id="prezzoCampo"><b>Prezzo</b></label>
					<input type="text" placeholder="Inserisci un prezzo" name="prezzo"
						id="prezzo" required>
						 <br> 
						 <label for="uname" id="tipologia_fk"><b>Tipologia</b></label> 
						 <select name="tipologia_fk">
							<c:forEach var="tipo" items="${listaTipologia}">
								<option id="tipologia_fk" value="${tipo.id }">${tipo.descrizione}</option>
							</c:forEach>
						</select>
					<!-- <input type="text" placeholder="Inserisci l'id della tipologia" 
							name="tipologia_fk" id="tipologia_fk" required> -->
					<!-- Submit Button -->
					<button type="submit" id="btnRegisterForm" style="color: white;">Aggiungi</button>
				</div>
			</form>
		</div>



		<div id="id02" class="modal">
			<form class="modal-content animate" action="insertTipCampoAdmin" 
			method="post" enctype="multipart/form-data" >
				<!-- TITLE -->
				<div class="titleModal">
					<span onclick="document.getElementById('id02').style.display='none'"
						class="close" title="Close">&times;</span>
					<h4 style="text-align: center; background-color: #4CAF50;">Inserisci una nuova tipologia di campo</h4>
				</div>
		
				<c:if test="${errorAddTipologia!=null}">
			<div class="alert alert-danger"  role="alert">
	  			<c:out value="${errorAddTipologia}" />
			</div>
		</c:if>
		
				<!-- Create a form container -->
				<div class="containerFormRegistrazione">
					<br> 
					<label for="uname" id="descrizione"><b>Descrizione</b></label>
					<input type="text" placeholder="Inserisci una descrizione"
						name="descrizione" id="descrizione" required> 
						<br>
						<label for="uname" id="incremento"><b>Incremento</b></label> 
						<input type="text" placeholder="Inserisci incremento" name="incremento"
							id="incremento" required>
						<br> 
						<label for="uname" id="imgLabel"><b>Img</b> </label> 
						<input type="file" name="img" id="img">
					<!-- Submit Button -->
					<button type="submit" id="btnRegisterForm" style="color: white;">Aggiungi</button>
				</div>
			</form>
		</div>

		
		
		<h4 class="titleTable">Tabella di riepilogo</h4>
		<table class="table table-dark table-striped" 
			style="margin-top:5%;width:80%;margin-left:10%;margin-right:10%">
			<tr class="addBtn">
				<th>Aggiungi Campo
				<input type="image" class="btnAdd" src="img/add-verde.png" width="25" height="25"
					onclick="document.getElementById('id01').style.display='block' ">
				</th>
					
				<th>Aggiungi Tipologia Campo	
				<input type="image" class="btnAdd" src="img/add-rosso.jpg" width="25" height="25"
					onclick="document.getElementById('id02').style.display='block' ">
				</th>
			</tr>
			
			<tr>
				<th class="head">Id</th>
				<th class="head">Prezzo</th>
				<th class="head">Tipologia</th>
				<th class="head">Immagine</th>
				<th class="head">Check</th>
				<th class="head">Rimuovi</th>
			</tr>

			<c:forEach var="campo" varStatus="i" items="${listaCampi}">
				<tr>
					<th><c:out value="${campo.id}" /></th>

					<th><input type="text" class="costo1" name="costo"  onkeypress="return isNumberKey(event)"
						id="costoCampo${campo.id}"  value="${campo.costo}"
						onchange="checkPrezzo('${campo.id }')" pattern="/^\d*$/"></th>

					<th>
					<select name="menuTipologia" id="menuTipologia${campo.id}"
						onchange="checkTipologia('${campo.id}')">
							<option value="0" selected="selected">
								${campo.tipologiaCampo.descrizione}</option>

							<c:forEach var="tipo" items="${listaTipologia}">
								<c:if
									test="${campo.tipologiaCampo.descrizione != tipo.descrizione}">
									<option value="${tipo.id }">${tipo.descrizione}</option>
								</c:if>
							</c:forEach>
					</select>
					</th>


					<th><img id="imgCampo"
						src="http://localhost:8080/Eustema-Project-Final/PrenotazioneImmagine?id=${campo.tipologiaCampo.id}"
						width="160" height="140" /></th>


					<th>
						<form action="checkCampo" method="get">
							<a href="checkCampo"> 
							<input type="hidden" name="idCampo"value="${campo.id}"> 
							<input type="hidden" name="costo"id="prezzo${campo.id}" value="${campo.costo}"> 
							<input	type="hidden" name="tipologia_fk" id="tipo${campo.id}"
								value="${campo.tipologiaCampo.id}"> 
							<input type="image" src="img/spunta.png" alt="Info"
								width="25" height="25"
								<%-- id="btnTipologia${campo.id}" 
								  	onload="checkButtonTipologia('${campo.id}')"--%> 
							>
							</a>
						</form>
					</th>


					<th>
						<form action="removeCampo" method="post">
							<a id="linkBtnRemove${campo.id}" href="removeCampo"> <input
								type="hidden" name="idCampo" value="${campo.id}"> <input
								type="hidden" name="statusCampo" value="${campo.prenotabile}">
								<c:choose>
									<c:when test="${campo.prenotabile==1}">
										<input type="image" src="img/pallino_verde.jpg" width="35"
											height="30" />
									</c:when>

									<c:otherwise>
										<input type="image" src="img/pallino_rosso.jpg" width="35"
											height="30" />
									</c:otherwise>
								</c:choose>
							</a>
						</form>
					</th>
				
				</tr>
			</c:forEach>
		</table>
		
		
	</div>
</body>
</html>