package controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
		
		//Get Model-View variables
		User model = new User();
		String view = "ConstrainedValidationSimple.jsp";
		
		//Get an instance of the manager
		ManageUsers manager = new ManageUsers();
		
		try {
			
			//Populate user class
			BeanUtils.populate(model,request.getParameterMap());	

			
			if (manager.isComplete(model)) {
				
				manager.checkUsername(model.getNickname(), model);
				manager.checkMail(model.getMail(), model);
				
				if (manager.isValidForm(model)) {	
					
					// Hash password
					String password_hashed = hash_password(model.getPassword());
					model.setPassword(password_hashed);
						
					// Set photo properties
					Part file = request.getPart("profilePhoto");
					model.setProfilePhoto(file.getSubmittedFileName());

					// Add user to the DataBase
					manager.addUser(model.getNickname(), model.getName(), model.getSurname(), model.getSecondSurname(), model.getMail(), 
							model.getPassword(), model.getBirthdate(), model.getProfilePhoto());
					
					//Save the photo manually
					if (model.getProfilePhoto().length() > 0) {
						saveProfilePhoto(file,model);						
					}	
			
					// Add Genres in the relation table
					String[] genres = model.getGenres();
					for (int i = 0; i < genres.length; i++) 
					{
						manager.addGenres(model.getNickname(), genres[i]);
					}
					
					//Shut down connection with the database
					manager.shutDownConnection();
					
					//Display the register form
					view = "Registered.jsp";
					
				} else {
					System.out.println("Something went wrong!");
				}
			}
	
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		//Set the model and dispatcher for the view
		request.setAttribute("model", model);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
			
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
