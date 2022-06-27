package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageTweets;
import managers.ManageUsers;
import models.Login;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetTweets")
public class GetTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTweets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		
		ManageTweets tweetManager = new ManageTweets();
		tweets = tweetManager.getTweets(0,10);
		tweetManager.finalize();
		
		if (user != null) {
			ManageUsers userManager = new ManageUsers();
			user = userManager.getUser(user.getId());
			userManager.finalize();
		} 
		
		request.setAttribute("tweets",tweets);
		request.setAttribute("user",user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewTweets.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

