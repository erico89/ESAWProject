package controllers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.Manager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.tuple.Pair;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/UpdateProfile")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String root_path;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
    }
    
    public void init() {
    	root_path = getServletContext().getRealPath("/");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		User userNew = new User();
		String view = "ViewEditProfile.jsp";
		Integer following = 0;
		Integer followers = 0;
		if (session != null || user != null) {
			// Save old password
			ManageUsers userManager = new ManageUsers();
			
			try {
				BeanUtils.populate(userNew,request.getParameterMap());
				
				userManager.checkUsername(userNew.getNickname(), userNew);
				userManager.checkMail(userNew.getMail(), userNew);
				
				// Set photo properties
				Part file = request.getPart("profilePhoto");
				if (file != null) {
					user.setProfilePhoto(file.getSubmittedFileName());
				}
				
				// Get parameters if null. 
				String psw = ((userNew.getPassword() == null || userNew.getPassword() == "") ? user.getPassword() : userNew.getPassword());
				String nickname = user.getNickname();
				String mail = ((userNew.getMail() == null || userNew.getMail() == "") ? user.getMail() : userNew.getMail());
				String name = ((userNew.getName() == null || userNew.getName() == "") ? user.getName() : userNew.getName());
				String surname = ((userNew.getSurname() == null || userNew.getSurname() == "") ? user.getSurname() : userNew.getSurname());
				String birthDate = ((userNew.getBirthdate() == null || userNew.getBirthdate() == "") ? user.getBirthdate() : userNew.getBirthdate());
				
				userManager.updateUser(user.getId(), nickname, name, surname, userNew.getSecondSurname(), mail, 
						psw, birthDate, userNew.getProfilePhoto());			
				
				//Save the photo manually
				if (userNew.getProfilePhoto() != null && userNew.getProfilePhoto().length() > 0) {
					saveProfilePhoto(file,user);						
				}
				
				// Get user
				userNew = userManager.getUser(user.getId());
				following = userManager.numFollowing(user.getId());
	            followers = userManager.numFollowers(user.getId());
	            
				
				view = "ViewUserInfo.jsp";
					
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			userManager.finalize();
		}
		
		request.setAttribute("user",userNew);
		request.setAttribute("numFollowing",following);
        request.setAttribute("numFollowers",followers);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view); 
		dispatcher.include(request,response);
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