<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#lcolumn').load('GetNotFollowedUsers');
	//$('#rcolumn').load('GetUserInfo');
	$('#iterator').load('GetUserTweets');
 });
</script>

<div class="p-3 my-3 myFrom shadow rounded">
	<div class="form-group">
	    <textarea id="tweetContent" class="form-control text-light bg-dark" rows="3" placeholder="What's happening? "></textarea>		
  	</div>
	<button id="addTweet" type="button" class="btn btn-primary">Post</button> 
</div>
 
<div id="iterator">
</div>

 


