<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
    
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" id="LogoutController" href="MainController"><img src="imgs/letter_with_logo.png" width="160" height="32"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  </div>
  
  <div class="dropdown pull-right">
    <a class="dropdown-toggle text-white hover-overlay" id="dropdownMenu1" data-toggle="dropdown">
      <i class="bi bi-person-circle fa-lg"></i>
    </a>

    <div class="dropdown-menu dropdown-menu-right bg-dark rounded text-white" role="menu" aria-labelledby="dropdownMenu1">
      <div class="m-2 text-center">
        <div class="mb-4">
          <h5>CalypSound</h5>
        </div>

        <div id="userInfo">
			 <div class="m-2">
			    <button type="button" class="menu btn btn-dark w-100 shadow " id="LoginController">Log in </button>
			</div>            
			<div class="m-2">
			    <button type="button" class="menu btn btn-dark w-100 shadow " id="RegisterController"> Sign in</button>
			</div>
        </div>
      </div>
    </div>
  </div>
</nav>