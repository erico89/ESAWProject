<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="css/structure.css">

<div class="row align-items-center justify-content-center"> 
	<div class="col-md-6 mt-5"> 
		<h1>Login</h1>
		<form action="LoginController" method="POST">
			<div class="form-group">
				<label for="Nickname"> NickName or Mail </label>
				<input type="text" id="user" class="form-control" name="user" placeholder="Nickname or Mail" value="${login.user}" required>
			</div>
			<div class="form-group">
				<label for="password"> Password </label>
				<input type="password" id="password" class="form-control" name="password" placeholder="Password" value="${login.password}" required>
			</div>
			<button class="btn btn-primary mt-3"> Submit </button>
		</form>
	</div>
</div>




