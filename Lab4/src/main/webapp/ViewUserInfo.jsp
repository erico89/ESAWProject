<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		 <script type="text/javascript">
		 $(document).ready(function(){
			$('#navigation').load('MenuController');
			$('#lcolumn').load('GetNotFollowedUsers');
			//$('#rcolumn').load('GetUserInfo');
			$('#iterator').load('GetUserTweets');
		 });
		</script>

		<div class="p-3 my-3 myFrom shadow rounded text-white">
		  <h2>My Profile</h2>
		  <div class="row justify-content-md-center text-center">
			  <div class="col-sm-4">		  
				<c:choose>
			        <c:when test = "${user.profilePhoto == null || user.profilePhoto.length() == 0}">
			            <img class="rounded-circle img-fluid" src= "imgs/avatar2.png" alt="Avatar">
			        </c:when>
			        <c:otherwise>
			            <img class="rounded-circle img-fluid" src= "./profile_photo/${user.profilePhoto}" alt="Avatar">
			        </c:otherwise>
			     </c:choose> 			  </div>
			  <div class="col-sm-8">
			 	<div class="row justify-content-md-center">
			 		<div class="col">
			 			<button type="button" class="btn btn-outline-light text-center profile_ops" id="GetFollowedUsers"> ${numFollowing} Following </button>
			 		</div>
			 		<div class="col">
			 			<button type="button" class="btn btn-outline-light text-center profile_ops" id="GetFollowerUsers"> ${numFollowers} Followers</button>			 		
			 		</div>
			 	</div>
			  </div>
		  </div>
		  <hr>
		  <p class="text-left text-white"> <i class="bi bi-archive"></i>${user.name} ${user.surname} ${user.secondSurname} </p>
		  <p class="text-left text-white"> <i class="bi bi-person"></i>${user.nickname} </p>
		  <p class="text-left text-white"> <i class="bi bi-gift"></i> ${user.birthdate} </p>
		  <p class="text-left text-white"> <i class="bi bi-envelope"></i> ${user.mail} </p>

		<button type= "button" class="menu btn btn-primary shadow rounded" style="border-color:white;color:white" id=EditProfile><i class="bi bi-person-plus"></i> Edit </button>		  
		</div>
		
		<div id="iterator">
		</div>