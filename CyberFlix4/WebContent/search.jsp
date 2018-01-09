<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cyber Flix</title>
</head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<%@page import="java.util.*" %>

<div class="w3-bar w3-border w3-card-4 w3-red">
	<h1> CyberFlix Search!</h1>
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

<h2> Let's Find You a Movie! </h2>
<p>
<p>
<div class="w3-card w3-red">
	<form action="CyberFlixServlet" method="get">
  		<strong>Search By Attribute:</strong>
  		<br>
  		Film Title: <input type="text" name="film_title"><br>
  		Film Description: <input type="text" name="film_description"><br>
  		Film Rating: <input type="text" name="film_rating"><br>
  		Running Time (In Minutes) 
  		<select name="run_time">
  		<option value ="300">any</option>
    	<option value="30">30</option>
    	<option value="60"> 60</option>
  		<option value="90"> 90</option>
  		<option value="120"> 120</option>
  		<option value="150"> 150</option>
  		<option value="200"> 200+</option>
    	</select>
    	<br>
    	<input type="submit" name = "attribute" value="Submit">
    	<br>
    </form>
 </div>
 <p>
 <div class="w3-card w3-red">   	
    <form action="CyberFlixServlet" method="get">	
  		<strong>Search By Category:</strong> 
  		<br>
  		Category:
  		<select name="film_category">
  		<option value ="NONE">NONE</option>
    	<option value="ACTION">Action</option>
    	<option value="ANIMATION">Animation</option>
    	<option value="CHILDREN">Children</option>
    	<option value="CLASSICS">Classics</option>
    	<option value="COMEDY">Comedy</option>
    	<option value="DOCUMENTARY">Documentary</option>
    	<option value="DRAMA">Drama</option>
    	<option value="FAMILY">Family</option>
    	<option value="FOREIGN">Foreign</option>
    	<option value="GAMES">Games</option>
    	<option value="HORROR">Horror</option>
    	<option value="MUSIC">Music</option>
    	<option value="NEW">New</option>
    	<option value="SCI_FI">Sci-Fi</option>
    	<option value="SPORTS">Sports</option>
    	<option value="TRAVEL">Travel</option>
  		</select>
  		<br>
    	<input type="submit" name = "category" value="Submit">
    	<br>
    </form>
</div>
<p>
<div class="w3-card w3-red">    
    <form action="CyberFlixServlet" method="get">	
  		<strong>Search Alphabetically:</strong> 
  		<br>
  		First Letter: <input type="text" name="letter">
  		<br>
    	<input type="submit" name = "alphabetically" value="Submit">
    	<br>
    </form>
</div>
</body>
</html>