<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<div class="row">
	<c:choose>
        <c:when test = "${user.profilePhoto == null || user.profilePhoto.length() == 0}">
            <img class="rounded-circle" src= "imgs/avatar2.png" alt="Avatar">
        </c:when>
        <c:otherwise>
            <img class="rounded-circle" src= ${user.profilePhoto} alt="Avatar">
        </c:otherwise>
     </c:choose> 
</div>
<div class="row">
	<p class="text-center text-white"> ${user.name} </p>
</div>
<div class="row">
	<div class="m-2">
	    <button type="button" class="btn btn-dark w-100">Logout</button>
	</div>            
</div>