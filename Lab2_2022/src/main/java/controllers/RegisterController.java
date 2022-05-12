package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

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
	// !!! Change image path to your personal folder path!!!
	private static final String img_path = "C:\\Users\\WENJI\\eclipse-workspace\\ESAWProject\\Lab2_2022\\src\\main\\webapp\\img/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User model = new User();
		ManageUsers manager = new ManageUsers();

		//String view = "ConstrainedValidationHTML5.jsp";
		String view = "ConstrainedValidationSimple.jsp";
		//String view = "ConstrainedValidationComplex.jsp";
		//String view = "ConstrainedValidationParsley.jsp";
		
		try {
			BeanUtils.populate(model,request.getParameterMap());
			
			// Manual set photo property
			Part file = request.getPart("photo");
			model.setPhoto(file.getSubmittedFileName());
			
			if (manager.isComplete(model)) {
				if (!manager.checkUsername(model.getUser(), model) && !manager.checkMail(model.getMail(), model)) {	
					// Hash password
					String pwd_hashed = process_pwd(model.getPwd1());
					model.setPwd1(pwd_hashed);
					
					// Insert user into DB.
					manager.addUser(model.getUser(), model.getMail(), model.getPwd1(), model.getName(), model.getSurname(), model.getSurname2(), model.getBirthDate(), model.getPhoto());
						
					// Store photo
					file.write(img_path + model.getPhoto());
					
					// Insert Genders
					List<String> genders = model.getGenders();
					for (int i = 0; i < genders.size(); i++) {
						manager.addGender(model.getUser(), genders.get(i));
					}
					
					manager.finalize();
					view = "Registered.jsp";
				} else {
					System.out.println("Some Error.");
				}
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	private String process_pwd(String pwd1) throws NoSuchAlgorithmException {
		if (pwd1.isEmpty()) {
			return "";
		}
		
		MessageDigest hash = null;
		try {
			hash = MessageDigest.getInstance("SHA256");
			byte[] pwd_hashed = hash.digest(pwd1.getBytes("UTF-8"));
			String result = "";
			for (byte b : pwd_hashed) {
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
