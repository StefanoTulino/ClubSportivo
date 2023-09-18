<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Insert title here</title>
		
		<link rel="stylesheet" href="css/infoUserPrenotazione.css">
		<script type="text/javascript" src="js/infoUserPrenotazione.js"></script>
			
	</head>
		<body>
			<div class="dropdown">
				<input type="image" src="img/infoPrenotazione.jpg" width="36%"
					height="38%" onclick="myFunction()" class="dropbtn">
				<div id="myDropdown" class="dropdown-content">
					<h4>Prenotazione:</h4>
					<c:forEach var="prenotazione" items="${userPrenotazioni}">
						<h4>
							<c:out value="${prenotazione.toString()}" />
							<br>
						</h4>
					</c:forEach>
			</div>
		</div>	

</body>
</html>