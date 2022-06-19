<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" class="menu" id="GetTimeline" href="#">
      <img src="imgs/letter_with_logo.png" width="160" height="32">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="menu-logged nav-link" id="GetTweets" href="#">For You</a>
        </li>
        <li class="nav-item">
          <a class="menu-logged nav-link" id="GetFollowingTweets" href="#">Following</a>
        </li>
      </ul>
    </div>
    <div class="dropdown pull-right">
      <a class="dropdown-toggle text-white hover-overlay" id="dropdownMenu1" data-toggle="dropdown">
        <i class="bi bi-person-circle fa-lg"></i>
      </a>

      <div class="dropdown-menu dropdown-menu-right bg-dark rounded text-white" role="menu"
        aria-labelledby="dropdownMenu1">
        <div class="m-2 ml-5 mr-5 text-center">
          <div class="mb-4">
            <h5>CalypSound</h5>
          </div>

          <div id="userInfo">
            <div class="row">
              <c:choose>

                <c:when test="${user.profilePhoto == null || user.profilePhoto.length() == 0}">
                  <img class="rounded-circle img-fluid" src="imgs/avatar2.png" alt="Avatar">
                </c:when>
 
                <c:otherwise>
                  <img class="rounded-circle img-fluid" src="./profile_photo/${user.profilePhoto}" alt="Avatar">
                </c:otherwise>

              </c:choose>
            </div>
            <div class="row">
              <div class="col text-center">
                <label class="mt-3 text-center text-white"> ${user.nickname} </label>
                <button type="button" class="menu btn btn-dark w-100 shadow" id="LogoutController">Logout</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>