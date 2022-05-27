<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
body{
    margin-bottom: 100px; 
}
.info_placement
{
	color: black;
	margin-top: -30px;
	margin-right: 45px;
}
.password_show_placement
{
	color: black;
	margin-top: -53px;
	margin-right: 10px;
	cursor: pointer;
}
</style>

<body>
	<div class="row mt-md-5 align-items-center justify-content-center rounded" style="background-color:#343A40; position: absolute; left: 600px; width:700px;" > 
		<div class="col-md-10 mt-5 mb-5" id="form"> 
			<div class="d-flex justify-content-center">
			<img src="imgs/logo4.png" width="200" height="200">
			</div><br>
			<div class="d-flex justify-content-center">
			<h5 style="color: white;font-weight: bold;">Register</h5>
			</div><br>
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
					
				    <span data-toggle="tooltip" data-placement="top" data-html="true" title="Your password must contain at lest 8 characters with 1 capital letter and 1 number">
					  <i class="bi bi-info-circle-fill fa-lg float-right info_placement"></i>
				    </span><br>

					<i id="toggleShowPassword" class="bi bi-eye-slash-fill fa-lg float-right password_show_placement" ></i>
	
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
	 
	 <script>
        const toggleShowPassword = document.getElementById("toggleShowPassword");
        const password = document.getElementById("password");

        toggleShowPassword.addEventListener("click", function () {
            // toggle the type attribute
            const type = password.getAttribute("type") === "password" ? "text" : "password";
            password.setAttribute("type", type);
            
            // toggle the icon
            this.classList.toggle("bi-eye");
        });

        // prevent form submit
        const form = document.querySelector("form");
        form.addEventListener('submit', function (e) {
            e.preventDefault();
        });
    </script>
</body>
