<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<div class="p-3 my-3 myFrom shadow rounded text-white">
  <h2>My Profile</h2>
  <p><img src="imgs/avatar3.png" class="rounded" style="height:106px;width:106px" alt="Avatar"></p>
  <hr>
  <p class="text-left text-white"> <i class="bi bi-person"></i>${user.name} </p>
  <p class="text-left text-white"> <i class="bi bi-envelope"></i> ${user.mail} </p>
  <p class="text-left text-white"> <i class="bi bi-gift"></i> ${user.birthdate} </p>

  <button type="button" class="btn btn-primary-outline tweet_ops shadow rounded text-black" style="color:white"><i class="bi bi-person-plus"></i> &nbsp;Edit</button> 
  
 </div>
<br>