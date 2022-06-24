<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div class="p-3 my-3 myFrom shadow rounded text-white"> 
        	<h5>Who to follow?</h5>
            <div class="form-group row px-3">
                <div class="col-sm-10 p-0">
                    <input type="search" id="keywordNotFollower" class="form-control  bg-dark text-white" placeholder="Search users"/>
                </div>
                <div class="col-sm-2 p-0">
	                <button id="getNotFollower" type="button" class="btn btn-primary">
	                    <i class="bi bi-search"></i>
	                </button>
                </div>
            </div>
            <c:forEach var="u" items="${users}">
                <div class="container-xlp-3 my-2 p-2 bg-dark text-white border border-white rounded">
                    <div class="row">
                        <div class="col-sm-4">
                            <c:choose>
                                <c:when test="${u.profilePhoto == null || u.profilePhoto.length() == 0}">
                                    <img src="imgs/avatar6.png" alt="Avatar" class="rounded-circle img-fluid">
                                </c:when>
                                <c:otherwise>
                                    <img src=${u.profilePhoto} alt="Avatar" class="rounded-circle img-fluid">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-sm-8">
                            <div class="overflow-hidden">
                                <div>${u.nickname}</div>
                            </div>
                            <div class="" user_id="${u.id}">
                                <button type="button" class="followUser btn btn-primary">
                                    <i class="fa fa-user-plus"></i> &nbsp;Follow
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>