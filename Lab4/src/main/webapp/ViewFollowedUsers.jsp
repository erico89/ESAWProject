<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<div class="p-3 my-3 myFrom shadow rounded text-white"> 
		<h5> User Following </h5>
		<div class="form-group row px-3">
                <div class="col-sm-11 p-0">
                    <input type="search" id="keywordFollower" class="form-control  bg-dark text-white" placeholder="Search who are followd you ..."/>
                </div>
                <div class="col-sm-1 p-0">
	                <button id="getFollower" type="button" class="btn btn-primary">
	                    <i class="bi bi-search"></i>
	                </button>
                </div>
            </div>
	        <c:forEach var="u" items="${users}">
	        <div class="container-xlp-3 my-3 p-3 bg-dark text-white border border-white rounded">
	        	<div class="row">
	        		<div class="col-sm-2">
		                <c:choose>
		                    <c:when test="${u.profilePhoto == null || u.profilePhoto.length() == 0}">
		                        <img class="rounded-circle img-fluid" src="imgs/avatar2.png" alt="Avatar">
		                    </c:when>
		                    <c:otherwise>
		                        <img class="rounded-circle img-fluid" src="./profile_photo/${u.profilePhoto}" alt="Avatar">
		                    </c:otherwise>
		                </c:choose>
					</div>
					<div class="col-sm-7">
						<div class="row overflow-hidden">
	                 	${u.nickname} 
	                 	</div>
	                 	<div class="row">
							<button type="button" class="userInfo btn btn-outline-dark text-white">
		                        <i class="fa fa-info-circle text-white"></i> &nbsp;Info
		                    </button>
	                 	</div>
	                </div>
	                <div class="col-sm-3">
		                <div class="" user_id="${u.id}">                    
		                    <button type="button" class="unfollowUser btn btn-outline-dark text-white">
		                        <i class="fa fa-trash text-white"></i> &nbsp;Unfollow
		                    </button>
	            		</div>
	            	</div>
            	</div>
          </div>
        </c:forEach>
       </div>