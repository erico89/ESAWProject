<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
		<div class="myFrom rounded my-5" id="form"> 
			<div class="p-5">
				<div class="d-flex justify-content-center">
					<h5 style="color: white;font-weight: bold;">Edit Profile</h5>
				</div>
				
				<form class="form-group" id="myform">	
					<div class="form-group">
						<label for="user"> Profile Photo </label>
						<p><img src="imgs/avatar3.png" class="rounded" style="height:106px;width:106px" alt="Avatar"></p>
					</div>
					<div class="form-group">
						<label for="user"> Nickname </label>
						<input type="text" id="user" class="form-control  text-light bg-dark" name="user" placeholder="${user.nickname}">
					</div>
						<div class="form-group">
						<label for="user"> Name </label>
						<input type="text" id="user" class="form-control  text-light bg-dark" name="user" placeholder="${user.name}">
					</div>
					<div class="form-group">
						<label for="surname"> Surname </label> 
						<input type="text" id="surname" class="form-control text-light bg-dark" name="surname" placeholder="Surname">  
					</div>
					<div class="form-group">
						<label for="email"> Email </label>
						<input type="text" id="user" class="form-control text-light bg-dark" name="email" placeholder="Email">
					</div>
					<div class="form-group">
						<label for="birthdate"> Birthdate </label>
						<input type="text" id="user" class="form-control text-light bg-dark" name="birthdate" placeholder="Birthdate">
					</div>
					
			    	<input class="btn btn-primary mt-3" type="submit" name="sumbit" value="Submit">
				</form>
			</div>
		</div>


