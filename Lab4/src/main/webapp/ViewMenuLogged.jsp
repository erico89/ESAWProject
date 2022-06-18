<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" class="menu" id="GetOwnTimeline" href="#">
    	<img src="imgs/letter_with_logo.png" width="160" height="32">
   	</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="menu nav-link" id="GetOwnTimeline" href="#">For You</a>
        </li>
        <li class="nav-item">
          <a class="menu nav-link" id="getFollinw" href="#">Following</a>
        </li>
      </ul>
	    <div class="dropdown">
	      <a class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
	        <i class="bi bi-person fa-lg"></i>
	      </a>
	
	      <div class="dropdown-menu dropdown-menu-right bg-dark rounded text-white" role="menu" aria-labelledby="dropdownMenu1">
	        <div class="m-2 text-center">
	          <div class="mb-4">
	            <h5>CalypSound</h5>
	          </div>
	
	          <div id="userInfo">
	          </div>
	        </div>
	      </div>
	    </div>
    </div>
  </nav>