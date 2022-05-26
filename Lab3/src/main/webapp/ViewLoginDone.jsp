<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<script type="text/javascript">
$('#navigation').load('MenuController');
</script>

<style>
h2.ex1 {
  margin: 90px;
  font-weight: bold;
}
</style>

<div class="row align-items-center justify-content-center">
	<h2 class ="ex1"> Login for user ${sessionScope.user} done!</h2>
	<h2 class="ex1">¡WELCOME TO CALYPSOUND!</h2>		
</div>
