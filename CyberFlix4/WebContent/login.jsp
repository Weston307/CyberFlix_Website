<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@page import="java.util.*" %>
<%@page import ="edu.txstate.CS3320.gardenhire.data.utils.HTMLTags" %>
<body>
<div class="w3-bar w3-border w3-card-4 w3-red">
	<h1> Login</h1>
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
	<div class="w3-card-4 w3-red">
		<form action="CyberFlixLoginHandleServlet" method="post">
 			Username: <input type="email" name="username" size="15" required>
 			<br>
  			Password: <input type="password" name="password" size="15" required>
  			<br>
        	<input type="submit" value="Login">
    	</form>
  	</div>
</div>
<br><br>
<div class="w3-container">
	<div class="w3-card-4 w3-red">
		<form action="CyberFlixRegisterServlet" method="post">
			First Name: <input type="text" name="first" size="20" required><br>
			Last Name: <input type="text" name="last" size="30" required><br>
 			Email: <input type="email" name="username" size="15"required>
 			<br>
  			Password: <input type="password" name="password" size="15" required>
  			<br>
        	<input type="submit" value="Register">
    	</form>
  	</div>
</div>
</body>
</html>