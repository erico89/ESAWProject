<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
		<div class="myFrom rounded my-5" id="form"> 
			<div class="p-5">
				<div class="d-flex justify-content-center">
					<h5 style="color: white;font-weight: bold;">Edit Profile</h5>
				</div>
				
				<form class="form-group" id="myform">	
					<div class="form-group">
						<label for="profile photo"> Profile Photo </label>
						<p><img class="rounded-circle img-fluid" style="height:106px;width:106px" src= "./profile_photo/${user.profilePhoto}" alt="Avatar">

					</div>
					<div class="form-group">
						<label for="nickname"> Nickname </label>
						<input type="text" id="user" class="form-control text-light bg-dark" name="nickname" value="${user.nickname}" readonly>
					</div>
					<div class="form-group">
						<label for="mail"> Email </label>
						<input type="text" id="mail" class="form-control text-light bg-dark" name="mail" value="${user.mail}">
					</div>
						<div class="form-group">
						<label for="password"> Password </label>
						<input type="password" id="password" class="form-control  text-light bg-dark" name="password" value="">
					</div>
						<div class="form-group">
						<label for="name"> Name </label>
						<input type="text" id="user" class="form-control  text-light bg-dark" name="name" value="${user.name}">
					</div>
					<div class="form-group">
						<label for="surname"> Surname </label> 
						<input type="text" id="surname" class="form-control text-light bg-dark" name="surname" value="${user.surname}">  
					</div>
						<div class="form-group">
						<label for="second_surname"> Second Surname </label> 
						<input type="text" id="second_surname" class="form-control text-light bg-dark" name="second_surname" value="${user.secondSurname}">  
					</div>
				
					<div class="form-group">
						<label for="birthdate"> Birthdate </label>
						<input type="text" id="birthdate" class="form-control text-light bg-dark" name="birthdate" value="${user.birthdate}">
					</div>
					
					<button type= "button" class="menu btn btn-primary shadow rounded" style="border-color:white;color:white" id=GetUserInfo><i class="bi bi-person-plus"></i> Save </button>		  
				</form>
			</div>
		</div>


