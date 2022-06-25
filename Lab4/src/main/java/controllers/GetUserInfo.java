package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/GetUserInfo")
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		// ERROR
		User user = (User) session.getAttribute("user");
		Integer following = 0;
		Integer followers = 0;

		if (session != null || user != null) {
			ManageUsers userManager = new ManageUsers();
			user = userManager.getUser(user.getId());
			following = userManager.numFollowing(user.getId());
			followers = userManager.numFollowers(user.getId());
			userManager.finalize();
		}
		
		request.setAttribute("user",user);
		request.setAttribute("numFollowing",following);
		request.setAttribute("numFollowers",followers);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewUserInfo.jsp"); 
		dispatcher.include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
