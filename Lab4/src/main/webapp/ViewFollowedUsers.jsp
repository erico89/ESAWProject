<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <c:forEach var="u" items="${users}">
            <div class=""><br>
                <c:choose>
                    <c:when test="${u.profilePhoto == null || u.profilePhoto.length() == 0}">
                        <img class="rounded-circle img-fluid" src="imgs/avatar2.png" alt="Avatar">
                    </c:when>
                    <c:otherwise>
                        <img class="rounded-circle img-fluid" src="./profile_photo/${u.profilePhoto}" alt="Avatar">
                    </c:otherwise>
                </c:choose>

                <h4> ${u.nickname} </h4>
                <div class="" user_id="${u.id}">
                    <button type="button" class="userInfo btn btn-outline-dark">
                        <i class="fa fa-info-circle"></i> &nbsp;Info
                    </button>
                    <button type="button" class="unfollowUser btn btn-outline-dark">
                        <i class="fa fa-trash"></i> &nbsp;Unfollow
                    </button>
                </div>

            </div>
        </c:forEach>