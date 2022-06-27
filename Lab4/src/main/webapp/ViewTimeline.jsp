<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#lcolumn').load('GetNotFollowedUsers');
	//$('#rcolumn').load('GetUserInfo');
	$('#iterator').load('GetTweets');
	
	$("#ImageUploader").click(function() {
	    $("#UploadImage").click();
	});
 });
</script>

<div class="p-3 my-3 myFrom shadow rounded">
	<div class="form-group">
	    <textarea id="tweetContent" class="form-control text-light bg-dark" rows="3" placeholder="What's happening? "></textarea>		
  	</div>  	
  	
	<button id="addTweet" type="button" class="btn btn-primary">Post</button> 
	<button id="ImageUploader" type="button" class="btn btn-primary-outline">
		<span style="color:white">
			<i class="bi bi-card-image"></i>
		</span>
	</button>
	<input type="file" id="UploadImage" class="form-control-file" style="display: none;"></input>
	<button id="AudioUploader" type="button" class="btn btn-primary-outline">
		<span style="color:white">
			<i class="bi bi-music-note-beamed"></i>
		</span>
	</button>
	<button id="emojiPicker" type="button" class="btn btn-primary-outline">
		<span style="color:white">
			<i class="bi bi-emoji-smile"></i> 
		</span>
	</button>
</div>
 
<div id="iterator">
</div>

 


