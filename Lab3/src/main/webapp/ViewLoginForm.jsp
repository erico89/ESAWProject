<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="css/structure.css">

<style>
input[type=text]{
	color: white;
	background:transparent;
	outline: none;
}
input[type=password]{
	color: white;
	background:transparent;
	outline: none;
}

.password_show_placement
{
	color: white;
	margin-top: -30px;
	margin-right: 10px;
	cursor: pointer;
}
</style>

<body>
	<div class="row mt-md-5 align-items-center justify-content-center rounded" style="background-color:#343A40; position: absolute; left: 700px; width:500px;" > 
			<div class="col-md-8 mt-5 mb-5" id="form"> 
				<div class="d-flex justify-content-center">
				<img src="imgs/logo4.png" width="200" height="200">
				</div><br>
				<div class="d-flex justify-content-center">
			<h5 style="color: white;font-weight: bold;">Login</h5>
				</div><br>
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
					<i id="toggleShowPassword" class="bi bi-eye-slash fa-lg float-right password_show_placement" ></i>
				</div>
				
		    	<input class="btn btn-primary mt-3" type="submit" name="sumbit" value="Submit">
			</form>
		</div>
	</div>
	
	<script>
	      const toggleShowPassword2 = document.getElementById("toggleShowPassword");
	      const password = document.getElementById("password");
	      
	      //Events
	      toggleShowPassword2.addEventListener("click", function()
	      		{
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





