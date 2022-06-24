<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>



 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#lcolumn').load('GetNotFollowedUsers');
	//$('#rcolumn').load('GetUserInfo');
	$('#iterator').load('GetFollowingTweets');
 });
</script>

<div class="p-3 my-3 myFrom shadow rounded">
	<div class="form-group">
	    <textarea id="tweetContent" class="form-control text-light bg-dark" rows="3" placeholder="What's happening? "></textarea>		
  	</div>
	<button id="addTweet" type="button" class="btn btn-primary">Post</button> 
	<button type="button" class="btn btn-primary-outline"><span style="color:white"><i class="bi bi-card-image"></i> </button>
	<button type="button" class="btn btn-primary-outline"><span style="color:white"><i class="bi bi-music-note-beamed"></i> </button>
	<button type="button" class="btn btn-primary-outline"><span style="color:white"><i class="bi bi-emoji-smile"></i> </button>
</div>
 
<div id="iterator">
</div>

 


