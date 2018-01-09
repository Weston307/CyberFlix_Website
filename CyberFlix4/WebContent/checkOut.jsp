<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart</title>
</head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@page import="java.util.*" %>
<%@page import ="edu.txstate.CS3320.gardenhire.data.utils.HTMLTags" %>
<body>
<div class="w3-bar w3-border w3-card-4 w3-red">
	<h1>Check Out</h1>
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
<div class="w3-container">
	<c:forEach items="${films}" var="film" varStatus="count">
		<div class="w3-card-4 w3-red">
			<div class="w3-display-container" style="height:300px;">
				<div class="w3-display-left">
					<a href="${detailServlet}?ID=${count.count}">
					<img src="${posters[count.count%8]}" alt="POSTER" style="width:50%;max-width:400px">
					</a>
				</div>
				<div class="w3-display-topmiddle">
      				${film.title}<br>
      				Price: ${movieValue}
      			</div>
      		</div>
      	</div>
      	<br>
	</c:forEach>

		<div class="w3-card-4 w3-red">
			<div class="w3-display-container" style="height:100px;">
      			<div class="w3-display-left">
      				<p>Total:
         			<fmt:setLocale value = "en_US"/>
         			<fmt:formatNumber value = "${total}" type = "currency"/>
      				</p>
      			</div>
      			<div class="w3-display-middle">
      				<c:if test="${loginSuccess == false}">
      					<form action="CyberFlixLoginServlet" method="get">
 							Please Login
  							<br>
        					<input type="submit" value="Login">
    					</form>
      				</c:if>
      				<c:if test="${loginSuccess == true}">
      					Thank you for your intrest in CyberFlix!: 
      					${username}
      				</c:if>
      			</div>
      		</div>
      	</div>

</div>
</body>
</html>