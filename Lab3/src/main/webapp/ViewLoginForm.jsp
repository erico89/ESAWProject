<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="css/structure.css">

<div class="row align-items-center justify-content-center"> 
	<div class="col-md-6 mt-5"> 
		<h1>Login</h1>
		<c:if test = "${!empty login.error}">
			<div class="alert alert-danger" role="alert">
					${login.error}
			</div>
		</c:if>
		<form class="form-group" action="LoginController" id="myform" method="POST" enctype='multipart/form-data'>	
			<div class="form-group">
				<label for="user"> NickName or Mail </label>
				<input type="text" id="user" class="form-control" name="user" placeholder="Nickname or Mail" value="${login.user}" required>
			</div>
			<div class="form-group">
				<label for="password"> Password </label>
				<input type="password" id="password" class="form-control" name="password" placeholder="Password" required>
			</div>
			
	    	<input class="btn btn-primary mt-3" type="submit" name="sumbit" value="Submit">
		</form>
	</div>
</div>




