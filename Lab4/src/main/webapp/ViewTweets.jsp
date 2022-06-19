<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div class="p-3 my-3 myFrom shadow rounded text-white"> Calyps
			<c:forEach var="t" items="${tweets}">
				<div class="container-xlp-3 my-3 p-3 bg-dark text-white border border-white rounded">
					<div class="row">
						<div class="col-sm-2">
							<img src="imgs/avatar2.png" alt="Avatar" class="rounded-circle img-fluid">
						</div>
						<div class="col-sm-10">
							<div class="row">
								<div class="col overflow-hidden">
									${t.nickname}
								</div>
								<div class="col text-right">
									${t.postDateTime}
								</div>
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
							<div class="row">
								<div class="col">
									${t.audio}
								</div>
							</div>
							<div class="row mt-2">
								<div class="">
									<button type="button" class="btn btn-primary-outline">
										<i class="bi bi-chat-dots text-white"></i>
										<span class="badge badge-light">${t.likes}</span>
									</button>
								</div>

								<div class="">
									<button type="button" class="btn btn-primary-outline">
										<i class="bi bi-arrow-repeat text-white"></i>
										<span class="badge badge-light">${t.retweets}</span>
									</button>
								</div>

								<div class="">
									<button type="button" class="btn btn-primary-outline">
										<i class="fa fa-thumbs-up icon text-white"></i> 
										<span class="badge badge-light">${t.likes}</span>
									</button>
								</div>

							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>