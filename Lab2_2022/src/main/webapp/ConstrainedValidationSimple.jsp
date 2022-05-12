<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Form </title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<style>
input:valid {
	border-left: 4px solid green;
}
input:invalid {
	border-left: 4px solid red;
}

</style>
</head>
<body>

<div class="row align-items-center justify-content-center">
	<div class="shadow-sm p-3 mb-5 bg-white rounded">	
		<h2> Register </h2>
		<form class="form-group" action="RegisterController" id="myform" method="post" enctype="multipart/form-data">
		  <label for="user"> User name:</label><br>
		  <input type="text" id="user" class="form-control" name="user" placeholder="User" value="${model.user}" required pattern=".{4,}"><br>
  		  <c:if test = "${model.error[0]}">
  		  <div class="alert alert-danger" role="alert">
			   Entered user name has been already registered
		  </div>
		  </c:if>
		  
  		  <label for="name"> Name:</label><br>
		  <input type="text" id="name" class="form-control" name="name" placeholder="Name" value="${model.name}" required><br>
  		  <label for="surname"> Surname:</label><br>
		  <input type="text" id="surname" class="form-control" name="surname" placeholder="Surname" value="${model.surname}" required><br>
   		  <label for="surname2"> Second Surname:</label><br>
		  <input type="text" id="surname2" class="form-control" name="surname2" placeholder="Surname2" value="${model.surname2}"><br>
		  <label for="mail"> Mail: </label><br>
		  <input type="email" id="mail" class="form-control" name="mail" placeholder="Mail" value="${model.mail}" required><br>
   		  <c:if test = "${model.error[1]}">
  		  <div class="alert alert-danger" role="alert">
			   Entered email has been already registered
		  </div>
		  </c:if>
		  <label for="pwd1"> Password: </label><br>
		  <input type="password" id="pwd1" class="form-control" name="pwd1" placeholder="Password" value="${model.pwd1}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$">
   		  <small id="emailHelp" class="form-text text-muted">Min. 8 character, with 1 letter Uppercase and 1 Number </small><br>
   		  <c:if test = "${model.error[2]}">
  		  <div class="alert alert-danger" role="alert">
			   Invalid format. Must be contain at least 1 letter Uppercase and 1 Number.
		  </div>
		  </c:if>
		  <label for="pwd2"> Confirm Password: </label>	
		  <input type="password" id="pwd2" class="form-control" name="pwd2" placeholder="Confirm Password" value="${model.pwd2}" required><br>
   		  <c:if test = "${model.error[3]}">
  		  <div class="alert alert-danger" role="alert">
			   Passwords must match!
		  </div>
		  </c:if>
		  <label for="birthDate"> Date of birth:</label><br>
		  <input type="date" id="birthDate" class="form-control" name="birthDate" placeholder="BirthDate" value="${model.birthDate}" required><br>
 		  <!-- 
 		  <label for="genders"> Music Genders:</label><br>
		  <fieldset class="form-group">
		  	<input type="checkbox" name="gender" value="Reggaeton">Reggaeton<br>  
	  		<input type="checkbox" name="gender" value="Trap">Trap<br>     
		  	<input type="checkbox" name="gender" value="Blues">Blues<br>  
	  		<input type="checkbox" name="gender" value="EDM">EDM<br> 
		  	<input type="checkbox" name="gender" value="Rock">Rock<br>  
	  		<input type="checkbox" name="gender" value="Pop">Pop<br> 		  
  		  </fieldset>
  		   -->
  		  <label for="perfil_photo">Profile photo:</label>
  		  <input type="file" id="photo" class="form-control-file" name="photo" placeholder="Photo" value="">
  		  
		  <button class="btn btn-primary mt-3"> Submit </button>
		</form>
	</div>
</div>

</body>
<script type="text/javascript" src="js/script.js"></script>
</html>