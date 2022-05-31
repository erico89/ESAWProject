<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row mt-md-5 mb-5 align-items-center justify-content-md-center rounded"> 
	<div class="col-md-5 myFrom" id="form"> 
		<div class="m-5">
			<div class="d-flex justify-content-center">
				<img src="imgs/logo4.png" width="200" height="200">
			</div>
			<div class="d-flex justify-content-center">
				<h5 style="color: white;font-weight: bold;">Register</h5>
			</div>
			<form class="form-group" action="RegisterController" id="myform" method="POST" enctype='multipart/form-data'>
				
				<div class="form-group ">
					<label for="Nickname"> Nickname </label>
					<input type="text" id="nickname" class="form-control text-light bg-dark" name="nickname" value="${user.nickname}" required pattern=".{4,}">
					<c:if test = "${user.errors[0]}">
						<div class="alert alert-danger" role="alert">
								The nickname you wrote has already been registered
						</div>
					</c:if>
				</div>
				<div class="form-group">
					<label for="name"> Name </label> 
					<input type="text" id="name" class="form-control text-light bg-dark" name="name" value="${user.name}" aria-hidden="true" required> 
				</div>
	
				<div class="form-group">
					<label for="surname"> Surname </label> 
					<input type="text" id="surname" class="form-control text-light bg-dark" name="surname" value="${user.surname}" required>  
				</div>
		  		  
				<div class="form-group">
					<label for="secondSurname"> Second Surname </label>  
					<input type="text" id="secondSurname" class="form-control text-light bg-dark" name="secondSurname" value="${user.secondSurname}">  
				</div>
			  
				<div class="form-group">
					<label for="mail"> Mail </label>  
					<input type="email" id="mail" class="form-control text-light bg-dark" name="mail" value="${user.mail}" required>  
					<c:if test = "${user.errors[1]}">
						<div class="alert alert-danger" role="alert">
							The email you wrote is already registered
						</div>
					</c:if>
				</div>
			  
				<div class="form-group">
					<label for="password"> Password </label>  
					<div>
						<input type="password" id="password" class="form-control text-light bg-dark" name="password" value="${user.password}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$">
						
						<div class="tools">
						    <span data-toggle="tooltip" data-placement="top" data-html="true" title="Your password must contain at lest 8 characters with 1 capital letter and 1 number">
							  <i class="bi bi-info-circle fa-lg float-right info_placement"></i>
						    </span>
							<i id="toggleShowPassword" class="bi bi-eye-slash fa-lg float-right password_show_placement" ></i>
						</div>
					</div>

	
					<c:if test = "${user.errors[2]}">
						<div class="alert alert-danger" role="alert">
							Invalid format. The password must contain at least 1 capital letter and 1 number.
				   		</div>
				   </c:if>
				</div>
				
				<div class="form-group">
					<label for="confirmationPassword"> Confirm Password </label>	
					<input type="password" id="confirmationPassword" class="form-control text-light bg-dark" name="confirmationPassword" value="${user.confirmationPassword}" required> 
		  		  	
		  		  	<i id="toggleShowConfirmationPassword" class="bi bi-eye-slash fa-lg float-right password_show_placement" ></i>
		  		  	
		  		  	<c:if test = "${user.errors[3]}">
						<div class="alert alert-danger" role="alert">
							Passwords don't match!
						</div>
					</c:if>
				</div>
			  
				<div class="form-group">
					<label for="birthdate"> Date of birth </label> 
					<input type="date" id="birthdate" class="form-control text-light bg-dark" name="birthdate" value="${user.birthdate}" required> 
				</div>
				  
				<div class="form-group text-light bg-dark">
				  <label for="genres"> Choose your favorite genres </label><br>
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
					<input type="file" id="profilePhoto" class="form-control-file" name="profilePhoto" value="">
				</div>
		    
		    	<input class="btn btn-primary mt-3" type="submit" name="sumbit" value="Submit">
			</form>
		</div>
	</div>
 </div>
 
