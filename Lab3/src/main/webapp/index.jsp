<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/me.png">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Lab 3 template </title>
<!-- Import bootstrap library -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  W3C Library
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 -->
<!-- MultiSelector Plugins  -->
<link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css">
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
       
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/structure.css">

<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".menu", async function(event) {
		//$('#content').load('ContentController',{content: $(this).attr('id')});
		const response = await fetch($(this).attr('id'));
		$('#content').html(await response.text());
		//$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	$(document).on("submit","form", function(event) {
		event.preventDefault(); // avoid to execute the actual submit of the form.

	    var form = $(this);
	    var actionUrl = form.attr('action');
	    var methodType = form.attr("method");
	    
	    $.ajax({
	        type: methodType,
	        url: actionUrl,
	        dataType: "JSON",
	        data: new FormData(this),
	        processData: false,
	        contentType: false,
	        success: function(response)
	        {
	        	console.log(response); // show response from the php script.
	        },
	        error: function(err) {
        		console.log(err);
	        }
	    });
	    
	});
	$("#genres").multiselect({
        enableHTML: true
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
	<div class="w3-container w3-card-4 w3-padding-24" id="content">
	<jsp:include page="${content}" />
	</div>
	<!-- End Content -->

  </body>
</html>