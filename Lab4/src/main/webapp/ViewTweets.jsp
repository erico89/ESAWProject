<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="p-3 my-3 myFrom shadow rounded text-white"> Calyps 
<c:forEach var="t" items="${tweets}">     
  <div class="container-xlp-3 my-3 p-3 bg-dark text-white border border-white rounded">
	 <div class="row">
	  <div class="col-sm-2">
	   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
	  </div>
	  <div class="col-sm-10">
	   <div class="row">
	    <div class="col overflow-hidden">
		 ${t.nickname}
		</div>
		<div class="col text-right">
		 ${t.postDateTime}
		</div>
	   </div>
	   <div class="row">
	   <div class="col overflow-hidden">
		 ${t.description}
	   </div>
	   </div>
	   <div class="row">
	   <div class="col">
		 ${t.image}
	   </div>
	   </div>
	   <div class="row">
	   <div class="col">
		 ${t.audio}
	   </div>
	   </div>
	   <div class="row">
	   <div class="col-sm-1">
	     <button type="button" class="btn btn-primary-outline"><span style="color:white"><i class="bi bi-chat-dots"></i> </button>
	   </div>
	   <div class="col-sm-1">
	    ${t.likes}
	   </div>
	   <div class="col-sm-1">
	     <button type="button" class="btn btn-primary-outline"><span style="color:white"><i class="bi bi-arrow-repeat"></i> </button>
	   </div>
	   <div class="col-sm-1">
	     ${t.retweets}
	    </div>
	    <div class="col-sm-1">
		 <button type="button" class="btn btn-primary-outline"><span style="color:white"><i class="fa fa-thumbs-up icon"></i> </button>
    	</div>
		<div class="col-sm-1">
		 ${t.likes}
		</div>
	   </div>
	 </div>
    </div>
  </div>
</c:forEach>
</div>
