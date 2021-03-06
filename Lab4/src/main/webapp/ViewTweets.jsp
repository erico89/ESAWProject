<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div class="p-3 my-3 myFrom shadow rounded text-white"> 
		<h5>Calyps</h5>
		<div class="form-group row px-3">
                <div class="col-sm-11 p-0">
                    <input type="search" id="keywordTweet" class="form-control bg-dark text-white" placeholder="Search what you want to see..."/>
                </div>
                <div class="col-sm-1 p-0">
	                <button type="button" class="btn btn-primary" id="getSearchTweet">
	                    <i class="bi bi-search"></i>
	                </button>
                </div>
            </div>
			<c:forEach var="t" items="${tweets}">
				<div class="container-xlp-3 my-3 p-3 bg-dark text-white border border-white rounded">
					<div class="row">
						<div class="col-sm-2">
							<c:choose>
			                    <c:when test="${t.profilePhoto == null || t.profilePhoto.length() == 0}">
			                        <img class="rounded-circle img-fluid" src="imgs/avatar2.png" alt="Avatar">
			                    </c:when>
			                    <c:otherwise>
			                        <img class="rounded-circle img-fluid" src="./profile_photo/${t.profilePhoto}" alt="Avatar">
			                    </c:otherwise>
			                </c:choose>
						</div>
						<div class="col-sm-10">
							<div class="row">
								<div class="col overflow-hidden">
									${t.nickname}
								</div>
								<c:if test="${user.id == t.user_id || user.rol_id == 1}">
									<div class="col text-right"  tweet_id="${t.tweet_id}">
										<button type="button" class="btn btn-outline-light delTweet"><i class="bi bi-trash"></i></button>
									</div>
								</c:if>
							</div>
							<div class="row">
								<div class="col overflow-hidden">
									${t.description}
								</div>
							</div>
							<div class="row">
								<div class="col">
									${t.image}
								</div>
							</div>
							<div class="row mt-2">
								<!-- Commentario de tweets, pendiente de implementar
								<div class="">
									<button type="button" class="btn btn-primary-outline tweet_ops"  tweet_id="${t.tweet_id}">
										<i class="bi bi-chat-dots text-white"></i>
										<span class="badge badge-light"></span>
									</button>
								</div>
 								-->
								<div class="">
									<button type="button" class="btn btn-primary-outline tweet_ops" id="AddLikes" tweet_id="${t.tweet_id}">
										<i class="fa fa-thumbs-up icon text-white"></i> 
										<span class="badge badge-light">${t.likes}</span>
									</button>
								</div>
								
								<div class="">
									<button type="button" class="btn btn-primary-outline tweet_ops" id="AddRetweet" tweet_id="${t.tweet_id}">
										<i class="bi bi-arrow-repeat text-white"></i>
										<span class="badge badge-light">${t.retweets}</span>
									</button>
								</div>

							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>