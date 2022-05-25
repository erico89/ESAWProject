<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row align-items-center justify-content-center">
	<div class="shadow-sm p-3 mb-5 mt-4 rounded" id="form">	
		<form class="form-group" action="RegisterController" id="myform" method="post" enctype="multipart/form-data">
		  
		  <label for="Nickname"> Nickname </label><br>
		  <input type="text" id="nickname" class="form-control" name="nickname" placeholder="Nickname" value="${model.nickname}" required pattern=".{4,}"><br>
	 		  <c:if test = "${model.errors[0]}">
	 		  <div class="alert alert-danger" role="alert">
			   The nickname you wrote has already been registered
		  </div>
		  </c:if>
		  
	 		  <label for="name"> Name </label><br>
		  <input type="text" id="name" class="form-control" name="name" placeholder="Name" value="${model.name}" required><br>
	 		  <label for="surname"> Surname </label><br>
		  <input type="text" id="surname" class="form-control" name="surname" placeholder="Surname" value="${model.surname}" required><br>
	  		  
	  		  <label for="secondSurname"> Second Surname </label><br>
		  <input type="text" id="secondSurname" class="form-control" name="secondSurname" placeholder="Second Surname" value="${model.secondSurname}"><br>
		  
		  <label for="mail"> Mail </label><br>
		  <input type="email" id="mail" class="form-control" name="mail" placeholder="Mail" value="${model.mail}" required><br>
	  		  <c:if test = "${model.errors[1]}">
	 		  <div class="alert alert-danger" role="alert">
			   The email you wrote is already registered
		  </div>
		  </c:if>
		  
		  <label for="password"> Password </label><br>
		  <input type="password" id="password" class="form-control" name="password" placeholder="Password" value="${model.password}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$">
		  <small id="emailHelp" class="form-text text-muted"> 8 characters minimum <br> 1 capital letter required <br> 1 number required</small><br>
	
	
		  
	  		  <c:if test = "${model.errors[2]}">
	 		  <div class="alert alert-danger" role="alert">
			   Invalid format. The password must contain at least 1 capital letter and 1 number.
		  </div>
		  </c:if>
		  
		  <label for="confirmationPassword"> Confirm Password </label>	
		  <input type="password" id="confirmationPassword" class="form-control" name="confirmationPassword" placeholder="Confirm Password" value="${model.confirmationPassword}" required><br>
	  		  <c:if test = "${model.errors[3]}">
	 		  <div class="alert alert-danger" role="alert">
			   Passwords don't match!
		  </div>
		  </c:if>
		  
		  <label for="birthdate"> Date of birth </label><br>
		  <input type="date" id="birthdate" class="form-control" name=birthdate placeholder="Birthdate" value="${model.birthdate}" required><br>
			  
			  <label for="genres"> Choose your favorite genres </label><br>
		  <fieldset class="form-group">
		    <input type="checkbox" name="genres" value="Pop">Pop<br>
		  	<input type="checkbox" name="genres" value="Latin">Latin<br>  
		  	<input type="checkbox" name="genres" value="Hip-Hop/Rap">Hip-Hop/Rap<br>
		  	<input type="checkbox" name="genres" value="EDM">EDM<br> 
	  		<input type="checkbox" name="genres" value="Trap">Trap<br>   
		  	<input type="checkbox" name="genres" value="Rock">Rock<br>  
		  	<input type="checkbox" name="genres" value="R&B">R&B<br>
			<input type="checkbox" name="genres" value="Heavy">Heavy<br>
			<input type="checkbox" name="genres" value="Indie">Indie<br>
			<input type="checkbox" name="genres" value="Country">Country<br>
			<input type="checkbox" name="genres" value="Techno">Techno<br>
			<input type="checkbox" name="genres" value="House">House<br>
		  	<input type="checkbox" name="genres" value="Blues">Blues<br> 
		  	<input type="checkbox" name="genres" value="Jazz">Jazz<br>
			<input type="checkbox" name="genres" value="Punk">Punk<br>
			<input type="checkbox" name="genres" value="Disco">Disco<br>
			<input type="checkbox" name="genres" value="Reggae">Reggae<br>
			<input type="checkbox" name="genres" value="Classical">Classical<br>
			<input type="checkbox" name="genres" value="Regional">Regional<br>
			<input type="checkbox" name="genres" value="Anime">Anime<br>  		 		  
	 		  </fieldset>
	 		  
	 		  <label for="profilePhoto">Profile photo </label>
	 		  <input type="file" id="profilePhoto" class="form-control-file" name="profilePhoto" placeholder="Profile Photo" value="">
		  <button class="btn btn-primary mt-3"> Submit </button>
		  
		</form>
	</div>
</div>