package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class GetFollowedUsers
 */
@WebServlet("/GetNotFollowersSearch")
public class GetNotFollowersSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNotFollowersSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> users = Collections.emptyList();
		String keyWord = null;
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		keyWord = request.getParameter("keyWord");

		if (session != null || user != null ) {
			ManageUsers userManager = new ManageUsers();
			users = userManager.getNotFollowedUsersKeyWord(user.getId(),keyWord,0,4);
			userManager.finalize();
		}
		
		request.setAttribute("users",users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewNotFollowedUsers.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
