<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
</head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<%@page import="java.util.*" import ="java.io.*"%>
<%@page import="edu.txstate.CS3320.gardenhire.data.film.Film" %>

<div class="w3-bar w3-border w3-card-4 w3-red">
	<h1>Search Results</h1>
	<a href="${home}" class="w3-bar-item w3-button w3-border-right w3-black">Home</a>
	<a href="${search}" class="w3-bar-item w3-button w3-border-right w3-black">Search</a>
	<a href="${login}" class="w3-bar-item w3-button w3-border-right w3-black">Login</a>
	<c:if test="${cartNotEmpty == 'true'}">
    	<a href="${cart}" class="w3-bar-item w3-button w3-border-right w3-black">Cart(${numMovies})</a>
    	<a href="${checkOut}" class="w3-bar-item w3-button w3-border-right w3-black">Check Out</a>
    </c:if>
    <c:if test="${staffLogin == 'true'}">
    	<a href="${staffView}" class="w3-bar-item w3-button w3-border-right w3-black">Customer View</a>
    </c:if>
</div>

<p>
<div class="w3-container w3-grey">
	<c:forEach items="${films}" var="film" varStatus="count">

	<div class="w3-container">
		<div class="w3-card-4 w3-red">
			<div class="w3-display-container" style="height:300px;">
				<div class="w3-display-left">
					<a href="${detailServlet}?ID=${count.count}">
					<img src="${posters[count.count%8]}" alt="POSTER" style="width:50%;max-width:400px">
					</a>
				</div>
				<div class="w3-display-topmiddle">
      				${film.title}<br>
      				Year: ${film.releaseYear}<br>
      				Rating: ${film.rating}<br>
      				Running Time: ${film.length}<br><br>
      				Synopsis: <br>${film.description}
      				<form action="CyberFlixCart" method="get">
    				<button type="submit" name="cart" value="${film.filmID}">Add to Cart</button>
   					</form>
      			</div>
      		</div>
    	</div>
    	<br>
	</div>

	</c:forEach>
</div>
</body>
</html>