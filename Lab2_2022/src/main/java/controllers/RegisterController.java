package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
				if (!manager.checkUsername(model.getUser()) && !manager.checkMail(model.getMail())) {	

					manager.addUser(model.getUser(), model.getMail(), model.getPwd1(), model.getName(), model.getSurname(), model.getSurname2(), model.getBirthDate(), model.getPhoto());
						
					// Store photo
					file.write("/Lab2_2022/src/main/webapp/img" + file.getSubmittedFileName());
					
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

}
