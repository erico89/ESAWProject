<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="myFrom rounded my-5" id="form"> 
	<div class="p-5">
		<div class="d-flex justify-content-center">
			<img src="imgs/logo4.png" width="200" height="200">
		</div>
		<div class="d-flex justify-content-center">
			<h5 style="color: white;font-weight: bold;">Login</h5>
		</div>
		<c:if test = "${error}">
			<div class="alert alert-danger" role="alert">
					User or Password incorrect.
			</div>
		</c:if>
		
		<form class="form-group" action="LoginController" id="myform" method="GET">	
			<div class="form-group">
				<label for="user"> NickName or Mail </label>
				<input type="text" id="user" class="form-control  text-light bg-dark" name="user" placeholder="Nickname or Mail" value="${login.user}" required>
			</div>
			<div class="form-group">
				<label for="password"> Password </label>
				<input type="password" id="password" class="form-control  text-light bg-dark" name="password" placeholder="Password" required>
				<i id="toggleShowPassword" class="bi bi-eye-slash fa-lg float-right password_show_placement" ></i>
			</div>
			
	    	<input class="btn btn-primary mt-3" type="submit" name="sumbit" value="Submit">
		</form>
	</div>
</div>





