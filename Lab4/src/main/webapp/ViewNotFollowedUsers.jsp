<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="p-3 my-3 myFrom shadow rounded text-white"> Who to follow? hola
<c:forEach var="u" items="${users}">      
<div class="container-xlp-3 my-2 p-2 bg-dark text-white border border-white rounded"> 
	<div class = "row">
	 <div class="col-sm-4">
     <c:choose>
        <c:when test = "${u.profilePhoto == null || u.profilePhoto.length() == 0}">
            <img src= "imgs/avatar6.png" alt="Avatar" style="width:60px">
        </c:when>
        <c:otherwise>
            <img src= ${u.profilePhoto} alt="Avatar" style="width:60px">
        </c:otherwise>
     </c:choose>    
     </div>
     <div class="col-sm-8">
      <div class = "row overflow-hidden">
       <div>${u.name}</div>
       </div>
       <div class = "row">
   	   <button type="button" class="followUser w3-row w3-button w3-green w3-section"><i class="fa fa-user-plus"></i> &nbsp;Follow</button> 
   	  </div>
   	 </div>
   	</div>
</div>
</c:forEach>
</div>