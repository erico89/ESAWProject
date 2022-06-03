package controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.User;
import utils.Hash;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String root_path;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

    public void init() {
    	root_path = getServletContext().getRealPath("/");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("RegisterController: ");

		//Get Model-View variables
		User user = new User();
		Hash hash = new Hash();
		String view = "ViewRegisterForm.jsp";
		
		//Get an instance of the manager
		ManageUsers manager = new ManageUsers();

		try {
			
			//Populate user class
			BeanUtils.populate(user,request.getParameterMap());	

			
			if (manager.isComplete(user)) {
				
				manager.checkUsername(user.getNickname(), user);
				manager.checkMail(user.getMail(), user);
				
				if (manager.isValidForm(user)) {	
					
					// Hash password
					user.setPassword(hash.hash_password(user.getPassword()));
						
					// Set photo properties
					Part file = request.getPart("profilePhoto");
					user.setProfilePhoto(file.getSubmittedFileName());

					// Add user to the DataBase
					manager.addUser(user.getNickname(), user.getName(), user.getSurname(), user.getSecondSurname(), user.getMail(), 
							user.getPassword(), user.getBirthdate(), user.getProfilePhoto());
					
					//Save the photo manually
					if (user.getProfilePhoto().length() > 0) {
						saveProfilePhoto(file,user);						
					}	
			
					// Add Genres in the relation table
					String[] genres = user.getGenres();
					for (int i = 0; i < genres.length; i++) 
					{
						manager.addGenres(user.getNickname(), genres[i]);
					}
					
					//Shut down connection with the database
					manager.finalize();
					
					//Display the register form
				   System.out.println(" user ok, forwarding to ViewLoginForm");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
				   dispatcher.forward(request, response);

				   
				} else {
					System.out.println("Something went wrong!");
				   request.setAttribute("user",user);
				   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
				   dispatcher.forward(request, response);
				}
			} else {
			   System.out.println(" forwarding to ViewRegisterForm");
			   request.setAttribute("user",user);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
			   dispatcher.forward(request, response);
			}
		    
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		/*
		
	   try {
	
		   User user = new User();
		   ManageUsers manager = new ManageUsers();
		   BeanUtils.populate(user, request.getParameterMap());
		
		   if (manager.isComplete(user)) {
			   
			   System.out.println(" user ok, forwarding to ViewLoginForm");
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			   dispatcher.forward(request, response);
		   
		   } 
		   else {
		
			   System.out.println(" forwarding to ViewRegisterForm");
			   request.setAttribute("user",user);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
			   dispatcher.forward(request, response);
		   }
	   
	   } catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
	   }
		*/
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

	private void saveProfilePhoto(Part file, User model) throws IOException 
	{
		//Set OS slasher
		String nameOS = System.getProperty("os.name").toLowerCase();
		String slasher;
		if(nameOS.contains("windows")) slasher = "\\";
		else slasher = "/";
		
		//Create output directory if it is not already created
		String outputDirectory = "profile_photo";
		File newDirectory = new File(root_path + slasher + outputDirectory);
		
	    if (!newDirectory.exists()){
	        newDirectory.mkdirs();
	    } 
	    
	    //Write the file in the output directory
	    try {
	    	file.write(newDirectory.getAbsolutePath() + slasher + model.getProfilePhoto());
	    	System.out.println("Photo saved at: " + newDirectory.getAbsolutePath());
	    }catch(IOException e) {
	    	e.getMessage();
	    }
	}




}
