<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


		<div class="p-3 my-3 myFrom shadow rounded text-white">
		  <h2>My Profile</h2>
		  <p>
		  <img class="rounded-circle img-fluid" style="height:106px;width:106px" src= "./profile_photo/${user.profilePhoto}" alt="Avatar">
		  </p>
		  <div class="col-sm-8">
		  	<p class="text-right text-white"> ${numFollowing} Following </p>
		  	<p class="text-right text-white"> ${numFollowers} Followers </p>
		  </div>
		  <hr>
		  <p class="text-left text-white"> <i class="bi bi-archive"></i>${user.name} ${user.surname} </p>
		  <p class="text-left text-white"> <i class="bi bi-person"></i>${user.nickname} </p>
		  <p class="text-left text-white"> <i class="bi bi-gift"></i> ${user.birthdate} </p>
		  <p class="text-left text-white"> <i class="bi bi-envelope"></i> ${user.mail} </p>

		<button type= "button" class="menu btn btn-primary shadow rounded" style="border-color:white;color:white" id=EditProfile><i class="bi bi-person-plus"></i> Edit </button>		  
		</div>
		 
		<div class="p-3 my-3 myFrom shadow rounded text-white" id=GetUserTweets> Calyps</div>