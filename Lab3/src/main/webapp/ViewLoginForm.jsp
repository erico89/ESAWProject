<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="css/structure.css">

<div class="container"> 
	<div class="row align-items-center justify-content-center"> 
		<div class="col-md-6"> 
			<p>
			<p>
			<form action="LoginController" method="POST">
				<label for="Nickname"> NickName or Mail </label><br>
				<input type="text" id="user" class="form-control" name="user" placeholder="Nickname or Mail" value="${login.user}" required><br>
				<label for="password"> Password </label><br>
				<input type="password" id="password" class="form-control" name="password" placeholder="Password" value="${login.password}" required>
				  <button class="btn btn-primary mt-3"> Submit </button>
			</form>
		</div>
	</div>
</div>




