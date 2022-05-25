package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.Login;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("LoginController: ");
		
		Login login = new Login();
		
	    try {
			
	    	BeanUtils.populate(login, request.getParameterMap());
	    	
	    	String password_hashed = hash_password(login.getPassword());
	    	login.setPassword(password_hashed);
	    	
	    	System.out.println("\n password " +login.getPassword());
    		System.out.println("\n user " +login.getUser());
			
	    	if (login.isComplete() && login.checkCredentials()){
		    	HttpSession session = request.getSession();
		    	session.setAttribute("user",login.getUser());
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginDone.jsp");
			    dispatcher.forward(request, response);
			    
		    } 
			else {
		     
				System.out.println("user is not logged, forwarding to ViewLoginForm ");
			    request.setAttribute("login",login);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			    dispatcher.forward(request, response);
		    	
		    }
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String hash_password(String password) throws NoSuchAlgorithmException 
	{
		if (password.isEmpty()) {
			return "";
		}
		
		MessageDigest hash = null;
		try {
			hash = MessageDigest.getInstance("SHA-256");
			byte[] integer_hash = hash.digest(password.getBytes("UTF-8"));
			String result = "";
			for (byte b : integer_hash) {
				String tmp = Integer.toHexString(b & 0xff);
				if (tmp.length() == 1) {
						tmp = "0"+tmp;
				}
				result += tmp;
			}
			return result;
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}