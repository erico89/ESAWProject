<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row align-items-center justify-content-center"> 
	<div class="col-md-6 mt-5" id="form"> 
		<h1>Registration</h1>
		<form class="form-group" action="RegisterController" id="myform" method="POST" enctype='multipart/form-data'>
			<div class="form-group">
				<label for="Nickname"> Nickname </label>
				<input type="text" id="nickname" class="form-control" name="nickname" placeholder="Nickname" value="${user.nickname}" required pattern=".{4,}">
				<c:if test = "${user.errors[0]}">
					<div class="alert alert-danger" role="alert">
							The nickname you wrote has already been registered
					</div>
				</c:if>
			</div>

			<div class="form-group">
				<label for="name"> Name </label> 
				<input type="text" id="name" class="form-control" name="name" placeholder="Name" value="${user.name}" required> 
			</div>

			<div class="form-group">
				<label for="surname"> Surname </label> 
				<input type="text" id="surname" class="form-control" name="surname" placeholder="Surname" value="${user.surname}" required>  
			</div>
	  		  
			<div class="form-group">
				<label for="secondSurname"> Second Surname </label>  
				<input type="text" id="secondSurname" class="form-control" name="secondSurname" placeholder="Second Surname" value="${user.secondSurname}">  
			</div>
		  
			<div class="form-group">
				<label for="mail"> Mail </label>  
				<input type="email" id="mail" class="form-control" name="mail" placeholder="Mail" value="${user.mail}" required>  
				<c:if test = "${user.errors[1]}">
					<div class="alert alert-danger" role="alert">
						The email you wrote is already registered
					</div>
				</c:if>
			</div>
		  
			<div class="form-group">
				<label for="password"> Password </label>  
				<input type="password" id="password" class="form-control" name="password" placeholder="Password" value="${user.password}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$">
				<small id="emailHelp" class="form-text"> 8 characters minimum <br> 1 capital letter required <br> 1 number required</small> 
				<c:if test = "${user.errors[2]}">
					<div class="alert alert-danger" role="alert">
						Invalid format. The password must contain at least 1 capital letter and 1 number.
			   		</div>
			   </c:if>
			</div>
			
			<div class="form-group">
				<label for="confirmationPassword"> Confirm Password </label>	
				<input type="password" id="confirmationPassword" class="form-control" name="confirmationPassword" placeholder="Confirm Password" value="${user.confirmationPassword}" required> 
	  		  	<c:if test = "${user.errors[3]}">
					<div class="alert alert-danger" role="alert">
						Passwords don't match!
					</div>
				</c:if>
			</div>
		  
			<div class="form-group">
				<label for="birthdate"> Date of birth </label> 
				<input type="date" id="birthdate" class="form-control" name="birthdate" placeholder="Birthdate" value="${user.birthdate}" required> 
			</div>
			  
			<div class="form-group">
			  <label for="genres"> Choose your favorite genres </label> 
			  <select class="form-group" name="genres" id="genres" multiple="multiple">
			    <option value="Pop">Pop</option>
			  	<option value="Latin">Latin</option>
			  	<option value="Hip-Hop/Rap">Hip-Hop&frasl;Rap</option>
			  	<option value="EDM">EDM</option> 
		  		<option value="Trap">Trap</option> 
			  	<option value="Rock">Rock</option>
			  	<option value="Rhythm and blues">Rhythm and blues</option>
				<option value="Heavy">Heavy</option>
				<option value="Indie">Indie</option>
				<option value="Country">Country</option>
				<option value="Techno">Techno</option>
				<option value="House">House</option>
			  	<option value="Blues">Blues</option> 
			  	<option value="Jazz">Jazz</option>
				<option value="Punk">Punk</option>
				<option value="Disco">Disco</option>
				<option value="Reggae">Reggae</option>
				<option value="Classical">Classical</option>
				<option value="Regional">Regional</option>
				<option value="Anime">Anime</option>		 		  
	 		  </select>
 		  	</div>

			<div class="form-group">
				<label for="profilePhoto">Profile photo </label>
				<input type="file" id="profilePhoto" class="form-control-file" name="profilePhoto" placeholder="Profile Photo" value="">
			</div>
	    
	    	<input class="btn btn-primary mt-3" type="submit" name="sumbit" value="Submit">
		</form>
 	</div>
</div>