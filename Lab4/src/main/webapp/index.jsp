<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> CalypSound </title>
<link rel="icon" href="imgs/logo2.png">


<!-- Import bootstrap library -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<!-- Import Google api jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!--  W3C Library
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 -->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/structure.css">

<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!
	<!-- Initialize the multiselectors plugin: -->
	$("#genres").multiselect({});

	$(document).on("click",".menu", async function(event) {
		const request = $(this).attr('id');
		const response = await fetch(request);
		$('#content').html(await response.text());
		var text = $(this).text() ;
		if (text == "Registration") {
			$("#genres").multiselect({});
		}
		event.preventDefault();
	});
	
	$(document).on("submit","form", function(event) {
	    var form = $(this);
	    var actionUrl = form.attr('action');
	    var methodType = form.attr("method");
	    
	    var data = (methodType == "GET") ? $(this).serialize() : new FormData(this);
	    
	    $.ajax({
	        type: methodType,
	        url: actionUrl,
	        data: data,
	        processData: false,
	        contentType: false,
	        success: function(response)
	        {
	        	if (actionUrl == "LoginController") {
	        		$("#lColumn").removeClass();
	        		$("#content").removeClass().addClass("col-sm-9");	
	        	}
	        	$('#content').html(response);
	        	
				$("#genres").multiselect({});
	        },
	        error: function(err) {
        		console.log(err);
	        }
	    });
		event.preventDefault();
	    
	});
	
	$(document).on("click", "#toggleShowPassword", function(){
		const password = document.getElementById("password");
		const type = password.getAttribute("type") === "password" ? "text" : "password";
		password.setAttribute("type", type);

		// toggle the icon
		this.classList.toggle("bi-eye");
	});

	$(document).on("click", "#toggleShowConfirmationPassword", function(){
		const password2 = document.getElementById("confirmationPassword");
		// toggle the type attribute
		const type = password2.getAttribute("type") === "password" ? "text" : "password";
		password2.setAttribute("type", type);
		
		// toggle the icon
		this.classList.toggle("bi-eye");
	});
	
	/* Add tweet */
	$(document).on("click","#addTweet",function(event){
		
		const data = {
				description: $("#tweetContent").val(),
				image: null,
				audio: null,
		}
		
		$.post( "AddTweet", data, function(event) {
			$("#content").load("GetOwnTimeline");		
		});
		event.preventDefault();
	});
	/* Delete tweet */
	$(document).on("click",".delTweet",function(event){
		var tweet = $(this).parent();
		$.post( "DelTweet", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	/* Follow user */
	$(document).on("click",".followUser",function(event){
		var user = $(this).parent();
		$.post( "FollowUser", { id: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* UnFollow user */
	$(document).on("click",".unfollowUser",function(event) {
		var user = $(this).parent();
		$.post( "UnFollowUser", { id: $(this).parent().attr("id") }, function(event) {
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
	});
});
</script>


</head>
<body>

 	<!-- Begin Navigation -->
 	<div class="w3-bar w3-red" id="navigation">
    <jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
 
 	<!-- Begin Content -->
	<div class="container">
		<div class="row align-items-start justify-content-md-center">
		 	<!-- Left Column -->
			<div class="col-sm-3" id="lColumn">
				<div id="rcolumn">
					<p></p>
				</div>
			</div>
			<!-- Middle Column -->	
			<div class="col-sm-6" id="content">
				<jsp:include page="${content}" />
			</div>
			<!-- Right Column -->
			<div class="col-sm-3" id="rColumn">
				<div id="lcolumn">
					<p></p>
				</div>
			</div>
		</div>
	</div>
	<!-- End Content -->

	<!-- Include the plugin's CSS and JS: -->
	<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
	<link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
  </body>
  <script type="text/javascript" src="js/registerScript.js"></script>

</html>