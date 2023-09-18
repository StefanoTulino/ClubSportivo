<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="jsp/header.jsp" %>	


<!DOCTYPE html>
<html>
	<head>
		<!-- Per renderlo Responsive -->
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Uniti per lo sport</title>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		
		<link rel="stylesheet" href="./css/home.css">
		<!-- <link rel="stylesheet" href="/WEB-INF/css/homePage.css"> -->
			
			
		<script>
		var myIndex = 0;
		carousel();
		
		function carousel() {
			  var i;
			  var x = document.getElementsByClassName("mySlides");
			  for (i = 0; i < x.length; i++) {
			    x[i].style.display = "none";  
			  }
			  myIndex++;
			  if (myIndex > x.length) {myIndex = 1}    
			  x[myIndex-1].style.display = "block";  
			  setTimeout(carousel, 4000);    
			}
		
		
		
		var slideIndex = 1;
		showDivs(slideIndex);

		function plusDivs(n) {
		  showDivs(slideIndex += n);
		}

		function currentDiv(n) {
		  showDivs(slideIndex = n);
		}

		function showDivs(n) {
		  var i;
		  var x = document.getElementsByClassName("mySlides");
		  var dots = document.getElementsByClassName("demo");
		  if (n > x.length) {slideIndex = 1}
		  if (n < 1) {slideIndex = x.length}
		  for (i = 0; i < x.length; i++) {
		    x[i].style.display = "none";  
		  }
		  for (i = 0; i < dots.length; i++) {
		    dots[i].className = dots[i].className.replace(" w3-white", "");
		  }
		  x[slideIndex-1].style.display = "block";  
		  dots[slideIndex-1].className += " w3-white";
		}
		
		</script>



		<style>
		/* .footer {
			margin-bottom: 0;
			padding-bottom: 0;
			bottom: 0;
		} */
	</style>

	</head>
		<body>
	
		<div class="contaneir">
							
			<div>
					
				<c:choose> 
					<c:when test="${msgLogin!=null && prenotazioniNonValide.size()>0}">
						<div class="alert alert-success" style="margin-top:5%;" role="alert">
	  						<c:out value="${msgLogin}" />
						</div>
						<div id="warning" class="alert alert-warning" style="margin-top:1%;" role="alert">
							Ci sono degli errori imprevisti! Cliccare sull'icona di triangolo giallo per risolvere!
							<span onclick="document.getElementById('warning').style.display='none'">&times;</span>
						</div>
					</c:when>
				
					<c:when test="${msgLogin!=null}">
						<div class="alert alert-success" style="margin-top:5%;" role="alert">
	  						<c:out value="${msgLogin}" />
						</div>
					</c:when>
				</c:choose>
				
			
			<c:if test="${errorLogin2!=null}">
				<div class="alert alert-danger" style="margin-top:5%;" role="alert">
	  				<c:out value="${errorLogin2}" />
					</div>
				</c:if>
				
			<c:if test="${erroreRegistrazione!=null}">
				<div class="alert alert-danger" style="margin-top:5%;" role="alert">
	  				<c:out value="${erroreRegistrazione}" />
					</div>
				</c:if>
		

			<c:if test="${msgPrenotazione!=null}">
				<div class="alert alert-success" style="margin-top:5%;" role="alert">
	  				<c:out value="${msgPrenotazione}" />
					</div>
				</c:if>
				
				<c:if test="${addTorneoAdmin!=null}">
				<div class="alert alert-success" style="margin-top:5%;" role="alert">
	  				<c:out value="${addTorneoAdmin}" />
					</div>
				</c:if>
				
				<c:if test="${msgPrenotazioneTorneo!=null}">
				<div class="alert alert-success" style="margin-top:5%;" role="alert">
	  				<c:out value="${msgPrenotazioneTorneo}" />
					</div>
				</c:if>
				
				<c:if test="${msgDeletePrenotazioneUser!=null}">
				<div class="alert alert-success" style="margin-top:5%;" role="alert">
	  				<c:out value="${msgDeletePrenotazioneUser}" />
					</div>
				</c:if>
				
			<c:if test="${msgProfilo!=null}">
				<div class="alert alert-success" style="margin-top:5%;" role="alert">
	  				<c:out value="${msgProfilo}" />
					</div>
				</c:if>
					
			</div>
			
			
			
			
  				<div class="w3-container">
					<h4 id="titleDivGallery">Galleria</h4>
				
				
				<!-- ANCHE SENZA /img me le prende, ma penso non sia corretto -->
				
				<div class="w3-content w3-display-container" >
				 <img class="mySlides" onload="carousel()" src="img/campo1.jpg" >
				 <img class="mySlides"  src="img/homePage1.jpg" >
				 <img class="mySlides" src="img/homePage2.jpg">
				 <img class="mySlides" src="img/homePage3.jpg">
				 <img class="mySlides" src="img/homePage6.jpg">
				 <img class="mySlides" src="img/homePage5.jpg">
					</div>
				</div>


		<div class="notizie">
			<h4 id="titleDiv">Sezione Notizie</h4>
			<ul>
				<li>Nuovo Campo di Tennnis</li>
				<li>Nuovo Campo di Calcio a 5</li>
			</ul>

			<p>Anche oggi sconto del 10% sulle prenotazioni prima delle ore
				20:00</p>

		</div>


		<div class="campi">
			<h4 id="titleDiv">Sezione Campi</h4>
			<p>Le principali attività sportive sono quelle dedicate al mondo
				del calcio,tennis e pallavolo.I campi da calcio sono suddivisi in
				varie categorie, quali campi di calcio a 5,calcio ad 8 ed i classici
				campi di calcio ad 11. Oltre a ciò, nel nostro centro sportivo è
				possibile giocare su vari campi di tennis(di tipo gomma,sabbia o
				addirittura erba) e a campi di pallavolo coperto o all'aperto. Si
				prega di prenotare online il campo di vostro piacimento.</p>

		</div>



	</div>
		
	</body>
</html>